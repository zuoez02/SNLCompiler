package com.yvan.semanticAnalysisMachine;

import java.util.LinkedList;
import java.util.List;

public class FieldChain {
    private List<FieldNode> fieldList;

    public FieldChain() {
	this.fieldList = new LinkedList<FieldNode>();
    }

    /**
     * @return the fieldList
     */
    public List<FieldNode> getFieldList() {
	return fieldList;
    }

    /**
     * @param fieldList
     *            the fieldList to set
     */
    public void setFieldList(List<FieldNode> fieldList) {
	this.fieldList = fieldList;
    }
}
