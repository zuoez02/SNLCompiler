package com.yvan.ll1GrammarAnalysis;

public class ProcAttr {
    private NK paramt; // param type, valparamtype or varparamtype

    public ProcAttr(NK paramt) {
	this.paramt = paramt;
    }

    public NK getParamt() {
	return paramt;
    }

    public void setParamt(NK paramt) {
	this.paramt = paramt;
    }

    public String toString() {
	return "\tParamt:\t" + this.paramt.toString() + "\n";
    }
}
