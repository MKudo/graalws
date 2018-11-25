package io.github.mkudo.graalws.nodes.arthmetic;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.WSArithmeticNode;

@NodeInfo(shortName = "const")
public final class WSLongLiteralNode extends WSArithmeticNode {
	private final long value;

	public WSLongLiteralNode(long value) {
		this.value = value;
	}

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		return value;
	}

	@Override
	public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
		return value;
	}
}
