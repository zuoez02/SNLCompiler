package com.yvan.ll1GrammarAnalysis;

import java.util.*;

import com.yvan.lexicalAnalysis.LEX;

public class TreeNode {
    private List<TreeNode> child;
    private int lineNo;
    private NK nodeKind;
    private NK kind;
    private List<String> name;
    private String typeName;
    private ArrayAttr arrayAttr;
    private ProcAttr procAttr;
    private ExpAttr expAttr;
    private TreeNode sibling;

    /**
     * @return the sibling
     */
    public TreeNode getSibling() {
	return sibling;
    }

    /**
     * @param sibling
     *            the sibling to set
     */
    public void setSibling(TreeNode sibling) {
	this.sibling = sibling;
    }

    public TreeNode(int line) {
	this.child = new ArrayList<TreeNode>();
	this.lineNo = line;
	this.nodeKind = NK.NULL;
	this.kind = NK.NULL;
	this.name = new ArrayList<String>();
	this.arrayAttr = null;
	this.procAttr = null;
	this.expAttr = null;
	this.sibling = null;
    }

    public ArrayList<TreeNode> getChild() {
	return (ArrayList<TreeNode>) child;
    }

    public void setChild(ArrayList<TreeNode> child) {
	this.child = child;
    }

    public int getLineNo() {
	return lineNo;
    }

    public void setLineNo(int lineNo) {
	this.lineNo = lineNo;
    }

    public NK getNodeKind() {
	return nodeKind;
    }

    public void setNodeKind(NK nodeKind) {
	this.nodeKind = nodeKind;
    }

    public NK getKind() {
	return kind;
    }

    public void setKind(NK kind) {
	this.kind = kind;
    }

    public ArrayList<String> getName() {
	return (ArrayList<String>) name;
    }

    public void addName(String name) {
	this.name.add(name);
    }

    public ArrayAttr getArrayAttr() {
	return arrayAttr;
    }

    public void setArrayAttr(ArrayAttr arrayAttr) {
	this.arrayAttr = arrayAttr;
    }

    public ProcAttr getProcAttr() {
	return procAttr;
    }

    public void setProcAttr(ProcAttr procAttr) {
	this.procAttr = procAttr;
    }

    public ExpAttr getExpAttr() {
	return expAttr;
    }

    public void setExpAttr(ExpAttr expAttr) {
	this.expAttr = expAttr;
    }

    public String getTypeName() {
	return typeName;
    }

    public void setTypeName(String typeName) {
	this.typeName = typeName;
    }

    public String onlyMe() {
	String s = "";
	if (this.getName().size() != 0)
	    for (int i = 0; i < name.size(); i++)
		s += this.getName().get(i) + " ";
	return "line: " + this.lineNo + " " + this.getNodeKind() + "\t" + s
		+ "\n";
    }

    public String toString() {
	String s = "";
	s = this.outPut(0);
	return s;
    }

    public String outPut(int j) {
	String s = "line:" + this.lineNo;
	for (int i = 0; i < j; i++) {
	    s += "    ";
	}

	// NodeKind
	s += this.nodeKind.toString() + " ";
	// ProcAttr
	if (this.procAttr != null)
	    if (this.nodeKind == NK.DECK)
		s += this.procAttr.getParamt().toString() + ": ";
	// Kind
	if (this.kind != NK.NULL && this.kind != NK.IDEK)
	    s += this.kind.toString() + " ";
	if (this.typeName != null)
	    s += ":" + this.getTypeName();
	// Name
	if (this.name.size() > 0) {
	    for (int i = 0; i < this.name.size(); i++) {
		s += " " + this.name.get(i) + " ";
	    }
	}
	// val or varkind or op
	if (this.expAttr != null) {
	    if (this.kind == NK.CONSTK) {
		s += this.expAttr.getVal();
	    }
	    if (this.expAttr.getVarkind() != NK.NULL) {
		s += this.expAttr.getVarkind();
	    }
	    if (this.expAttr.getOp() != LEX.NULL) {
		s += this.expAttr.getOp();
	    }
	}
	if (this.arrayAttr != null) {
	    s += "Low=" + this.arrayAttr.getLow() + " Top="
		    + this.arrayAttr.getTop() + "  basetype="
		    + this.arrayAttr.getChildType();
	}
	s += "\n";
	if (this.child.size() > 0) {
	    for (int i = 0; i < this.child.size(); i++) {
		// System.out.println(this.child.get(i).getNodeKind());
		s += this.child.get(i).outPut(++j);
		j--;
	    }
	}
	return s;
    }

    public void Print(int j) {
	for (int i = 0; i < j; i++) {
	    System.out.print("   ");
	}
	// NodeKind
	System.out.print(this.nodeKind.toString() + " ");
	// ProcAttr
	if (this.procAttr != null)
	    if (this.nodeKind == NK.DECK)
		System.out.print(this.procAttr.getParamt().toString() + ": ");
	// Kind
	if (this.kind != NK.NULL && this.kind != NK.IDEK)
	    System.out.print(this.kind.toString() + " ");
	// Name
	if (this.name.size() > 0) {
	    for (int i = 0; i < this.name.size(); i++) {
		System.out.print(" " + this.name.get(i) + " ");
	    }
	}
	// val or varkind or op
	if (this.expAttr != null) {
	    if (this.kind == NK.CONSTK) {
		System.out.print(this.expAttr.getVal());
	    }
	    if (this.expAttr.getVarkind() != NK.NULL) {
		System.out.print(this.expAttr.getVarkind());
	    }
	    if (this.expAttr.getOp() != LEX.NULL) {
		System.out.print(this.expAttr.getOp());
	    }
	}
	if (this.arrayAttr != null) {
	    System.out.print("Low=" + this.arrayAttr.getLow() + " Top="
		    + this.arrayAttr.getTop() + "  basetype="
		    + this.arrayAttr.getChildType());
	}
	System.out.println("\tline:" + this.lineNo);
	if (this.child.size() > 0) {
	    for (int i = 0; i < this.child.size(); i++) {
		// System.out.println(this.child.get(i).getNodeKind());
		this.child.get(i).Print(++j);
		j--;
	    }
	}
    }

    public void printMe() {
	System.out.println("************");
	System.out.println("childNum:" + this.child.size());
	System.out.println("sibling:" + this.sibling);
	System.out.println("line:" + this.lineNo);
	System.out.println("NodeKind:" + this.nodeKind);
	System.out.println("Kind:" + this.kind);
	// System.out.print("Name:");
	// for(int i=0;i<name.size();i++)
	// System.out.print( " " + name.get(i) );
	// System.out.println();
	System.out.println("typeName:" + this.typeName);
	System.out.println("************");
	if (this.arrayAttr != null)
	    System.out.println("arrayAttr:" + this.arrayAttr.toString());
	if (this.procAttr != null)
	    System.out.println("procAttr:" + this.procAttr.toString());
	if (this.expAttr != null)
	    System.out.println("expAttr:" + this.expAttr.toString());
    }

}
