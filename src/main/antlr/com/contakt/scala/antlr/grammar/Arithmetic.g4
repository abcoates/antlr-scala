grammar Arithmetic;

@header {
package com.contakt.scala.antlr.grammar; // forced by IDEA Antlr plugin to use 'com...' instead of 'org...'
}

arithmetic: expr?;

expr
    : '(' expr ')' # BracketExpression
    | expr MULDIV expr # MultiplicationOrDivision
    | expr ADDSUB expr # AdditionOrSubtraction
    | NUMBER # Number
    ;

MULDIV: '*' | '/';
ADDSUB: '+' | '-';
NUMBER: DIGIT+;
fragment DIGIT: '0'..'9';
