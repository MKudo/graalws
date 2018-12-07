package io.github.mkudo.graalws.nodes.stack;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSSwapNode extends WSStatementNode {
	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();

			var last = slots.get(slots.size() - 1);
			var second = slots.get(slots.size() - 2);

			var right = frame.getLong(last);
			var left = frame.getLong(second);

			frame.setLong(last, left);
			frame.setLong(second, right);
		} catch (FrameSlotTypeException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
