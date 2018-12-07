package io.github.mkudo.graalws.nodes.stack;

import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSPushNode extends WSStatementNode {
	private final WSLongLiteralNode target;

	public WSPushNode(WSLongLiteralNode target) {
		this.target = target;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var size = descriptor.getSize();

			var targetValue = target.executeLong(frame);
			var targetSlot = descriptor.addFrameSlot(size + 1, FrameSlotKind.Long);
			frame.setLong(targetSlot, targetValue);
		} catch (UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
