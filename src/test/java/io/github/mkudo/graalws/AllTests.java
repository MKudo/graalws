package io.github.mkudo.graalws;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import io.github.mkudo.graalws.nodes.SyntaxToNodeTest;
import io.github.mkudo.graalws.parser.EvalSyntaxTest;
import io.github.mkudo.graalws.parser.GrammerTest;

@RunWith(Suite.class)
@SuiteClasses({ GrammerTest.class, SyntaxToNodeTest.class, EvalSyntaxTest.class })
public class AllTests {
}
