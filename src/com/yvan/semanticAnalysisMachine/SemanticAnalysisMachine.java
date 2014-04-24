/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

import com.yvan.ll1GrammarAnalysis.NK;
import com.yvan.ll1GrammarAnalysis.TreeNode;

/**
 * @author YvanLuto
 * 
 */
public class SemanticAnalysisMachine {
    private int currentLevel; // current level of the symbol table
    private Scope scope;
    private TreeNode root;
    private IdKind willCreate; // what kind of identifier will be create
    private ErrorList errorList;

    /**
     * @param root
     */
    public SemanticAnalysisMachine(TreeNode root) {
	this.root = root;
	this.scope = new Scope();
	this.currentLevel = 0;
	this.errorList = new ErrorList();
    }

    /**
     * @return the currentLevel
     */
    public int getCurrentLevel() {
	return currentLevel;
    }

    /**
     * @param currentLevel
     *            the currentLevel to set
     */
    public void setCurrentLevel(int currentLevel) {
	this.currentLevel = currentLevel;
    }

    /**
     * @return the scope
     */
    public Scope getScope() {
	return scope;
    }

    /**
     * @param scope
     *            the scope to set
     */
    public void setScope(Scope scope) {
	this.scope = scope;
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

    /**
     * begin to analysis
     */
    public void analysis() {
	// System.out.println(this.root.toString());
	System.out.println("======================");
	createTable(this.currentLevel);
	analysisNode(root);
	System.out.println(this.errorList.toString());
	System.out.println("Semantic Analysis Finished");

    }

    /**
     * create a new symbolTable and will be push to scope
     * 
     * @param level
     *            the current level
     */
    public void createTable(int level) {
	SymbolTable symbolTable = new SymbolTable(level);
	this.scope.getScope().add(symbolTable);
    }

    /**
     * delete a specific symbolTable and remove from scope
     * 
     * @param level
     */
    public void deleteTable(int level) {
	if (level > 0)
	    this.scope.getScope().remove(level);
    }

    /**
     * @return the errorList
     */
    public ErrorList getErrorList() {
	return errorList;
    }

    /**
     * @param errorList
     *            the errorList to set
     */
    public void setErrorList(ErrorList errorList) {
	this.errorList = errorList;
    }

    /**
     * Analysis a specific tree node recursively
     * 
     * @param treeNode
     */
    public void analysisNode(TreeNode treeNode) {
	// System.out.print(treeNode.onlyMe());
	// System.out.println("IdKind:" + this.willCreate);
	// System.out.println("Scope:");
	// System.out.println(this.scope.toString());
	// analysis current node
	NK nodeKind = treeNode.getNodeKind();
	if (nodeKind == NK.TYPEK || nodeKind == NK.VARK
		|| nodeKind == NK.PROCDECK || nodeKind == NK.STMLK) {
	    switch (nodeKind) {
	    case TYPEK:
		this.willCreate = IdKind.TYPEKIND;
		break;
	    case VARK:
		this.willCreate = IdKind.VARKIND;
		break;
	    case PROCDECK:
		this.willCreate = IdKind.PROCKIND;
		break;
	    default:
		break;
	    }
	    if (nodeKind == NK.PROCDECK) {
		String name = treeNode.getName().get(0);
		if (findName(name, true) != null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(1)));
		    return;
		}
		SymbolNode symbolNode = SymbolNodeCreater.create(treeNode
			.getName().get(0), treeNode, currentLevel,
			IdKind.PROCKIND, 0, errorList);
		this.scope.getTop().push(symbolNode);
		this.currentLevel++;
		if (this.scope.getScope().size() <= this.currentLevel) {
		    this.createTable(currentLevel);
		}
		for (int i = 0; i < treeNode.getChild().size(); i++) {
		    analysisNode(treeNode.getChild().get(i));
		}
		this.scope.pop();
		this.currentLevel--;
	    } else {
		for (int i = 0; i < treeNode.getChild().size(); i++) {
		    analysisNode(treeNode.getChild().get(i));
		}
	    }
	} else if (nodeKind == NK.DECK) {
	    if (this.willCreate == IdKind.PROCKIND)
		this.willCreate = IdKind.VARKIND;
	    for (int i = 0; i < treeNode.getName().size(); i++) {
		String name = treeNode.getName().get(i);
		if (findName(name, false) != null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(1)));
		    continue;
		}
		SymbolNode symbolNode = SymbolNodeCreater.create(name,
			treeNode, this.currentLevel, this.willCreate, 0,
			errorList);
		// push to param list if the symbolNode is a param of procedure
		// at the top of the symbol talble
		// of the scope
		if (symbolNode.getIdentifier().getVarAttr() != null) {
		    if (symbolNode.getIdentifier().getVarAttr().getAccess() == Access.INDIR) {
			SymbolTable st = this.scope
				.getLevelTop(currentLevel - 1);
			int position = st.getSymbolTable().size() - 1;
			SymbolNode sn = st.getSymbolTable().get(position);
			sn.getIdentifier().getProcAttr().getParamList()
				.push(symbolNode);
		    }
		}
		if (treeNode.getTypeName() != null) {
		    String typeName = treeNode.getTypeName();
		    SymbolNode t = findName(typeName, true);
		    if (t.getInRep() != null)
			symbolNode.setInRep(t.getInRep());
		}
		this.scope.getTop().push(symbolNode);
	    }
	    if (treeNode.getKind() == NK.RECORDK)
		for (int i1 = 0; i1 < treeNode.getChild().size(); i1++) {
		    analysisNode(treeNode.getChild().get(i1));
		}
	} else if (treeNode.getNodeKind() == NK.STMTK) {
	    String name;
	    SymbolNode symbolNode;
	    // statements node semantic analysis
	    switch (treeNode.getKind()) {
	    case READK:
		name = treeNode.getName().get(0);
		symbolNode = findName(name, true);
		if (symbolNode == null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(2)));
		    return;
		} else if (symbolNode.getIdentifier().getIdKind() != IdKind.VARKIND) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(3)));
		    return;
		}
		break;
	    case WRITEK:
		this.afterTraversing(treeNode);
		// System.out.println("::::" + treeNode.getChild().get(0));
		// name = treeNode.getChild().get(0).getName().get(0);
		// symbolNode = findName(name,false);
		// if(symbolNode == null) {
		// this.errorList.push(new
		// SemanticError(treeNode.getLineNo(),ErrorType.createError(2)));
		// return;
		// } else if(symbolNode.getIdentifier().getIdKind() !=
		// IdKind.VARKIND) {
		// this.errorList.push(new
		// SemanticError(treeNode.getLineNo(),ErrorType.createError(3)));
		// return;
		// }
		break;
	    case IFK:
		TreeNode t1 = treeNode.getChild().get(0).getChild().get(0);
		if (t1.getKind() != NK.OPK) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(11)));
		    return;
		}
		NK rightType = afterTraversing(t1);
		if (rightType == null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(12)));
		    return;
		}
		TreeNode t2 = treeNode.getChild().get(1);
		analysisNode(t2);
		TreeNode t3 = treeNode.getChild().get(2);
		analysisNode(t3);
		break;
	    case WHILEK:
		TreeNode t0 = treeNode.getChild().get(0).getChild().get(0);
		if (t0.getKind() != NK.OPK) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(11)));
		    return;
		}
		NK type = afterTraversing(t0);
		if (type == null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(12)));
		    return;
		}
		TreeNode t4 = treeNode.getChild().get(1);
		analysisNode(t4);
		break;
	    case ASSIGNK:
		NK assign = afterTraversing(treeNode);
		SymbolNode sn = null;
		if (assign == null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(12)));
		    return;
		}
		if (treeNode.getChild().get(0).getName() != null) {
		    sn = findName(treeNode.getChild().get(0).getName().get(0),
			    true);
		    if (sn == null) {
			return;
		    }
		    if (sn.getIdentifier().getIdKind() != IdKind.VARKIND) {
			this.errorList.push(new SemanticError(treeNode
				.getLineNo(), ErrorType.createError(7)));
			return;
		    }
		}
		break;
	    case CALLK:
		SymbolNode proc = findName(treeNode.getChild().get(0).getName()
			.get(0), true);
		if (proc == null) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(13)));
		    return;
		}
		if (proc.getIdentifier().getIdKind() != IdKind.PROCKIND) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(10)));
		    return;
		}
		int nodechild = treeNode.getChild().size() - 1;
		int paramNum = proc.getIdentifier().getProcAttr()
			.getParamList().getParamList().size();
		if (nodechild != paramNum) {
		    this.errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(9)));
		    return;
		}
		SymbolNode sym;
		TreeNode tre;
		for (int i = 0; i < paramNum; i++) {
		    tre = treeNode.getChild().get(i + 1);
		    sym = proc.getIdentifier().getProcAttr().getParamList()
			    .getParamList().get(i);
		    if (tre.getExpAttr().getType() == NK.CONSTK) {
			if (sym.getInRep().getTypeKind() != TypeKind.INTTY) {
			    this.errorList.push(new SemanticError(treeNode
				    .getLineNo(), ErrorType.createError(8)));
			    return;
			}
		    } else if (tre.getExpAttr().getVarkind() == NK.IDV) {
			if (findName(tre.getName().get(0), true) == null) {
			    this.errorList.push(new SemanticError(treeNode
				    .getLineNo(), ErrorType.createError(2)));
			    return;
			}
			SymbolNode s = findName(tre.getName().get(0), true);
			if (!s.getInRep().getTypeKind()
				.equals(sym.getInRep().getTypeKind())) {
			    this.errorList.push(new SemanticError(treeNode
				    .getLineNo(), ErrorType.createError(8)));
			    return;
			}
		    }
		}
		break;
	    default:
		break;
	    }
	} else {
	    for (int i = 0; i < treeNode.getChild().size(); i++) {
		analysisNode(treeNode.getChild().get(i));
	    }
	}
    }

    /**
     * Add a symbol node to a symbol table.
     * 
     * @param symbolNode
     * @param symbolTable
     */
    public void add(SymbolNode symbolNode, SymbolTable symbolTable) {
	symbolTable.getSymbolTable().add(symbolNode);
    }

    /**
     * Find whether there is a symbol node which has the same name.
     * 
     * @param name
     *            match the name in the symbol table
     * @param flag
     *            if the flag is true then search the all symbol tables in the
     *            scope, or only search in the top symbol table
     * @return ifFind if found, return true, or return false
     */
    public SymbolNode findName(String name, boolean flag) {
	SymbolNode ifFind = null;
	SymbolTable top = this.scope.getTop();
	if (!flag) {
	    ifFind = searchOne(name, top);
	} else {
	    for (int i = this.scope.getScope().size() - 1; i >= 0; i--) {
		ifFind = searchOne(name, this.scope.getLevelTop(i));
		if (ifFind != null)
		    break;
	    }
	}
	return ifFind;
    }

    /**
     * Find whether there is a symbol node which has a same name in the specific
     * symbol table.
     * 
     * @param name
     *            the name to be matched
     * @param symbolTable
     *            the symbol table searched
     * @return if found, return true, or return false
     */
    public SymbolNode searchOne(String name, SymbolTable symbolTable) {
	if (symbolTable.getSymbolTable() != null)
	    for (int i = 0; i < symbolTable.getSymbolTable().size(); i++) {
		if (symbolTable.getSymbolTable().get(i).getName().equals(name))
		    return symbolTable.getSymbolTable().get(i);
	    }
	return null;
    }

    public NK afterTraversing(TreeNode treeNode) {
	TreeNode child0 = null, child1 = null;
	SymbolNode sn = null;
	// no child
	if (treeNode.getChild().size() == 0) {
	    if (treeNode.getKind() == NK.CONSTK)
		return NK.INTEGERK;
	    if (treeNode.getExpAttr() != null) {
		if (treeNode.getExpAttr().getVarkind() == NK.ARRAYMEMBV) {
		    sn = findName(treeNode.getName().get(0), true);
		    if (sn == null) {
			this.errorList.push(new SemanticError(treeNode
				.getLineNo(), ErrorType.createError(2)));
			return NK.NULL;
		    }
		    if (sn.getInRep() != null)
			if (sn.getInRep().getArrayAttr().getElemTy() != null)
			    return Transform.TKtoNK(sn.getInRep()
				    .getArrayAttr().getElemTy().getTypeKind());
		}
	    }
	    sn = findName(treeNode.getName().get(0), true);
	    if (sn == null) {
		this.errorList.push(new SemanticError(treeNode.getLineNo(),
			ErrorType.createError(2)));
		return NK.NULL;
	    }
	    NK kind = Transform.TKtoNK(sn.getInRep().getTypeKind());
	    return kind;
	}

	// get left child
	NK child0Type = null, child1Type = null;
	if (treeNode.getChild().size() != 0) {
	    child0 = treeNode.getChild().get(0);
	    child0Type = this.afterTraversing(child0);
	    if (treeNode.getChild().size() == 2) {
		child1 = treeNode.getChild().get(1);
		child1Type = this.afterTraversing(child1);
	    }
	}

	if (child0Type == null) {
	    return child0Type;
	}
	if (child1Type == null) {
	    return child0Type;
	}
	if (child0Type.equals(child1Type))
	    return child0Type;
	else
	    return NK.NULL;
    }
}
