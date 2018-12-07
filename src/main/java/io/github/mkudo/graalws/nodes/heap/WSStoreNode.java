package io.github.mkudo.graalws.nodes.heap;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.WSLanguage;
import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSStoreNode extends WSStatementNode {
	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();

			var last = slots.get(slots.size() - 1);
			var second = slots.get(slots.size() - 2);

			var value = frame.getLong(last);
			var key = frame.getLong(second);
			var context = getRootNode().getLanguage(WSLanguage.class).getContextReference().get();
			var heap = context.getHeap();

			heap.put(key, value);

			descriptor.removeFrameSlot(last);
			descriptor.removeFrameSlot(second);
		} catch (FrameSlotTypeException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
