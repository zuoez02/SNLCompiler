package com.yvan.compiler;

import javax.swing.*;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import com.yvan.lexicalAnalysis.LexicalAnalysisMachine;
import com.yvan.lexicalAnalysis.TokenList;
import com.yvan.ll1GrammarAnalysis.*;
import com.yvan.semanticAnalysisMachine.*;
import com.yvan.recursiveDescentGrammarAnalysis.*;

public class GUI {
    private JFrame mainFrame;
    private JButton compileBtn;
    private JButton commitCheck;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private TokenList tokenList;
    private GrammarTree grammarTree;
    private ErrorList semanticErrors;

    public GUI() {
	JFrame mainFrame = new JFrame("SNL Compiler");
	mainFrame.setLayout(new BorderLayout(20, 20));
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

	compileBtn = new JButton("Compile");
	compileBtn.setSize(80, 20);
	// push the compile button
	compileBtn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == compileBtn) {
		    try {
			displayArea.setText("");
			CompileFile();
		    } catch (MatchException e) {
			e.printStackTrace();
		    }
		}
	    }
	});
	Vector<String> display = new Vector<String>();
	display.add("Token List");
	display.add("grammar tree");
	commitCheck = new JButton("Check");
	commitCheck.setSize(80, 20);
	commitCheck.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == commitCheck) {
		    CheckType();
		}
	    }
	});
	displayArea = new JTextArea();
	displayArea.setLineWrap(true);
	scrollPane = new JScrollPane(displayArea,
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

	panel.add(compileBtn);
	panel.add(commitCheck);

	mainFrame.add(panel, BorderLayout.NORTH);
	mainFrame.add(scrollPane, BorderLayout.CENTER);

	// push the close window button to exit
	mainFrame.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent e) {
		System.exit(1);
	    }
	});
	// mainFrame.add(displayArea.getSrc());
	mainFrame.setSize(920, 480);
	mainFrame.setVisible(true);
    }

    // click compile button to read a file
    private void CompileFile() throws MatchException {
	String address = JOptionPane.showInputDialog("Input the address");
	if (address == "" || address == null)
	    return;
	File file = new File(address);
	boolean flag = false;

	// if file is exist then analysis or output not valid
	if (file.exists()) {
	    displayArea.append("Begin to lexical analysis......");
	    try {
		tokenList = new LexicalAnalysisMachine(file).getTokenList();
	    } catch (Exception e) {
		displayArea.append("Finnished unexpected");
		e.printStackTrace();		
	    }
	    displayArea.append("Finished\t");
	    displayArea.append(this.tokenList.error());
	} else {
	    displayArea.setText("Invalid file\n");
	    flag = true;
	}
	if (flag) {
	    displayArea.append("Compile stopped\n");
	    return;
	}
	flag = false;
	//
	// choose a method of grammar analysis
	Object[] method = { "LL(1)", "Recursive Descent" };
	String s = (String) JOptionPane.showInputDialog(mainFrame,
		"Grammar analysis method", "Choose a method",
		JOptionPane.QUESTION_MESSAGE, null, method, method[0]);
	if (s.equals("LL(1)")) {
	    // begin to LL(1) grammar analysis
	    displayArea.append("\nBegin to LL(1) grammar analysis......");
	    LL1GrammarAnalysisMachine ll1GAM = new LL1GrammarAnalysisMachine(
		    tokenList);
	    this.grammarTree = ll1GAM.getGrammarTree();
	    try {
		ll1GAM.LL1GramarAnalysis();
	    } catch (Exception e) {
		e.printStackTrace();
		flag = true;
	    }
	    if(ll1GAM.getRunningStatus() != null)
		flag = true;
	    if(flag) {
		displayArea.append("\n" + ll1GAM.getRunningStatus() + "\n");
		return;
	    }
	    displayArea.append("Finnished\n");
	} else {
	    // begin to Recursive Descent grammar analysis
	    displayArea
	    .append("\nBegin to Recursive Descent grammar analysis......");
	    RecursiveDescentParser rdp = new RecursiveDescentParser(
		    this.tokenList.copy());
	    try {
		rdp.analysis();
		displayArea.append("Finnished\n");
		this.grammarTree = new GrammarTree(0);
		this.grammarTree.setRoot(rdp.getRoot());
	    } catch (MatchException e) {
		displayArea.append("analysis finnished unexpected\n");
		displayArea.append("error:" + rdp.getError());
		flag = true;
		// e.printStackTrace();
		//		throw e;
	    }

	}
	if(flag)
	    return;
	// System.out.print(this.grammarTree.getRoot().toString());
	displayArea.append("\nBegin to Semantic analysis......");
	SemanticAnalysisMachine sam = new SemanticAnalysisMachine(
		this.grammarTree.getRoot());
	sam.analysis();
	displayArea.append("Finnished\t");
	this.semanticErrors = sam.getErrorList();

	displayArea.append(sam.getErrorList().toString());
	// displayArea.setText(ll1GAM.getSteps());
    }

    // click check button to choose what you want to see
    private void CheckType() {
	if (this.tokenList == null) {
	    JOptionPane.showMessageDialog(null, "You didn't compile any file");
	} else {
	    Object[] type = { "Token List", "Grammar Tree", "Semantic Error" };
	    String chooseType = (String) JOptionPane.showInputDialog(null,
		    "Choose a type", "Type", JOptionPane.PLAIN_MESSAGE,
		    new ImageIcon("icon.png"), type, "TokenList");
	    if (chooseType.equals("Token List"))
		DisplayTokenList();
	    if (chooseType.equals("Grammar Tree"))
		DisplayGrammarTree();
	    if (chooseType.equals("Semantic Error"))
		DisplaySemanticError();
	}
    }

    // display token list in display area
    private void DisplayTokenList() {
	String s = tokenList.toString();
	this.displayArea.setText(s);
    }

    // display grammar tree in display area
    private void DisplayGrammarTree() {
	String s = this.grammarTree.toString();
	this.displayArea.setText(s);
    }

    private void DisplaySemanticError() {
	String s = this.semanticErrors.toString();
	this.displayArea.setText(s);
    }
}