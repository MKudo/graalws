package io.github.mkudo.graalws.runtime;

/**
 * To jump out API's limitation.
 * 
 * @author kudo
 */
public class ExceptionCallingError extends Error {
	private static final long serialVersionUID = 1117913463252992969L;
	private final Exception actual;

	public ExceptionCallingError(Exception actual) {
		this.actual = actual;
	}

	public Exception getActualException() {
		return actual;
	}
}
