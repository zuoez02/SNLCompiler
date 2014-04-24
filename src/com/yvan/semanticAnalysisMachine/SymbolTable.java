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
public class SymbolTable {
    private List<SymbolNode> symbolTable;
    private int level;

    public SymbolTable() {
	this.symbolTable = new LinkedList<SymbolNode>();
    }

    /**
     * 
     * @param level
     */
    public SymbolTable(int level) {
	this.setLevel(level);
	this.symbolTable = new LinkedList<SymbolNode>();
    }

    /**
     * @return the symbolTable
     */
    public List<SymbolNode> getSymbolTable() {
	return symbolTable;
    }

    /**
     * add a symbol node to the symbol table
     * 
     * @param symbolNode
     */
    public void push(SymbolNode symbolNode) {
	if (symbolNode != null)
	    this.symbolTable.add(symbolNode);
    }

    /**
     * @return the level
     */
    public int getLevel() {
	return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
	this.level = level;
    }

    public String toString() {
	String s = "";
	for (int i = 0; i < this.symbolTable.size(); i++) {
	    s += this.symbolTable.get(i).getName();
	    s += "  \t>>";
	}
	return s;
    }
}
