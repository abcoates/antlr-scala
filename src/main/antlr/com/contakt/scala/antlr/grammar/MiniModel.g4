grammar MiniModel;

@header {
package com.contakt.scala.antlr.grammar; // forced by IDEA Antlr plugin to use 'com...' instead of 'org...'
}

model: 'model' '{' declaration* '}';

declaration: class_declaration;

class_declaration: 'class' name '{' property_declaration* '}';

property_declaration: 'property' name ':' type multiplicity? ';';

multiplicity: '[' (NUMBER '..' NUMBER | NUMBER | 'unbounded') ']';

name: NAME;

type: NAME;

NAME: [A-Za-z_][A-Za-z_0-9]*;

NUMBER: [0-9]+;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
