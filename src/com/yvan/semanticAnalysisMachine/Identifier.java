/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto
 * 
 */
public class Identifier {
    private int level; // which level the identifier is
    private IdKind idKind; // what kind the identifier is ( TypeKind, VarKind,
			   // ProcKind)
    private VarAttr varAttr; // variation attribute, available when the idKind
			     // is VarKind
    private ProcAttr procAttr; // procedure attribute, available when the idKind
			       // is ProcKind

    public Identifier() {
	this.level = 0;
    }

    /**
     * @param level
     * @param idKind
     * @param varAttr
     * @param procAttr
     */
    public Identifier(int level, IdKind idKind, VarAttr varAttr,
	    ProcAttr procAttr) {
	super();
	this.level = level;
	this.idKind = idKind;
	this.varAttr = varAttr;
	this.procAttr = procAttr;
    }

    /**
     * @return the level
     */
    public int getLevel() {
	return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
	this.level = level;
    }

    /**
     * @return the idKind
     */
    public IdKind getIdKind() {
	return idKind;
    }

    /**
     * @param idKind
     *            the idKind to set
     */
    public void setIdKind(IdKind idKind) {
	this.idKind = idKind;
    }

    /**
     * @return the varAttr
     */
    public VarAttr getVarAttr() {
	return varAttr;
    }

    /**
     * @param varAttr
     *            the varAttr to set
     */
    public void setVarAttr(VarAttr varAttr) {
	this.varAttr = varAttr;
    }

    /**
     * @return the procAttr
     */
    public ProcAttr getProcAttr() {
	return procAttr;
    }

    /**
     * @param procAttr
     *            the procAttr to set
     */
    public void setProcAttr(ProcAttr procAttr) {
	this.procAttr = procAttr;
    }

}
