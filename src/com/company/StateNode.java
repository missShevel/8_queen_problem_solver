package com.company;

import java.util.LinkedList;

public class StateNode {
    int [] positions  = new int [8];
    LinkedList<StateNode> children;
    StateNode parent;

    public StateNode(int [] pos, StateNode p){
//        if (!checkRows(matrixPositions)){
//           rowInsert(matrixPositions);
//        }
    this.positions = pos;
    this.children = new LinkedList<>();
    this.parent = p;
    if(this.parent != null) {
        this.parent.children.add(this);
    }

    }
    public static StateNode buildRoot(int [] pos){
        return new StateNode(pos, null);
    }

}
