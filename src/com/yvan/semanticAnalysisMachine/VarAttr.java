/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto
 * 
 */
public class VarAttr {
    private Access access; // access = (DIR, INDIR)
    private int off; // the offset the variation is in the program

    /**
     * @param access
     * @param off
     */
    public VarAttr(Access access, int off) {
	this.access = access;
	this.off = off;
    }

    /**
     * @return the access
     */
    public Access getAccess() {
	return access;
    }

    /**
     * @param access
     *            the access to set
     */
    public void setAccess(Access access) {
	this.access = access;
    }

    /**
     * @return the off
     */
    public int getOff() {
	return off;
    }

    /**
     * @param off
     *            the off to set
     */
    public void setOff(int off) {
	this.off = off;
    }

}
