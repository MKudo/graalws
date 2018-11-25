package io.github.mkudo.graalws.parser;

import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSNodeBuilder extends WSBaseVisitor<Node> {
	@Override
	public Node visitFile(WSParser.FileContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStatement(WSParser.StatementContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowOperation(WSParser.FlowOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitLabel(WSParser.LabelContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowCall(WSParser.FlowCallContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowEndProgram(WSParser.FlowEndProgramContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowJump(WSParser.FlowJumpContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowJumpEqZero(WSParser.FlowJumpEqZeroContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowJumpLtZero(WSParser.FlowJumpLtZeroContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitFlowReturn(WSParser.FlowReturnContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackOperation(WSParser.StackOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackDiscard(WSParser.StackDiscardContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackDuplicate(WSParser.StackDuplicateContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackDuplicateN(WSParser.StackDuplicateNContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackPush(WSParser.StackPushContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackSlide(WSParser.StackSlideContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitStackSwap(WSParser.StackSwapContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitMemoryOperation(WSParser.MemoryOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticOperation(WSParser.ArithmeticOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticAdd(WSParser.ArithmeticAddContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticDiv(WSParser.ArithmeticDivContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticMod(WSParser.ArithmeticModContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticMul(WSParser.ArithmeticMulContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitArithmeticSub(WSParser.ArithmeticSubContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitHeapOperation(WSParser.HeapOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitHeapLoad(WSParser.HeapLoadContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitHeapStore(WSParser.HeapStoreContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitIoOperation(WSParser.IoOperationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitIoPutChar(WSParser.IoPutCharContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitIoPutNumber(WSParser.IoPutNumberContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitIoReadChar(WSParser.IoReadCharContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitIoReadNumber(WSParser.IoReadNumberContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public Node visitNumber(WSParser.NumberContext ctx) {
		try {
			return new WSLongLiteralNode(NumberHelper.toLong(ctx));
		} catch (UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
