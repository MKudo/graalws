package io.github.mkudo.graalws.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.instrumentation.GenerateWrapper;
import com.oracle.truffle.api.instrumentation.InstrumentableNode;
import com.oracle.truffle.api.instrumentation.ProbeNode;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;

@GenerateWrapper
@NodeInfo(description = "The abstract node for WhiteSpace statement.")
public abstract class WSStatementNode extends Node implements InstrumentableNode {
	@Override
	public WrapperNode createWrapper(ProbeNode probeNode) {
		return new WSStatementNodeWrapper(this, probeNode);
	}

	public abstract void executeVoid(VirtualFrame frame);

	@Override
	public boolean isInstrumentable() {
		return true;
	}
}
