package io.github.mkudo.graalws.runtime;

import com.oracle.truffle.api.nodes.ControlFlowException;

public class WSEndProgramException extends ControlFlowException {
	private static final long serialVersionUID = -8239045161849273906L;

	public static final WSEndProgramException INSTANCE = new WSEndProgramException();

	private WSEndProgramException() {
	}
}
