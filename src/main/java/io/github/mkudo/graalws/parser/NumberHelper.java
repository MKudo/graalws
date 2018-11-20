package io.github.mkudo.graalws.parser;

import org.antlr.v4.runtime.tree.TerminalNode;

import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.parser.WSParser.NumberContext;
import io.github.mkudo.graalws.runtime.ExceptionCallingError;

final class NumberHelper {
	private NumberHelper() {
	}

	static long toLong(NumberContext ctx) {
		int childCount = ctx.getChildCount();

		if (childCount > 0) {
			var child = ctx.getChild(0);

			if (child instanceof TerminalNode) {
				int type = TerminalNode.class.cast(child).getSymbol().getType();
				boolean isNegative = type == WSParser.T;

				long value = 0;

				for (int ii = 1; ii < childCount; ii++) {
					child = ctx.getChild(ii);

					if (child instanceof TerminalNode) {
						type = TerminalNode.class.cast(child).getSymbol().getType();

						if (type == WSParser.S) {
							value *= 2;
						} else if (type == WSParser.T) {
							value *= 2;
							value++;
						} else {
							// L
							break;
						}
					} else {
						throw new ExceptionCallingError(new UnexpectedResultException(child.getClass().toString()));
					}
				}

				if (isNegative) {
					value *= -1;
				}

				return value;
			}
		}

		throw new ExceptionCallingError(new UnexpectedResultException("child count is 0"));
	}
}
