package io.github.mkudo.graalws.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.TruffleLanguage.Env;

import io.github.mkudo.graalws.WSLanguage;

public final class WSContext {
	private final Env env;
	private final WSLanguage language;

	private final Map<Long, Long> heap = new HashMap<>();

	private final BufferedReader in;
	private final PrintWriter out;

	public WSContext(final WSLanguage language, Env env) {
		this.env = env;
		this.language = language;

		this.in = new BufferedReader(new InputStreamReader(env.in()));
		this.out = new PrintWriter(env.out(), true);
	}

	public Map<Long, Long> getHeap() {
		return heap;
	}

	public BufferedReader getInput() {
		return in;
	}

	public PrintWriter getOutput() {
		return out;
	}
}
