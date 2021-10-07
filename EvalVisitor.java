import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Double> {
    /** "memory" for our calculator; variable/value pairs go here */
    Map<String, Double> memory = new HashMap<String, Double>();

    /** ID '=' expr NEWLINE */
    @Override
    public Double visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();  // id is left-hand side of '='
        double value = visit(ctx.expr());   // compute value of expression on right
        memory.put(id, value);           // store it in our memory
        return value;
    }

    /** expr NEWLINE */
    @Override
    public Double visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        double value = visit(ctx.expr()); // evaluate the expr child
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);
        System.out.println(df.format(value));         // print the result
        return value;                          // return dummy value
    }

    /** INT */
    @Override
    public Double visitFloat(LabeledExprParser.FloatContext ctx) {
        return Double.valueOf(ctx.FLOAT().getText());
    }

    /** ID */
    @Override
    public Double visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if ( memory.containsKey(id) ) return memory.get(id);
        return 0.0;
    }

    /** expr op=('*'|'/') expr */
    @Override
    public Double visitMulDivPow(LabeledExprParser.MulDivPowContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.POW ) return Math.pow(left, right);
        if ( ctx.op.getType() == LabeledExprParser.MUL ) return left * right;
        return left / right; // must be DIV
    }

    /** expr op=('+'|'-') expr */
    @Override
    public Double visitAddSub(LabeledExprParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));  // get value of left subexpression
        double right = visit(ctx.expr(1)); // get value of right subexpression
        if ( ctx.op.getType() == LabeledExprParser.ADD ) return left + right;
        return left - right; // must be SUB
    }

    /** '(' expr ')' */
    @Override
    public Double visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr()); // return child expr's value
    }

    @Override
    public Double visitClear(LabeledExprParser.ClearContext ctx){
        memory = new HashMap<String, Double>();
        return 0.0;
    }

    @Override
    public Double visitPi(LabeledExprParser.PiContext ctx){
        return Math.PI;
    }

    @Override 
    public Double visitEuler(LabeledExprParser.EulerContext ctx){
        return Math.E;
    }

    @Override
    public Double visitTrig(LabeledExprParser.TrigContext ctx) {
        double expr = visit(ctx.expr());  // get value of subexpression
        if ( ctx.op.getType() == LabeledExprParser.SIN ) return Math.sin(expr);
        if ( ctx.op.getType() == LabeledExprParser.COS ) return Math.cos(expr);
        return Math.tan(expr); //must be tan
    }

    @Override
    public Double visitInvTrig(LabeledExprParser.InvTrigContext ctx) {
        double expr = visit(ctx.expr());  // get value of subexpression
        if ( ctx.op.getType() == LabeledExprParser.ASIN ) return Math.asin(expr);
        if ( ctx.op.getType() == LabeledExprParser.ACOS ) return Math.cos(expr);
        return Math.tan(expr); //must be tan
    }

    @Override
    public Double visitSqrt(LabeledExprParser.SqrtContext ctx){
        double value = visit(ctx.expr());
        return Math.sqrt(value);
    }

    @Override
    public Double visitLog(LabeledExprParser.LogContext ctx){
        double value = visit(ctx.expr());
        if (ctx.op.getType() == LabeledExprParser.LOG10) return Math.log10(value);
        return Math.log(value);
    }
}