package com.app;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.reader.MyReader;

public class Main {
	public static void main(String[] args) {
		try {
			MyReader reader = new MyReader("ArquivoTeste.java");
			
			MyParser parser = new MyParser(reader);

		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.toString());
		} catch (IOException ioe) {
			System.err.println(ioe.toString());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
}
