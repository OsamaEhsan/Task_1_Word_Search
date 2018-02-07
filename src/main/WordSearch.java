package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;

public class WordSearch {

	public  ArrayList<String[]> ReadBoardFromFile() {
		String fileName = "Board.txt";
		String line = null;
		ArrayList<String[]> Board = new ArrayList<>();
		int lengthCheck = 0;
		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				String[] parsedTokens = line.split(" ");
				Board.add(parsedTokens);
				if(lengthCheck == 0) 
					lengthCheck = parsedTokens.length;
				else if(lengthCheck != parsedTokens.length) {
					throw new IllegalClassFormatException();
				}
					
			}   
			bufferedReader.close();          
		}
		catch(FileNotFoundException ex) {
			System.out.println( "Unable to open file '" + fileName + "'");  
			// ex.printStackTrace();
		}
		catch(IOException ex) {
			System.out.println( "Error reading file '" + fileName + "'");
			// ex.printStackTrace();
		} catch (IllegalClassFormatException ex) {
			System.out.println("Illegal board format, irregular size of board");
			// ex.printStackTrace();	
		}
		return Board;
	}
}
