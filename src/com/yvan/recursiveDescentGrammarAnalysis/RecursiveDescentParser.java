package com.yvan.recursiveDescentGrammarAnalysis;

import com.yvan.lexicalAnalysis.*;
import com.yvan.ll1GrammarAnalysis.*;

public class RecursiveDescentParser {
    private TreeNode root;
    private TokenInputStream inputStream;
    private Token current;
    private String temp;
    private String error;

    public RecursiveDescentParser(TokenList tokenList) {
	this.setRoot(null);
	this.inputStream = new TokenInputStream(tokenList.copy());
	this.current = null;
    }

    public void analysis() throws MatchException {
	this.current = this.inputStream.getHeadToken();
	// System.out.println(this.current.getLEX());
	// System.out.println(this.current.getLine());
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	try {
	    this.setRoot(parse());
	    System.out.println("===================");
	    print(this.root, 0);
	    this.transformTree(this.root);
	} catch (MatchException e) {
	    System.out.println(this.current);
	    throw e;
	} finally {
	    System.out.println(this.current);
	    try {
		System.out.println("===================\nGrammarTree:");
		this.root.Print(0);
	    } catch (Exception e) {

	    }
	}
    }

    public TreeNode parse() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "parse");
	TreeNode t = null;
	t = program();
	if (this.current.getLEX() != LEX.EOF)
	    System.out.println("code ends before file\n");
	return t;
    }

    public TreeNode program() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "program");
	TreeNode t1 = programHead();
	TreeNode t2 = declarePart();
	TreeNode t3 = programBody();
	TreeNode root = new TreeNode(1);
	root.setNodeKind(NK.PROK);
	root.getChild().add(t1);
	root.getChild().add(t2);
	root.getChild().add(t3);
	match(LEX.DOT);
	System.out.println("rootchild0:" + root.getChild().get(1).onlyMe());
	return root;
    }

    public TreeNode programHead() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "programHead");
	TreeNode treeNode = new TreeNode(this.current.getLine());
	treeNode.setNodeKind(NK.PHEADK);
	match(LEX.PROGRAM);
	if (treeNode != null && this.current.getLEX() == LEX.ID)
	    treeNode.getName().add(this.current.getSEM());
	match(LEX.ID);
	return treeNode;
    }

    public TreeNode declarePart() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "declarePart");
	TreeNode typeP;
	typeP = new TreeNode(this.current.getLine());
	typeP.setNodeKind(NK.TYPEK);
	TreeNode tp1 = typeDec();
	if (tp1 != null)
	    typeP.getChild().add(tp1);
	TreeNode varP = new TreeNode(this.current.getLine());
	varP.setNodeKind(NK.VARK);
	TreeNode tp2 = varDec();
	if (tp2 != null)
	    varP.getChild().add(tp2);
	TreeNode s = procDec();
	int i = 0, j = 0, k = 0;
	TreeNode head = null;
	if (typeP.getChild().size() != 0)
	    i = 1;
	if (varP.getChild().size() != 0)
	    j = 2;
	if (s != null)
	    k = 4;
	switch (i + j + k) {
	case 0:
	    break;
	case 1:
	    head = typeP;
	    break;
	case 2:
	    head = varP;
	    break;
	case 3:
	    head = typeP;
	    typeP.setSibling(varP);
	    break;
	case 4:
	    head = s;
	    break;
	case 5:
	    head = typeP;
	    typeP.setSibling(s);
	    break;
	case 6:
	    head = varP;
	    varP.setSibling(s);
	    break;
	case 7:
	    head = typeP;
	    typeP.setSibling(varP);
	    varP.setSibling(s);
	    break;
	default:
	    break;
	}
	return head;
    }

    public TreeNode typeDec() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeDec");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case TYPE:
	    t = typeDeclaration();
	    break;
	case VAR:
	case PROCEDURE:
	case BEGIN:
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
	return t;
    }

    public TreeNode typeDeclaration() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeDeclaration");
	match(LEX.TYPE);
	TreeNode t = typeDecList();
	if (t == null)
	    this.error = "a type decalaration is expected";
	return t;
    }

    public TreeNode typeDecList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeDecList");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.DECK);
	typeId(t);
	match(LEX.EQ);
	typeDef(t);
	match(LEX.SEMI);
	TreeNode p = typeDecMore();
	if (p != null)
	    t.setSibling(p);
	return t;

    }

    public TreeNode typeDecMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeDecMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case VAR:
	case PROCEDURE:
	case BEGIN:
	    break;
	case ID:
	    t = typeDecList();
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
	return t;
    }

    public void typeId(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeId");
	if (this.current.getLEX() == LEX.ID && t != null)
	    t.getName().add(this.current.getSEM());
	match(LEX.ID);
    }

    public void typeDef(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "typeDef");
	if (t == null)
	    return;
	switch (this.current.getLEX()) {
	case INTEGER:
	case CHAR:
	    baseType(t);
	    break;
	case ARRAY:
	case RECORD:
	    structureType(t);
	    break;
	case ID:
	    t.setKind(NK.IDK);
	    t.setTypeName(this.current.getSEM());
	    match(LEX.ID);
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
    }

    public void baseType(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "baseType");
	switch (this.current.getLEX()) {
	case INTEGER:
	    t.setKind(NK.INTEGERK);
	    match(LEX.INTEGER);
	    break;
	case CHAR:
	    t.setKind(NK.CHARK);
	    match(LEX.CHAR);
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
    }

    public void structureType(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "structureType");
	switch (this.current.getLEX()) {
	case ARRAY:
	    arrayType(t);
	    break;
	case RECORD:
	    t.setKind(NK.RECORDK);
	    recType(t);
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
    }

    public void arrayType(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "arrayType");
	match(LEX.ARRAY);
	match(LEX.LMIDPAREN);
	if (this.current.getLEX() == LEX.INTC) {
	    t.setArrayAttr(new ArrayAttr());
	    t.getArrayAttr().setLow(Integer.parseInt(this.current.getSEM()));
	}
	match(LEX.INTC);
	match(LEX.UNDERANGE);
	if (this.current.getLEX() == LEX.INTC) {
	    t.getArrayAttr().setTop(Integer.parseInt(this.current.getSEM()));
	}
	match(LEX.INTC);
	match(LEX.RMIDPAREN);
	match(LEX.OF);
	baseType(t);
	t.getArrayAttr().setChildType(t.getKind());
	t.setKind(NK.ARRAYK);
    }

    public void recType(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "recType");
	match(LEX.RECORD);
	TreeNode p = fieldDecList();
	if (p != null)
	    t.getChild().add(p);
	else
	    this.error = "a record body is requested";
	match(LEX.END);
    }

    public TreeNode fieldDecList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "fieldDecList");
	TreeNode p = null;
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.DECK);
	if (t != null) {
	    switch (this.current.getLEX()) {
	    case INTEGER:
	    case CHAR:
		baseType(t);
		idList(t);
		match(LEX.SEMI);
		p = fieldDecMore();
		break;
	    case ARRAY:
		arrayType(t);
		idList(t);
		match(LEX.SEMI);
		p = fieldDecMore();
		break;
	    default:
		readNext();
		System.out.println("unexpected token is here");
		break;
	    }
	    t.setSibling(p);
	}
	return t;
    }

    public TreeNode fieldDecMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "fieldDecMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case END:
	    break;
	case INTEGER:
	case CHAR:
	case ARRAY:
	    t = fieldDecList();
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
	return t;
    }

    public void idList(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "idList");
	if (this.current.getLEX() == LEX.ID) {
	    t.getName().add(this.current.getSEM());
	}
	match(LEX.ID);
	idMore(t);
    }

    public void idMore(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "idMore");
	switch (this.current.getLEX()) {
	case SEMI:
	    break;
	case COMMA:
	    match(LEX.COMMA);
	    idList(t);
	    break;
	default:
	    readNext();
	    System.out.println("unexpected token is here");
	    break;
	}
    }

    public TreeNode varDec() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varDec");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case PROCEDURE:
	case BEGIN:
	    break;
	case VAR:
	    t = varDeclaration();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode varDeclaration() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varDeclaration");
	match(LEX.VAR);
	TreeNode t = varDecList();
	if (t == null)
	    this.error = "a var declaration is expected";
	return t;
    }

    public TreeNode varDecList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varDecList");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.DECK);
	if (t != null) {
	    typeDef(t);
	    varIdList(t);
	    match(LEX.SEMI);
	    t.setSibling(varDecMore());
	}
	return t;
    }

    public TreeNode varDecMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varDecMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case PROCEDURE:
	case BEGIN:
	    break;
	case INTEGER:
	case CHAR:
	case ARRAY:
	case RECORD:
	case ID:
	    t = varDecList();
	    break;
	default:
	    readNext();
	}
	return t;
    }

    public void varIdList(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varIdList");
	if (this.current.getLEX() == LEX.ID) {
	    t.getName().add(this.current.getSEM());
	    match(LEX.ID);
	} else {
	    readNext();
	    this.error = "a varid is expected here!";
	}
	varIdMore(t);
    }

    public void varIdMore(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "varIdMore");
	switch (this.current.getLEX()) {
	case SEMI:
	    break;
	case COMMA:
	    match(LEX.COMMA);
	    varIdList(t);
	    break;
	default:
	    readNext();
	    break;
	}
    }

    public TreeNode procDec() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "procDec");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case BEGIN:
	    break;
	case PROCEDURE:
	    t = procDeclaration();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode procDeclaration() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "procDeclaration");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.PROCDECK);
	match(LEX.PROCEDURE);
	if (t != null) {
	    if (this.current.getLEX() == LEX.ID) {
		t.getName().add(this.current.getSEM());
		match(LEX.ID);
	    }
	    match(LEX.LPAREN);
	    paramList(t);
	    match(LEX.RPAREN);
	    match(LEX.SEMI);
	    TreeNode procDecPart = procDecPart();
	    if (procDecPart != null)
		t.getChild().add(procDecPart);
	    TreeNode procBody = procBody();
	    if (procBody != null)
		t.getChild().add(procBody);
	    t.setSibling(procDec());
	}
	return t;
    }

    public void paramList(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "paramList");
	TreeNode p = null;
	switch (this.current.getLEX()) {
	case RPAREN:
	    break;
	case INTEGER:
	case CHAR:
	case RECORD:
	case ARRAY:
	case ID:
	case VAR:
	    p = paramDecList();
	    t.getChild().add(p);
	    break;
	default:
	    readNext();
	}
    }

    public TreeNode paramDecList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "paramDecList");
	TreeNode t = param();
	TreeNode p = paramMore();
	if (p != null)
	    t.setSibling(p);
	return t;
    }

    public TreeNode paramMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "paramMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case RPAREN:
	    break;
	case SEMI:
	    match(LEX.SEMI);
	    t = paramDecList();
	    if (t == null)
		this.error = "a param declaration is request";
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode param() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "param");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.DECK);
	if (t != null) {
	    switch (this.current.getLEX()) {
	    case INTEGER:
	    case CHAR:
	    case RECORD:
	    case ARRAY:
	    case ID:
		t.setProcAttr(new ProcAttr(NK.VALPARAMTYPE));
		typeDef(t);
		formList(t);
		break;
	    case VAR:
		match(LEX.VAR);
		t.setProcAttr(new ProcAttr(NK.VARPARAMTYPE));
		typeDef(t);
		formList(t);
		break;
	    default:
		readNext();
		break;
	    }
	}
	return t;
    }

    public void formList(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "formList");
	if (this.current.getLEX() == LEX.ID) {
	    t.getName().add(this.current.getSEM());
	    match(LEX.ID);
	}
	fidMore(t);
    }

    public void fidMore(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "fidMore");
	switch (this.current.getLEX()) {
	case SEMI:
	case RPAREN:
	    break;
	case COMMA:
	    match(LEX.COMMA);
	    formList(t);
	    break;
	default:
	    readNext();
	    break;
	}
    }

    public TreeNode procDecPart() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "procDecPart");
	return declarePart();
    }

    public TreeNode procBody() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "procBody");
	TreeNode t = programBody();
	if (t == null)
	    this.error = "a program body is request";
	return t;
    }

    public TreeNode programBody() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "programBody");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMLK);
	match(LEX.BEGIN);
	if (t != null)
	    t.getChild().add(stmList());
	match(LEX.END);
	return t;
    }

    public TreeNode stmList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "stmList");
	TreeNode t = stm();
	TreeNode p = stmMore();
	if (p != null)
	    t.setSibling(p);
	return t;
    }

    public TreeNode stmMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "stmMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case END:
	case FI:
	case ELSE:
	case ENDWH:
	    break;
	case SEMI:
	    match(LEX.SEMI);
	    t = stmList();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode stm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "stm");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case IF:
	    t = conditionalStm();
	    break;
	case WHILE:
	    t = loopStm();
	    break;
	case RETURN:
	    t = returnStm();
	    break;
	case READ:
	    t = inputStm();
	    break;
	case WRITE:
	    t = outputStm();
	    break;
	case ID:
	    this.temp = this.current.getSEM();
	    match(LEX.ID);
	    t = assCall();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode assCall() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "asscall");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case LMIDPAREN:
	case DOT:
	case ASSIGN:
	    t = assignmentRest();
	    break;
	case LPAREN:
	    t = callStmRest();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    // ///////////////////////////////////////////////
    public TreeNode assignmentRest() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "assignmentRest");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.ASSIGNK);
	if (t != null) {
	    TreeNode child1 = new TreeNode(this.current.getLine());
	    child1.setNodeKind(NK.EXPK);
	    child1.setKind(NK.IDEK);
	    child1.addName(temp);
	    child1.setExpAttr(new ExpAttr());
	    child1.getExpAttr().setVarkind(NK.IDV);
	    variMore(child1);
	    t.getChild().add(child1);
	}
	match(LEX.ASSIGN);
	t.getChild().add(exp());
	return t;
    }

    public TreeNode conditionalStm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "conditionalStm");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.IFK);
	match(LEX.IF);
	if (t != null) {
	    TreeNode tn1 = new TreeNode(this.current.getLine());
	    tn1.setNodeKind(NK.STMLK);
	    t.getChild().add(tn1);
	    tn1.getChild().add(exp());
	}
	match(LEX.THEN);
	if (t != null) {
	    TreeNode tn2 = new TreeNode(this.current.getLine());
	    tn2.setNodeKind(NK.STMLK);
	    t.getChild().add(tn2);
	    tn2.getChild().add(stmList());
	}
	if (this.current.getLEX() == LEX.ELSE) {
	    match(LEX.ELSE);
	    if (t != null) {
		TreeNode tn3 = new TreeNode(this.current.getLine());
		tn3.setNodeKind(NK.STMLK);
		t.getChild().add(tn3);
		tn3.getChild().add(stmList());
	    }
	}
	match(LEX.FI);
	return t;
    }

    public TreeNode loopStm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "loopStm");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.WHILEK);
	match(LEX.WHILE);
	if (t != null) {
	    TreeNode tn = new TreeNode(this.current.getLine());
	    tn.setNodeKind(NK.STMLK);
	    t.getChild().add(tn);
	    tn.getChild().add(exp());
	    match(LEX.DO);
	    TreeNode tn2 = new TreeNode(this.current.getLine());
	    tn2.setNodeKind(NK.STMLK);
	    t.getChild().add(tn2);
	    tn2.getChild().add(stmList());
	    match(LEX.ENDWH);
	}
	return t;
    }

    public TreeNode inputStm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "inputStm");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.READK);
	match(LEX.READ);
	match(LEX.LPAREN);
	if (t != null && this.current.getLEX() == LEX.ID) {
	    t.getName().add(this.current.getSEM());
	}
	match(LEX.ID);
	match(LEX.RPAREN);
	return t;
    }

    public TreeNode outputStm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "outputStm");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.WRITEK);
	match(LEX.WRITE);
	match(LEX.LPAREN);
	if (t != null)
	    t.getChild().add(exp());
	match(LEX.RPAREN);
	return t;
    }

    public TreeNode returnStm() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "returnStm");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.RETURNK);
	match(LEX.RETURN);
	return t;
    }

    public TreeNode callStmRest() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "callStmRest");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.STMTK);
	t.setKind(NK.CALLK);
	match(LEX.LPAREN);
	if (t != null) {
	    TreeNode child0 = new TreeNode(this.current.getLine());
	    child0.setNodeKind(NK.EXPK);
	    child0.setKind(NK.IDEK);
	    child0.getName().add(temp);
	    if (child0 != null)
		t.getChild().add(child0);
	}
	TreeNode p = actParamList();
	if (p != null)
	    t.getChild().add(p);
	match(LEX.RPAREN);
	return t;
    }

    public TreeNode actParamList() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "actParamList");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case RPAREN:
	    break;
	case ID:
	case INTC:
	    t = exp();
	    if (t != null)
		t.setSibling(actParamMore());
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode actParamMore() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "actParamMore");
	TreeNode t = null;
	switch (this.current.getLEX()) {
	case RPAREN:
	    break;
	case COMMA:
	    match(LEX.COMMA);
	    t = actParamList();
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode exp() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "exp");
	TreeNode t = simple_exp();
	if (this.current.getLEX() == LEX.LT || this.current.getLEX() == LEX.EQ) {
	    TreeNode p = new TreeNode(this.current.getLine());
	    p.setNodeKind(NK.EXPK);
	    p.setKind(NK.OPK);
	    p.getChild().add(t);
	    p.setExpAttr(new ExpAttr());
	    p.getExpAttr().setOp(this.current.getLEX());
	    t = p;
	    match(this.current.getLEX());
	    t.getChild().add(simple_exp());
	}
	return t;
    }

    public TreeNode simple_exp() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "simple_exp");
	TreeNode t = term();
	LEX lex = this.current.getLEX();
	while ((lex == LEX.PLUS) || (lex == LEX.MINUS)) {
	    TreeNode p = new TreeNode(this.current.getLine());
	    p.setNodeKind(NK.EXPK);
	    p.getChild().add(t);
	    p.setExpAttr(new ExpAttr());
	    p.getExpAttr().setOp(lex);
	    t = p;
	    // System.out.println("lex:"+lex);
	    // System.out.println("current:" + this.current.getLEX());
	    match(lex);
	    t.getChild().add(term());
	    lex = this.current.getLEX();
	}
	return t;
    }

    public TreeNode term() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "term");
	TreeNode t = factor();
	LEX lex = this.current.getLEX();
	while ((lex == LEX.TIMES) || (lex == LEX.OVER)) {
	    TreeNode p = new TreeNode(this.current.getLine());
	    p.setNodeKind(NK.EXPK);
	    p.getChild().add(t);
	    p.setExpAttr(new ExpAttr());
	    p.getExpAttr().setOp(lex);
	    t = p;
	    match(lex);
	    p.getChild().add(factor());
	    lex = this.current.getLEX();
	}
	return t;
    }

    public TreeNode factor() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "factor");
	TreeNode t = null;
	// System.out.println(this.inputStream.getHeadToken());
	// System.out.println(this.current);
	switch (this.current.getLEX()) {
	case INTC:
	    t = new TreeNode(this.current.getLine());
	    t.setNodeKind(NK.EXPK);
	    t.setKind(NK.CONSTK);
	    t.setExpAttr(new ExpAttr());
	    t.getExpAttr().setVal(Integer.parseInt(this.current.getSEM()));
	    match(LEX.INTC);
	    break;
	case ID:
	    t = variable();
	    break;
	case LPAREN:
	    match(LEX.LPAREN);
	    t = exp();
	    match(LEX.RPAREN);
	    break;
	default:
	    readNext();
	    break;
	}
	return t;
    }

    public TreeNode variable() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "variable");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.EXPK);
	t.setKind(NK.IDEK);
	t.setExpAttr(new ExpAttr());
	t.getExpAttr().setVarkind(NK.IDV);
	if (this.current.getLEX() == LEX.ID)
	    t.addName(this.current.getSEM());
	match(LEX.ID);
	variMore(t);
	return t;
    }

    public void variMore(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "variMore");
	switch (this.current.getLEX()) {
	case ASSIGN:
	case TIMES:
	case EQ:
	case LT:
	case PLUS:
	case MINUS:
	case OVER:
	case RPAREN:
	case RMIDPAREN:
	case SEMI:
	case COMMA:
	case THEN:
	case ELSE:
	case FI:
	case DO:
	case ENDWH:
	case END:
	    break;
	case LMIDPAREN:
	    match(LEX.LMIDPAREN);
	    t.getChild().add(exp());
	    t.setExpAttr(new ExpAttr());
	    t.getExpAttr().setVarkind(NK.ARRAYMEMBV);
	    t.getChild().get(0).getExpAttr().setVarkind(NK.IDV);
	    match(LEX.RMIDPAREN);
	    break;
	case DOT:
	    match(LEX.DOT);
	    t.getChild().add(fieldvar());
	    t.getExpAttr().setVarkind(NK.FIELDMEMBV);
	    t.getChild().get(0).setExpAttr(new ExpAttr());
	    t.getChild().get(0).getExpAttr().setVarkind(NK.IDV);
	    break;
	default:
	    readNext();
	    break;
	}
    }

    public TreeNode fieldvar() throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "fieldVar");
	TreeNode t = new TreeNode(this.current.getLine());
	t.setNodeKind(NK.EXPK);
	t.setKind(NK.IDEK);
	if (this.current.getLEX() == LEX.ID)
	    t.addName(this.current.getSEM());
	match(LEX.ID);
	fieldvarMore(t);
	return t;
    }

    public void fieldvarMore(TreeNode t) throws MatchException {
	System.out.println("=============================================");
	System.out.println("HeadToken:" + this.inputStream.getHeadToken());
	System.out.println("Current Method:" + "fieldVarMore");
	switch (this.current.getLEX()) {
	case ASSIGN:
	case TIMES:
	case EQ:
	case LT:
	case PLUS:
	case MINUS:
	case OVER:
	case RPAREN:
	case SEMI:
	case COMMA:
	case THEN:
	case ELSE:
	case FI:
	case DO:
	case ENDWH:
	case END:
	    break;
	case LMIDPAREN:
	    match(LEX.LMIDPAREN);
	    t.getChild().add(exp());
	    t.getChild().get(0).getExpAttr().setVarkind(NK.ARRAYMEMBV);
	    match(LEX.RMIDPAREN);
	    break;
	default:
	    readNext();
	    break;
	}
    }

    public boolean match(LEX lex) throws MatchException {
	if (this.current.getLEX().equals(lex)) {
	    readNext();
	    return true;
	}
	throw new MatchException("can not match \'" + lex + "\'\n");
    }

    public void readNext() {
	this.inputStream.removeHead();
	this.current = this.inputStream.getHeadToken();
    }

    /**
     * @return the error
     */
    public String getError() {
	return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
	this.error = error;
    }

    /**
     * @return the root
     */
    public TreeNode getRoot() {
	return root;
    }

    /**
     * @param root
     *            the root to set
     */
    public void setRoot(TreeNode root) {
	this.root = root;
    }

    public void transformTree(TreeNode treeNode) {
	// if(treeNode == null)
	// return;
	System.out.print(treeNode.onlyMe());
	System.out.println("childNum:" + treeNode.getChild().size());
	System.out.println("sibling :" + !(treeNode.getSibling() == null));
	// treeNode.printMe();
	int i = 0;
	if (treeNode.getChild().size() > 0)
	    for (i = 0; i < treeNode.getChild().size(); i++) {
		TreeNode t = treeNode.getChild().get(i);
		// if(t.getKind() == NK.IFK) {
		// for(int j =0;j<t.getChild().size();j++) {
		// TreeNode child = t.getChild().get(i);
		// TreeNode tn = new TreeNode(child.getLineNo());
		// tn.getChild().add(child);
		// t.getChild().remove(i);
		// t.getChild().add(i, tn);
		// }
		// continue;
		// }
		transformTree(t);
		if (t.getSibling() != null)
		    t = t.getSibling();
		else
		    continue;
		while (t != null) {
		    transformTree(t);
		    i++;
		    treeNode.getChild().add(i, t);
		    t = t.getSibling();
		}
		// i++;
	    }
    }

    public void print(TreeNode root, int j) {
	if (root == null)
	    return;
	for (int i = 0; i < j; i++)
	    System.out.print("  ");
	System.out.print(root.onlyMe());

	for (int i = 0; i < root.getChild().size(); i++) {
	    // System.out.print("son:");
	    print(root.getChild().get(i), j + 1);
	}

	if (root.getSibling() != null) {
	    // System.out.print("bro:");
	    print(root.getSibling(), j);
	}
    }
}
