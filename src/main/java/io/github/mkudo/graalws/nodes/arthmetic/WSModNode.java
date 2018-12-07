package io.github.mkudo.graalws.nodes.arthmetic;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSArithmeticNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public class WSModNode extends WSArithmeticNode {
	@Override
	public Object executeGeneric(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();

			var last = slots.get(slots.size() - 1);
			var second = slots.get(slots.size() - 2);

			var right = frame.getLong(last);
			var left = frame.getLong(second);
			var result = left % right;

			descriptor.removeFrameSlot(last);
			frame.setLong(second, result);

			return Long.valueOf(result);
		} catch (FrameSlotTypeException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
