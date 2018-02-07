package main;


public class Node {
	
	Node[] Child = new Node[26];
    boolean leaf;

    public Node() {
        leaf = false;
        for (int i = 0 ; i < 26 ; i++)
           Child[i] = null;
    }
}
