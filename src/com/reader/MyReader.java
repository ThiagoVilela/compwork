package com.reader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MyReader {
	
	private Lexator filename;
	
	public MyReader(String filename) throws FileNotFoundException, IOException, Exception {
		this.filename = new Lexator(filename);
	}

}
