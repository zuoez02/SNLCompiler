/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto Save a semantic error information
 */
public class SemanticError {
    private int line;
    private String information;

    /**
     * @param line
     * @param information
     */
    public SemanticError(int line, String information) {
	this.line = line;
	this.information = information;
    }

    /**
     * @return the line
     */
    public int getLine() {
	return line;
    }

    /**
     * @param line
     *            the line to set
     */
    public void setLine(int line) {
	this.line = line;
    }

    /**
     * @return the information
     */
    public String getInformation() {
	return information;
    }

    /**
     * @param information
     *            the information to set
     */
    public void setInformation(String information) {
	this.information = information;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "line:" + this.line + "\t" + this.information;
    }

}
