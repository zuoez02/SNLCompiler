package com.yvan.ll1GrammarAnalysis;

public enum NES {
    // main program
    PROGRAM,
    // program header
    PROGRAMHEAD, PROGRAMNAME,
    // program declare
    DECLAREPART,
    // type declare
    TYPEDECPART, TYPEDEC, TYPEDECLIST, TYPEDECMORE, TYPEID,
    // type
    TYPEDEF, BASETYPE, STRUCTURETYPE, ARRAYTYPE, LOW, TOP, RECTYPE, FIELDDECLIST, FIELDDECMORE, IDLIST, IDMORE,
    // var declare
    VARDECPART, VARDEC, VARDECLIST, VARDECMORE, VARIDLIST, VARIDMORE,
    // procedure declare
    PROCDECPART, PROCDEC, PROCDECMORE, PROCNAME,
    // param declare
    PARAMLIST, PARAMDECLIST, PARAMMORE, PARAM, FORMLIST, FIDMORE,
    // declare in procedure
    // PROCDECPART
    // procedure body
    PROCBODY,
    // main program body
    PROGRAMBODY,
    // statement array
    STMLIST, STMMORE,
    // statement
    STM,

    ASSCALL,
    // assign statement
    ASSIGNMENTREST,
    // condition statement
    CONDITIONALSTM,
    // loop statement
    LOOPSTM,
    // input statement
    INPUTSTM, INVAR,
    // output statement
    OUTPUTSTM,
    // return statement
    RETURNSTM,
    // procedure invoked statement
    CALLSTMREST, ACTPARAMLIST, ACTPARAMMORE,
    // conditional expression
    RELEXP, OTHERRELE,
    // Expression Evaluator
    EXP, OTHERTERM,
    // term
    TERM, OTHERFACTOR,
    // factor
    FACTOR, VARIABLE, VARIMORE, FIELDVAR, FIELDVARMORE, CMPOP, ADDOP, MULTOP;

}
