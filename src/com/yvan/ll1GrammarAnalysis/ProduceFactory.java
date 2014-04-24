package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

public class ProduceFactory {
    public ProduceFactory() {
    }

    public Object[] Produce(int ProStmNum) {
	Object obj[] = null;
	if (ProStmNum == -1) {
	    obj = new Object[] { LEX.ERROR };
	}
	switch (ProStmNum) {
	case 1:
	    obj = new Object[] { NES.PROGRAMHEAD, NES.DECLAREPART,
		    NES.PROGRAMBODY };
	    break;
	case 2:
	    obj = new Object[] { LEX.PROGRAM, NES.PROGRAMNAME };
	    break;
	case 3:
	    obj = new Object[] { LEX.ID };
	    break;
	case 4:
	    obj = new Object[] { NES.TYPEDECPART, NES.VARDECPART,
		    NES.PROCDECPART };
	    break;
	case 5:
	    obj = new Object[] {};
	    break;
	case 6:
	    obj = new Object[] { NES.TYPEDEC };
	    break;
	case 7:
	    obj = new Object[] { LEX.TYPE, NES.TYPEDECLIST };
	    break;
	case 8:
	    obj = new Object[] { NES.TYPEID, LEX.EQ, NES.TYPEDEF, LEX.SEMI,
		    NES.TYPEDECMORE };
	    break;
	case 9:
	    obj = new Object[] {};
	    break;
	case 10:
	    obj = new Object[] { NES.TYPEDECLIST };
	    break;
	case 11:
	    obj = new Object[] { LEX.ID };
	    break;
	case 12:
	    obj = new Object[] { NES.BASETYPE };
	    break;
	case 13:
	    obj = new Object[] { NES.STRUCTURETYPE };
	    break;
	case 14:
	    obj = new Object[] { LEX.ID };
	    break;
	case 15:
	    obj = new Object[] { LEX.INTEGER };
	    break;
	case 16:
	    obj = new Object[] { LEX.CHAR };
	    break;
	case 17:
	    obj = new Object[] { NES.ARRAYTYPE };
	    break;
	case 18:
	    obj = new Object[] { NES.RECTYPE };
	    break;
	case 19:
	    obj = new Object[] { LEX.ARRAY, LEX.LMIDPAREN, NES.LOW,
		    LEX.UNDERANGE, NES.TOP, LEX.RMIDPAREN, LEX.OF, NES.BASETYPE };
	    break;
	case 20:
	    obj = new Object[] { LEX.INTC };
	    break;
	case 21:
	    obj = new Object[] { LEX.INTC };
	    break;
	case 22:
	    obj = new Object[] { LEX.RECORD, NES.FIELDDECLIST, LEX.END };
	    break;
	case 23:
	    obj = new Object[] { NES.BASETYPE, NES.IDLIST, LEX.SEMI,
		    NES.FIELDDECMORE };
	    break;
	case 24:
	    obj = new Object[] { NES.ARRAYTYPE, NES.IDLIST, LEX.SEMI,
		    NES.FIELDDECMORE };
	    break;
	case 25:
	    obj = new Object[] {};
	    break;
	case 26:
	    obj = new Object[] { NES.FIELDDECLIST };
	    break;
	case 27:
	    obj = new Object[] { LEX.ID, NES.IDMORE };
	    break;
	case 28:
	    obj = new Object[] {};
	    break;
	case 29:
	    obj = new Object[] { LEX.COMMA, NES.IDLIST };
	    break;
	case 30:
	    obj = new Object[] {};
	    break;
	case 31:
	    obj = new Object[] { NES.VARDEC };
	    break;
	case 32:
	    obj = new Object[] { LEX.VAR, NES.VARDECLIST };
	    break;
	case 33:
	    obj = new Object[] { NES.TYPEDEF, NES.VARIDLIST, LEX.SEMI,
		    NES.VARDECMORE };
	    break;
	case 34:
	    obj = new Object[] {};
	    break;
	case 35:
	    obj = new Object[] { NES.VARDECLIST };
	    break;
	case 36:
	    obj = new Object[] { LEX.ID, NES.VARIDMORE };
	    break;
	case 37:
	    obj = new Object[] {};
	    break;
	case 38:
	    obj = new Object[] { LEX.COMMA, NES.VARIDLIST };
	    break;
	case 39:
	    obj = new Object[] {};
	    break;
	case 40:
	    obj = new Object[] { NES.PROCDEC };
	    break;
	case 41:
	    obj = new Object[] { LEX.PROCEDURE, NES.PROCNAME, LEX.LPAREN,
		    NES.PARAMLIST, LEX.RPAREN, LEX.SEMI, NES.PROCDECPART,
		    NES.PROCBODY, NES.PROCDECMORE };
	    break;
	case 42:
	    obj = new Object[] {};
	    break;
	case 43:
	    obj = new Object[] { NES.PROCDECPART };
	    break; // is not the same as the word in the book
	case 44:
	    obj = new Object[] { LEX.ID };
	    break;
	case 45:
	    obj = new Object[] {};
	    break;
	case 46:
	    obj = new Object[] { NES.PARAMDECLIST };
	    break;
	case 47:
	    obj = new Object[] { NES.PARAM, NES.PARAMMORE };
	    break;
	case 48:
	    obj = new Object[] {};
	    break;
	case 49:
	    obj = new Object[] { LEX.SEMI, NES.PARAMDECLIST };
	    break;
	case 50:
	    obj = new Object[] { NES.TYPEDEF, NES.FORMLIST };
	    break;
	case 51:
	    obj = new Object[] { LEX.VAR, NES.TYPEDEF, NES.FORMLIST };
	    break;
	case 52:
	    obj = new Object[] { LEX.ID, NES.FIDMORE };
	    break;
	case 53:
	    obj = new Object[] {};
	    break;
	case 54:
	    obj = new Object[] { LEX.COMMA, NES.FORMLIST };
	    break;
	case 55:
	    obj = new Object[] { NES.DECLAREPART };
	    break;
	case 56:
	    obj = new Object[] { NES.PROGRAMBODY };
	    break;
	case 57:
	    obj = new Object[] { LEX.BEGIN, NES.STMLIST, LEX.END };
	    break;
	case 58:
	    obj = new Object[] { NES.STM, NES.STMMORE };
	    break;
	case 59:
	    obj = new Object[] {};
	    break;
	case 60:
	    obj = new Object[] { LEX.SEMI, NES.STMLIST };
	    break;
	case 61:
	    obj = new Object[] { NES.CONDITIONALSTM };
	    break;
	case 62:
	    obj = new Object[] { NES.LOOPSTM };
	    break;
	case 63:
	    obj = new Object[] { NES.INPUTSTM };
	    break;
	case 64:
	    obj = new Object[] { NES.OUTPUTSTM };
	    break;
	case 65:
	    obj = new Object[] { NES.RETURNSTM };
	    break;
	case 66:
	    obj = new Object[] { LEX.ID, NES.ASSCALL };
	    break;
	case 67:
	    obj = new Object[] { NES.ASSIGNMENTREST };
	    break;
	case 68:
	    obj = new Object[] { NES.CALLSTMREST };
	    break;
	case 69:
	    obj = new Object[] { NES.VARIMORE, LEX.ASSIGN, NES.EXP };
	    break;
	case 70:
	    obj = new Object[] { LEX.IF, NES.RELEXP, LEX.THEN, NES.STMLIST,
		    LEX.ELSE, NES.STMLIST, LEX.FI };
	    break;
	case 71:
	    obj = new Object[] { LEX.WHILE, NES.RELEXP, LEX.DO, NES.STMLIST,
		    LEX.ENDWH };
	    break;
	case 72:
	    obj = new Object[] { LEX.READ, LEX.LPAREN, NES.INVAR, LEX.RPAREN };
	    break;
	case 73:
	    obj = new Object[] { LEX.ID };
	    break;
	case 74:
	    obj = new Object[] { LEX.WRITE, LEX.LPAREN, NES.EXP, LEX.RPAREN };
	    break;
	case 75:
	    obj = new Object[] { LEX.RETURN };
	    break;
	case 76:
	    obj = new Object[] { LEX.LPAREN, NES.ACTPARAMLIST, LEX.RPAREN };
	    break;
	case 77:
	    obj = new Object[] {};
	    break;
	case 78:
	    obj = new Object[] { NES.EXP, NES.ACTPARAMMORE };
	    break;
	case 79:
	    obj = new Object[] {};
	    break;
	case 80:
	    obj = new Object[] { LEX.COMMA, NES.ACTPARAMLIST };
	    break;
	case 81:
	    obj = new Object[] { NES.EXP, NES.OTHERRELE };
	    break;
	case 82:
	    obj = new Object[] { NES.CMPOP, NES.EXP };
	    break;
	case 83:
	    obj = new Object[] { NES.TERM, NES.OTHERTERM };
	    break;
	case 84:
	    obj = new Object[] {};
	    break;
	case 85:
	    obj = new Object[] { NES.ADDOP, NES.EXP };
	    break;
	case 86:
	    obj = new Object[] { NES.FACTOR, NES.OTHERFACTOR };
	    break;
	case 87:
	    obj = new Object[] {};
	    break;
	case 88:
	    obj = new Object[] { NES.MULTOP, NES.TERM };
	    break;
	case 89:
	    obj = new Object[] { LEX.LPAREN, NES.EXP, LEX.RPAREN };
	    break;
	case 90:
	    obj = new Object[] { LEX.INTC };
	    break;
	case 91:
	    obj = new Object[] { NES.VARIABLE };
	    break;
	case 92:
	    obj = new Object[] { LEX.ID, NES.VARIMORE };
	    break;
	case 93:
	    obj = new Object[] {};
	    break;
	case 94:
	    obj = new Object[] { LEX.LMIDPAREN, NES.EXP, LEX.RMIDPAREN };
	    break;
	case 95:
	    obj = new Object[] { LEX.DOT, NES.FIELDVAR };
	    break;
	case 96:
	    obj = new Object[] { LEX.ID, NES.FIELDVARMORE };
	    break;
	case 97:
	    obj = new Object[] {};
	    break;
	case 98:
	    obj = new Object[] { LEX.LMIDPAREN, NES.EXP, LEX.RMIDPAREN };
	    break;
	case 99:
	    obj = new Object[] { LEX.LT };
	    break;
	case 100:
	    obj = new Object[] { LEX.EQ };
	    break;
	case 101:
	    obj = new Object[] { LEX.PLUS };
	    break;
	case 102:
	    obj = new Object[] { LEX.MINUS };
	    break;
	case 103:
	    obj = new Object[] { LEX.TIMES };
	    break;
	case 104:
	    obj = new Object[] { LEX.OVER };
	    break;
	}
	return obj;
    }
}
