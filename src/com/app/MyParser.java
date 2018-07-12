package com.app;

import com.reader.MyReader;

public class MyParser {
	
	private MyReader reader;
	private boolean onError;
	private boolean recovered;
	
	public MyParser(MyReader reader) throws Exception{
		this.reader = reader;
		this.onError = false;
		this.recovered = false;
		reader.next();
	}
}
