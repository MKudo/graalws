package io.github.mkudo.graalws.nodes.controlflow;

import com.oracle.truffle.api.frame.VirtualFrame;

import io.github.mkudo.graalws.nodes.WSStatementNode;
import io.github.mkudo.graalws.runtime.WSReturnException;

public class WSReturnNode extends WSStatementNode {
	@Override
	public void executeVoid(VirtualFrame frame) {
		throw WSReturnException.INSTANCE;
	}
}
