grammar LabeledExpr;
prog: stat+;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    |   'clear' NEWLINE             # clear
    ;

expr:   expr op=('*'|'/'|'^') expr  # MulDivPow
    |   expr op=('+'|'-') expr      # AddSub
    |   op=('log'|'ln') expr        # log
    |   op=('sin'|'cos'|'tan') expr     # trig
    |   op=('asin'|'acos'|'atan') expr # invTrig
    |   'sqrt' expr            # sqrt
    |   FLOAT                       # float
    |   ID                          # id
    |   PI                          # pi
    |   E                           # euler
    |   '(' expr ')'                # parens
    ;

FLOAT:  ('0'..'9')+
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
POW: '^';
LOG10: 'log';
LN: 'ln';
COS: 'cos';
TAN: 'tan';
SIN: 'sin';
ACOS: 'acos';
ASIN: 'asin';
ATAN: 'atan';
SQRT: 'sqrt';
PI: ('pi');
E: 'e';
ID : [a-zA-Z]+;
INT: [0-9]+;
NEWLINE: '\r'? '\n' | ';';
WS : [ \t]+->skip;