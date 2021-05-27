package com.company;
import com.google.gson.*;

import java.util.*;

public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {
        // write your code here

        int mat[][] = { { 1, 1, 1, 1, 0, 0, 0, 0 },
                { 1, 1, 1, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }};
//
        Board bb = new Board(mat);
        ArrayList<int[]> traversal = new ArrayList<>();
        int depth;

        bb.printBoard();
        System.out.println("____________");
        int [] positions = bb.boardToPositionsList();
      bb.printBoard();
        System.out.println("Stop being nervous! It is just a preview of inserted info!");
        SearchTree ss = new SearchTree(positions, "BFS");
        ArrayList<ArrayList<Integer>> solution= ss.findSolution(traversal, depth = 0);
                    for (ArrayList<Integer> bbb: solution) {
                        System.out.println(bbb);
                        Board b = new Board(bbb);
                        b.printBoard();
                System.out.println("_____________");
            }

                  for (int[] node : traversal) {
                System.out.println(Arrays.toString(node));
            }

        System.out.println("Solution was found on depth " + depth);


//        SearchTree ss = new SearchTree(positions);
//        Queue<StateNode> q = new LinkedList<>();
//        LinkedList<StateNode> path = new LinkedList<>();
//
//        // LinkedList<StateNode> bfsSolution = ss.recreatePath(ss.BFS(ss.root));
//        //LinkedList<StateNode> ldfsSolution = ss.recreatePath(ss.LDFS(2, ss.root).getState());
//        LinkedList<StateNode> idsSolution = ss.recreatePath(ss.IDS(ss.root).getState());
//        LinkedList<Board> solution = new LinkedList<>();
//
//        if (idsSolution.isEmpty()) {
//
//            System.out.println("Solution wasn't found for this depth!");
//
//        } else {
//            for (StateNode state : idsSolution) {
//                Board b = new Board();
//                b.setFigures(state.positions);
//                solution.add(b);
//            }
//





        }
    }


