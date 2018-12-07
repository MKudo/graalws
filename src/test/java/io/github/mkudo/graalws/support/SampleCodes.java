package io.github.mkudo.graalws.support;

public final class SampleCodes {
	public static final String NUMBER_M1 = "\t\t\n";
	public static final String NUMBER_0 = " \n";
	public static final String NUMBER_1 = " \t\n";
	public static final String NUMBER_2 = " \t \n";
	public static final String NUMBER_3 = " \t\t\n";
	public static final String NUMBER_4 = " \t  \n";
	public static final String NUMBER_5 = " \t \t\n";

	public static final String ARITHMETIC_ADD = "\t   ";
	public static final String ARITHMETIC_DIV = "\t \t ";
	public static final String ARITHMETIC_MOD = "\t \t\t";
	public static final String ARITHMETIC_MUL = "\t  \n";
	public static final String ARITHMETIC_SUB = "\t  \t";

	/* Require arguments (number) */
	public static final String FLOW_CALL = "\n \t";
	public static final String FLOW_END_PROGRAM = "\n\n\n";
	/* Require arguments (number) */
	public static final String FLOW_JUMP = "\n \n";
	/* Require arguments (number) */
	public static final String FLOW_JUMP_EQZ = "\n\t ";
	/* Require arguments (number) */
	public static final String FLOW_JUMP_LTZ = "\n\t\t";
	/* Require arguments (number) */
	public static final String FLOW_LABEL = "\n  ";
	public static final String FLOW_RETURN = "\n\t\n";

	public static final String HEAP_LOAD = "\t\t\t";
	public static final String HEAP_STORE = "\t\t ";

	public static final String IO_PUT_CHAR = "\t\n  ";
	public static final String IO_PUT_NUMBER = "\t\n \t";
	public static final String IO_READ_CHAR = "\t\n\t ";
	public static final String IO_READ_NUMBER = "\t\n\t\t";

	public static final String STACK_DISCARD = " \n\n";
	public static final String STACK_DUP = " \n ";
	/* Require arguments (number) */
	public static final String STACK_DUP_N = " \t ";
	/* Require arguments (number) */
	public static final String STACK_PUSH = "  ";
	/* Require arguments (number) */
	public static final String STACK_SLIDE = " \t\n";
	public static final String STACK_SWAP = " \n\t";

	private SampleCodes() {
	}
}
