package com.yvan.semanticAnalysisMachine;

import com.yvan.ll1GrammarAnalysis.NK;

public class Transform {
    public static NK TKtoNK(TypeKind typeKind) {
	if (typeKind == TypeKind.INTTY)
	    return NK.INTEGERK;
	if (typeKind == TypeKind.CHARTY)
	    return NK.CHARK;
	if (typeKind == TypeKind.ARRAYTY)
	    return NK.ARRAYK;
	if (typeKind == TypeKind.RECORDTY)
	    return NK.RECORDK;
	return null;
    }

    public static TypeKind NKtoTK(NK nk) {
	switch (nk) {
	case INTEGERK:
	    return TypeKind.INTTY;
	case CHARK:
	    return TypeKind.CHARTY;
	case ARRAYK:
	    return TypeKind.ARRAYTY;
	case RECORDK:
	    return TypeKind.RECORDTY;
	default:
	    break;
	}
	return null;
    }
}
