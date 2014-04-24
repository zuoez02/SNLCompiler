package com.yvan.ll1GrammarAnalysis;

public class ArrayAttr {
    private int low;
    private int top;
    private NK childType;

    public ArrayAttr() {

    }

    public int getLow() {
	return low;
    }

    public void setLow(int low) {
	this.low = low;
    }

    public int getTop() {
	return top;
    }

    public void setTop(int top) {
	this.top = top;
    }

    public NK getChildType() {
	return childType;
    }

    public void setChildType(NK childType) {
	this.childType = childType;
    }

    public String toString() {
	String s = "";
	s = s + "\tLow:\t" + this.low + "\n";
	s = s + "\tTop:\t" + this.top + "\n";
	s = s + "\tChildType:\t" + this.childType.toString() + "\n";
	return s;
    }
}
