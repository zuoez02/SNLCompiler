package com.yvan.lexicalAnalysis;

public class Token {

    private LEX LEX; // store the type
    private String SEM; // store the word
    private int line; // store which line
    private Token nextToken; // a pointer to next token, default is null

    // create an empty token
    public Token() {
	this.LEX = null;
	this.SEM = null;
	this.line = 0;
	this.nextToken = null;
    }

    // create a valid token
    public Token(LEX LEX, String SEM, int line) {
	this.LEX = LEX;
	this.SEM = SEM;
	this.line = line;
	this.nextToken = null;
    }

    public Token(LEX lex) {
	this.LEX = lex;
	this.SEM = null;
	this.line = -1;
	this.nextToken = null;
    }

    // set the pointer to next token
    public void setNextToken(Token nextToken) {
	this.nextToken = nextToken;
    }

    // get next token by pointer
    public Token getNextToken() {
	return this.nextToken;
    }

    // make a string of the message of the token
    public String toString() {
	String s = null;
	s = "line:" + this.line + "\tLEX:" + this.LEX + "\t\tSEM:" + this.SEM;
	return s;
    }

    // return LEX of the token
    public LEX getLEX() {
	return this.LEX;
    }

    // return token's line
    public int getLine() {
	return this.line;
    }

    public String getSEM() {
	return this.SEM;
    }

    public void setLEX(LEX lEX) {
	LEX = lEX;
    }

    public void setSEM(String sEM) {
	SEM = sEM;
    }

    public void setLine(int line) {
	this.line = line;
    }

    public Token copy() {
	Token token = new Token();
	token.setLEX(this.LEX);
	token.setSEM(this.SEM);
	token.setLine(this.line);
	return token;
    }
}
