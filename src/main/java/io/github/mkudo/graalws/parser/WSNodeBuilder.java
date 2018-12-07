package io.github.mkudo.graalws.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.WSRootNode;
import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSAddNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSDivNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSModNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSMulNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSSubNode;
import io.github.mkudo.graalws.nodes.controlflow.WSBlockNode;
import io.github.mkudo.graalws.nodes.controlflow.WSCallNode;
import io.github.mkudo.graalws.nodes.controlflow.WSEndProgramNode;
import io.github.mkudo.graalws.nodes.controlflow.WSJumpNode;
import io.github.mkudo.graalws.nodes.controlflow.WSLabelNode;
import io.github.mkudo.graalws.nodes.controlflow.WSReturnNode;
import io.github.mkudo.graalws.nodes.heap.WSLoadNode;
import io.github.mkudo.graalws.nodes.heap.WSStoreNode;
import io.github.mkudo.graalws.nodes.io.WSPutNode;
import io.github.mkudo.graalws.nodes.io.WSReadNode;
import io.github.mkudo.graalws.nodes.stack.WSDiscardNode;
import io.github.mkudo.graalws.nodes.stack.WSDuplicateNode;
import io.github.mkudo.graalws.nodes.stack.WSPushNode;
import io.github.mkudo.graalws.nodes.stack.WSSwapNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSNodeBuilder extends WSBaseVisitor<Node> {
	/* Open scope to test */
	Map<Long, WSLabelNode> labels = new HashMap<>();

	private TruffleLanguage<?> lang;
	private FrameDescriptor frameDescriptor = null;

	public WSNodeBuilder(TruffleLanguage<?> lang) {
		this.lang = lang;
	}

	@Override
	public Node visitFile(WSParser.FileContext ctx) {
		FrameDescriptor save = frameDescriptor;

		try {
			List<WSStatementNode> statements = new ArrayList<>();
			frameDescriptor = new FrameDescriptor();

			for (ParseTree child : ctx.children) {
				var result = child.accept(this);

				if (result instanceof WSStatementNode) {
					statements.add(WSStatementNode.class.cast(result));
				}
			}

			var block = new WSBlockNode(statements.toArray(new WSStatementNode[statements.size()]));

			return new WSRootNode(lang, frameDescriptor, block);
		} finally {
			frameDescriptor = save;
		}
	}

	@Override
	public Node visitLabel(WSParser.LabelContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return getOrCreateLabel(WSLongLiteralNode.class.cast(param));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitFlowCall(WSParser.FlowCallContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return new WSCallNode(getOrCreateLabel(WSLongLiteralNode.class.cast(param)));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitFlowEndProgram(WSParser.FlowEndProgramContext ctx) {
		return new WSEndProgramNode();
	}

	@Override
	public Node visitFlowJump(WSParser.FlowJumpContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return WSJumpNode.jp(getOrCreateLabel(WSLongLiteralNode.class.cast(param)));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitFlowJumpEqZero(WSParser.FlowJumpEqZeroContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return WSJumpNode.eqz(getOrCreateLabel(WSLongLiteralNode.class.cast(param)));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitFlowJumpLtZero(WSParser.FlowJumpLtZeroContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return WSJumpNode.ltz(getOrCreateLabel(WSLongLiteralNode.class.cast(param)));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitFlowReturn(WSParser.FlowReturnContext ctx) {
		return new WSReturnNode();
	}

	@Override
	public Node visitStackDiscard(WSParser.StackDiscardContext ctx) {
		return new WSDiscardNode(new WSLongLiteralNode(-1));
	}

	@Override
	public Node visitStackDuplicate(WSParser.StackDuplicateContext ctx) {
		return new WSDuplicateNode(new WSLongLiteralNode(1));
	}

	@Override
	public Node visitStackDuplicateN(WSParser.StackDuplicateNContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return new WSDuplicateNode(WSLongLiteralNode.class.cast(param));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitStackPush(WSParser.StackPushContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return new WSPushNode(WSLongLiteralNode.class.cast(param));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitStackSlide(WSParser.StackSlideContext ctx) {
		Node param = visitChildren(ctx);

		if (param instanceof WSLongLiteralNode) {
			return new WSDiscardNode(WSLongLiteralNode.class.cast(param));
		}

		throw new ExceptionCarringError(new UnexpectedResultException(param));
	}

	@Override
	public Node visitStackSwap(WSParser.StackSwapContext ctx) {
		return new WSSwapNode();
	}

	@Override
	public Node visitArithmeticAdd(WSParser.ArithmeticAddContext ctx) {
		return new WSAddNode();
	}

	@Override
	public Node visitArithmeticDiv(WSParser.ArithmeticDivContext ctx) {
		return new WSDivNode();
	}

	@Override
	public Node visitArithmeticMod(WSParser.ArithmeticModContext ctx) {
		return new WSModNode();
	}

	@Override
	public Node visitArithmeticMul(WSParser.ArithmeticMulContext ctx) {
		return new WSMulNode();
	}

	@Override
	public Node visitArithmeticSub(WSParser.ArithmeticSubContext ctx) {
		return new WSSubNode();
	}

	@Override
	public Node visitHeapLoad(WSParser.HeapLoadContext ctx) {
		return new WSLoadNode();
	}

	@Override
	public Node visitHeapStore(WSParser.HeapStoreContext ctx) {
		return new WSStoreNode();
	}

	@Override
	public Node visitIoPutChar(WSParser.IoPutCharContext ctx) {
		return new WSPutNode(true);
	}

	@Override
	public Node visitIoPutNumber(WSParser.IoPutNumberContext ctx) {
		return new WSPutNode(false);
	}

	@Override
	public Node visitIoReadChar(WSParser.IoReadCharContext ctx) {
		return new WSReadNode(true);
	}

	@Override
	public Node visitIoReadNumber(WSParser.IoReadNumberContext ctx) {
		return new WSReadNode(false);
	}

	@Override
	public Node visitNumber(WSParser.NumberContext ctx) {
		try {
			return new WSLongLiteralNode(NumberHelper.toLong(ctx));
		} catch (UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}

	private WSLabelNode getOrCreateLabel(WSLongLiteralNode labelId) {
		try {
			long value = labelId.executeLong(null);

			return labels.computeIfAbsent(value, (key) -> new WSLabelNode(key));
		} catch (UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
