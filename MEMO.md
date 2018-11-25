# Memo
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
## graal & truffle
- 調べていて気がついたのだけど、whitespace からなら直接 Java bytecode 吐いた方が、スタックマシンである JVM と相性が良いような……
- stack と heap をどちらも Virtual frame 上に表現すると混ざって面倒なので、どうにかしないといけなさそう……
  - やはり直接バイトコード吐いた方が……
- ところで何を export したらいいのかよく分からない。truffle 自体が JDK 8 準拠のようなので、jigsaw は対応しない方針
  - Java 11 以降はフレームワーク開発者はこの点の情報開示に気を付けないといけなさそう
- 開発者に拡張するポイントを提供して、実装してもらう系のフレームワークは、同時にテストサポートも提供して欲しい……
- テスト＆デバッグのためにもまずは IO から攻めることにする
### Literal
- long しか扱わないのに意外と苦戦した
- @TypeSystem が自動生成するクラスが存在しなくても import に書いておくか、デフォルトスコープで読み込ませないと死ぬ
  - WSTypes を node じゃないからと別パッケージにおいてはまる
  - 気持ち悪いけどとりあえず nodes に配置（デフォルトスコープで読ませる）
- generics いらない気がするけど……
### Node tree
- RootNode は特別なノードで型が違う
- 戻り値の有無があるので、標準的な Statement は void にしておいて、Statement を拡張した Expression node で戻り値を返せるようにするのが作法っぽい
  - Expression でも executeGeneric で一度全部受け止めて、引数の型別にメソッドに自動で振り分ける模様
  - WhiteSpace はそもそも引数で型一致させられないから使う必要なかった？
### References
- [graal](https://github.com/oracle/graal)
- [simplelanguage](https://github.com/graalvm/simplelanguage)
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
  - fibonacci は出力できなかった……
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
