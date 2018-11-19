grammar ws;

file
	: statement*
	;

statement
	: L S T
	;

L	: '\n';
S	: ' ';
T	: '\t';

COMMENT	: . -> skip;

/*
VSCode にプラグインがあるみたい
 */
