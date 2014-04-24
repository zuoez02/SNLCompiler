package com.yvan.lexicalAnalysis;

public class TokenList {
    private Token headToken;
    private Token tailToken;

    // create an empty token list
    public TokenList() {
	this.headToken = null;
	this.tailToken = null;
    }

    // get the first token of the list
    public Token getHeadToken() {
	return this.headToken;
    }

    // set the first token of the list
    public void setHeadToken(Token headToken) {
	this.headToken = headToken;
    }

    // get the last token of the list
    public Token getTailToken() {
	return tailToken;
    }

    // set the last token of the list
    public void setTailToken(Token tailToken) {
	this.tailToken = tailToken;
    }

    // add a token to the tail of the list
    public void add(Token token) {
	// the token must be valid
	if (token == null)
	    return;
	// if the list is null
	if (this.headToken == null) {
	    setHeadToken(token);
	    setTailToken(token);
	} else {
	    tailToken.setNextToken(token);
	    setTailToken(token);
	}
    }

    // output the message of the list to console
    public void outputTokenList() {
	if (headToken != null) {
	    Token token = headToken;
	    do {
		System.out.println(token.toString());
		token = token.getNextToken();
	    } while (token != null);
	}
    }

    // return a string of the message of the list
    public String toString() {
	String s = "";
	Token token = this.headToken;
	while (token != null) {
	    s = s + token.toString();
	    token = token.getNextToken();
	    s = s + "\n";
	}
	return s;
    }

    // copy a new token list which is the same as this
    public TokenList copy() {
	TokenList newList = new TokenList();
	Token token = this.headToken;
	do {
	    newList.add(token.copy());
	    token = token.getNextToken();
	} while (token != null);
	return newList;
    }

    public String error() {
	String error = "";
	int i = 0;
	Token token = this.headToken;
	while (token != null) {
	    if (token.getLEX() == LEX.ERROR) {
		error += "line:" + token.getLine() + "\n";
		i++;
	    }
	    token = token.getNextToken();
	}
	error += "Error(s) number is " + i + "\n";
	return error;
    }
}
