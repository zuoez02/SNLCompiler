/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

import java.util.LinkedList;
import java.util.List;

/**
 * @author YvanLuto Save semantic errors
 */
public class ErrorList {
    private List<SemanticError> list;

    public ErrorList() {
	this.list = new LinkedList<SemanticError>();
    }

    public void push(SemanticError error) {
	this.list.add(error);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	String s = "";
	for (int i = 0; i < this.list.size(); i++) {
	    s += this.list.get(i).toString() + "\n";
	}
	s += " errors(" + this.list.size() + ")\n";
	return s;
    }

    /**
     * get how many errors
     * 
     * @return
     */
    public int getErrorsNum() {
	return this.list.size();
    }

}
