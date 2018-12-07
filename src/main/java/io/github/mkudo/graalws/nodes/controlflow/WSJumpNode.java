package io.github.mkudo.graalws.nodes.controlflow;

import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.ExceptionCarringError;
import io.github.mkudo.graalws.runtime.WSJumpException;

public final class WSJumpNode extends WSStatementNode {
	public enum Type {
		EQZ, JP, LTZ
	}

	@Child
	private WSLabelNode label;
	private Type type;

	private WSJumpNode() {
	}

	public static WSJumpNode eqz(WSLabelNode label) {
		var result = new WSJumpNode();
		result.label = label;
		result.type = Type.EQZ;
		return result;
	}

	public static WSJumpNode jp(WSLabelNode label) {
		var result = new WSJumpNode();
		result.label = label;
		result.type = Type.JP;
		return result;
	}

	public static WSJumpNode ltz(WSLabelNode label) {
		var result = new WSJumpNode();
		result.label = label;
		result.type = Type.LTZ;
		return result;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		if (needToJump(frame)) {
			throw new WSJumpException(label);
		}
	}

	public Type getType() {
		return type;
	}

	private boolean needToJump(VirtualFrame frame) {
		if (type == Type.JP) {
			return true;
		}

		try {
			boolean result = false;
			var descriptor = frame.getFrameDescriptor();
			var slots = descriptor.getSlots();

			var last = slots.get(slots.size() - 1);

			if (type == Type.EQZ) {
				result = frame.getLong(last) == 0;
			} else if (type == Type.LTZ) {
				result = frame.getLong(last) < 0;
			}

			descriptor.removeFrameSlot(last);

			return result;
		} catch (FrameSlotTypeException e) {
			throw new ExceptionCarringError(e);
		}
	}
}
