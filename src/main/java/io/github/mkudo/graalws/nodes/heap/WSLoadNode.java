package io.github.mkudo.graalws.nodes.heap;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.WSLanguage;
import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSLoadNode extends WSStatementNode {
	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();
			var targetSlot = slots.get(slots.size() - 1);
			var key = frame.getLong(targetSlot);

			var context = getRootNode().getLanguage(WSLanguage.class).getContextReference().get();
			var heap = context.getHeap();
			long value = heap.getOrDefault(key, 0L);

			frame.setLong(targetSlot, value);
		} catch (FrameSlotTypeException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
