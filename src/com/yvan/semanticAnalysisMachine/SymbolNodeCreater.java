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
public class SymbolNodeCreater {
    /**
     * Create a symbol node
     * 
     * @param name
     *            the name of the symbolUnit
     * @param treeNode
     *            treeNode provides attributes needed
     * @param level
     *            the level of identifier
     * @return sn
     */
    public static SymbolNode create(String name, TreeNode treeNode, int level,
	    IdKind idKind, int off, ErrorList errorList) {
	SymbolNode sn = new SymbolNode();
	sn.setName(name); // set the symbol node name
	Identifier identifier = new Identifier();
	identifier.setLevel(level);
	identifier.setIdKind(idKind);
	if (idKind == IdKind.VARKIND) { // VARKIND identifier has varAttr
	    Access access;
	    if (treeNode.getProcAttr() != null)
		access = Access.INDIR;
	    else
		access = Access.DIR;
	    identifier.setVarAttr(new VarAttr(access, off));
	} else if (idKind == IdKind.PROCKIND) { // PROCKIND identifier has
						// procAttr
	    identifier.setProcAttr(new ProcAttr());
	}
	sn.setIdentifier(identifier);
	InterRep ir = null;
	// TYPEKIND or VARKIND has internal representation
	if (idKind == IdKind.TYPEKIND) {
	    ir = new InterRep();
	    if (treeNode.getKind() == NK.INTEGERK)
		ir.setTypeKind(TypeKind.INTTY);
	    else
		ir.setTypeKind(TypeKind.CHARTY);
	    ir.setSize(1);
	    sn.setInRep(ir);
	} else if (idKind == IdKind.VARKIND) {
	    ir = new InterRep();
	    switch (treeNode.getKind()) {
	    case INTEGERK:
		ir.setTypeKind(TypeKind.INTTY);
		ir.setSize(1);
		break;
	    case CHARK:
		ir.setTypeKind(TypeKind.CHARTY);
		ir.setSize(1);
		break;
	    case ARRAYK:
		ir.setTypeKind(TypeKind.ARRAYTY);
		int low = treeNode.getArrayAttr().getLow();
		int top = treeNode.getArrayAttr().getTop();
		if (top < low) {
		    errorList.push(new SemanticError(treeNode.getLineNo(),
			    ErrorType.createError(4)));
		    return null;
		}
		ir.setArrayAttr(new ArrayAttr());
		ir.getArrayAttr().setIndexTy(new InterRep(TypeKind.INTTY));
		switch (treeNode.getArrayAttr().getChildType()) {
		case INTEGERK:
		    ir.getArrayAttr().setElemTy(new InterRep(TypeKind.INTTY));
		    break;
		case CHARK:
		    ir.getArrayAttr().setElemTy(new InterRep(TypeKind.CHARTY));
		    break;
		default:
		    break;
		}
		ir.setSize(ir.getArrayAttr().getElemTy().getSize()
			* (top - low));
		break;
	    case RECORDK:
		ir.setTypeKind(TypeKind.RECORDTY);
		ir.setFieldChain(new FieldChain());
		break;
	    default:
		break;
	    }
	    sn.setInRep(ir);
	}

	return sn;
    }
}
