/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto
 * 
 */
public class InterRep {
    private int size;
    private TypeKind typeKind;
    private ArrayAttr arrayAttr;
    private FieldChain fieldChain;

    public InterRep() {
	this.size = 0;
    }

    public InterRep(TypeKind typeKind) {
	this.size = 0;
	this.typeKind = typeKind;
	if (typeKind == TypeKind.INTTY || typeKind == TypeKind.CHARTY
		|| typeKind == TypeKind.BOOLTY)
	    this.size = 1;
	this.arrayAttr = null;
	this.fieldChain = null;
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

    /**
     * @return the typeKind
     */
    public TypeKind getTypeKind() {
	return typeKind;
    }

    /**
     * @param typeKind
     *            the typeKind to set
     */
    public void setTypeKind(TypeKind typeKind) {
	this.typeKind = typeKind;
    }

    /**
     * @return the arrayAttr
     */
    public ArrayAttr getArrayAttr() {
	return arrayAttr;
    }

    /**
     * @param arrayAttr
     *            the arrayAttr to set
     */
    public void setArrayAttr(ArrayAttr arrayAttr) {
	this.arrayAttr = arrayAttr;
    }

    /**
     * @return the fieldChain
     */
    public FieldChain getFieldChain() {
	return fieldChain;
    }

    /**
     * @param fieldChain
     *            the fieldChain to set
     */
    public void setFieldChain(FieldChain fieldChain) {
	this.fieldChain = fieldChain;
    }

}
