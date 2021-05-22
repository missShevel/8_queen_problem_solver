package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws CloneNotSupportedException{
	// write your code here
        int [] pos = {2,4,5,7,0,1,3,6} ;

        Board b = new Board();
       // b.printBoard();
        b.setFigures(pos);
       // System.out.println("|||||||||||");
       // b.printBoard();
       // System.out.println(Arrays.toString(b.boardToPositionsList(b.board)));
//        StateNode root = StateNode.buildRoot(b.boardToPositionsList(b.board));
//        StateNode s = new StateNode(b.boardToPositionsList(b.board), root);
        int mat[][] = { { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }};

        Board bb = new Board(mat);
        bb.printBoard();
        System.out.println(Arrays.toString(bb.boardToPositionsList()));
        System.out.println("_______________________");


        bb.rowsInsert();
        int [] positions = bb.boardToPositionsList();
        System.out.println(Arrays.toString(positions));
       // bb.printBoard();
//ArrayList<Integer> freePos = {0, 1, 2, 3, 4, 5, 6, 7};
        searchTree ss = new searchTree(positions);
        ss.root.generateChildren(0);


    }

}
