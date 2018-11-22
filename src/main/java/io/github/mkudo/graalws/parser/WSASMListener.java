package io.github.mkudo.graalws.parser;

import java.io.PrintWriter;

import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSASMListener extends WSBaseListener {
	private static final String INDENT = "     ";
	private final PrintWriter debugOut;
	private long lastNumber = 0;

	public WSASMListener(PrintWriter debugOut) {
		this.debugOut = debugOut;
	}

	@Override
	public void enterFile(WSParser.FileContext ctx) {
		debugOut.println("");
	}

	@Override
	public void exitLabel(WSParser.LabelContext ctx) {
		debugOut.printf("part %d%n", lastNumber);
	}

	@Override
	public void exitFlowCall(WSParser.FlowCallContext ctx) {
		debugOut.printf("%scall %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowEndProgram(WSParser.FlowEndProgramContext ctx) {
		debugOut.printf("%sexit%n", INDENT);
	}

	@Override
	public void exitFlowJump(WSParser.FlowJumpContext ctx) {
		debugOut.printf("%sgoto %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowJumpEqZero(WSParser.FlowJumpEqZeroContext ctx) {
		debugOut.printf("%szero %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowJumpLtZero(WSParser.FlowJumpLtZeroContext ctx) {
		debugOut.printf("%sless %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowReturn(WSParser.FlowReturnContext ctx) {
		debugOut.printf("%sback%n", INDENT);
	}

	@Override
	public void exitStackDiscard(WSParser.StackDiscardContext ctx) {
		debugOut.printf("%saway%n", INDENT);
	}

	@Override
	public void exitStackDuplicate(WSParser.StackDuplicateContext ctx) {
		debugOut.printf("%scopy%n", INDENT);
	}

	@Override
	public void exitStackDuplicateN(WSParser.StackDuplicateNContext ctx) {
		debugOut.printf("%scopy %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitStackPush(WSParser.StackPushContext ctx) {
		debugOut.printf("%spush %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitStackSlide(WSParser.StackSlideContext ctx) {
		debugOut.printf("%saway %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitStackSwap(WSParser.StackSwapContext ctx) {
		debugOut.printf("%sswap%n", INDENT);
	}

	@Override
	public void exitArithmeticAdd(WSParser.ArithmeticAddContext ctx) {
		debugOut.printf("%sadd%n", INDENT);
	}

	@Override
	public void exitArithmeticDiv(WSParser.ArithmeticDivContext ctx) {
		debugOut.printf("%sdiv%n", INDENT);
	}

	@Override
	public void exitArithmeticMod(WSParser.ArithmeticModContext ctx) {
		debugOut.printf("%smod%n", INDENT);
	}

	@Override
	public void exitArithmeticMul(WSParser.ArithmeticMulContext ctx) {
		debugOut.printf("%smul%n", INDENT);
	}

	@Override
	public void exitArithmeticSub(WSParser.ArithmeticSubContext ctx) {
		debugOut.printf("%ssub%n", INDENT);
	}

	@Override
	public void exitHeapLoad(WSParser.HeapLoadContext ctx) {
		debugOut.printf("%sget%n", INDENT);
	}

	@Override
	public void exitHeapStore(WSParser.HeapStoreContext ctx) {
		debugOut.printf("%sset%n", INDENT);
	}

	@Override
	public void exitIoPutChar(WSParser.IoPutCharContext ctx) {
		debugOut.printf("%sochr%n", INDENT);
	}

	@Override
	public void exitIoPutNumber(WSParser.IoPutNumberContext ctx) {
		debugOut.printf("%soint%n", INDENT);
	}

	@Override
	public void exitIoReadChar(WSParser.IoReadCharContext ctx) {
		debugOut.printf("%sichr%n", INDENT);
	}

	@Override
	public void exitIoReadNumber(WSParser.IoReadNumberContext ctx) {
		debugOut.printf("%siint%n", INDENT);
	}

	@Override
	public void exitNumber(WSParser.NumberContext ctx) {
		try {
			lastNumber = NumberHelper.toLong(ctx);
		} catch (UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
