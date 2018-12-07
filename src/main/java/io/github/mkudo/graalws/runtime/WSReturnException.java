package io.github.mkudo.graalws.runtime;

import com.oracle.truffle.api.nodes.ControlFlowException;

public final class WSReturnException extends ControlFlowException {
	private static final long serialVersionUID = 542440654164969214L;

	public static final WSReturnException INSTANCE = new WSReturnException();

	private WSReturnException() {
	}
}
