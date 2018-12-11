package io.github.mkudo.graalws.nodes.io;

import java.io.IOException;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.WSLanguage;
import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSReadNode extends WSStatementNode {
	private final boolean fromChar;

	public WSReadNode(boolean fromChar) {
		this.fromChar = fromChar;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();
			var targetSlot = slots.get(slots.size() - 1);
			var key = frame.getLong(targetSlot);
			descriptor.removeFrameSlot(targetSlot.getIdentifier());

			var context = getRootNode().getLanguage(WSLanguage.class).getContextReference().get();
			var heap = context.getHeap();
			long value = 0L;

			String line = context.getInput().readLine();

			if (fromChar) {
				if (line.length() != 1) {
					throw new UnexpectedResultException("value [" + line + "] is not char value");
				}

				value = line.charAt(0);
			} else {
				value = Long.parseLong(line);
			}

			heap.put(key, value);
		} catch (FrameSlotTypeException | IOException | UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
