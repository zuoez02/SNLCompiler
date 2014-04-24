/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto
 * 
 */
public class SymbolNode {
    private String name;
    private Identifier identifier;
    private InterRep inRep;

    /**
     * 
     */
    public SymbolNode() {

    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the identifier
     */
    public Identifier getIdentifier() {
	return identifier;
    }

    /**
     * @param identifier
     *            the identifier to set
     */
    public void setIdentifier(Identifier identifier) {
	this.identifier = identifier;
    }

    /**
     * @return the inRep
     */
    public InterRep getInRep() {
	return inRep;
    }

    /**
     * @param inRep
     *            the inRep to set
     */
    public void setInRep(InterRep inRep) {
	this.inRep = inRep;
    }

}
