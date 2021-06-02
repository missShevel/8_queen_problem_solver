package com.company;
import javax.swing.*;
import java.awt.EventQueue;
public class Main {


    public static void main(String[] args) throws CloneNotSupportedException {


                new Main();
            }
            public Main() {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        JFrame mainFrame = new JFrame("8Queens");
                        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        EightQueenPanel queenFrame = new EightQueenPanel();
                        mainFrame.setSize(560, 1000);
                        mainFrame.add(queenFrame);
                        mainFrame.setVisible(true);

                    }
                });
            }


        }




