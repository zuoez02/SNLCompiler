package com.yvan.lexicalAnalysis;

public class OutKind {
    private LEX outKind; // out kind is a lex type actually

    public OutKind() {
	this.setOutKind(LEX.NULL);
    }

    // return the out kind
    public LEX getOutKind() {
	return outKind;
    }

    // set the out kind by a lex type
    public void setOutKind(LEX outKind) {
	this.outKind = outKind;
    }

    // set the out kind by a state number
    public void setOutKind(int state) {
	switch (state) {
	case 1:
	    outKind = LEX.ID;
	    break;
	case 2:
	    outKind = LEX.INTC;
	    break;
	case 3:
	    outKind = LEX.PLUS;
	    break;
	case 4:
	    outKind = LEX.MINUS;
	    break;
	case 5:
	    outKind = LEX.TIMES;
	    break;
	case 6:
	    outKind = LEX.OVER;
	    break;
	case 7:
	    outKind = LEX.LPAREN;
	    break;
	case 8:
	    outKind = LEX.RPAREN;
	    break;
	case 9:
	    outKind = LEX.LMIDPAREN;
	    break;
	case 10:
	    outKind = LEX.RMIDPAREN;
	    break;
	case 11:
	    outKind = LEX.LT;
	    break;
	case 12:
	    outKind = LEX.EQ;
	    break;
	case 13:
	    break;
	case 14:
	    outKind = LEX.EMPTY;
	    break;
	case 15:
	    outKind = LEX.COLON;
	    break;
	case 16:
	    outKind = LEX.ASSIGN;
	    break;
	case 17:
	    outKind = LEX.ERROR;
	    break;
	case 18:
	    break;
	case 19:
	    outKind = LEX.CHARC;
	    break;
	case 20:
	    outKind = LEX.DOT;
	    break;
	case 21:
	    outKind = LEX.UNDERANGE;
	    break;
	case 22:
	    outKind = LEX.SEMI;
	    break;
	case 23:
	    outKind = LEX.COMMA;
	    break;
	}
    }
}