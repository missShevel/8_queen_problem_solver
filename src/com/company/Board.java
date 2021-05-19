package com.company;

import java.util.ArrayList;

public class Board {
    int [][] board  = new int[8][8];

    public Board(int [][] m){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                this.board[i][j] = m[i][j];
            }
        }
    }
    public Board(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                this.board[i][j] = 0;
            }
        }
    }
    private boolean checkNumberOfFigures(){
        int counter = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(this.board[i][j] == 1) {
                    counter++;
                }
            }
        }
        return (counter == 8);
    }

    public void rowsInsert() {
        ArrayList<Integer> indexes = this.findIndexesToBePlacedInFreeRows();
        System.out.println(indexes);
        int pos = 0;
        for (int i = 0; i < 8; i++) {
            if (isEmptyRow(board[i])) {
                board[i][indexes.get(pos)] = 1;
                pos++;
            }
        }
    }

    private boolean isEmptyRow(int [] row){
        for (int i = 0; i < 8; i++) {
            if(row[i] == 1){
                return false;
            }
        }
        return true;
    }


    private ArrayList<Integer> findIndexesToBePlacedInFreeRows(){
        ArrayList<Integer> indexes = new ArrayList<>();
        if(!this.checkRows()){
            for (int i = 0; i < 8; i++) {
                int c = 0;
                while(checkIfRowHasMoreThanOneQueen(board[i])) {
                    for (int j = 0; j < 8; j++) {
                        if(board[i][j] == 1){
                            if (c < 1 ){
                                c++;
                            } else {
                                indexes.add(j);
                                board[i][j] = 0;
                            }
                        }
                    }
                }
            }
        }
        return indexes;
    }
    private boolean checkRows() {
        int queensPerRow = 0;
        for (int i = 0; i < 8; i++) {
            queensPerRow = 0;
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j] == 1) {
                    queensPerRow++;
                    if (queensPerRow > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    private boolean checkIfRowHasMoreThanOneQueen(int[] row){
        int c = 0;
        for (int i = 0; i < 8; i++) {
            if(row[i] == 1){
                c++;
            }
        }
        return (c > 1);
    }

    /** TODO:
     * [done]check whether all queens are on the board (should be 8)
     *------------------------------------------------------------------------
     *
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
    public int[] boardToPositionsList(int [][]positionMatrix){
        int [] figuresPositions = new int[8];
        for(int i = 0; i < positionMatrix.length; i++){
            for(int j = 0; j < positionMatrix.length; j++){
                if(positionMatrix[i][j] == 0) continue;
                figuresPositions[i] = j;
            }
        }
        return figuresPositions;
    }

    public void setFigures(int [] positions){
        for (int i = 0; i < 8; i++){
            this.board[i][positions[i]] = 1;
        }
    }

    public void printBoard(){
        for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(this.board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
