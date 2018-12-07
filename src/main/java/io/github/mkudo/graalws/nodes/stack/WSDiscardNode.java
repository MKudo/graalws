package io.github.mkudo.graalws.nodes.stack;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSDiscardNode extends WSStatementNode {
	private final WSLongLiteralNode target;

	public WSDiscardNode(WSLongLiteralNode target) {
		this.target = target;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();
			var count = target.executeLong(frame);
			var targetSlot = slots.get(slots.size() - 1);

			if (count == -1) {
				descriptor.removeFrameSlot(targetSlot);
			} else {
				var topValue = frame.getLong(targetSlot);

				for (int ii = 0; ii <= count; ii++) {
					descriptor.removeFrameSlot(targetSlot);
					targetSlot = slots.get(slots.size() - (2 + ii));
				}

				frame.setLong(targetSlot, topValue);
			}
		} catch (FrameSlotTypeException | UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
