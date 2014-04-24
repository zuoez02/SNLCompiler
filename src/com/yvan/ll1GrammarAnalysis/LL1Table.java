package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

public class LL1Table {
    public int searchProduceNumber(NES top, LEX head) {

	switch (top) {
	case PROGRAM:
	    if (head == LEX.PROGRAM)
		return 1;
	    break;
	case PROGRAMHEAD:
	    if (head == LEX.PROGRAM)
		return 2;
	    break;
	case PROGRAMNAME:
	    if (head == LEX.ID)
		return 3;
	    break;
	case DECLAREPART:
	    if (head == LEX.TYPE || head == LEX.VAR || head == LEX.PROCEDURE
		    || head == LEX.BEGIN)
		return 4;
	    break;
	case TYPEDECPART:
	    if (head == LEX.VAR || head == LEX.PROCEDURE || head == LEX.BEGIN)
		return 5;
	    if (head == LEX.TYPE)
		return 6;
	    break;
	case TYPEDEC:
	    if (head == LEX.TYPE)
		return 7;
	    break;
	case TYPEDECLIST:
	    if (head == LEX.ID)
		return 8;
	    break;
	case TYPEDECMORE:
	    if (head == LEX.VAR || head == LEX.PROCEDURE || head == LEX.BEGIN)
		return 9;
	    if (head == LEX.ID)
		return 10;
	    break;
	case TYPEID:
	    if (head == LEX.ID)
		return 11;
	    break;
	case TYPEDEF:
	    if (head == LEX.INTEGER || head == LEX.CHAR)
		return 12;
	    if (head == LEX.ARRAY || head == LEX.RECORD)
		return 13;
	    if (head == LEX.ID)
		return 14;
	    break;
	case BASETYPE:
	    if (head == LEX.INTEGER)
		return 15;
	    if (head == LEX.CHAR)
		return 16;
	    break;
	case STRUCTURETYPE:
	    if (head == LEX.ARRAY)
		return 17;
	    if (head == LEX.RECORD)
		return 18;
	    break;
	case ARRAYTYPE:
	    if (head == LEX.ARRAY)
		return 19;
	    break;
	case LOW:
	    if (head == LEX.INTC)
		return 20;
	    break;
	case TOP:
	    if (head == LEX.INTC)
		return 21;
	    break;
	case RECTYPE:
	    if (head == LEX.RECORD)
		return 22;
	    break;
	case FIELDDECLIST:
	    if (head == LEX.INTEGER || head == LEX.CHAR)
		return 23;
	    if (head == LEX.ARRAY)
		return 24;
	    break;
	case FIELDDECMORE:
	    if (head == LEX.END)
		return 25;
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY)
		return 26;
	    break;
	case IDLIST:
	    if (head == LEX.ID)
		return 27;
	    break;
	case IDMORE:
	    if (head == LEX.SEMI)
		return 28;
	    if (head == LEX.COMMA)
		return 29;
	    break;
	case VARDECPART:
	    if (head == LEX.PROCEDURE || head == LEX.BEGIN)
		return 30;
	    if (head == LEX.VAR)
		return 31;
	    break;
	case VARDEC:
	    if (head == LEX.VAR)
		return 32;
	    break;
	case VARDECLIST:
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY
		    || head == LEX.RECORD || head == LEX.ID)
		return 33;
	    break;
	case VARDECMORE:
	    if (head == LEX.PROCEDURE || head == LEX.BEGIN)
		return 34;
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY
		    || head == LEX.RECORD || head == LEX.ID)
		return 35;
	    break;
	case VARIDLIST:
	    if (head == LEX.ID)
		return 36;
	    break;
	case VARIDMORE:
	    if (head == LEX.SEMI)
		return 37;
	    if (head == LEX.COMMA)
		return 38;
	    break;
	case PROCDECPART:
	    if (head == LEX.BEGIN)
		return 39;
	    if (head == LEX.PROCEDURE)
		return 40;
	    if (head == LEX.TYPE || head == LEX.VAR || head == LEX.PROCEDURE
		    || head == LEX.BEGIN)
		return 55;
	    break;
	case PROCDEC:
	    if (head == LEX.PROCEDURE)
		return 41;
	    break;
	case PROCDECMORE:
	    if (head == LEX.BEGIN)
		return 42;
	    if (head == LEX.PROCEDURE)
		return 43;
	    break;
	case PROCNAME:
	    if (head == LEX.ID)
		return 44;
	    break;
	case PARAMLIST:
	    if (head == LEX.RPAREN)
		return 45;
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY
		    || head == LEX.RECORD || head == LEX.ID || head == LEX.VAR)
		return 46;
	    break;
	case PARAMDECLIST:
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY
		    || head == LEX.RECORD || head == LEX.ID || head == LEX.VAR)
		return 47;
	    break;
	case PARAMMORE:
	    if (head == LEX.RPAREN)
		return 48;
	    if (head == LEX.SEMI)
		return 49;
	    break;
	case PARAM:
	    if (head == LEX.INTEGER || head == LEX.CHAR || head == LEX.ARRAY
		    || head == LEX.RECORD || head == LEX.ID)
		return 50;
	    if (head == LEX.VAR)
		return 51;
	    break;
	case FORMLIST:
	    if (head == LEX.ID)
		return 52;
	    break;
	case FIDMORE:
	    if (head == LEX.SEMI || head == LEX.RPAREN)
		return 53;
	    if (head == LEX.COMMA)
		return 54;
	    break;
	case PROCBODY:
	    if (head == LEX.BEGIN)
		return 56;
	    break;
	case PROGRAMBODY:
	    if (head == LEX.BEGIN)
		return 57;
	    break;
	case STMLIST:
	    if (head == LEX.ID || head == LEX.IF || head == LEX.WHILE
		    || head == LEX.RETURN || head == LEX.READ
		    || head == LEX.WRITE)
		return 58;
	    break;
	case STMMORE:
	    if (head == LEX.ELSE || head == LEX.FI || head == LEX.END
		    || head == LEX.ENDWH)
		return 59;
	    if (head == LEX.SEMI)
		return 60;
	    break;
	case STM:
	    if (head == LEX.IF)
		return 61;
	    if (head == LEX.WHILE)
		return 62;
	    if (head == LEX.READ)
		return 63;
	    if (head == LEX.WRITE)
		return 64;
	    if (head == LEX.RETURN)
		return 65;
	    if (head == LEX.ID)
		return 66;
	    break;
	case ASSCALL:
	    if (head == LEX.ASSIGN || head == LEX.LMIDPAREN) // add head ==
							     // LEX.LMIDPAREN
		return 67;
	    if (head == LEX.LPAREN)
		return 68;
	    break;
	case ASSIGNMENTREST:
	    if (head == LEX.LMIDPAREN || head == LEX.DOT || head == LEX.ASSIGN)
		return 69;
	    break;
	case CONDITIONALSTM:
	    if (head == LEX.IF)
		return 70;
	    break;
	case LOOPSTM:
	    if (head == LEX.WHILE)
		return 71;
	    break;
	case INPUTSTM:
	    if (head == LEX.READ)
		return 72;
	    break;
	case INVAR:
	    if (head == LEX.ID)
		return 73;
	    break;
	case OUTPUTSTM:
	    if (head == LEX.WRITE)
		return 74;
	    break;
	case RETURNSTM:
	    if (head == LEX.RETURN)
		return 75;
	    break;
	case CALLSTMREST:
	    if (head == LEX.LPAREN)
		return 76;
	    break;
	case ACTPARAMLIST:
	    if (head == LEX.RPAREN)
		return 77;
	    if (head == LEX.LPAREN || head == LEX.INTC || head == LEX.ID)
		return 78;
	    break;
	case ACTPARAMMORE:
	    if (head == LEX.RPAREN)
		return 79;
	    if (head == LEX.COMMA)
		return 80;
	    break;
	case RELEXP:
	    if (head == LEX.LPAREN || head == LEX.INTC || head == LEX.ID)
		return 81;
	    break;
	case OTHERRELE:
	    if (head == LEX.LT || head == LEX.EQ)
		return 82;
	    break;
	case EXP:
	    if (head == LEX.LPAREN || head == LEX.INTC || head == LEX.ID)
		return 83;
	    break;
	case OTHERTERM:
	    if (head == LEX.LT || head == LEX.EQ || head == LEX.RMIDPAREN
		    || head == LEX.THEN || head == LEX.ELSE || head == LEX.FI
		    || head == LEX.DO || head == LEX.ENDWH
		    || head == LEX.RPAREN || head == LEX.END
		    || head == LEX.SEMI || head == LEX.COMMA)
		return 84;
	    if (head == LEX.PLUS || head == LEX.MINUS)
		return 85;
	    break;
	case TERM:
	    if (head == LEX.LPAREN || head == LEX.INTC || head == LEX.ID)
		return 86;
	    break;
	case OTHERFACTOR:
	    if (head == LEX.LT || head == LEX.EQ || head == LEX.RMIDPAREN
		    || head == LEX.THEN || head == LEX.ELSE || head == LEX.FI
		    || head == LEX.DO || head == LEX.ENDWH
		    || head == LEX.RPAREN || head == LEX.END
		    || head == LEX.SEMI || head == LEX.COMMA
		    || head == LEX.PLUS || head == LEX.MINUS)
		return 87;
	    if (head == LEX.TIMES || head == LEX.OVER)
		return 88;
	    break;
	case FACTOR:
	    if (head == LEX.LPAREN)
		return 89;
	    if (head == LEX.INTC)
		return 90;
	    if (head == LEX.ID)
		return 91;
	    break;
	case VARIABLE:
	    if (head == LEX.ID)
		return 92;
	    break;
	case VARIMORE:
	    if (head == LEX.LT || head == LEX.EQ || head == LEX.RMIDPAREN
		    || head == LEX.THEN || head == LEX.ELSE || head == LEX.FI
		    || head == LEX.DO || head == LEX.ENDWH
		    || head == LEX.RPAREN || head == LEX.END
		    || head == LEX.SEMI || head == LEX.COMMA
		    || head == LEX.PLUS || head == LEX.MINUS
		    || head == LEX.ASSIGN || head == LEX.TIMES
		    || head == LEX.OVER)
		return 93;
	    if (head == LEX.LMIDPAREN)
		return 94;
	    if (head == LEX.DOT)
		return 95;
	    break;
	case FIELDVAR:
	    if (head == LEX.ID)
		return 96;
	    break;
	case FIELDVARMORE:
	    if (head == LEX.LT || head == LEX.EQ || head == LEX.RMIDPAREN
		    || head == LEX.THEN || head == LEX.ELSE || head == LEX.FI
		    || head == LEX.DO || head == LEX.ENDWH
		    || head == LEX.RPAREN || head == LEX.END
		    || head == LEX.SEMI || head == LEX.COMMA
		    || head == LEX.PLUS || head == LEX.MINUS
		    || head == LEX.ASSIGN || head == LEX.TIMES
		    || head == LEX.OVER)
		return 97;
	    if (head == LEX.LMIDPAREN)
		return 98;
	    break;
	case CMPOP:
	    if (head == LEX.LT)
		return 99;
	    if (head == LEX.EQ)
		return 100;
	    break;
	case ADDOP:
	    if (head == LEX.PLUS)
		return 101;
	    if (head == LEX.MINUS)
		return 102;
	    break;
	case MULTOP:
	    if (head == LEX.TIMES)
		return 103;
	    if (head == LEX.OVER)
		return 104;
	    break;
	}
	// whether the target is null
	return -1;
    }
}
