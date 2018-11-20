package io.github.mkudo.graalws.parser;

public final class WSASMListener extends WSBaseListener {
	private final String INDENT = "     ";
	private long lastNumber = 0;

	@Override
	public void enterFile(WSParser.FileContext ctx) {
		System.out.println("");
	}

	@Override
	public void exitLabel(WSParser.LabelContext ctx) {
		System.out.printf("part %d%n", lastNumber);
	}

	@Override
	public void exitFlowCall(WSParser.FlowCallContext ctx) {
		System.out.printf("%scall %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowEndProgram(WSParser.FlowEndProgramContext ctx) {
		System.out.printf("%sexit%n", INDENT);
	}

	@Override
	public void exitFlowJump(WSParser.FlowJumpContext ctx) {
		System.out.printf("%sgoto %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowJumpEqZero(WSParser.FlowJumpEqZeroContext ctx) {
		System.out.printf("%szero %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowJumpLtZero(WSParser.FlowJumpLtZeroContext ctx) {
		System.out.printf("%sless %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitFlowReturn(WSParser.FlowReturnContext ctx) {
		System.out.printf("%sback%n", INDENT);
	}

	@Override
	public void exitStackDiscard(WSParser.StackDiscardContext ctx) {
		System.out.printf("%saway%n", INDENT);
	}

	@Override
	public void exitStackDuplicate(WSParser.StackDuplicateContext ctx) {
		System.out.printf("%scopy%n", INDENT);
	}

	@Override
	public void exitStackDuplicateN(WSParser.StackDuplicateNContext ctx) {
		System.out.printf("%scopyN?%n", INDENT);
	}

	@Override
	public void exitStackPush(WSParser.StackPushContext ctx) {
		System.out.printf("%spush %d%n", INDENT, lastNumber);
	}

	@Override
	public void exitStackSlide(WSParser.StackSlideContext ctx) {
		System.out.printf("%sslide?%n", INDENT);
	}

	@Override
	public void exitStackSwap(WSParser.StackSwapContext ctx) {
		System.out.printf("%sswap%n", INDENT);
	}

	@Override
	public void exitArithmeticAdd(WSParser.ArithmeticAddContext ctx) {
		System.out.printf("%sadd%n", INDENT);
	}

	@Override
	public void exitArithmeticDiv(WSParser.ArithmeticDivContext ctx) {
		System.out.printf("%sdiv%n", INDENT);
	}

	@Override
	public void exitArithmeticMod(WSParser.ArithmeticModContext ctx) {
		System.out.printf("%smod%n", INDENT);
	}

	@Override
	public void exitArithmeticMul(WSParser.ArithmeticMulContext ctx) {
		System.out.printf("%smul%n", INDENT);
	}

	@Override
	public void exitArithmeticSub(WSParser.ArithmeticSubContext ctx) {
		System.out.printf("%ssub%n", INDENT);
	}

	@Override
	public void exitHeapLoad(WSParser.HeapLoadContext ctx) {
		System.out.printf("%sget%n", INDENT);
	}

	@Override
	public void exitHeapStore(WSParser.HeapStoreContext ctx) {
		System.out.printf("%sset%n", INDENT);
	}

	@Override
	public void exitIoPutChar(WSParser.IoPutCharContext ctx) {
		System.out.printf("%sochr%n", INDENT);
	}

	@Override
	public void exitIoPutNumber(WSParser.IoPutNumberContext ctx) {
		System.out.printf("%soint%n", INDENT);
	}

	@Override
	public void exitIoReadChar(WSParser.IoReadCharContext ctx) {
		System.out.printf("%sichr%n", INDENT);
	}

	@Override
	public void exitIoReadNumber(WSParser.IoReadNumberContext ctx) {
		System.out.printf("%siint%n", INDENT);
	}

	@Override
	public void exitNumber(WSParser.NumberContext ctx) {
		lastNumber = NumberHelper.toLong(ctx);
	}
}
