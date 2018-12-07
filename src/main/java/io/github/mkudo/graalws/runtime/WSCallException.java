package io.github.mkudo.graalws.runtime;

import com.oracle.truffle.api.nodes.ControlFlowException;

import io.github.mkudo.graalws.nodes.controlflow.WSLabelNode;

public class WSCallException extends ControlFlowException {
	private static final long serialVersionUID = 3832674525032924747L;

	private final WSLabelNode label;

	public WSCallException(WSLabelNode label) {
		super();
		this.label = label;
	}

	public WSLabelNode getLabel() {
		return label;
	}
}
