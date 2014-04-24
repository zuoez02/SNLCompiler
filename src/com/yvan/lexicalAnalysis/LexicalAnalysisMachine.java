package com.yvan.lexicalAnalysis;

import java.io.*;

public class LexicalAnalysisMachine {
    private TokenList tokenList;
    private boolean isAnnotate;

    // create a lexical analysis machine and begin to analysis the file
    // automatically
    public LexicalAnalysisMachine(File file) throws IOException {
	this.isAnnotate = false;
	this.tokenList = new TokenList();
	// get code from file
	BufferedReader br = new BufferedReader(new FileReader(file));
	String code = null;
	int line = 0;
	// read line
	while ((code = br.readLine()) != null) {
	    line++;
	    if (code.length() > 0)
		analysis(code, line);
	}
	this.tokenList.add(makeToken(LEX.EOF, null, ++line));
	// close read stream
	br.close();

    }

    private void analysis(String code, int line) {
	// initialize state
	int state = 0;
	// initialize a reading char and a reading String
	String word = "";
	int index = 0;
	char getChar = code.charAt(index);
	// initialize OutKind Object
	OutKind outKind = new OutKind();
	// // initialize a isAnnotate check whether state is annotation
	// boolean isAnnotate = false;
	// initialize a isCharc check whether is input a string
	boolean isCharc = false;
	// initialize a StateTransform machine
	StateTransform st = new StateTransform();
	String wordBefore = word;
	LEX outKindBefore = outKind.getOutKind();

	// read the code
	while (index < code.length()) {
	    // whether is annotated
	    if (!isAnnotate) {
		// change isCharc if getChar is "'"
		if (getChar == '\'') {
		    isCharc = !(isCharc);
		    if (!isCharc) {
			Token token = new Token(LEX.CHARC, word, line);
			tokenList.add(token);
		    }
		    state = 0;
		    outKind.setOutKind(0);
		    index++;
		    word = "";
		    if (index != code.length())
			getChar = code.charAt(index);
		    continue;
		}
		if (isCharc) {
		    word = word + getChar;
		    index++;
		    getChar = code.charAt(index);
		    continue;
		}
		wordBefore = word;
		outKindBefore = outKind.getOutKind();
		state = st.stateTransform(state, getChar);
		// state is not 0 and continue add to word
		if (state != 0) {
		    if (getChar == '{')
			isAnnotate = true;
		    word = word + getChar;
		    outKind.setOutKind(state);
		    index++;
		} else {
		    // state is 0, make a token and add to token list
		    word = "";
		    outKind.setOutKind(LEX.NULL);
		    Token token = makeToken(outKindBefore, wordBefore, line);
		    this.tokenList.add(token);
		}
	    } else {
		// to stop annotating
		if (getChar == '}')
		    isAnnotate = false;
		index++;
	    }
	    // whether is the last character of the line
	    if (index == code.length()) {
		Token token1 = makeToken(outKind.getOutKind(), word, line);
		this.tokenList.add(token1);
	    } else {
		getChar = code.charAt(index);
	    }

	}
    }

    // make a token with string word
    private Token makeToken(LEX lex, String word, int line) {
	// whether is a ' ' or is annotate
	if (lex == LEX.EMPTY || lex == LEX.NULL)
	    return null;
	Token token;
	// if the word is LEX.ID, it should judge if it is a reserved word
	if (lex == LEX.ID) {
	    lex = LEX.reservedLookUp(word);
	}
	if (lex != LEX.ID && lex != LEX.INTC)
	    word = null;
	token = new Token(lex, word, line);
	return token;
    }

    // return TokenList
    public TokenList getTokenList() {
	return this.tokenList;
    }
}