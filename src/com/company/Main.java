package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {
        // write your code here
        int[] pos = {0, 5, 0, 0, 0, 0, 0, 0};

//        Board b = new Board();
//       // b.printBoard();
//        b.setFigures(pos);
//       // System.out.println("|||||||||||");
//       // b.printBoard();
//       // System.out.println(Arrays.toString(b.boardToPositionsList(b.board)));
////        StateNode root = StateNode.buildRoot(b.boardToPositionsList(b.board));
////        StateNode s = new StateNode(b.boardToPositionsList(b.board), root);
//        int mat[][] = { { 1, 1, 1, 1, 0, 0, 0, 0 },
//                { 1, 1, 1, 0, 0, 0, 0, 0 },
//                { 1, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0 },
//                { 0, 0, 0, 0, 0, 0, 0, 0 }};
//
//        Board bb = new Board(mat);
//        bb.printBoard();
//        System.out.println(Arrays.toString(bb.boardToPositionsList()));
//        System.out.println("_______________________");
//
//
//        bb.rowsInsert();
//        int [] positions = bb.boardToPositionsList();
//    //    System.out.println(Arrays.toString(positions));
//       // bb.printBoard();
////ArrayList<Integer> freePos = {0, 1, 2, 3, 4, 5, 6, 7};
        searchTree ss = new searchTree(pos);
        Queue<StateNode> q = new LinkedList<>();
        LinkedList<StateNode> path = new LinkedList<>();
        //  ss.root.generateChildren(0);
        // LinkedList<StateNode> bfsSolution = ss.recreatePath(ss.BFS(ss.root));
        LinkedList<StateNode> ldfsSolution = ss.recreatePath(ss.LDFS(2, ss.root).getState());
        LinkedList<Board> solution = new LinkedList<>();

        if (ldfsSolution.isEmpty()) {

            System.out.println("Solution wasn't found for this depth!");

        } else {
            for (StateNode state : ldfsSolution) {
                Board b = new Board();
                b.setFigures(state.positions);
                solution.add(b);
            }

            for (Board bb : solution) {

                bb.printBoard();
                System.out.println("_____________");
            }
            //     System.out.println(ss.root.findFreePositions(5));


        }
    }

}
