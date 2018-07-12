package com.reader;

public class Token {
	
	private TokenSigns tokenSign;
	private String value;
	private int row;
	private int column;
	
	public Token(TokenSigns tokenSign, int row, int column) {
		this.tokenSign = tokenSign;
		this.value = "";
		this.row = row;
		this.column = column;
	}
	
	public Token(TokenSigns tokenSign, String value, int row, int column) {
		this.tokenSign = tokenSign;
		this.value = value;
		this.row = row;
		this.column = column;
	}

	public TokenSigns getTokenSign() {
		return tokenSign;
	}

	public void setTokenSign(TokenSigns tokenSign) {
		this.tokenSign = tokenSign;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
}
