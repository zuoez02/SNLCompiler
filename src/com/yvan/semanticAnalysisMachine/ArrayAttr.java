package com.yvan.semanticAnalysisMachine;

public class ArrayAttr {
    private InterRep indexTy; // internal representation of the index
    private InterRep elemTy; // internal representation of the element in the
			     // array

    public ArrayAttr() {
	this.indexTy = null;
	this.elemTy = null;
    }

    /**
     * @return the indexTy
     */
    public InterRep getIndexTy() {
	return indexTy;
    }

    /**
     * @param indexTy
     *            the indexTy to set
     */
    public void setIndexTy(InterRep indexTy) {
	this.indexTy = indexTy;
    }

    /**
     * @return the elemTy
     */
    public InterRep getElemTy() {
	return elemTy;
    }

    /**
     * @param elemTy
     *            the elemTy to set
     */
    public void setElemTy(InterRep elemTy) {
	this.elemTy = elemTy;
    }
}
