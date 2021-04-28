package com.company;

public class Board {
    int [][] board  = new int[8][8];


    public Board(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                this.board[i][j] = 0;
            }
        }
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
