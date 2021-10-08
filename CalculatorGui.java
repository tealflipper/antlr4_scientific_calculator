
// Java program to create a simple calculator
// with basic +, -, /, * using java swing elements
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class CalculatorGui extends JFrame implements ActionListener {
	// create a frame
	static JFrame f;

	// create a textfield
	static JTextField l;

	// store operator and operands
	String inputString;
	String outputString;
	String curString;

	// default constructor
	CalculatorGui() {
		inputString = outputString = curString = "";
	}

	// main function
	public static void main(String args[]) {
		// create a frame
		f = new JFrame("calculator");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			// set look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		// create a object of class
		CalculatorGui c = new CalculatorGui();

		// create a textfield
		l = new JTextField(20);

		// set the textfield to non editable
		l.setEditable(true);

		// create number buttons and some operators
		JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, ba, bs, bd, bm, be, beq, beq1;
		JButton bpow, bsin, bcos, btan, bdot, bsqrt, bpi, bx, baa, bac, by, bz, bassign;
		JButton blog, bln, bsn;

		// create number buttons
		b0 = new JButton("0");
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");

		// equals button
		beq1 = new JButton("enter");

		// create operator buttons
		bsn = new JButton("E");
		ba = new JButton("+");
		bs = new JButton("-");
		bd = new JButton("/");
		bm = new JButton("*");
		bpow = new JButton("^");
		bsin = new JButton("sin");
		bcos = new JButton("cos");
		btan = new JButton("tan");
		bsqrt = new JButton("sqrt");
		bpi = new JButton("pi");
		be = new JButton("e");
		beq = new JButton("Clear");
		bx = new JButton("x");
		by = new JButton("y");
		bz = new JButton("z");
		baa = new JButton("(");
		bac = new JButton(")");
		bassign = new JButton("=");
		blog = new JButton("log");
		bln = new JButton("ln");
		// create . button
		bdot = new JButton(".");

		// create a panel
		JPanel p = new JPanel();

		// add action listeners
		bsn.addActionListener(c);
		bm.addActionListener(c);
		bd.addActionListener(c);
		bs.addActionListener(c);
		ba.addActionListener(c);
		b9.addActionListener(c);
		b8.addActionListener(c);
		b7.addActionListener(c);
		b6.addActionListener(c);
		b5.addActionListener(c);
		b4.addActionListener(c);
		b3.addActionListener(c);
		b2.addActionListener(c);
		b1.addActionListener(c);
		b0.addActionListener(c);
		bdot.addActionListener(c);
		beq.addActionListener(c);
		beq1.addActionListener(c);
		be.addActionListener(c);
		bpow.addActionListener(c);
		bsin.addActionListener(c);
		bcos.addActionListener(c);
		btan.addActionListener(c);
		bsqrt.addActionListener(c);
		bpi.addActionListener(c);
		bx.addActionListener(c);
		by.addActionListener(c);
		bz.addActionListener(c);
		baa.addActionListener(c);
		bac.addActionListener(c);
		bassign.addActionListener(c);
		blog.addActionListener(c);
		bln.addActionListener(c);

		// add elements to panel
		p.add(l);
		p.add(bsn);
		p.add(ba);
		p.add(bs);
		p.add(bm);
		p.add(bd);
		p.add(bdot);
		p.add(bpow);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(b0);
		p.add(bsin);
		p.add(bcos);
		p.add(btan);
		p.add(bsqrt);
		p.add(be);
		p.add(bpi);
		p.add(bx);
		p.add(by);
		p.add(bz);
		p.add(bassign);
		p.add(blog);
		p.add(bln);
		p.add(baa);
		p.add(bac);
		p.add(beq1);
		p.add(beq);

		// set Background of panel
		p.setBackground(Color.gray);

		// add panel to frame
		f.add(p);

		f.setSize(500, 500);
		f.show();
	}

	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		// string is being input
		// if the value is a number
		if (s.charAt(0) != 'C' && s != "enter") {
			// if operand is present then add to second no
			inputString += s;
			curString += s;

			outputString = curString;
			// set the value of text
			l.setText(outputString);
		} else if (s.charAt(0) == 'C') {

			// clear the one letter
			inputString = "clear;";
			outputString = " ";
			double value;

			ANTLRInputStream input = new ANTLRInputStream(inputString);
			LabeledExprLexer lexer = new LabeledExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LabeledExprParser parser = new LabeledExprParser(tokens);
			ParseTree tree = parser.prog(); // parse; start at prog ​
			//
			EvalVisitor eval = new EvalVisitor();
			value = eval.visit(tree); // set the value of text
			inputString = "";
			curString = "";
			// set the value of text
			l.setText(outputString);
		} else if (s == "enter") {

			double value = 0.0;
			outputString = curString;
			inputString += ";";
			ANTLRInputStream input = new ANTLRInputStream(inputString);
			LabeledExprLexer lexer = new LabeledExprLexer(input);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LabeledExprParser parser = new LabeledExprParser(tokens);
			ParseTree tree = parser.prog(); // parse; start at prog ​System.out.println(inputString+" value: "+value);
			//
			EvalVisitor eval = new EvalVisitor();
			value = eval.visit(tree); // set the value of text

			outputString = " " + value;
			l.setText(outputString);
			curString = " ";
		}

		// set the value of text
		l.setText(outputString);

	}
}
