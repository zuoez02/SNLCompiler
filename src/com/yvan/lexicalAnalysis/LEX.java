package com.yvan.lexicalAnalysis;

public enum LEX {

    // Default is null
    NULL,

    // ²¾¼Çµ¥´Ê·ûºÅ
    ERROR, EOF,
    // reserved word
    PROGRAM, PROCEDURE, TYPE, VAR, IF, THEN, ELSE, FI, WHILE, DO, ENDWH, BEGIN, END, READ, WRITE, ARRAY, OF, RECORD, RETURN, EMPTY,

    // Type
    INTEGER, CHAR,
    // multiple character word symbol
    ID, INTC, CHARC,
    // special symbol
    ASSIGN, EQ, LT, PLUS, MINUS, TIMES, OVER, LPAREN, RPAREN, DOT, COLON, SEMI, COMMA, LMIDPAREN, RMIDPAREN, UNDERANGE, ;

    // check out whether the word is a reserved word
    public static LEX reservedLookUp(String word) {
	String key = word.toLowerCase();
	if (key.equals("program"))
	    return LEX.PROGRAM;
	if (key.equals("procedure"))
	    return LEX.PROCEDURE;
	if (key.equals("type"))
	    return LEX.TYPE;
	if (key.equals("var"))
	    return LEX.VAR;
	if (key.equals("if"))
	    return LEX.IF;
	if (key.equals("then"))
	    return LEX.THEN;
	if (key.equals("else"))
	    return LEX.ELSE;
	if (key.equals("fi"))
	    return LEX.FI;
	if (key.equals("while"))
	    return LEX.WHILE;
	if (key.equals("do"))
	    return LEX.DO;
	if (key.equals("endwh"))
	    return LEX.ENDWH;
	if (key.equals("begin"))
	    return LEX.BEGIN;
	if (key.equals("end"))
	    return LEX.END;
	if (key.equals("read"))
	    return LEX.READ;
	if (key.equals("write"))
	    return LEX.WRITE;
	if (key.equals("array"))
	    return LEX.ARRAY;
	if (key.equals("of"))
	    return LEX.OF;
	if (key.equals("record"))
	    return LEX.RECORD;
	if (key.equals("return"))
	    return LEX.RETURN;
	if (key.equals("integer"))
	    return LEX.INTEGER;
	if (key.equals("char"))
	    return LEX.CHAR;
	return LEX.ID;

    }
}