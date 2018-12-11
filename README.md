# graalws
> **The "Wird" is the key to unlocking the secret of the "Graal".**

Personal project to learn graalvm and truffel.

## Description
WhiteSpace implementation for graalvm & truffel.

## Requirement
- Program Language
  - Java

- Compiler Compiler
  - Antlr

- Build tool
  - Maven

- Library
  - Please check pom.xml

- Eclipse
  - Need to install m2e-apt plugin

## Usage
In your java code (require graalvm and truffel).

```
try (Context context = Context.newBuilder().build()) {
    context.eval("ws", programString);
}
```

The Context is `org.graalvm.polyglot.Context`.

## License
- This project
  - APL か Free BSD と思っているけど、graal の UPL をよく読んでから決める
  - (I'm just checking UPL graal license)

## Authors
- This project
  - [Masahiko Kudo](https://github.com/MKudo)
- Example whitespace codes
  - Using [whitespacers](https://github.com/hostilefork/whitespacers/) project(examples).
