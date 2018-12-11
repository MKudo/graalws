package io.github.mkudo.graalws.parser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.graalvm.polyglot.Context;
import org.junit.Test;

import io.github.mkudo.graalws.support.SampleCodes;

public class EvalSyntaxTest {
	private String lineSeparator = System.lineSeparator();

	@Test
	public void arthmeticAddTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.ARITHMETIC_ADD + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("3" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void arthmeticDivTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_4 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.ARITHMETIC_DIV + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("2" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void arthmeticModTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.ARITHMETIC_MOD + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void arthmeticMulTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.ARITHMETIC_MUL + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("4" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void arthmeticSubTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.ARITHMETIC_SUB + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowCallTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_CALL + SampleCodes.NUMBER_3 + SampleCodes.FLOW_LABEL
					+ SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR
					+ SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_C
					+ SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("AC", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowEndProgramTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.CHAR_A + SampleCodes.IO_PUT_CHAR + SampleCodes.STACK_PUSH
					+ SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_END_PROGRAM
					+ SampleCodes.STACK_PUSH + SampleCodes.CHAR_C + SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("AB", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowLabelTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("A", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowJumpTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_JUMP + SampleCodes.NUMBER_3 + SampleCodes.FLOW_LABEL
					+ SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR
					+ SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_C
					+ SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("AC", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowJumpEqZeroTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_0
					+ SampleCodes.FLOW_JUMP_EQZ + SampleCodes.NUMBER_3 + SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_PUSH + SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_LABEL
					+ SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_C + SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("AC", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowJumpLtZeroTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_0
					+ SampleCodes.FLOW_JUMP_LTZ + SampleCodes.NUMBER_3 + SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_PUSH + SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_LABEL
					+ SampleCodes.NUMBER_3 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_C + SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("ABC", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void flowReturnTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_A
					+ SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_CALL + SampleCodes.NUMBER_3 + SampleCodes.FLOW_LABEL
					+ SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.CHAR_B + SampleCodes.IO_PUT_CHAR
					+ SampleCodes.FLOW_END_PROGRAM + SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_3
					+ SampleCodes.STACK_PUSH + SampleCodes.CHAR_C + SampleCodes.IO_PUT_CHAR + SampleCodes.FLOW_RETURN;
			context.eval("ws", program);
			assertEquals("ACB", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void heapLoadTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.HEAP_LOAD
					+ SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("0" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void heapStoreTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_5
					+ SampleCodes.HEAP_STORE + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2 + SampleCodes.HEAP_LOAD
					+ SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("5" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void ioReadCharTest() throws IOException {
		try (InputStream bis = new ByteArrayInputStream("A".getBytes(StandardCharsets.UTF_8));
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().in(bis).out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.IO_READ_CHAR
					+ SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.HEAP_LOAD + SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("A", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void ioReadNumberTest() throws IOException {
		try (InputStream bis = new ByteArrayInputStream("5".getBytes(StandardCharsets.UTF_8));
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().in(bis).out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_4 + SampleCodes.IO_READ_NUMBER
					+ SampleCodes.STACK_PUSH + SampleCodes.NUMBER_4 + SampleCodes.HEAP_LOAD + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("5" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void ioWriteCharTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.CHAR_A + SampleCodes.IO_PUT_CHAR;
			context.eval("ws", program);
			assertEquals("A", bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void ioWriteNumberTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2 + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("2" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackDiscardTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_DISCARD + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackDuplicteTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_DUP
					+ SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("3" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackDuplicteNTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_DUP_N + SampleCodes.NUMBER_2
					+ SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("2" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackPushTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_5 + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("5" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackSlideTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3 + SampleCodes.STACK_SLIDE + SampleCodes.NUMBER_2
					+ SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("3" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}

	@Test
	public void stackSwapTest() throws IOException {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
				Context context = Context.newBuilder().out(bos).build()) {
			var program = SampleCodes.STACK_PUSH + SampleCodes.NUMBER_1 + SampleCodes.STACK_PUSH + SampleCodes.NUMBER_2
					+ SampleCodes.STACK_SWAP + SampleCodes.IO_PUT_NUMBER;
			context.eval("ws", program);
			assertEquals("1" + lineSeparator, bos.toString(StandardCharsets.UTF_8));
		}
	}
}
