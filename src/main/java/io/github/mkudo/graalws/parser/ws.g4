grammar ws;

file
	: statement*
	;

statement
	: L flowOperation
	| S stackOperation
	| T memoryOperation
	;

flowOperation
	: label
	| flowCall
	| flowEndProgram
	| flowJump
	| flowJumpEqZero
	| flowJumpLtZero
	| flowReturn
	;

label			: S S number;
flowCall		: S T number;
flowEndProgram	: L L;
flowJump		: S L number;
flowJumpEqZero	: T S number;
flowJumpLtZero	: T T number;
flowReturn		: T L;

stackOperation
	: stackDiscard
	| stackDuplicate
	| stackDuplicateN
	| stackPush
	| stackSlide
	| stackSwap
	;

stackDiscard	: L L;
stackDuplicate	: L S;
stackDuplicateN	: T S number;
stackPush		: S number;
stackSlide		: T L number;
stackSwap		: L T;

memoryOperation
	: S arithmeticOperation
	| T heapOperation
	| L ioOperation
	;

arithmeticOperation
	: arithmeticAdd
	| arithmeticDiv
	| arithmeticMod
	| arithmeticMul
	| arithmeticSub
	;

arithmeticAdd	: S S;
arithmeticDiv	: T S;
arithmeticMod	: S L;
arithmeticMul	: T T;
arithmeticSub	: S T;

heapOperation
	: heapLoad
	| heapStore
	;

heapLoad		: T;
heapStore		: S;

ioOperation
	: ioPutChar
	| ioPutNumber
	| ioReadChar
	| ioReadNumber
	;

ioPutChar		: S S;
ioPutNumber		: S T;
ioReadChar		: T S;
ioReadNumber	: T T;

number : (S | T)+ L;

L	: '\n';
S	: ' ';
T	: '\t';

COMMENT	: . -> skip;

/*
VSCode にプラグインがあるみたい
 */
