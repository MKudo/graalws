package io.github.mkudo.graalws.nodes;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

@GenerateWrapper
@NodeInfo(description = "The abstract node for WhiteSpace expression(arithmetic operations).")
@TypeSystemReference(WSTypes.class)
public abstract class WSArithmeticNode extends WSStatementNode {
	@Override
	public WrapperNode createWrapper(ProbeNode probeNode) {
		return new WSArithmeticNodeWrapper(this, probeNode);
	}

	public abstract Object executeGeneric(VirtualFrame frame);

	public long executeLong(VirtualFrame frame) throws UnexpectedResultException {
		return WSTypesGen.expectLong(executeGeneric(frame));
	}

	@Override
	public void executeVoid(VirtualFrame frame) {
		executeGeneric(frame);
	}
}
