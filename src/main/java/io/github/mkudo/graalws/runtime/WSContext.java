package io.github.mkudo.graalws.runtime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.oracle.truffle.api.TruffleLanguage.Env;

public final class WSContext {
	private final Map<Long, Long> heap = new HashMap<>();

	private final BufferedReader in;
	private final PrintWriter out;

	public WSContext(Env env) {
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
