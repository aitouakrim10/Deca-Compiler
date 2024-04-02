lexer grammar DecaLexer;

options {
   language=Java;
   // Tell ANTLR to make the generated lexer class extend the
   // the named class, which is where any supporting code and
   // variables will be placed.
   superClass = AbstractDecaLexer;
}

@members {
}  

fragment SPE : [!@#$%^&*-+=<>?/.,:;{}] ;
fragment STRING_CAR : ~('"' | '\\' | '\n') ;
fragment CHAR : [a-zA-Z] ;
fragment DIGIT  : '0'..'9';
fragment POSTIVE_DIGIT : '1'..'9';
fragment NUM : DIGIT+ ;
fragment SIGN : (PLUS |MINUS|);
fragment EXP : ('E' |'e') SIGN INT ;
fragment DEC : NUM '.' NUM;
fragment FlOATDEC : (DEC | DEC EXP) ('F' | 'f' | );
fragment DIGITHEX : [0-9A-Fa-f] ;
fragment NUMHEX : DIGITHEX+ ;
fragment FLOATHEX : ('0x' | '0X') NUMHEX '.'   NUMHEX ('p' | 'P') SIGN NUM ('f' | 'F' | )?;
OBRACE: '{' ;
CBRACE: '}' ;
OPARENT: '(' ;
CPARENT: ')' ;
SEMI : ';' ;
LBRACKET : '[';
RBRACKET : ']';

UNDER : '_' ;
COMMA : ',' ;
EQUALS : '=' ;
PLUS : '+';
MINUS : '-' ;
TIMES : '*' ;
AND : '&&' ;
OR : '||';
WHILE : 'while';
RETURN : 'return';
EQEQ : '==' ;
NEQ : '!=' ;
LEQ : '<=' ;
GEQ : '>=' ;
GT : '>' ;
LT : '<' ;
COMMENT : '/*' .*? '*/' { skip(); } ;
COMMENTLINE : '//'  (~'\n')*? ('\n'| EOL) { skip(); };
CHARR : '\'' (STRING_CAR | '\\"' | '\\\\') '\'' ;
STRING : '"' (STRING_CAR | '\\"' | '\\\\')* '"' ;

MULTI_LINE_STRING : '"' ((STRING_CAR | '\n' |  '\\"' | '\\\\')+)* '"';

PERCENT : '%' ;
EXCLAM : '!' ;
SLASH : '/' ;
NEW : 'new';
DOT : '.' ;

PRINTLN: 'println' ;
PRINT : 'print' ;
PRINTX : 'printx' ;
PRINTLNX : 'printlnx' ;
IF : 'if' ;

ELSE : 'else' ;
TRUE : 'true' ;
FALSE : 'false' ;
THIS : 'this' ;
CLASS : 'class' ;
EXTENDS : 'extends' ;
PROTECTED : 'protected' ;
NULL : 'null' ;
INSTANCEOF : 'instanceof' ;
READINT : 'readInt';
READFLOAT : 'readFloat';
ASM : 'asm';
fragment FILENAME : (CHAR | DIGIT | '.' | '-' | '_')+ ;
INCLUDE : '#include' (' ')* '"' FILENAME '"'{ doInclude(getText());};

FLOAT : (FlOATDEC | FLOATHEX);
INT : ('0' | POSTIVE_DIGIT)+;
IDENT : ((CHAR |'$' | '_')+) (CHAR | DIGIT | '$' | '_')* ;

EOL : ('\n')+ {skip();} ;
TAB : '\t' {skip();};
Br : '\r' {skip();};
ESPACE : ' ' {skip();};
VIDE : (ESPACE|EOL)+ { skip(); } ;


