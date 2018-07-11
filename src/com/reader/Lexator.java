package com.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Lexator {
	
	private String filename;
	private int row;
	private int column;
	private List<String> symboltable;
	private LineNumberReader liner;
	private Hashtable<String,TokenSigns> reservedWords; //Ou hashmap?
	
	public Lexator(String filename) throws FileNotFoundException {
		this.filename = filename;
		this.row = 0;
		this.column = 0;
		this.symboltable = new ArrayList<String>();
		this.liner = new LineNumberReader(new FileReader(filename));
		
		reservedWords = new Hashtable<String,TokenSigns>();
		for (TokenSigns myenum : TokenSigns.values()) {
			reservedWords.put(myenum.toString(), myenum);
		}
		
		/*
		reservedWords.put(TokenSigns.ABSTRACT.toString(), TokenSigns.ABSTRACT);
        reservedWords.put(TokenSigns.BOOLEAN.toString(), TokenSigns.BOOLEAN);
        reservedWords.put(TokenSigns.CHAR.toString(), TokenSigns.CHAR);
        reservedWords.put(TokenSigns.CLASS.toString(), TokenSigns.CLASS);
        reservedWords.put(TokenSigns.ELSE.toString(), TokenSigns.ELSE);
        reservedWords.put(TokenSigns.EXTENDS.toString(), TokenSigns.EXTENDS);
        reservedWords.put(TokenSigns.FALSE.toString(), TokenSigns.FALSE);
        reservedWords.put(TokenSigns.IF.toString(), TokenSigns.IF);
        reservedWords.put(TokenSigns.IMPORT.toString(), TokenSigns.IMPORT);
        reservedWords.put(TokenSigns.INSTANCEOF.toString(), TokenSigns.INSTANCEOF);
        reservedWords.put(TokenSigns.INT.toString(), TokenSigns.INT);
        reservedWords.put(TokenSigns.NEW.toString(), TokenSigns.NEW);
        reservedWords.put(TokenSigns.NULL.toString(), TokenSigns.NULL);
        reservedWords.put(TokenSigns.PACKAGE.toString(), TokenSigns.PACKAGE);
        reservedWords.put(TokenSigns.PRIVATE.toString(), TokenSigns.PRIVATE);
        reservedWords.put(TokenSigns.PROTECTED.toString(), TokenSigns.PROTECTED);
        reservedWords.put(TokenSigns.PUBLIC.toString(), TokenSigns.PUBLIC);
        reservedWords.put(TokenSigns.RETURN.toString(), TokenSigns.RETURN);
        reservedWords.put(TokenSigns.STATIC.toString(), TokenSigns.STATIC);
        reservedWords.put(TokenSigns.SUPER.toString(), TokenSigns.SUPER);
        reservedWords.put(TokenSigns.THIS.toString(), TokenSigns.THIS);
        reservedWords.put(TokenSigns.TRUE.toString(), TokenSigns.TRUE);
        reservedWords.put(TokenSigns.VOID.toString(), TokenSigns.VOID);
        reservedWords.put(TokenSigns.WHILE.toString(), TokenSigns.WHILE);
		*/
		
	}
}
