package com.yvan.ll1GrammarAnalysis;

import java.util.LinkedList;
import java.util.List;

public class AnalysisStack {
    private List<Object> stack; // the analysis stack

    public AnalysisStack() {
	this.stack = new LinkedList<Object>();
    }

    // push an object in the stack
    public void push(Object obj) {
	this.stack.add(obj);
    }

    // pop the top object of the stack
    public Object pop() {
	if (!this.stack.isEmpty())
	    return this.stack.remove(this.stack.size() - 1);
	return null;
    }

    public Object getTop() {
	return this.stack.get(this.stack.size() - 1);
    }

    public boolean isEmpty() {
	return this.stack.isEmpty();
    }

    public String toString() {
	String s = "";
	for (int i = 0; i < this.stack.size(); i++) {
	    s = s + " " + this.stack.get(i).toString();
	}
	return s;
    }

    public int getLength() {
	return this.stack.size();
    }
}
