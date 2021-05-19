package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
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
        System.out.println("_______________________");
        bb.boardToPositionsList(mat);
        bb.rowsInsert();
        bb.printBoard();

    }

//         * for situation like this [Q Q Q Q - - - -]
//            *                         [Q Q Q - - - - -]
//            *                         [Q - - - - - - -]
//            *                         [- - - - - - - -]
//            *                         [- - - - - - - -]
//            *                         [- - - - - - - -]
//            *                         [- - - - - - - -]
//            *                         [- - - - - - - -]
//            * put figures which are on the same row to separate ones
//     * (checkRows -- > rowInsert)
}
