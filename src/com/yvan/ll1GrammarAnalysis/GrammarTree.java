package com.yvan.ll1GrammarAnalysis;

import com.yvan.lexicalAnalysis.*;

// process to update the grammar tree 
public class GrammarTree {
    private GrammarTreeStack stack;
    private GrammarTreeStack operandStack;
    private GrammarTreeStack operatorStack;
    private boolean getExpResult;
    private boolean getExpResult2;
    private int expFlag;
    private TreeNode root;

    public GrammarTree(int line) {
	this.stack = new GrammarTreeStack();
	this.operandStack = new GrammarTreeStack();
	this.operatorStack = new GrammarTreeStack();
	this.root = new TreeNode(line);
	this.root.setNodeKind(NK.PROK);
	this.stack.push(this.root);
	this.getExpResult = true;
	this.getExpResult2 = false;
	this.expFlag = 0;
    }

    public GrammarTreeStack getOperandStack() {
	return operandStack;
    }

    public GrammarTreeStack getOperatorStack() {
	return operatorStack;
    }

    public TreeNode getRoot() {
	return root;
    }

    public void setRoot(TreeNode root) {
	this.root = root;
    }

    // compare two operator's PRI
    public int Priosity(TreeNode treeNode) {
	switch (treeNode.getExpAttr().getOp()) {
	case END:
	    return 0;
	case LT:
	    return 1;
	case EQ:
	    return 1;
	case PLUS:
	    return 2;
	case MINUS:
	    return 2;
	case TIMES:
	    return 3;
	case OVER:
	    return 3;
	default:
	    return -1;
	}
    }

    // ProgramHead ::= PROGRAM ProgramName
    public TreeNode process2(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.PHEADK);
	TreeNode temp = this.stack.pop();
	System.out.println("temp's children:" + temp.getChild().size());
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // ProgramName ::= ID
    public TreeNode process3(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.addName(token.getSEM());
	treeNode.setLineNo(token.getLine());
	treeNode.setLineNo(token.getLine());
	return treeNode;
    }

