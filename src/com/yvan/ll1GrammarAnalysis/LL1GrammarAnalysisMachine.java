package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

public class LL1GrammarAnalysisMachine {
    private AnalysisStack analysisStack; // analysis stack
    private TokenInputStream inputStream; // input stream with tokens
    private GrammarTree grammarTree; // grammar tree
    private LL1Table table; // LL(1) analysis matrix
    private ProduceFactory produceFactory;
    private String runningStatus;
    private String runningSteps;

    public LL1GrammarAnalysisMachine(TokenList tokenList) {
	// initialize the machine
	analysisStack = new AnalysisStack();
	inputStream = new TokenInputStream(tokenList.copy());
	grammarTree = new GrammarTree(0);
	table = new LL1Table();
	analysisStack.push("#");
	analysisStack.push(NES.PROGRAM);
	produceFactory = new ProduceFactory();
	runningStatus = null;
	runningSteps = null;
    }

    public GrammarTree getGrammarTree() {
	return grammarTree;
    }

    public void LL1GramarAnalysis() {
	int line = 0;
	Object top = this.analysisStack.getTop();
	Object head = this.inputStream.getHead();
	line = this.inputStream.getLine();
	// input stream is not null and the stack's top is not '#'
	// begin to grammar analysis
	while ((top != "#") && (head != null)) {
	    System.out
		    .println("----------------------------------------------------------------------------------------------------");
	    this.runningSteps += this.analysisStack.toString() + ".\n";
	    System.out.println(this.analysisStack.toString());
	    System.out.println("top : \" " + top.toString() + " \" is "
		    + top.getClass());
	    System.out.println("head: \" " + head.toString() + " \" is "
		    + head.getClass());
	    if (head == LEX.DOT) {
		this.runningStatus = "Program End!\n";
		break;
	    }
	    // pop from stack if the top is the same as the first element of the
	    // input stream
	    if (top.equals(head)) {
		System.out.println("Delete Top:" + top);
		this.analysisStack.pop();
		top = this.analysisStack.getTop();
		this.inputStream.removeHead();
		head = this.inputStream.getHead();
		line = this.inputStream.getLine();
		System.out.println("line:" + line);
		// continue;
	    } else {
		// according the stack's top and stream's first element,
		// searching the table and
		// add objects DESC from produce statement of the searching
		// result.
		int ProStmNum = this.table.searchProduceNumber((NES) top,
			(LEX) head);
		System.out.println("ProStmNum=" + ProStmNum);
		Object[] product = this.produceFactory.Produce(ProStmNum);
		int length = product.length;
		// push objects to stack from tail to head.
		this.analysisStack.pop();
		for (int i = length - 1; i >= 0; i--) {
		    this.analysisStack.push(product[i]);
		}
		System.out.println("Grammar :"
			+ this.grammarTree.getStack().toString());
		System.out.println("Operator:"
			+ this.grammarTree.getOperatorStack().toString());
		System.out.println("Operand :"
			+ this.grammarTree.getOperandStack().toString());
		System.out.println("process...");
		this.grammarTree.process(ProStmNum,
			this.inputStream.getHeadToken(), line);
		top = this.analysisStack.getTop();
		head = this.inputStream.getHead();
		System.out.println("line:" + line);
		// System.out.println("*******************");
		// this.grammarTree.Print();
		// System.out.println("*******************");

		// System.out.println("Stack Top Child:" +
		// this.grammarTree.getStack().getTop().getChild().size());

		// output error message
		if (top.equals(LEX.ERROR)) {
		    this.runningStatus = "Error on line" + line
			    + ". The system end unexpectedly";
		    System.out.println(this.runningStatus);
		    break;
		}
		line = this.inputStream.getLine();

	    }

	}
	System.out.println("top : \" " + top.toString() + " \" is "
		+ top.getClass());
	System.out.println("head: \" " + head.toString() + " \" is "
		+ head.getClass());

	// if ((head == LEX.DOT) && (top == "#")){
	// // this.grammarTree.Print();
	// runningStatus = "Grammar analysis Successfully!";
	// }

	System.out.println(this.runningStatus);

    }

    // return runningStatus
    public String getStatus() {
	return this.runningStatus;
    }

    // return runningSteps()
    public String getSteps() {
	return this.runningSteps;
    }

    /**
     * @return the runningStatus
     */
    public String getRunningStatus() {
	return runningStatus;
    }

    /**
     * @param runningStatus
     *            the runningStatus to set
     */
    public void setRunningStatus(String runningStatus) {
	this.runningStatus = runningStatus;
    }
}
