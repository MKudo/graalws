package io.github.mkudo.graalws.nodes.io;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.WSLanguage;
import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;

public final class WSPutNode extends WSStatementNode {
	private final boolean toChar;

	public WSPutNode(boolean toChar) {
		this.toChar = toChar;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		try {
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();
			var targetSlot = slots.get(slots.size() - 1);
			var targetValue = frame.getLong(targetSlot);
			descriptor.removeFrameSlot(targetSlot);

			var context = getRootNode().getLanguage(WSLanguage.class).getContextReference().get();

			if (toChar) {
				if (targetValue < 0 || targetValue > 255) {
					throw new UnexpectedResultException("value [" + targetValue + "] is not ASCII value");
				}

				char c = (char) targetValue;

				context.getOutput().print(c);
			} else {
				context.getOutput().println(targetValue);
			}
		} catch (FrameSlotTypeException | UnexpectedResultException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