    // TypeDeclaration ::= TYPE TypeDecList
    public TreeNode process7(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.TYPEK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // TypeDecList ::= TypeID= TypeDef;TypeDecMore
    public TreeNode process8(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // TypeDecMore ::= null
    public TreeNode process9() {
	return this.stack.pop();

    }

    // TypeId ::= ID
    public TreeNode process11(Token token) {
	TreeNode treeNode = this.stack.pop();
	String id = token.getSEM();
	treeNode.addName(id);
	treeNode.setLineNo(token.getLine());
	this.stack.push(treeNode);
	return treeNode;
    }

    // TypeDef ::= ID
    public TreeNode process14(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.setKind(NK.IDK);
	treeNode.setTypeName(token.getSEM());
	this.stack.push(treeNode);
	return treeNode;
    }

    // BaseType ::= INTEGER
    public TreeNode process15() {
	TreeNode treeNode = this.stack.pop();
	// if treeNode is an ArrayKind, fill in the childType in Array attribute
	if (treeNode.getKind().equals(NK.ARRAYK))
	    treeNode.getArrayAttr().setChildType(NK.INTEGERK);
	else
	    treeNode.setKind(NK.INTEGERK);
	// if treeNode's name is null, it must be from FieldDecList
	// and need to be push to stack again
	if (treeNode.getName().size() == 0)
	    this.stack.push(treeNode);
	return treeNode;
    }

    // BaseType ::= CHAR
    public TreeNode process16() {
	TreeNode treeNode = this.stack.pop();
	if (treeNode.getKind().equals(NK.ARRAYK))
	    treeNode.getArrayAttr().setChildType(NK.CHARK);
	else
	    treeNode.setKind(NK.CHARK);
	if (treeNode.getName().size() == 0)
	    this.stack.push(treeNode);
	return treeNode;
    }

    // ArrayType ::= ARRAY [low .. top] OF BaseType
    public TreeNode process19() {
	TreeNode treeNode = this.stack.pop();
	treeNode.setKind(NK.ARRAYK);
	treeNode.setArrayAttr(new ArrayAttr());
	this.stack.push(treeNode);
	return treeNode;
    }

    // Low ::= INTC
    public TreeNode process20(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.getArrayAttr().setLow(Integer.parseInt(token.getSEM()));
	this.stack.push(treeNode);
	return treeNode;
    }

    // Top ::= INTC
    public TreeNode process21(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.getArrayAttr().setTop(Integer.parseInt(token.getSEM()));
	this.stack.push(treeNode);
	return treeNode;
    }

    // RecType ::= RECORD FieldDecList END
    public TreeNode process22() {
	TreeNode treeNode = this.stack.pop();
	treeNode.setKind(NK.RECORDK);
	this.stack.push(treeNode);
	return treeNode;
    }

    // FieldDecList ::= BaseType IdList;FieldDecMore
    public TreeNode process23(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // FieldDecList ::= Array Type IdList;FieldDecMore
    public TreeNode process24(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	treeNode.setKind(NK.ARRAYK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // FieldDecMore ::= null
    public TreeNode process25() {
	return this.stack.pop();
    }

    // IdList ::= ID IdMore
    public TreeNode process27(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.addName(token.getSEM());
	this.stack.push(treeNode);
	return treeNode;
    }

    // IdMore ::= null
    public TreeNode process28() {
	return this.stack.pop();
    }

    // VarDeclaration ::= VAR VarDecList
    public TreeNode process32(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.VARK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // VarDecList ::= TypeDef VarIdList;VarDecMore
    public TreeNode process33(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // VarDecMore ::= null
    public TreeNode process34() {
	return this.stack.pop();
    }

    // VarIdList ::= ID VarIdMore
    public TreeNode process36(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.getName().add(token.getSEM());
	this.stack.push(treeNode);
	return treeNode;
    }

    // VarIdMore ::= null
    public TreeNode process37() {
	return this.stack.pop();
    }

    // ProcDeclaration ::= PROCEDURE ProcName(ParamList) ProcDecPart ProcBody
    // ProcDecMore
    public TreeNode process41(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.PROCDECK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // ProcDecMore ::= null
    public TreeNode process42() {
	return this.stack.pop();
    }

    // ProcName ::= ID
    public TreeNode process44(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.addName(token.getSEM());
	this.stack.push(treeNode);
	return treeNode;
    }

    // public TreeNode process45() {
    // TreeNode treeNode = this.stack.pop();
    // return treeNode;
    // }
    // 48 ParamMore ::= null
    // public TreeNode process48() {
    // return this.stack.pop();
    // }

    // Param ::= TypeDef FormList
    public TreeNode process50(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	treeNode.setProcAttr(new ProcAttr(NK.VALPARAMTYPE));
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    public GrammarTreeStack getStack() {
	return stack;
    }

    // Param ::= VAR TypeDef FormList
    public TreeNode process51(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.DECK);
	treeNode.setProcAttr(new ProcAttr(NK.VARPARAMTYPE));
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // FormList ::= ID FidMore
    public TreeNode process52(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.addName(token.getSEM());
	this.stack.push(treeNode);
	return treeNode;
    }

    // FidMore ::= null
    public TreeNode process53() {
	return this.stack.pop();
    }

    // ProgramBody ::= BEGIN StmList END
    public TreeNode process57(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMLK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // StmMore ::= null
    public TreeNode process59() {
	return this.stack.pop();
    }

    // Stm ::= ConditionalStm
    public TreeNode process61(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	treeNode.setKind(NK.IFK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // Stm ::= LoopStm
    public TreeNode process62(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	treeNode.setKind(NK.WHILEK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // Stm ::= InputStm
    public TreeNode process63(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	treeNode.setKind(NK.READK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // Stm ::= OutputStm
    public TreeNode process64(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	treeNode.setKind(NK.WRITEK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // Stm ::= ReturnStm
    public TreeNode process65(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	treeNode.setKind(NK.RETURNK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	this.stack.push(treeNode);
	return treeNode;
    }

    // Stm ::= ID AssCall
    public TreeNode process66(Token token) {
	int line = token.getLine();
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.STMTK);
	TreeNode temp = this.stack.pop();
	temp.getChild().add(treeNode);
	this.stack.push(temp);
	TreeNode treeNode2 = new TreeNode(line);
	treeNode2.setNodeKind(NK.EXPK);
	treeNode2.setKind(NK.IDEK);
	treeNode2.addName(token.getSEM());
	treeNode.getChild().add(treeNode2);
	this.stack.push(treeNode);
	this.operandStack.push(treeNode2);
	return treeNode;
    }

    // AssCall ::= AssignmentRest
    public TreeNode process67() {
	TreeNode treeNode = this.stack.pop();
	treeNode.setKind(NK.ASSIGNK);
	this.stack.push(treeNode);
	return treeNode;
    }

    // AssCall ::= CallStmRest
    public TreeNode process68() {
	TreeNode treeNode = this.stack.pop();
	treeNode.setKind(NK.CALLK);
	this.stack.push(treeNode);
	return treeNode;
    }

    // AssignmentRest ::= VariMore:=Exp
    public TreeNode process69(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(treeNode);
	return treeNode;
    }

    // 70 ConditionalStm ::= IF RelExp THEN StmList ELSE StmList FI
    public TreeNode process70(int line) {
	TreeNode treeNode = this.stack.pop();
	TreeNode child1 = new TreeNode(line);
	child1.setNodeKind(NK.STMLK);
	TreeNode child2 = new TreeNode(line);
	child2.setNodeKind(NK.STMLK);
	TreeNode child3 = new TreeNode(line);
	child3.setNodeKind(NK.STMLK);
	treeNode.getChild().add(child1);
	treeNode.getChild().add(child2);
	treeNode.getChild().add(child3);
	this.stack.push(child3);
	this.stack.push(child2);
	this.stack.push(child1);
	return treeNode;
    }

    // LoopStm ::= WHILE RelExp DO StmList ENDWH
    public TreeNode process71(int line) {
	TreeNode treeNode = this.stack.pop();
	TreeNode child1 = new TreeNode(line);
	child1.setNodeKind(NK.STMLK);
	TreeNode child2 = new TreeNode(line);
	child2.setNodeKind(NK.STMLK);
	treeNode.getChild().add(child1);
	treeNode.getChild().add(child2);
	this.stack.push(child2);
	this.stack.push(child1);

	return treeNode;
    }

    // InVar ::= ID
    public TreeNode process73(Token token) {
	TreeNode treeNode = this.stack.pop();
	treeNode.addName(token.getSEM());
	return treeNode;
    }

    // OutputStm ::= WRITE(Exp)
    public TreeNode process74(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(treeNode);
	return treeNode;
    }

    // ReturnStm ::= RETURN
    public TreeNode process75() {
	return this.stack.pop();
    }

    // 77 ActParamList ::= null
    public TreeNode process77() {
	return this.stack.pop();
    }

    // 78 ActParamList ::= Exp ActParamMore
    public TreeNode process78(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(treeNode);
	return treeNode;
    }

    // 79 ActParamMore ::= null
    // public TreeNode process79() {
    // return this.stack.pop();
    // }
    // 80 ActParamMore ::= , ActParamList
    public TreeNode process80() {
	TreeNode treeNode = this.stack.getTop();
	int position = treeNode.getChild().size() - 1;
	this.stack.push(treeNode.getChild().get(position));
	return treeNode;
    }

    // 81 RelExp ::= Exp OtherRelE
    public TreeNode process81(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(treeNode);
	this.getExpResult = false;
	return treeNode;
    }

    // OtherRelE ::= CmpOp Exp
    public TreeNode process82(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(token.getLEX());
	TreeNode sTop = this.operatorStack.getTop();
	while (this.Priosity(sTop) >= this.Priosity(treeNode)) {
	    TreeNode t = this.operatorStack.pop();
	    TreeNode rNum = this.operandStack.pop();
	    TreeNode lNum = this.operandStack.pop();
	    t.getChild().add(lNum);
	    t.getChild().add(rNum);
	    this.operatorStack.push(t);
	    sTop = this.operandStack.getTop();
	}
	this.operatorStack.push(treeNode);
	this.getExpResult = true;
	return treeNode;
    }

    // 84 OtherTerm ::= null
    public TreeNode process84(Token token) {
	TreeNode treeNode = null;
	if ((token.getLEX() == LEX.RPAREN) && (this.expFlag != 0)) {
	    while (this.operatorStack.getTop().getExpAttr().getOp()
		    .equals(LEX.LPAREN)) {
		TreeNode t = this.operatorStack.pop();
		TreeNode rNum = this.operandStack.pop();
		TreeNode lNum = this.operandStack.pop();
		t.getChild().add(lNum);
		t.getChild().add(rNum);
		this.operandStack.push(t);
	    }
	    this.operatorStack.pop();
	    this.expFlag--;
	} else {
	    if (this.getExpResult || this.getExpResult2) {
		while (this.operatorStack.getTop().getExpAttr().getOp() != LEX.END) {
		    TreeNode t = this.operatorStack.pop();
		    TreeNode rNum = this.operandStack.pop();
		    TreeNode lNum = this.operandStack.pop();
		    t.getChild().add(lNum);
		    t.getChild().add(rNum);
		    this.operandStack.push(t);
		}
		this.operatorStack.pop();
		TreeNode temp = this.stack.pop();
		temp.getChild().add(this.operandStack.pop());
		this.getExpResult2 = false;
	    }
	}
	return treeNode;
    }

    // 85 OtherTerm ::= AddOp Exp
    public TreeNode process85(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(token.getLEX());
	TreeNode sTop = this.operatorStack.getTop();
	while (this.Priosity(sTop) >= this.Priosity(treeNode)) {
	    TreeNode t = this.operatorStack.pop();
	    TreeNode rNum = this.operandStack.pop();
	    TreeNode lNum = this.operandStack.pop();
	    t.getChild().add(lNum);
	    t.getChild().add(rNum);
	    this.operandStack.push(t);
	    sTop = this.operatorStack.getTop();
	}
	this.operatorStack.push(treeNode);
	return treeNode;
    }

    // 88 OtherFactor ::= MultOp Term
    public TreeNode process88(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(token.getLEX());
	TreeNode sTop = this.operatorStack.getTop();
	while (this.Priosity(sTop) >= this.Priosity(treeNode)) {
	    TreeNode t = this.operatorStack.pop();
	    TreeNode rNum = this.operandStack.pop();
	    TreeNode lNum = this.operandStack.pop();
	    t.getChild().add(lNum);
	    t.getChild().add(rNum);
	    this.operandStack.push(t);
	    sTop = this.operatorStack.getTop();
	    this.operatorStack.push(treeNode);
	}
	return treeNode;
    }

    // 89 Factor ::= (Exp)
    public TreeNode process89(int line) {
	TreeNode treeNode = new TreeNode(line);
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.OPK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setOp(LEX.LPAREN);
	this.operatorStack.push(treeNode);
	this.expFlag++;
	return treeNode;
    }

    // 90 Factor ::= INTC
    public TreeNode process90(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.CONSTK);
	treeNode.setExpAttr(new ExpAttr());
	int i = Integer.parseInt(token.getSEM());
	treeNode.getExpAttr().setVal(i);
	this.operandStack.push(treeNode);
	return treeNode;
    }

    // 92 Variable ::= ID VariMore
    public TreeNode process92(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.IDEK);
	treeNode.addName(token.getSEM());
	treeNode.setExpAttr(new ExpAttr());
	this.operandStack.push(treeNode);
	return treeNode;
    }

    // 93 VariMore ::= null
    public TreeNode process93() {
	TreeNode treeNode = this.operandStack.pop();
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setVarkind(NK.IDV);
	this.operandStack.push(treeNode);
	return treeNode;
    }

    // 94 VariMore ::= [Exp]
    public TreeNode process94(int line) {
	TreeNode treeNode = this.operandStack.pop();
	treeNode.setExpAttr(new ExpAttr());
	treeNode.getExpAttr().setVarkind(NK.ARRAYMEMBV);
	this.operandStack.push(treeNode);
	this.stack.push(treeNode);
	TreeNode t = new TreeNode(line);
	t.setNodeKind(NK.EXPK);
	t.setKind(NK.OPK);
	t.setExpAttr(new ExpAttr());
	t.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(t);
	this.getExpResult2 = true;
	return treeNode;
    }

    // 95 VariMore ::= FieldVar
    public TreeNode process95() {
	TreeNode treeNode = this.operandStack.pop();
	treeNode.getExpAttr().setVarkind(NK.FIELDMEMBV);
	this.operandStack.push(treeNode);
	this.stack.push(treeNode);
	return treeNode;
    }

    // 96 FieldVar ::= ID FieldVarMore
    public TreeNode process96(Token token) {
	TreeNode treeNode = new TreeNode(token.getLine());
	treeNode.setNodeKind(NK.EXPK);
	treeNode.setKind(NK.IDEK);
	treeNode.setExpAttr(new ExpAttr());
	treeNode.addName(token.getSEM());
	TreeNode t = this.stack.pop();
	t.getChild().add(treeNode);
	this.operandStack.push(treeNode);
	return treeNode;
    }

    // 97 FieldVarMore ::= null
    public TreeNode process97() {
	TreeNode treeNode = this.operandStack.pop();
	treeNode.getExpAttr().setVarkind(NK.IDV);
	return treeNode;
    }

    // 98 FieldVarMore ::= [Exp]
    public TreeNode process98(int line) {
	TreeNode treeNode = this.operandStack.pop();
	treeNode.getExpAttr().setVarkind(NK.ARRAYMEMBV);
	this.operandStack.push(treeNode);
	TreeNode t = new TreeNode(line);
	t.setNodeKind(NK.EXPK);
	t.setKind(NK.OPK);
	t.setExpAttr(new ExpAttr());
	t.getExpAttr().setOp(LEX.END);
	this.operatorStack.push(t);
	this.getExpResult2 = true;
	return treeNode;
    }

    public TreeNode process(int i, Token token, int line) {
	TreeNode t = null;
	switch (i) {
	case 2:
	    t = process2(line);
	    break;
	case 3:
	    t = process3(token);
	    break;
	case 7:
	    t = process7(line);
	    break;
	case 8:
	    t = process8(line);
	    break;
	case 9:
	    t = process9();
	    break;
	case 11:
	    t = process11(token);
	    break;
	case 14:
	    t = process14(token);
	    break;
	case 15:
	    t = process15();
	    break;
	case 16:
	    t = process16();
	    break;
	case 19:
	    t = process19();
	    break;
	case 20:
	    t = process20(token);
	    break;
	case 21:
	    t = process21(token);
	    break;
	case 22:
	    t = process22();
	    break;
	case 23:
	    t = process23(line);
	    break;
	case 24:
	    t = process24(line);
	    break;
	case 25:
	    t = process25();
	    break;
	case 27:
	    t = process27(token);
	    break;
	case 28:
	    t = process28();
	    break;
	case 32:
	    t = process32(line);
	    break;
	case 33:
	    t = process33(line);
	    break;
	case 34:
	    t = process34();
	    break;
	case 36:
	    t = process36(token);
	    break;
	case 37:
	    t = process37();
	    break;
	case 41:
	    t = process41(line);
	    break;
	case 42:
	    t = process42();
	    break;
	case 44:
	    t = process44(token);
	    break;
	// case 45:t = process45();break;
	case 50:
	    t = process50(line);
	    break;
	case 51:
	    t = process51(line);
	    break;
	case 52:
	    t = process52(token);
	    break;
	case 53:
	    t = process53();
	    break;
	case 57:
	    t = process57(line);
	    break;
	case 59:
	    t = process59();
	    break;
	case 61:
	    t = process61(line);
	    break;
	case 62:
	    t = process62(line);
	    break;
	case 63:
	    t = process63(line);
	    break;
	case 64:
	    t = process64(line);
	    break;
	case 65:
	    t = process65(line);
	    break;
	case 66:
	    t = process66(token);
	    break;
	case 67:
	    t = process67();
	    break;
	case 68:
	    t = process68();
	    break;
	case 69:
	    t = process69(line);
	    break;
	case 70:
	    t = process70(line);
	    break;
	case 71:
	    t = process71(line);
	    break;
	case 73:
	    t = process73(token);
	    break;
	case 74:
	    t = process74(line);
	    break;
	case 75:
	    t = process75();
	    break;
	case 77:
	    t = process77();
	    break;
	case 78:
	    t = process78(line);
	    break;
	// case 79:t = process79();break;
	case 80:
	    t = process80();
	    break;
	case 81:
	    t = process81(line);
	    break;
	case 82:
	    t = process82(token);
	    break;
	case 84:
	    t = process84(token);
	    break;
	case 85:
	    t = process85(token);
	    break;
	case 88:
	    t = process88(token);
	    break;
	case 89:
	    t = process89(line);
	    break;
	case 90:
	    t = process90(token);
	    break;
	case 92:
	    t = process92(token);
	    break;
	case 93:
	    t = process93();
	    break;
	case 94:
	    t = process94(line);
	    break;
	case 95:
	    t = process95();
	    break;
	case 96:
	    t = process96(token);
	    break;
	case 97:
	    t = process97();
	    break;
	case 98:
	    t = process98(line);
	    break;
	default:
	    t = null;
	}
	return t;
    }

    // print grammar tree to console log
    public void Print() {
	System.out.println("Grammar Tree:");
	this.root.Print(0);
    }

    public String toString() {
	String s = "";
	s = this.root.outPut(0);
	return s;
    }

}
