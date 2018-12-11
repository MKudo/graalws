# Memo
## This project
- WSBlockNode
  - 構文上は存在しないが、RootNode は特別なノードのようなので一段挟み込みました
    - 当初は LoopNode を駆使して実装するつもり……が、jump と call が雑に混在するので行き詰った
      - ひとまずは力づくで実装した
      - なので、最適化されなさそうだけど、ここはループの内側ではなく、本体なのでひとまず良しとします
      - 本物のループの実装は implements RepeatingNode して executeRepeating 実装していくのが正しそう
  - ControlFlowException はここでハンドルしています
    - jump target も戻ってくる CallNode も last として扱い、その次の命令から実行するようにしています
      - jump target = label なので、実行する必要が無い = 次の命令から実行する
      - call node = return で戻ってきたら次から実行する
    - ノード構築時に Label を処理しきっても良かったのですが、この制御構造にするために LabelNode を埋め込みました
      - WhiteSpace 自身の構造からすると、SetLabel にあたる命令は実行系に無くてもいけそう
  - プログラムの概念構造的にここに callStack あるのおかしくない？（クラスの持ち物として不適切じゃない？）
- WSReadNode / WSPutNode
  - 数字／文字の処理をまとめており、分岐でさばいていてイマイチなのでマネをしてはいけません
  - WhiteSpace は扱う型がこれしかないので雑に作った（言い訳）
- WSJumpNode
  - そこを踏まえて Enum にしてみたけどイマイチ……こういうのはどうするのが良いのだろう？？ Comparator でも抱える？？
- arthmetic nodes
  - 考えてみれば、スタックに積んでおけばいいので void 戻り値で問題なかった
- heap / stack
  - stack
    - frame の上に実装しています。シンプルに増減させていますが、これが効率がいいのかは不明
  - heap
    - 当初 frame の上に混在させていましたが、すぐにバグることに気付いたので移動
    - scope が重ねられるようなので、きちんと多重 scope にして top scope に置きたかった
    - この heap、取り出してテストしたくなったので、topScope よりも binding で他の言語と共有できるようにしたい
      - 現在は context に Map で持たせています
## ANTLR
- 複数言語向けの出力ができるようなので g4 ファイル内に言語依存のコードを書かないようにしてみる。趣味
- statement として全部並べても動くかもしれないけど、先頭トークンでカテゴライズされているんだしまとめた方が良い気がする。趣味
- CR はコメント扱いで飛ばす。旧 Mac で死ぬかもしれないけど旧 Mac では Java 11 は動かないはずなので問題ないはず
- grammer の名前とファイル名を一致させないとダメで、かつそこからクラスが作られるので ws -> WS にした。WhiteSpace にするとイチイチ長い……
### Visitor と Listener
- 戻り値の型に制約のある Visitor より Listener の方が使い勝手が良い
  - Listener 内に状態を抱え込むので再入は丁寧にケアする
  - （traverse 中に Listener 追加したらどうなるんだろう？）
- CompositVisitor 実装するくらいなら Listener 複数 add した方が良い（ひとつの Visitor で全部やるのは禁止）
- 今回は Node ツリーの構築で、Node を継承した何かを取りまわせばいいので Visitor メインかな
- テスト用の ASM っぽいもの吐き出すのは Listener で
  - file node だけ enter で後は exit
    - python 出力に紛れる最初の改行を再現（exit だと最後の改行になる）
    - 全部 exit だと引数（number node）の処理が面倒なので
### References
- [ANTLR](https://www.antlr.org/)
- [antlr4](https://github.com/antlr/antlr4)
- [grammars-v4](https://github.com/antlr/grammars-v4)
## graal
### References
- [graal](https://github.com/oracle/graal)
- [simplelanguage](https://github.com/graalvm/simplelanguage)
- [JVM-Math-Language](https://github.com/jyukutyo/JVM-Math-Language)
## truffle
- Node
  - @Child / @Children
    - 付けておくと getChildren で取り出せるようになるので、自力でトラバースする時（テストとか）に便利
- Jump や Return は例外で飛び出す
  - 例外、というのが少し気持ち悪いけど ControlFlowException を継承しておくとよろしく最適化してくれたりする、模様
- どういう時にどうエラーを通知するべき？
  - parser 時エラーをハンドルするやり方は simplelanguage にある通り最終的に Exception で抜ける。Language#parse の API も Exception を投げられるようになっているので、そこに準拠
  - 実行時については node のトラバース時の処理になるので Error?
- 繰り返しや分岐では Profile を操作しておくとコンパイラがより良いコードを生成してくれる、らしい
  - ※この辺りをやっていたコードは WSBlockNode を大きく修正した時に消したので、完全に調べたことメモです
  - 繰り返し
    - break / continue 時に BranchProfile を丁寧に操作
    - 繰り返しには LoopNode や RepeatingNode といった枠組みや interface も用意されています
  - 分岐
    - 分岐毎に ConditionProfile に丁寧に true / false を積み上げる
    - 分岐は interface や枠組みが無いけど、将来の追加予定だったりする？
- Language
  - Java SPI (META-INF/services の下に設定を置くアレ) でクラスローダー下の言語を読み込んでいる
  - ただ、自分で用意せず TruffleLanguage を extends したクラスに @TruffleLanguage.Registration を貼って生成してもらう
  - Context を生成して、それに Source を eval する（文字列でコードを渡しても内部的には Source にしてから実行している）
    - たぶんこんな構造（要検証）
      - Engine : Root、下に複数の言語実行系を抱える
      - Context : 実行コンテクスト。Engine を持っているけど、複数作れそう。Binding（変数とか）の共有単位？
      - Source : ひとつのプログラム。ひとつの言語と紐づいている？
    - Context、autocloseable なので、try-with-resource で扱ったけど、そうしなくても大丈夫？
## maven
- antlr plugin は src/main/antlr4 下がデフォルトのターゲットの模様。g4 ファイルがソースに混ざるよりスッキリするのでそれで
- JDK 11 はいつサポートされるんだろう……動く最新の 10 にしてみる。趣味
## whitespace
- whitespace のサイトが失われているっぽい……
- ライセンスが不詳。誰も気にしないかもしれないが
- インタプリンタの実装から仕様を推測しているが、以下怪しいポイント
  - 2 つの命令がありそうで無さそうで……独自拡張、またはどこからか本流となった拡張の可能性もある
  - 数字やラベルの終端は「改行」で、ここまで含めて処理する模様
- whitespacers の examples を ASM 出力する python の実装で実行した結果と比較してテスト
  - fibonacci が動かない……
- I/O read / write がさりげなく非対称でした……（読む時は heap へ、書く時は stack から）
### References
- [whitespace tutorial(archive)](https://web.archive.org/web/20151108084710/http://compsoc.dur.ac.uk:80/whitespace/tutorial.html)
- [wikipedia(jp)](https://ja.wikipedia.org/wiki/Whitespace)
- [Rubyist Magazine(jp)](https://magazine.rubyist.net/articles/0022/0022-Legwork.html)
- [whitespacers](https://github.com/hostilefork/whitespacers/)
- [WhitespaceをC言語ソースに変換する](http://koturn.hatenablog.com/entry/2015/08/10/000000)
## others
- 引用っぽい文言を最初に書きたかった（捏造）
  - graal が聖杯なので聖杯伝説っぽいイメージで
  - Wird (brank rune) が空白なのにルーン（力ある文字）というあたり、whitespace っぽいので
