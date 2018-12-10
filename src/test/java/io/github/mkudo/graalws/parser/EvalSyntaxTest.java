package io.github.mkudo.graalws.parser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import org.graalvm.polyglot.Context;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.github.mkudo.graalws.support.SampleCodes;

public class EvalSyntaxTest {
	private String lineSeparator = System.lineSeparator();
	private Context context;
	private ByteArrayOutputStream bos;

	@Before
	public void setUp() {
		bos = new ByteArrayOutputStream();
		context = Context.newBuilder().out(bos).build();
	}

	@After
	public void cleanUp() {
		context.close();
	}

	@Test
	public void arthmeticAddTest() {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
				+ SampleCodes.ARITHMETIC_ADD + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("3" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void arthmeticDivTest() {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_4 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
				+ SampleCodes.ARITHMETIC_DIV + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("2" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void arthmeticModTest() {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
				+ SampleCodes.ARITHMETIC_MOD + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void arthmeticMulTest() {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
				+ SampleCodes.ARITHMETIC_MUL + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("4" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void arthmeticSubTest() {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
				+ SampleCodes.ARITHMETIC_SUB + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}

	@Test
	public void stackPushTest() throws UnsupportedEncodingException {
		var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_5 + SampleCodes.IO_PUT_NUMBER;
		context.eval("ws", program);
		assertEquals("5" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
	}
}
