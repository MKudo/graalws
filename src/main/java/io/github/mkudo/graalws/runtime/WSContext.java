package io.github.mkudo.graalws.runtime;

import com.oracle.truffle.api.TruffleLanguage.Env;

import io.github.mkudo.graalws.WSLanguage;

public final class WSContext {
	private final Env env;
	private final WSLanguage language;

	public WSContext(final WSLanguage language, Env env) {
		this.env = env;
		this.language = language;
	}
}
