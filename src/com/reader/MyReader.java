package com.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Stack;
import java.util.Vector;

public class MyReader {
	
	private Lexator filename;
	private Lexator lexator;
	private Stack<Vector<Token>> stackArray;
	private Vector<Token> backtrackingArray;
	private boolean didLook;
	private Token previewToken;
	private Token actualToken;
	private Vector<Token> tokenArray;
	
	public MyReader(String filename) throws FileNotFoundException, IOException, Exception {
		this.filename = new Lexator(filename);
		this.backtrackingArray = new Vector<Token>();
		this.stackArray = new Stack<Vector<Token>>();
		this.tokenArray = new Vector<Token>();
	}
	
	public void next() throws Exception{
		previewToken = actualToken;
		if (backtrackingArray.size() == 0) {
			actualToken = lexator.getToken();
		} else {

		}
	}
}
