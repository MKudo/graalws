package io.github.mkudo.graalws.nodes;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;

import io.github.mkudo.graalws.nodes.controlflow.WSBlockNode;

@NodeInfo(language = "WhiteSpace", description = "The root node of WhiteSpace statements.")
public class WSRootNode extends RootNode {
	@Child
	private WSBlockNode block;

	public WSRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor, WSBlockNode block) {
		super(language, frameDescriptor);
		this.block = block;
	}

	@Override
	public Object execute(VirtualFrame frame) {
		block.executeVoid(frame);
		return Boolean.TRUE;
	}
}
