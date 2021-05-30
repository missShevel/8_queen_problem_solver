package com.company;

import java.util.ArrayList;

public class Board {
    int [][] board = new int [8][8];

    public Board(int [][] m){
        for (int i = 0; i < 8; i++){
            System.arraycopy(m[i], 0, this.board[i], 0, 8);
        }
    }

    public Board(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                assert false;
                this.board[i][j] = 0;
            }
        }
    }

    public Board(ArrayList<Integer> squareNumbers){
        this.board = squareNumbersToBoard(squareNumbers);
    }

/* Метод переведення матриці, яка представляє дошку, у масив номерів клітинок, у яких стоять фігури */
    public ArrayList<Integer> boardToSquareNumbers(){
        ArrayList<Integer> result = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                counter++;
                if(this.board[i][j] != 0){
                    result.add(counter);
                }
            }
        }
        return result;
    }
    /* Метод переведення масиву номерів клітинок, у яких стоять фігури, у матрицю, яка представляє дошку */
    public int[][] squareNumbersToBoard(ArrayList<Integer> squareNumbers){
        int[][] boardMatrix = new int[8][8];
        for(int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++) {
                boardMatrix[i][j] = 0;
            }
        }
        for(Integer n : squareNumbers) {

            int row = (n/8)+1;
            int col = n-(row-1)*8 ;
        boardMatrix[row-1][col] = 1;
        }
        return boardMatrix;
    }
/* Метод, для розстановки фігур в окремі рядки */
    private void rowsInsert() {
        ArrayList<Integer> indexes = this.findIndexesToBePlacedInFreeRows();
        int pos = 0;
        for (int i = 0; i < 8; i++) {
            if (isEmptyRow(board[i])) {
                board[i][indexes.get(pos)] = 1;
                pos++;
            }
        }
    }
    /* Метод для перевірки наявності фігури у рядку */
    private boolean isEmptyRow(int [] row){
        for (int i = 0; i < 8; i++) {
            if(row[i] == 1){
                return false;
            }
        }
        return true;
    }
    /* Метод для знаходження номерів колонок у вільних рядках, у які буде посталено фігури*/
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
    /* Метод перевірки випадку наявності декількох фігур в будь-якому рядку дошки*/
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
    /* Метод перевірки наяності декількох фігур в конкретному рядку дошки*/
    private boolean checkIfRowHasMoreThanOneQueen(int[] row){
        int c = 0;
        for (int i = 0; i < 8; i++) {
            if(row[i] == 1){
                c++;
            }
        }
        return (c > 1);
    }
    /* Метод для представлення матриці у вигляді масиву номерів колонок
    (порядковий номер у масиві - це номер рядка, а значення, яке знаходиться під цим номером - номер колонки) */
    public int[] boardToPositionsList(){
        if(!this.checkRows()){
            this.rowsInsert();
        }
        int [] figuresPositions = new int[8];
        for(int i = 0; i < this.board.length; i++){
            for(int j = 0; j < this.board.length; j++){
                if(this.board[i][j] == 0) continue;
                figuresPositions[i] = j;
            }
        }
        return figuresPositions;
    }
    /* Метод переведення масиву номерів колонок в матрицю розстановки*/
    public void setFigures(int[] positions){
        for (int i = 0; i < 8; i++){
            this.board[i][positions[i]] = 1;
        }
    }

}
