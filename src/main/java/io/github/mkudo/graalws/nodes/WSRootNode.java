package io.github.mkudo.graalws.nodes;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;

@NodeInfo(language = "WhiteSpace", description = "The root node of WhiteSpace statements.")
public class WSRootNode extends RootNode {
	public WSRootNode(TruffleLanguage<?> language, FrameDescriptor frameDescriptor) {
		super(language, frameDescriptor);
	}

	@Override
	public Object execute(VirtualFrame frame) {
		// TODO implements
		return null;
	}
}
