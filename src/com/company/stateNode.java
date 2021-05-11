package com.company;

import java.util.LinkedList;

public class stateNode {
    int [] positions  = new int [8];
    LinkedList<stateNode> children;
    stateNode parent;

    public stateNode(int [][] matrixPositions){
//        if (!checkRows(matrixPositions)){
//           rowInsert(matrixPositions);
//        }
        for (int i = 0; i < matrixPositions.length; i++){
            for (int j = 0; j < matrixPositions[i].length; j++){
                if (matrixPositions[i][j] == 0) continue;
                positions[i] = matrixPositions[i][j];
            }
        }
    }
    /** TODO:
     * check whether all queens are on the board (should be 8)
     *------------------------------------------------------------------------
     * public boolean checkIfAllFiguresArePlaced(int [][] matrixPositions){
     *
     * }
     *------------------------------------------------------------------------
     * for situation like this [Q Q Q Q - - - -]
     *                         [Q Q Q - - - - -]
     *                         [Q - - - - - - -]
     *                         [- - - - - - - -]
     *                         [- - - - - - - -]
     *                         [- - - - - - - -]
     *                         [- - - - - - - -]
     *                         [- - - - - - - -]
     * put figures which are on the same row to separate ones
     * (checkRows -- > rowInsert)
     *------------------------------------------------------------------------
    public void rowInsert(int[][] matrixPositions){
        int [][] improvedMatrix = new int [8][8];

    }

    public boolean checkRows(int[][] matrixPositions){

    }
     -------------------------------------------------------------------------
     */
}
