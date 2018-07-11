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
	}
}
