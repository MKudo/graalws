package io.github.mkudo.graalws.runtime;

import com.oracle.truffle.api.nodes.ControlFlowException;

import io.github.mkudo.graalws.nodes.controlflow.WSLabelNode;

public final class WSJumpException extends ControlFlowException {
	private static final long serialVersionUID = -5114076338973151214L;

	private final WSLabelNode label;

	public WSJumpException(WSLabelNode label) {
		super();
		this.label = label;
	}

	public WSLabelNode getLabel() {
		return label;
	}
}
