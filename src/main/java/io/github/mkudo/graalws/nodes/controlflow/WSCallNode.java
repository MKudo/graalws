package io.github.mkudo.graalws.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.WSCallException;

public class WSCallNode extends WSStatementNode {
	@Child
	private WSLabelNode label;

	public WSCallNode(WSLabelNode label) {
		this.label = label;
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		throw new WSCallException(label);
	}
}
