package com.company;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    ArrayList<ArrayList<Integer>> solution;
    ArrayList<int[]> traversal = new ArrayList<>();
    boolean isFound = true;

    /* Метод для отримання списку станів, які є послідовністю вирішення */
    public ArrayList<ArrayList<Integer>> getSolution() {
        return this.solution;
    }
    /* Метод для отримання списку вузлів, що було перевірено алгоритмом у дереві */
    public String getTraversal() {

        StringBuilder result = new StringBuilder();
        for (int[] state : this.traversal){
            if(state != this.traversal.get(this.traversal.size() - 1)){
                result.append(Arrays.toString(state)).append("\n");
            } else {
                if(!this.getSolution().isEmpty()) {
                    result.append("The solution was found on depth ").append(Arrays.toString(state));
                } else {
                    result.append(Arrays.toString(state)).append("\nThe solution was not found on this depth");
                }
            }
        }

        return result.toString();

    }

    public Solution (ArrayList<Integer> input, String method) throws CloneNotSupportedException {
        Board bb = new Board(input);
        int [] positions = bb.boardToPositionsList();
        ArrayList<int[]> traversalStr = new ArrayList<>();
        SearchTree ss = new SearchTree(positions, method);
        this.solution = ss.findSolution(traversalStr);
        this.traversal = traversalStr;
    }

    public Solution (ArrayList<Integer> input, String method, int depth) throws CloneNotSupportedException {
        Board bb = new Board(input);
        int [] positions = bb.boardToPositionsList();
        ArrayList<int[]> traversalStr = new ArrayList<>();
        SearchTree ss = new SearchTree(positions, method, depth);
        this.solution = ss.findSolution(traversalStr);
        this.traversal = traversalStr;
        if(this.solution.isEmpty()){
            this.isFound = false;
        }
    }

}
