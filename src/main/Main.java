package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	
	public static void main(String[] args) {
		
		WordSearch controller = new WordSearch();
		Scanner input = new Scanner(System.in);
		System.out.println("Word Search from 2D board\n"
						 + "Kindly enter your board first in Board.txt file using the valid format.\n"
						 + "Now enter the word you want to search in the Board, system will return true or false.");
		String word = input.nextLine();
		ArrayList<String[]> Board = controller.ReadBoardFromFile();
		for (String[] row : Board) {
			for (int i = 0; i < row.length; i++) {
				System.out.print(row[i]);
			}
			System.out.println();
		}
	}
}
