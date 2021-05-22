package com.company;

import java.util.HashMap;
import java.util.LinkedList;

public class StateNode implements Cloneable{
    int [] positions;
    HashMap<Integer,StateNode> children;
    StateNode parent;

public StateNode clone() throws CloneNotSupportedException{
    StateNode clone = (StateNode) super.clone();
    clone.positions = this.positions.clone();
    return clone;
}

    private StateNode(int [] pos) {
//        if (!checkRows(matrixPositions)){
//           rowInsert(matrixPositions);
//        }
        this.positions = pos;
        this.children = new HashMap<>();
        this.parent = null;
    }
    private StateNode(int [] pos, StateNode p){
        this.positions = pos;
        this.children = new HashMap<>();
        this.parent = p;
    }

    /**
     *
     *
     *
     * @param pos - поточна перестановка
     * @param p - попередня перестановка
     * @param number - номер перестановки конкретно цієї фігури (номер у списку дітей для подальшого пошуку)
     */
    private void addChild (int [] pos, int number){
//        if (!checkRows(matrixPositions)){
//           rowInsert(matrixPositions);
//        }
    StateNode child = new StateNode(pos, this);
    this.children.put(number, child);

    }

    public static StateNode buildRoot(int [] pos){
        return new StateNode(pos);
    }


    public void generateChildren(int queenIndex, int [] freePositions) throws CloneNotSupportedException {
        int childrenCounter = 0;

        for (int positionNumber : freePositions){
            StateNode clone = this.clone();
            int [] newStateInfo = clone.positions;
            newStateInfo[queenIndex] = positionNumber;
            this.addChild(newStateInfo,childrenCounter);
            childrenCounter++;
        }

    }

}


