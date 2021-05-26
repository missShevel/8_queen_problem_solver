package com.company;

import java.util.*;

public class StateNode implements Cloneable {
    int[] positions;
    ArrayList<StateNode> children;
    StateNode parent;
    int depth;

    protected StateNode clone() throws CloneNotSupportedException {
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
        this.depth = 0;
    }

    private StateNode(int[] pos, StateNode p) {
        this.positions = pos;
        this.children = new ArrayList<>();
        this.parent = p;
        this.depth = p.depth + 1;
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


    public void generateChildren(/*int queenIndex*/) throws CloneNotSupportedException {
int queenIndex = this.depth;
        ArrayList<Integer> freePositions = findFreePositions(queenIndex);

       // int childrenCounter = 0;
    if(!freePositions.isEmpty()) {
    for (int positionNumber : freePositions) {
        StateNode clone = this.clone();
        int[] newStateInfo = clone.positions;
        newStateInfo[queenIndex] = positionNumber;
        this.addChild(newStateInfo);
        // childrenCounter++;
    }
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
        ArrayList<Integer> freePositions1 = new ArrayList<>();
        ArrayList<Integer> freePositions = new ArrayList<>();
        ArrayList<Integer> listOfPositions = generate();
        int s = listOfPositions.size();
        Set<Integer> positionsUnderAttack = new HashSet<>();
        for (int i = 0; i < indexOfQueenToBePlaced; i++) {
            positionsUnderAttack.add(this.positions[i] + indexOfQueenToBePlaced - i);
            positionsUnderAttack.add(this.positions[i] - indexOfQueenToBePlaced - i);

        }
          for(Integer avalPos : listOfPositions){
              if(!positionsUnderAttack.contains(avalPos)){
                  freePositions1.add(avalPos);
              }
          }
          for(int j = 0; j < freePositions1.size(); j++){
              for(int k = 0; k < indexOfQueenToBePlaced; k++){
                  if(freePositions1.get(j) == this.positions[k]){
                      positionsUnderAttack.add(freePositions1.get(j));
                  }
              }
          }
        for(Integer avalPos : listOfPositions){
            if(!positionsUnderAttack.contains(avalPos)){
                freePositions.add(avalPos);
            }
        }
        return freePositions;

    }

}


