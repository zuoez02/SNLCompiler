package com.yvan.semanticAnalysisMachine;

public class FieldNode {
    private String name;
    private TypeKind unitType;
    private int offset;

    public FieldNode() {

    }

    /**
     * @param name
     * @param unitType
     * @param offset
     */
    public FieldNode(String name, TypeKind unitType, int offset) {
	super();
	this.name = name;
	this.unitType = unitType;
	this.offset = offset;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the unitType
     */
    public TypeKind getUnitType() {
	return unitType;
    }

    /**
     * @param unitType
     *            the unitType to set
     */
    public void setUnitType(TypeKind unitType) {
	this.unitType = unitType;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
	return offset;
    }

    /**
     * @param offset
     *            the offset to set
     */
    public void setOffset(int offset) {
	this.offset = offset;
    }

}
