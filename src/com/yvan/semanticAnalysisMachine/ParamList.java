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
public class ParamList {
    private List<SymbolNode> paramList;

    public ParamList() {
	this.paramList = new LinkedList<SymbolNode>();
    }

    public void push(SymbolNode symbolNode) {
	this.paramList.add(symbolNode);
    }

    /**
     * @return the paramList
     */
    public List<SymbolNode> getParamList() {
	return paramList;
    }

}
