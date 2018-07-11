package com.reader;

public enum TokenSigns {
	ABSTRACT("abstract"),
	AND("&&"), 
	ASSIGN("="), 
	BOOLEAN("boolean"),
	CHAR("char"),
	CHAR_LITERAL("<CHAR_LITERAL>"), 
	CLASS("class"),
	CLOSE_BRACK("]"), 
	CLOSE_CURLY("}"), 
	CLOSE_PAREN(")"), 
	COMMA(","), 
	DEC("--"), 
	DOT("."), 
	ELSE("else"),
	EQUAL("=="), 
	EXTENDS("extends"),
	EOF("<EOF>"),
	FALSE("false"),
	GREATER_THAN(">"), 
	IDENTIFIER("<IDENTIFIER>"), 
	IF("if"),
	IMPORT("import"),
	INC("++"), 
	INSTANCEOF("instanceof"), 
	INT("int"), NEW("new"), 
	INT_LITERAL("<INT_LITERAL>"), 
	LOWER_EQUAL("<="), 
	MINUS("-"), 
	NOT("!"), 
	NULL("null"), 
	OPEN_BRACK("["), 
	OPEN_CURLY("{"), 
	OPEN_PAREN("("), 
	PACKAGE("package"), 
	PLUS("+"), 
	PLUS_ASSIGN("+="), 
	PRIVATE("private"), 
	PROTECTED("protected"),
	PUBLIC("public"), 
	RETURN("return"), 
	SEMI(";"), 
	STAR("*"), 
	STATIC("static"), 
	STRING_LITERAL("<STRING_LITERAL>"),
	SUPER("super"), 
	THIS("this"), 
	TRUE("true"), 
	VOID("void"), 
	WHILE("while");
	
	private String spellit;
	
	private TokenSigns(String spellit) {
	    this.spellit = spellit;
	}
	
	public String spellit() {
	    return spellit;
	}
	
	@Override
	public String toString() {
	    return spellit;
	}
}
