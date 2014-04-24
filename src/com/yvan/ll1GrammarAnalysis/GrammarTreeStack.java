package com.yvan.ll1GrammarAnalysis;

import java.util.LinkedList;
import java.util.List;

public class GrammarTreeStack {
    private List<TreeNode> stack;

    public GrammarTreeStack() {
	this.stack = new LinkedList<TreeNode>();
    }

    public boolean isEmpty() {
	return this.stack.isEmpty();
    }

    public void push(TreeNode treeNode) {
	this.stack.add(treeNode);
    }

    public TreeNode pop() {
	if (!this.stack.isEmpty()) {
	    return this.stack.remove(this.stack.size() - 1);
	}
	return null;
    }

    public TreeNode getTop() {
	if (!this.stack.isEmpty()) {
	    return this.stack.get(this.stack.size() - 1);
	}
	return null;
    }

    public String toString() {
	String s = "";
	for (int i = 0; i < this.stack.size(); i++) {
	    s = s + this.stack.get(i).getNodeKind().toString();
	    s += "(" + this.stack.get(i).getKind().toString() + ")";
	    if (this.stack.get(i).getName().size() != 0)
		s += "\'" + this.stack.get(i).getName().get(0) + "\'";
	    if (this.stack.get(i).getExpAttr() != null)
		if (this.stack.get(i).getExpAttr().getOp() != null)
		    s += "OP:"
			    + this.stack.get(i).getExpAttr().getOp().toString();
	    s += " ";
	}
	return s;
    }
}
