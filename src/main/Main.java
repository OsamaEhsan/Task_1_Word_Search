package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		WordSearch controller = new WordSearch();
		Scanner input = new Scanner(System.in);
		String word = "1";
		ArrayList<String> Board = controller.ReadBoardFromFile();
		System.out.println("Word Search from 2D board\n"
						 + "Kindly enter your board first in Board.txt file using the valid format.\n"
						 + "Now enter the word you want to search in the Board, system will return true or false.\n"
						 + "Enter 0 to exit the program.");
		while(word != "0") {
			word = input.nextLine();
			Node root = new Node();
		    controller.insert(root, word.toUpperCase());
		    
		    boolean check = controller.findWords(Board, root);
		    System.out.println(check);
		}
		

	}
}
