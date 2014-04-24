package com.yvan.ll1GrammarAnalysis;

public enum NK {
    // null
    NULL,
    // grammar tree node type
    PROK, PHEADK, TYPEK, VARK, PROCDECK, STMLK, DECK, STMTK, EXPK,

    // declare type
    ARRAYK, CHARK, INTEGERK, RECORDK, IDK,

    // statement type
    IFK, WHILEK, ASSIGNK, READK, WRITEK, CALLK, RETURNK,

    // exp type
    OPK, CONSTK, IDEK,

    // var kind
    IDV, ARRAYMEMBV, FIELDMEMBV,

    // ExpType
    VOID, INTEGER, BOOLEAN,

    // param type
    VALPARAMTYPE, VARPARAMTYPE;
}
