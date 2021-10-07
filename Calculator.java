import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;

public class Calculator {
  public static void main(String[] args) throws Exception{
    String inputFile = null;
    if (args.length > 0) inputFile = args[0];
    InputStream is = System.in;
    if (inputFile != null) is = new FileInputStream(inputFile);
    ANTLRInputStream input = new ANTLRInputStream(is); 
    LabeledExprLexer lexer = new LabeledExprLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    LabeledExprParser parser = new LabeledExprParser(tokens);
    ParseTree tree = parser.prog(); // parse; start at prog ​
    //System.out.println(tree.toStringTree(parser)); // print tree as text 
    EvalVisitor eval = new EvalVisitor();
    eval.visit(tree);
  }
}
