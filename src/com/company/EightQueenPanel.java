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
    private String str = "Insert queens";
    private String algorithm;
    private JPanel gui;
    private int count = 0;
    private JPanel board;
    Solution solution;
    JRadioButton r1;
    JRadioButton r2;
    JRadioButton r3;
    private JButton submitButton = new JButton("GET SOLUTION");
    private JButton resetButton = new JButton("RESET");
    private JButton[] buttons;
    private JTextArea textArea, textArea1;
    private JScrollPane scr;
    private ArrayList<Integer> inputArray = new ArrayList<>();
    ArrayList<ArrayList<Integer>> solutionArray = new ArrayList<>();
    int depth = 8;
    int index = 0;

/*TODO:
1. radioButtons for methods (BFS, LDFS, IDS)
2. textArea for depth for LDFS
3. remove rastavlyau korolev
* **/

    public EightQueenPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(Color.cyan);
        timer = new Timer(500, new SubmitListener());
        gui = new JPanel();
        board = createBoard();
        ActionListener submitListener = new SubmitListener();
        ActionListener resetListener = new ResetListener();
        add(gui);

        textArea = new JTextArea(str, 9,25);
        textArea1 = new JTextArea("8");
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        textArea.setEditable(false);
        scr = new JScrollPane(textArea);
        textArea.setLineWrap(true);
        textArea1.setLineWrap(true);

        add(scr);

        JPanel buttons =new JPanel();
        buttons.setLayout (new BoxLayout (buttons, BoxLayout.Y_AXIS));
        //buttons.setSize(200,200);
        r1 = new JRadioButton("BFS",true);
        r2 = new JRadioButton("IDS");
        r3 = new JRadioButton("LDFS");
        r1.setBounds(20,20,80,30);
        r2.setBounds(50,100, 80,30);
        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(r1);
        rbGroup.add(r2);
        rbGroup.add(r3);
        add(buttons);
        buttons.add(new JLabel("Select Algorithm"));
        buttons.add(r1);
        buttons.add(r2);
        buttons.add(r3);
        buttons.add(new JLabel("LDFS Depth"));
        buttons.add(textArea1);






        add(submitButton);
        submitButton.addActionListener(submitListener);
        add(resetButton);
        resetButton.addActionListener(resetListener);
        gui.add(board);


    }

    public class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {

            if (r1.isSelected()) algorithm = "BFS";
            if (r2.isSelected()) algorithm = "IDS";
            if (r3.isSelected()) algorithm = "LDFS";
            if (inputArray.isEmpty()) {
                for (int i = 1; i < 65; i++) {
                    if (buttons[i].getIcon()!=null) inputArray.add(i-1);
                }
            }
            if (inputArray.size()<8) {
                inputArray.clear();
                textArea.setText("Insert more queens"); return;}

            if (algorithm.equals("LDFS")) {

                String strDepth = textArea1.getText();
                try {depth = Integer.parseInt(strDepth);}
                catch (Exception e) {
                    textArea.setText("Invalid depth value. Please enter int > 0.");
                    return;
                }

                try {solution = new Solution(inputArray, algorithm, depth);

                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            } else
                try {solution = new Solution(inputArray, algorithm);

                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }



            if(!solution.isFound) {
                textArea.setText(solution.getTraversal());
                inputArray.clear();
                solutionArray.clear();
                return;
            }

            if (solutionArray.isEmpty()) {
                solutionArray = solution.getSolution();
                textArea.setText(solution.getTraversal());
            }

            File pic = new File("src/com/company/queen.png");
            ImageIcon icon = new ImageIcon(pic.toString());
            icon.setImage(icon.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT));

            if (index < solutionArray.size()) {
                timer.start();
            }

            for (int i = 1; i < 65; i++) {
                buttons[i].setIcon(null);
            }

            for (int i = 0; i < 8; i++) {
                buttons[solutionArray.get(index).get(i)].setIcon(icon);
                System.out.println(solutionArray.get(index).get(i));
            }

            JPanel newBoard = new JPanel(new GridLayout(8, 8));
            for (int i = 1; i < 65; i++) {
                newBoard.add(buttons[i]);
            }
            count = 8;
            gui.remove(board);
            board = newBoard;
            //textArea.append(str);
            gui.add(board);
            revalidate();
            repaint();
            index++;
            if (index == solutionArray.size()) {
                timer.stop();
                index=0;
                inputArray.clear();
                solutionArray.clear();
            }
        }
    }

    public class ResetListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            solutionArray.clear();
            inputArray.clear();
            index=0;
            gui.remove(board);
            board = createBoard();
            gui.add(board);
            str = "Insert queens";
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
                timer.stop();
                inputArray.clear();
                solutionArray.clear();
                index=0;
                JButton btnForClick =(JButton)event.getSource();
                if (count >= 8) if (btnForClick.getIcon()==null) return; else {btnForClick.setIcon(null); count--; return;}
                if (count < 8)
                    if(btnForClick.getIcon()==null ){
                        try {
                            File pic = new File("src/com/company/queen.png");
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
        buttons = new JButton[65];

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