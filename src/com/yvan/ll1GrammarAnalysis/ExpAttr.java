package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

public class ExpAttr {
    private LEX op;
    private int val;
    private NK varkind;
    private NK type;

    public ExpAttr() {
	this.op = LEX.NULL;
	this.val = 0;
	this.varkind = NK.NULL;
	this.type = NK.NULL;
    }

    public LEX getOp() {
	return op;
    }

    public void setOp(LEX op) {
	this.op = op;
    }

    public int getVal() {
	return val;
    }

    public void setVal(int val) {
	this.val = val;
    }

    public NK getVarkind() {
	return varkind;
    }

    public void setVarkind(NK varkind) {
	this.varkind = varkind;
    }

    public NK getType() {
	return type;
    }

    public void setType(NK type) {
	this.type = type;
    }

    public String toString() {
	String s = "\n";
	s = s + "\tOp:\t" + this.op.toString() + "\n";
	s += "\tVal:\t" + this.val + "\n";
	s += "\tVarKind:\t" + this.varkind.toString() + "\n";
	s += "\tType:\t" + this.type.toString() + "\n";
	return s;
    }
}
