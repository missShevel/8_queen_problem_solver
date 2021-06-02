package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
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
//                        ArrayList<Integer> input = new ArrayList<Integer>();
//                        input.add(4);
//                        input.add(15);
//                        input.add(17);
//                        input.add(32);
//                        input.add(37);
//                        input.add(44);
//                        input.add(50);
//                        input.add(59);
////
//                        String method = "BFS";
//
//                        Solution one = null;
//                        try {
//                            one = new Solution(input, method);
//                        } catch (CloneNotSupportedException e) {
//                            e.printStackTrace();
//                        }
//                        System.out.println(one.getSolution());
//                        System.out.println("  +++++++++++++++  ");
//                        System.out.println(one.getTraversal());
                    }
                });
            }


        }




