package io.github.mkudo.graalws;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;

import io.github.mkudo.graalws.runtime.WSContext;

@TruffleLanguage.Registration(id = WSLanguage.ID, name = "WhiteSpace", defaultMimeType = WSLanguage.MIME_TYPE, characterMimeTypes = WSLanguage.MIME_TYPE, contextPolicy = ContextPolicy.EXCLUSIVE)
public final class WSLanguage extends TruffleLanguage<WSContext> {
	public static final String ID = "ws";
	public static final String MIME_TYPE = "application/x-ws";

	@Override
	protected WSContext createContext(Env env) {
		return new WSContext(this, env);
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		return false;
	}
}
