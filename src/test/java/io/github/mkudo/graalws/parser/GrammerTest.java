package io.github.mkudo.graalws.parser;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class GrammerTest {
	@Test
	public void testCalc() {
		try (var reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("calc.ws")))) {
			var lexer = new WSLexer(CharStreams.fromReader(reader, "calc.ws"));
			var parser = new WSParser(new CommonTokenStream(lexer));
			var walker = new ParseTreeWalker();
			walker.walk(new WSASMListener(), parser.file());
			// TODO How to name label...
			// TODO Check out put... how?
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}
}
