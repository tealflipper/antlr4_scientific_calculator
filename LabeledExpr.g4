grammar LabeledExpr;
prog: stat+;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    |   'clear' NEWLINE             # clear
    ;

expr:   expr op=('*'|'/') expr      # MulDiv
    |   expr op=('+'|'-') expr      # AddSub
    |   FLOAT                       # float
    |   ID                          # id
    |   PI                          # pi
    |   E                           # exponent
    |   '(' expr ')'                # parens
    ;

FLOAT: ('0'..'9')+
    |   ('0'..'9')+ '.' ('0'..'9')* EXPONENT?
    |   '.'  ('0'..'9')+ EXPONENT?
    |   ('0'..'9')+ EXPONENT
    ;

EXPONENT: ('e'|'E') ('+'|'-')? ('0'..'9')+ ;
//assign token name to value in grammar
MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';
COS: 'cos';
TAN: 'tan';
SIN: 'sin';
SQRT: 'sqrt';
PI: ('pi');
E: 'e';
ID : [a-zA-Z]+;
INT: [0-9]+;
NEWLINE: '\r'? '\n' | ';';
WS : [ \t]+->skip;