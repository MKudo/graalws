package io.github.mkudo.graalws.nodes.controlflow;

import java.rmi.UnexpectedException;
import java.util.Stack;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;
import io.github.mkudo.graalws.runtime.WSCallException;
import io.github.mkudo.graalws.runtime.WSEndProgramException;
import io.github.mkudo.graalws.runtime.WSJumpException;
import io.github.mkudo.graalws.runtime.WSReturnException;

@NodeInfo(shortName = "block", description = "The node implementing a source code block")
public final class WSBlockNode extends WSStatementNode {
	@Children
	private final WSStatementNode[] statements;

	public WSBlockNode(WSStatementNode[] statements) {
		this.statements = statements;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		Stack<WSStatementNode> callStack = new Stack<>();

		WSStatementNode target = null;
		WSStatementNode last = null;

		do {
			try {
				for (WSStatementNode statement : statements) {
					target = null;

					if (last != null) {
						if (statement == last) {
							last = null;
						}
					} else {
						target = statement;
						target.executeVoid(frame);
					}
				}

				if (last != null) {
					throw new ExceptionCarringError(
							new UnexpectedException("The target label is not exists : " + last));
				}
			} catch (WSCallException e) {
				callStack.push(target);
				last = e.getLabel();
			} catch (WSEndProgramException e) {
				break;
			} catch (WSJumpException e) {
				last = e.getLabel();
			} catch (WSReturnException e) {
				last = callStack.pop();
			}
		} while (last != null);
	}
}
