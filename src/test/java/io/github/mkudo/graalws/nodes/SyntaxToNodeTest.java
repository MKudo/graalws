package io.github.mkudo.graalws.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;

import io.github.mkudo.graalws.nodes.arthmetic.WSAddNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSDivNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSLongLiteralNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSModNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSMulNode;
import io.github.mkudo.graalws.nodes.arthmetic.WSSubNode;
import io.github.mkudo.graalws.nodes.controlflow.WSBlockNode;
import io.github.mkudo.graalws.nodes.controlflow.WSCallNode;
import io.github.mkudo.graalws.nodes.controlflow.WSEndProgramNode;
import io.github.mkudo.graalws.nodes.controlflow.WSJumpNode;
import io.github.mkudo.graalws.nodes.controlflow.WSLabelNode;
import io.github.mkudo.graalws.nodes.controlflow.WSReturnNode;
import io.github.mkudo.graalws.nodes.heap.WSLoadNode;
import io.github.mkudo.graalws.nodes.heap.WSStoreNode;
import io.github.mkudo.graalws.nodes.io.WSPutNode;
import io.github.mkudo.graalws.nodes.io.WSReadNode;
import io.github.mkudo.graalws.nodes.stack.WSDiscardNode;
import io.github.mkudo.graalws.nodes.stack.WSDuplicateNode;
import io.github.mkudo.graalws.nodes.stack.WSPushNode;
import io.github.mkudo.graalws.nodes.stack.WSSwapNode;
import io.github.mkudo.graalws.parser.WSLexer;
import io.github.mkudo.graalws.parser.WSNodeBuilder;
import io.github.mkudo.graalws.parser.WSParser;
import io.github.mkudo.graalws.support.SampleCodes;

public class SyntaxToNodeTest {
	@Test
	public void arthmeticAddTest() {
		var actual = buildNodeTree(SampleCodes.ARITHMETIC_ADD);
		WSStatementNode[] expectedBody = { new WSAddNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void arthmeticDivTest() {
		var actual = buildNodeTree(SampleCodes.ARITHMETIC_DIV);
		WSStatementNode[] expectedBody = { new WSDivNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void arthmeticModTest() {
		var actual = buildNodeTree(SampleCodes.ARITHMETIC_MOD);
		WSStatementNode[] expectedBody = { new WSModNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void arthmeticMulTest() {
		var actual = buildNodeTree(SampleCodes.ARITHMETIC_MUL);
		WSStatementNode[] expectedBody = { new WSMulNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void arthmeticSubTest() {
		var actual = buildNodeTree(SampleCodes.ARITHMETIC_SUB);
		WSStatementNode[] expectedBody = { new WSSubNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowCallTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_CALL + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { new WSCallNode(new WSLabelNode(2)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowEndProgramTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_END_PROGRAM);
		WSStatementNode[] expectedBody = { new WSEndProgramNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowLabelTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_LABEL + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { new WSLabelNode(2) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowJumpTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_JUMP + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { WSJumpNode.jp(new WSLabelNode(2)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowJumpEqZeroTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_JUMP_EQZ + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { WSJumpNode.eqz(new WSLabelNode(2)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowJumpLtZeroTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_JUMP_LTZ + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { WSJumpNode.ltz(new WSLabelNode(2)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void flowReturnTest() {
		var actual = buildNodeTree(SampleCodes.FLOW_RETURN);
		WSStatementNode[] expectedBody = { new WSReturnNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void ioReadCharTest() {
		var actual = buildNodeTree(SampleCodes.IO_READ_CHAR);
		WSStatementNode[] expectedBody = { new WSReadNode(true) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void ioReadNumberTest() {
		var actual = buildNodeTree(SampleCodes.IO_READ_NUMBER);
		WSStatementNode[] expectedBody = { new WSReadNode(false) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void ioWriteCharTest() {
		var actual = buildNodeTree(SampleCodes.IO_PUT_CHAR);
		WSStatementNode[] expectedBody = { new WSPutNode(true) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void ioWriteNumberTest() {
		var actual = buildNodeTree(SampleCodes.IO_PUT_NUMBER);
		WSStatementNode[] expectedBody = { new WSPutNode(false) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void heapLoadTest() {
		var actual = buildNodeTree(SampleCodes.HEAP_LOAD);
		WSStatementNode[] expectedBody = { new WSLoadNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void heapStoreTest() {
		var actual = buildNodeTree(SampleCodes.HEAP_STORE);
		WSStatementNode[] expectedBody = { new WSStoreNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackDiscardTest() {
		var actual = buildNodeTree(SampleCodes.STACK_DISCARD);
		WSStatementNode[] expectedBody = { new WSDiscardNode(new WSLongLiteralNode(-1)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackDuplicteTest() {
		var actual = buildNodeTree(SampleCodes.STACK_DUP);
		WSStatementNode[] expectedBody = { new WSDuplicateNode(new WSLongLiteralNode(1)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackDuplicteNTest() {
		var actual = buildNodeTree(SampleCodes.STACK_DUP_N + SampleCodes.NUMBER_4);
		WSStatementNode[] expectedBody = { new WSDuplicateNode(new WSLongLiteralNode(4)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackPushTest() {
		var actual = buildNodeTree(SampleCodes.STACK_PUSH + SampleCodes.NUMBER_3);
		WSStatementNode[] expectedBody = { new WSPushNode(new WSLongLiteralNode(3)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackSlideTest() {
		var actual = buildNodeTree(SampleCodes.STACK_SLIDE + SampleCodes.NUMBER_2);
		WSStatementNode[] expectedBody = { new WSDiscardNode(new WSLongLiteralNode(2)) };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	@Test
	public void stackSwapTest() {
		var actual = buildNodeTree(SampleCodes.STACK_SWAP);
		WSStatementNode[] expectedBody = { new WSSwapNode() };
		var expected = new WSRootNode(null, null, new WSBlockNode(expectedBody));
		checkTheyAreSameTree(expected, actual);
	}

	private Node buildNodeTree(String targetSyntax) {
		var lexer = new WSLexer(CharStreams.fromString(targetSyntax));
		var parser = new WSParser(new CommonTokenStream(lexer));
		var builder = new WSNodeBuilder(null);

		return builder.visit(parser.file());
	}

	private void checkTheyAreSameTree(Node expected, Node actual) {
		if (expected.getClass() != actual.getClass()) {
			fail("Expected : " + expected.getClass() + " but actual : " + actual.getClass());
		}

		if (expected instanceof WSLongLiteralNode) {
			try {
				assertEquals(WSLongLiteralNode.class.cast(expected).executeLong(null),
						WSLongLiteralNode.class.cast(actual).executeLong(null));
			} catch (UnexpectedResultException e) {
				e.printStackTrace();
				fail();
			}
		}

		var expectedChildren = expected.getChildren();
		var actualChildren = actual.getChildren();

		if (expectedChildren != null) {
			if (actualChildren == null) {
				fail("Expected : " + expected.getClass() + " require children but actual node has no child");
			}

			var actualChildrenIt = actualChildren.iterator();

			for (Node expectedNode : expectedChildren) {
				if (!actualChildrenIt.hasNext()) {
					fail("Expected : " + expectedNode.getClass() + " but actual : null");
				}

				Node actualNode = actualChildrenIt.next();
				checkTheyAreSameTree(expectedNode, actualNode);
			}

			if (actualChildrenIt.hasNext()) {
				fail("Expected : null but actual : " + actualChildrenIt.next().getClass());
			}
		}
	}
}
