/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * Procedure¡®s attribute in the identifier
 * 
 * @author YvanLuto
 * 
 */
public class ProcAttr {
    private ParamList paramList; // parameter list
    private int size; // the space the procedure need

    /**
     * default
     */
    public ProcAttr() {
	this.setParamList(new ParamList());
	this.setSize(0);
    }

    /**
     * @return the paramList
     */
    public ParamList getParamList() {
	return paramList;
    }

    /**
     * @param paramList
     *            the paramList to set
     */
    public void setParamList(ParamList paramList) {
	this.paramList = paramList;
    }

    /**
     * @return the size
     */
    public int getSize() {
	return size;
    }

    /**
     * @param size
     *            the size to set
     */
    public void setSize(int size) {
	this.size = size;
    }
}
