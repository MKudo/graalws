package io.github.mkudo.graalws.nodes.stack;

import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSDuplicateNode extends WSStatementNode {
	private final WSLongLiteralNode target;

	public WSDuplicateNode(WSLongLiteralNode target) {
		this.target = target;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();
			var count = target.executeLong(frame);
			var targetSlot = slots.get(slots.size() - (int) count);
			var targetValue = frame.getLong(targetSlot);

			targetSlot = descriptor.addFrameSlot(slots.size() + 1, FrameSlotKind.Long);
			frame.setLong(targetSlot, targetValue);
		} catch (FrameSlotTypeException | UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
