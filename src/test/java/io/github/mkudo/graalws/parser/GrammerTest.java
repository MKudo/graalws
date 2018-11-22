package io.github.mkudo.graalws.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

public class GrammerTest {
	private static final Set<String> NEED_TO_CHECK_LABEL = Collections
			.unmodifiableSet(new HashSet<>(Arrays.asList(" call ", " goto ", " less ", "part ", " zero ")));

	@Test
	public void testCalc() {
		try {
			executeTest("calc");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCount() {
		try {
			executeTest("count");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testFact() {
		try {
			executeTest("fact");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	// Can not make asm file with python implementation.
	// @Test
	public void testFibonacci() {
		try {
			executeTest("fibonacci");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testHanoi() {
		try {
			executeTest("hanoi");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testHworld() {
		try {
			executeTest("hworld");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testName() {
		try {
			executeTest("name");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testSudoku() {
		try {
			executeTest("sudoku");
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			fail();
		}
	}

	private void compare(List<String> expected, byte[] actual) throws IOException {
		var labels = new HashMap<String, Long>();

		try (var isr = new InputStreamReader(new ByteArrayInputStream(actual)); var reader = new BufferedReader(isr)) {
			var actualLine = reader.readLine();

			for (var expectedLine : expected) {
				compare(expectedLine, actualLine, labels);

				actualLine = reader.readLine();
			}
		}
	}

	private void compare(String expected, String actual, Map<String, Long> labels) {
		String labelCompareOp = null;

		for (String op : NEED_TO_CHECK_LABEL) {
			if (expected.contains(op)) {
				labelCompareOp = op;
				break;
			}
		}

		if (labelCompareOp == null) {
			assertEquals(expected, actual);
		} else {
			if (actual.contains(labelCompareOp)) {
				var quotePos = expected.indexOf("\"");
				var label = expected.substring(quotePos);

				var spPos = actual.lastIndexOf(" ");
				var current = Long.valueOf(actual.substring(spPos + 1));

				var saved = labels.get(label);

				if (saved == null) {
					labels.put(label, current);
				} else {
					assertEquals(saved, current);
				}
			} else {
				fail(actual + " does not have " + labelCompareOp);
			}
		}
	}

	private void executeTest(String target) throws IOException, URISyntaxException {
		var actual = new ByteArrayOutputStream();
		var expected = Files.readAllLines(Paths.get(this.getClass().getResource(target + ".asm").toURI()));
		var fileName = target + ".ws";

		try (var reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(fileName)));
				var debugOut = new PrintWriter(actual)) {
			var lexer = new WSLexer(CharStreams.fromReader(reader, fileName));
			var parser = new WSParser(new CommonTokenStream(lexer));
			var walker = new ParseTreeWalker();
			walker.walk(new WSASMListener(debugOut), parser.file());
		}

		compare(expected, actual.toByteArray());
	}
}
