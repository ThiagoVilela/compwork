package com.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.sound.sampled.Line;

public class Lexator {
	
	private String filename;
	private int row;
	private int column;
	private char character;
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
	
	public Token getToken() throws Exception{
		//Limpo os espaços e comentários
		clearBnC(liner);
		
		//Retorno o match do character com o enum montado em forma de Token
		return getMatch(character);
	}
	
	public void clearBnC(LineNumberReader liner) throws Exception{
		boolean clean = true;
		while(clean) {
			character = clearBlank(character,liner);
			if(character != '/') {
				clean = false;
			}
			else {
				character = clearComment(character,liner);
			}
		}
	}
	
	public char clearBlank(char character, LineNumberReader liner) {
		while(isBlank(character)) {
			this.getNewRowColumn(liner);
			try {
				character = (char) liner.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return character;
	}
	
	public boolean isBlank(char charactere) {
		if(charactere == ' ' || charactere == '\n' || charactere == '\t' || charactere == '\f') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void getNewRowColumn(LineNumberReader liner) {
		int newRow = liner.getLineNumber();
		column++;
		if(newRow > row) {
			column = 0;
		}
		row = newRow;
	}
	
	public char clearComment(char character, LineNumberReader liner) throws Exception{
		if(character == '/') {
			getNewRowColumn(liner);
			character = (char) liner.read();
			if(character == '/') {
				while(character != '\n' && character != (char) -1) {
					getNewRowColumn(liner);
					character = (char) liner.read();
				}
			}
			else {
				System.out.println("Error Identified!");
				System.out.println("Line: " + row + " and Column: " + column);
				System.out.println("Invalid operator.");
			}
		}
		return character;
	}
	
	public Token getMatch(char character) throws Exception {
		int firstColumnToken;
		StringBuffer sentence;
		
		switch(character) {
			case '(':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.OPEN_PAREN, "(", row, column);
		    case ')':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.CLOSE_PAREN, ")", row, column);
		    case '{':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.OPEN_CURLY, "{", row, column);
		    case '}':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.CLOSE_CURLY, "}", row, column);
		    case '[':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.OPEN_BRACK, "[", row, column);
		    case ']':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.CLOSE_BRACK, "]", row, column);
		    case ';':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.SEMI, ";", row, column);
		    case ',':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.COMMA, ",", row, column);
		    case '!':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.NOT, "!", row, column);
		    case '*':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.STAR, "*", row, column);
		    case '.':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.DOT, ".", row, column);
		    case (char) -1:
		        getNewRowColumn(liner);
		        return new Token(TokenSigns.EOF, "<EOF>", row, column);
		    case '=':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        if (character == '=') {
		            getNewRowColumn(liner);
		            character = (char) liner.read();
		            return new Token(TokenSigns.EQUAL, "==", row, column);
		        } else {
		            return new Token(TokenSigns.ASSIGN, "=", row, column);
		        }
		    case '>':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        return new Token(TokenSigns.GREATER_THAN, ">", row, column);
		    case '+':
		        getNewRowColumn(liner);
		        character = (char) liner.read();
		        if (character == '=') {
		            getNewRowColumn(liner);
		            character = (char) liner.read();
		            return new Token(TokenSigns.PLUS_ASSIGN, "+=", row, column);
		        } else if (character == '+') {
		            getNewRowColumn(liner);
		            character = (char) liner.read();
		            return new Token(TokenSigns.INC, "++", row, column);
		        } else {
		            return new Token(TokenSigns.PLUS, "+", row, column);
		        }
		     case '0':
		            getNewRowColumn(liner);
		            character = (char) liner.read();
		            return new Token(TokenSigns.INT_LITERAL,"0", row, column);
		     case '-':
                 getNewRowColumn(liner);
                 character = (char) liner.read();
                 if (character == '-') {
                     firstColumnToken = column;
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     return new Token(TokenSigns.DEC, "--", row, firstColumnToken);
                 } else {
                     return new Token(TokenSigns.MINUS, "-", row, column);
                 }

             case '&':
                 getNewRowColumn(liner);
                 character = (char) liner.read();
                 if (character == '&') {
                     firstColumnToken = column;
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     return new Token(TokenSigns.AND, "&&", row, firstColumnToken);
                 } else {
                	 System.out.println("Error Identified!");
                	 System.out.println("Line: " + row + " and Column: " + column);
                	 System.out.println("Invalid operator.");
                     return getToken();
                 }
             case '<':
                 getNewRowColumn(liner);
                 character = (char) liner.read();
                 if (character == '=') {
                     firstColumnToken = column;
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     return new Token(TokenSigns.LOWER_EQUAL, "<=", row, firstColumnToken);
                 }else {
                	 System.out.println("Error Identified!");
                	 System.out.println("Line: " + row + " and Column: " + column);
                	 System.out.println("Invalid operator.");
                     return getToken();
                 }
             case '\'':
                 sentence = new StringBuffer();
                 sentence.append('\'');
                 firstColumnToken = column;
                 getNewRowColumn(liner);
                 character = (char) liner.read();
                 if (character == '\\') {
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     sentence.append(characterEscape(liner));
                 } else {
                     sentence.append(character);
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                 }
                 if (character == '\'') {
                     sentence.append('\'');
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     return new Token(TokenSigns.CHAR_LITERAL, sentence.toString(), row, firstColumnToken);
                 } else {
                
                    System.out.println("ERRO - Linha: " + row + ", coluna: " + column + " ****** Token " + character
                             + " encontrado onde ' era esperado.");
                     while (character != '\'' && character != ';' && character != '\n') {
                         getNewRowColumn(liner);
                         character = (char) liner.read();
                     }
                     return new Token(TokenSigns.CHAR_LITERAL, sentence.toString(), row, firstColumnToken);
                 }
             case '"':
                 sentence = new StringBuffer();
                 sentence.append("\"");
                 firstColumnToken = column;
                 getNewRowColumn(liner);
                 character = (char) liner.read();
                 while (character != '"' && character != '\n' && character != (char) -1) {
                     if (character == '\\') {
                         getNewRowColumn(liner);
                         character = (char) liner.read();
                         sentence.append(characterEscape(liner));
                     } else {
                         sentence.append(character);
                         getNewRowColumn(liner);
                         character = (char) liner.read();
                     }
                 }
                 if (character == (char) -1) {
                	 System.out.println("Error Identified!");
                	 System.out.println("Line: " + row + " and Column: " + column);
                	 System.out.println("Unexpected EOF.");
                 } else if (character == '\n') {
                	 System.out.println("Error Identified!");
                	 System.out.println("Line: " + row + " and Column: " + column);
                	 System.out.println("Unexpected linebreak.");
                 } else {
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     sentence.append("\"");
                 }
                 //getNewRowColumn(liner);
                 //character = (char) liner.read();
                 return new Token(TokenSigns.STRING_LITERAL, sentence.toString(), row, firstColumnToken);
             case '1':
             case '2':
             case '3':
             case '4':
             case '5':
             case '6':
             case '7':
             case '8':
             case '9':
                 sentence = new StringBuffer();
                 firstColumnToken = column;
                 while (isNumber(character)) {
                     sentence.append(character);
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                 }
                 return new Token(TokenSigns.INT_LITERAL, sentence.toString(), row, firstColumnToken);
             default:
                if (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character == '_' || character == '$') {
                     sentence = new StringBuffer();
                     firstColumnToken = column;
                     while (isNumber(character) || (character >= 'a' && character <= 'z' || character >= 'A' && character <= 'Z' || character == '_' || character == '$')) {
                         sentence.append(character);
                         getNewRowColumn(liner);
                         character = (char) liner.read();
                     }
                     String identifier = sentence.toString();
                     if (reservedWords.containsKey(identifier)) {
                         return new Token(reservedWords.get(identifier), identifier, row, firstColumnToken);
                     } else {
                         addSymbolTable(identifier);
                         return new Token(TokenSigns.IDENTIFIER, identifier, row, firstColumnToken);
                     }
                 } else {
                     if(row != 0 || column != 0) {
                    	 System.out.println("Error Identified!");
                    	 System.out.println("Line: " + row + " and Column: " + column);
                    	 System.out.println("Unexpcted Token: " + character);
                     }
                     getNewRowColumn(liner);
                     character = (char) liner.read();
                     return getToken();
             }
     	}
	}
	
	private String characterEscape(LineNumberReader lnr) throws Exception{
        switch (character) {
	        case '\\':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\\\";
	        case 'f':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\f";
	        case '"':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\"";
	        case 'b':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\b";
	        case 't':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\t";
	        case '\'':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\'";
	        case 'n':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\n";
	        case 'r':
	            getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "\\r";
	        default:
	        	System.out.println("Error Identified!");
				System.out.println("Line: " + row + " and Column: " + column);
				System.out.println("Invalid operator.");
				getNewRowColumn(lnr);
	            character = (char) lnr.read();
	            return "";
        }
    }
	
    private boolean isNumber(char character) {
        switch (character) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }
    
    private int addSymbolTable(String lexeme){
        int pos = symboltable.indexOf(lexeme); 
        if (pos < 0) { 
        	symboltable.add(lexeme);
            return (symboltable.size() - 1);
        }
        return pos;
    }
}
