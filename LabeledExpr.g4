grammar LabeledExpr;
prog: stat+;

stat:   expr NEWLINE                # printExpr
    |   ID '=' expr NEWLINE         # assign
    |   NEWLINE                     # blank
    |   'clear' NEWLINE             # clear
    ;

expr:   expr op=('*'|'/') expr      # MulDiv
    |   expr op=('+'|'-') expr      # AddSub
    |   (SIN|TAN|COS) expr          # Sin
    |   FLOAT                       # float
    |   ID                          # id
    |   PI                          # pi
    |   E                           # exponent
    |   '(' expr ')'                # parens
    ;
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
FLOAT: [-+]?([0-9]*[.])?[0-9]+([eE][-+]?[0-9]+)?;
NEWLINE: '\r'? '\n' ;
WS : [ \t]+->skip;