/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

import java.util.LinkedList;
import java.util.List;

/**
 * @author YvanLuto
 * 
 */
public class Scope {
    private List<SymbolTable> scope;

    public Scope() {
	this.scope = new LinkedList<SymbolTable>();
    }

    /**
     * @return the scope
     */
    public List<SymbolTable> getScope() {
	return scope;
    }

    /**
     * @param scope
     *            the scope to set
     */
    public void setScope(List<SymbolTable> scope) {
	this.scope = scope;
    }

    /**
     * get the top symbol table in the scope
     * 
     * @return
     */
    public SymbolTable getTop() {
	if (this.scope.size() > 0)
	    return this.scope.get(this.scope.size() - 1);
	return null;
    }

    /**
     * get specific level symbol table
     * 
     * @param level
     *            the level of the symbol table which you want
     * @return the specific symbol table
     */
    public SymbolTable getLevelTop(int level) {
	if (level >= 0 && level < this.scope.size())
	    return this.scope.get(level);
	return null;
    }

    public SymbolTable pop() {
	return this.scope.remove(this.scope.size() - 1);
    }

    public String toString() {
	String s = "";
	for (int i = 0; i < this.scope.size(); i++) {
	    s += "Level:" + i + "\tTable:" + this.scope.get(i).toString()
		    + "\n";
	}
	return s;
    }

}
