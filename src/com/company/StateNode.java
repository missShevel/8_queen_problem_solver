package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class StateNode implements Cloneable {
    int[] positions;
    ArrayList<StateNode> children;
    StateNode parent;

    public StateNode clone() throws CloneNotSupportedException {
        StateNode clone = (StateNode) super.clone();
        clone.positions = this.positions.clone();
        return clone;
    }

    private StateNode(int[] pos) {
//        if (!checkRows(matrixPositions)){
//           rowInsert(matrixPositions);
//        }
        this.positions = pos;
        this.children = new ArrayList<>();
        this.parent = null;
    }

    private StateNode(int[] pos, StateNode p) {
        this.positions = pos;
        this.children = new ArrayList<>();
        this.parent = p;
    }

//    /**
//     * @param pos    - поточна перестановка
//     * @param p      - попередня перестановка
//     * @param number - номер перестановки конкретно цієї фігури (номер у списку дітей для подальшого пошуку)
//     */


    private void addChild(int[] pos) {

        StateNode child = new StateNode(pos, this);
        this.children.add(child);

    }

    public static StateNode buildRoot(int[] pos) {
        return new StateNode(pos);
    }


    public void generateChildren(int queenIndex) throws CloneNotSupportedException {

        ArrayList<Integer> freePositions = findFreePositions(queenIndex);
        freePositions.remove((Object) queenIndex);
       // int childrenCounter = 0;

        for (int positionNumber : freePositions) {
            StateNode clone = this.clone();
            int[] newStateInfo = clone.positions;
            newStateInfo[queenIndex] = positionNumber;
            this.addChild(newStateInfo);
           // childrenCounter++;
        }
    }

    private ArrayList<Integer> generate() {
        ArrayList<Integer> listOfPositions = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            listOfPositions.add(i);

        }
        return listOfPositions;
    }

    private ArrayList<Integer> findFreePositions(int indexOfQueenToBePlaced) {
        ArrayList<Integer> listOfPositions = generate();
        ArrayList<Integer> positionsUnderAttack = new ArrayList<>();
        for (int i = 0; i < indexOfQueenToBePlaced; i++) {
            positionsUnderAttack.add(this.positions[i] + indexOfQueenToBePlaced - i);
            for (int j = 0; j < listOfPositions.size(); j++) {
                if (listOfPositions.get(j).equals(positionsUnderAttack.get(i))) {
                    listOfPositions.remove((Object)j);
                }
            }
        }
        return listOfPositions;

    }

}


