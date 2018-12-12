package io.github.mkudo.graalws;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.TruffleLanguage.ContextPolicy;
import com.oracle.truffle.api.nodes.RootNode;
import com.oracle.truffle.api.source.Source;

import io.github.mkudo.graalws.parser.WSLexer;
import io.github.mkudo.graalws.parser.WSNodeBuilder;
import io.github.mkudo.graalws.parser.WSParser;
import io.github.mkudo.graalws.runtime.WSContext;

@TruffleLanguage.Registration(id = WSLanguage.ID, name = "WhiteSpace", defaultMimeType = WSLanguage.MIME_TYPE, characterMimeTypes = WSLanguage.MIME_TYPE, contextPolicy = ContextPolicy.EXCLUSIVE)
public final class WSLanguage extends TruffleLanguage<WSContext> {
	public static final String ID = "ws";
	public static final String MIME_TYPE = "application/x-ws";

	@Override
	protected WSContext createContext(Env env) {
		return new WSContext(env);
	}

	@Override
	protected boolean isObjectOfLanguage(Object object) {
		return false;
	}

	@Override
	protected CallTarget parse(ParsingRequest request) throws Exception {
		var source = request.getSource();

		if (!request.getArgumentNames().isEmpty()) {
			// Or push arguments to stack
			throw new IllegalArgumentException("WhiteSpace does not support parameter.");
		}

		return Truffle.getRuntime().createCallTarget(buildNodes(source));
	}

	private RootNode buildNodes(Source source) {
		Lexer lexer = null;

		if (source.getPath() == null) {
			lexer = new WSLexer(CharStreams.fromString(source.getCharacters().toString()));
		} else {
			lexer = new WSLexer(CharStreams.fromString(source.getCharacters().toString(), source.getName()));
		}

		var parser = new WSParser(new CommonTokenStream(lexer));
		// TODO create ErrorListner to report error(as Exception) to truffle
//		lexer.removeErrorListeners();
//		parser.removeErrorListeners();
//		lexer.addErrorListener(listener);
//		parser.addErrorListener(listener);

		var builder = new WSNodeBuilder(this);
		var root = builder.visit(parser.file());

		if (root instanceof RootNode) {
			return RootNode.class.cast(root);
		}

		// TODO Is this safe for truffle?
		throw new RuntimeException("WhiteSpace parser can not treat input (" + source.getPath() + ")");
	}
}
