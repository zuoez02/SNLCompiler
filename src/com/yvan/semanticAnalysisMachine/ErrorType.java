/**
 * 
 */
package com.yvan.semanticAnalysisMachine;

/**
 * @author YvanLuto make string about error information
 */
public class ErrorType {
    public static String createError(int i) {
	switch (i) {
	case 1:
	    return new String("Error( 1):\t" + "Identifier declared repeatedly");
	case 2:
	    return new String("Error( 2):\t" + "Identifier didn't declared");
	case 3:
	    return new String("Error( 3):\t"
		    + "Identifier's type kind is not expected");
	case 4:
	    return new String("Error( 4):\t" + "Array index out of bounds");
	case 5:
	    return new String(
		    "Error( 5):\t"
			    + "Array member variable or field member variable quote illegal");
	case 6:
	    return new String(
		    "Error( 6):\t"
			    + "Incompatible types on either side of the assignment statement");
	case 7:
	    return new String(
		    "Error( 7):\t"
			    + "Left side of the assignment statement is not a variable identifier");
	case 8:
	    return new String("Error( 8):\t"
		    + "In the procedure call parameter types do not match");
	case 9:
	    return new String(
		    "Error( 9):\t"
			    + "Do not match the number of parameters in the procedure call");
	case 10:
	    return new String(
		    "Error(10):\t"
			    + "Identifier is not a procedure identifier in the procedure call");
	case 11:
	    return new String("Error(11):\t"
		    + "Condition part of IF or WHILE is not bool type ");
	case 12:
	    return new String(
		    "Error(12):\t "
			    + "Component of the operator in the expression is incompatible");
	case 13:
	    return new String("Error(13):\t"
		    + "Procedure's name didn't declared");
	default:
	    return null;
	}
    }
}
