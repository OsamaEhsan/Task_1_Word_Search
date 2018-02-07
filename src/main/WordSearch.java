package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.util.ArrayList;

public class WordSearch {

	/**
	 * Reads the board from Board.txt in the source folder, parse the characters based on characters
	 * @return ArrayList of String, each string consists of number of characters
	 * 
	 * throws exception if the matrix is of irregular length
	 */
	public  ArrayList<String> ReadBoardFromFile() {
		String fileName = "Board.txt";
		String line = null;
		ArrayList<String> Board = new ArrayList<>();
		int lengthCheck = 0;
		try {

			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				Board.add(line.replaceAll(" ", "").toUpperCase());
				if(lengthCheck == 0) 
					lengthCheck = line.length();
				else if(lengthCheck != line.length()) {
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
	
	/**
	 * Insert the word to be found in Node data structure
	 * @param root, head/root of the tree 
	 * @param Key, word to be found
	 */
	public void insert(Node root, String Key)
	{
		int n = Key.length();
		Node tempNode = root;
		for (int i=0; i<n; i++)
		{
			int index = Key.charAt(i) - 'A';

			if (tempNode.Child[index] == null)
				tempNode.Child[index] = new Node();

			tempNode = tempNode.Child[index];
		}
		tempNode.leaf = true;
	}
	 
	/**
	 * Check if the index to be checked is not out of bounds or already checked
	 * @param i, current row index
	 * @param j, current column index
	 * @param checked, checked matrix
	 * @param M, matrix row size
	 * @param N, matrix column size
	 * @return true or false
	 */
	boolean CheckValid(int i, int j, boolean checked[][], int M, int N)
	{
		return (i >=0 && i < M && j >=0 && j < N && !checked[i][j]);
	}
	 
	/**Recursively search for the word to be found in the board
	 * @param root, Word to be found in tree
	 * @param board, Board read from file
	 * @param i, current row index
	 * @param j, current column index
	 * @param checked, checked matrix
	 * @param str, build up string to be compared
	 * @return true or false
	 */
	boolean searchWord(Node root, ArrayList<String> board, int i, int j, boolean checked[][], String str)
	{
		int M = board.size(), N =board.get(0).length();
		boolean check = false;
		if (root.leaf == true)
			check = true;

		if (CheckValid(i, j, checked, M, N))
		{
			checked[i][j] = true;
			for (int K =0; K < 26; K++)
			{
				if (root.Child[K] != null)
				{
					char ch = (char) (K + 'A') ;

					if (CheckValid(i, j+1,checked, M, N) && board.get(i).charAt(j+1) == ch)
						check = searchWord(root.Child[K],board,i, j+1, checked,str+ch);
					if (CheckValid(i+1,j, checked, M, N) && board.get(i+1).charAt(j) == ch)
						check = searchWord(root.Child[K],board,i+1, j, checked,str+ch);
					if (CheckValid(i, j-1,checked, M, N) && board.get(i).charAt(j-1) == ch)
						check = searchWord(root.Child[K],board,i ,j-1, checked,str+ch);
					if (CheckValid(i-1, j,checked, M, N) && board.get(i-1).charAt(j) == ch)
						check = searchWord(root.Child[K],board,i-1, j, checked,str+ch);
				}
			}
			checked[i][j] = false;
		}
		return check;
	}
	
	/** find words across the board by looping through the board
	 * @param board, Board read from file
	 * @param root, Word to be found in tree
	 * @return true or false
	 */
	public boolean findWords(ArrayList<String> board, Node root)
	{
		int M = board.size(), N =board.get(0).length(); 
		boolean[][] checked = new boolean[M][N];
		Node tempNode = root ;
		boolean check = false;
		String str = "";
		for (int i = 0 ; i < M; i++)
		{
			for (int j = 0 ; j < N ; j++)
			{
				if (tempNode.Child[(board.get(i).charAt(j)) - 'A'] != null)
				{
					str = str+board.get(i).charAt(j);
					check = searchWord(tempNode.Child[(board.get(i).charAt(j)) - 'A'],board, i, j, checked, str);
					str = "";
				}
			}
		}
		return check;
	}
}







