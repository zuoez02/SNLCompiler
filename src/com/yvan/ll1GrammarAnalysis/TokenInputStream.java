package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

public class TokenInputStream {
    private TokenList tokenList;

    public TokenInputStream(TokenList tokenList) {
	this.tokenList = tokenList;
    }

    // get the head token's LEX as the input stream first element
    public LEX getHead() {
	Token head = null;
	head = this.tokenList.getHeadToken();
	LEX headLEX = null;
	headLEX = head.getLEX();
	return headLEX;
    }

    // get the head token
    public Token getHeadToken() {
	return this.tokenList.getHeadToken();
    }

    // get the line of the head token
    public int getLine() {
	Token head = this.tokenList.getHeadToken();
	int line = head.getLine();
	return line;
    }

    // set the second token as head token
    public void removeHead() {
	Token currentHead = this.tokenList.getHeadToken();
	Token secondToken = currentHead.getNextToken();
	this.tokenList.setHeadToken(secondToken);
    }

    // get the input stream's token list
    public TokenList getTokenList() {
	return this.tokenList;
    }
}
