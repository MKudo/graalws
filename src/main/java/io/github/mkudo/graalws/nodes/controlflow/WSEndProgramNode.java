package io.github.mkudo.graalws.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.WSEndProgramException;

public class WSEndProgramNode extends WSStatementNode {
	@Override
	public void executeVoid(VirtualFrame frame) {
		throw WSEndProgramException.INSTANCE;
	}
}
