package com.company;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

class EightQueenPanel extends JPanel {
    private Timer timer;
    private String str = "Rastavlay korolev";
    private JPanel gui;
    private int count = 0;
    private JPanel board;
    Solution solution;
    //private ButtonGroup radio
    private JButton submitButton = new JButton("Submit");
    private JButton resetButton = new JButton("RESET");
    private JButton[] buttons = new JButton[65];
    private JTextArea textArea;
    private JScrollPane scr;
    private ArrayList<Integer> inputArray = new ArrayList<>();
    ArrayList<ArrayList<Integer>> solutionArray = new ArrayList<>();
    int index = 0;
/*TODO:
1. radioButtons for methods (BFS, LDFS, IDS)
2. textArea for depth for LDFS
3. remove rastavlyau korolev
* **/
    public EightQueenPanel() {
        setLayout(new FlowLayout(FlowLayout.LEADING));
        setBackground(Color.cyan);
        timer = new Timer(1000, new SubmitListener());
        gui = new JPanel();
        board = createBoard();
        ActionListener submitListener = new SubmitListener();
        ActionListener resetListener = new ResetListener();
        add(gui);
        add(submitButton);
        submitButton.addActionListener(submitListener);
        add(resetButton);
        resetButton.addActionListener(resetListener);
        gui.add(board);
        textArea = new JTextArea(str, 10,20);
        scr = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        add(scr);
    }

    public class SubmitListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent event) {
            if (inputArray.isEmpty()) {
                for (int i = 1; i < 65; i++) {
                    if (buttons[i].getIcon()!=null) inputArray.add(i-1);
                }
            }

            try {

                solution = new Solution(inputArray,"LDFS", 8);

                if (solutionArray.isEmpty()) {
                    solutionArray = solution.getSolution();
                    textArea.setText(solution.getTraversal());
                }

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }




            File pic = new File("/Users/user/Downloads/src/queen.jpg");
            ImageIcon icon = new ImageIcon(pic.toString());
            icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));

            if (index < solutionArray.size()) {
                timer.start();
            }

            for (int i = 1; i < 65; i++) {
                buttons[i].setIcon(null);
            }
            //str="\n";
            for (int i = 0; i < 8; i++) {
                buttons[solutionArray.get(index).get(i)].setIcon(icon);
                System.out.println(solutionArray.get(index).get(i));
                //str += solutionArray[index][i] + " ";
            }

            JPanel newBoard = new JPanel(new GridLayout(8, 8));
            for (int i = 1; i < 65; i++) {
                newBoard.add(buttons[i]);
            }
            count = 8;
            gui.remove(board);
            board = newBoard;
            textArea.append(str);
            gui.add(board);
            revalidate();
            repaint();
            index++;
            if (index == solutionArray.size()) {
                timer.stop();index=0;
            }
        }
    }

    public class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            index=0;
            gui.remove(board);
            board = createBoard();
            gui.add(board);
            str = "Rastavlay korolev";
            textArea.setText(str);
            revalidate();
            repaint();
        }
    }

    public JPanel createBoard() {
        count = 0;
        class ClickListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                JButton btnForClick =(JButton)event.getSource();
                if (count >= 8) if (btnForClick.getIcon()==null) return; else {btnForClick.setIcon(null); count--; return;}
                if (count < 8)
                    if(btnForClick.getIcon()==null ){
                        try {
                            File pic = new File("/Users/user/Downloads/src/queen.jpg");
                            ImageIcon icon = new ImageIcon(pic.toString());
                            icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));
                            btnForClick.setIcon(icon);
                            count++;
                            System.out.println(btnForClick.getText() + count + " +");
                        } catch (Exception ex) {
                            System.out.println("Something wrong with icon import.");
                        }
                    }else{
                        btnForClick.setIcon(null);
                        count--;
                        System.out.println(count + " -");
                    }
            }
        }

        ClickListener listener = new ClickListener();
        JPanel panel = new JPanel(new GridLayout(8, 8));

        boolean evenColumn = true;

        for (int i = 1; i <= 64; i++) {
            JButton btn = new JButton();
            btn.setPreferredSize(new Dimension(64, 64));
            if (evenColumn == true) {
                if (i % 8 == 0) {
                    evenColumn = false;
                }
                if (i % 2 == 0) {
                    btn.setBackground(Color.WHITE);
                    panel.add(btn);
                } else {
                    btn.setBackground(Color.BLACK);
                    panel.add(btn);
                }
                btn.addActionListener(listener);
                buttons[i] = btn;
            } else {
                if (i % 8 == 0) {
                    evenColumn = true;
                }
                if (i % 2 == 0) {
                    btn.setBackground(Color.BLACK);
                    panel.add(btn);
                } else {
                    btn.setBackground(Color.WHITE);
                    panel.add(btn);
                }
                btn.addActionListener(listener);
                buttons[i] = btn;
            }
        }
        return panel;
    }
}