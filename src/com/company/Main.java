package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int [] pos = {2,4,5,7,0,1,3,6} ;

        Board b = new Board();
        b.printBoard();
        b.setFigures(pos);
        System.out.println("|||||||||||");
        b.printBoard();
    }
}
