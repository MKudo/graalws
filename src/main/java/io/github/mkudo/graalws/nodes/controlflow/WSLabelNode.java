package io.github.mkudo.graalws.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;

public final class WSLabelNode extends WSStatementNode {
	private final long labelId;

	public WSLabelNode(long labelId) {
		this.labelId = labelId;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		// Do nothing
	}

	public long getLabel() {
		return labelId;
	}
}
