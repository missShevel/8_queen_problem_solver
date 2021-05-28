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

    /* Метод для ініціалізації кореня дерева */
    public static StateNode buildRoot(int[] pos) {
        return new StateNode(pos);
    }

    /* Метод для розвертання вузла */
    public void generateChildren() throws CloneNotSupportedException {
        int queenIndex = this.depth;
        ArrayList<Integer> freePositions = findFreePositions(queenIndex);
        if (!freePositions.isEmpty()) {
            for (int positionNumber : freePositions) {
                StateNode clone = this.clone();
                int[] newStateInfo = clone.positions;
                newStateInfo[queenIndex] = positionNumber;
                this.addChild(newStateInfo);

            }
        }
    }

    /* Метод для додавання дочірніх вузлів */
    private void addChild(int[] pos) {

        StateNode child = new StateNode(pos, this);
        this.children.add(child);

    }
    /* Метод для знаходження варіантів розстановки фігури в дочірніх вузлах для їх ініціалізації */
    private ArrayList<Integer> findFreePositions(int indexOfQueenToBePlaced) {
        ArrayList<Integer> freePositions1 = new ArrayList<>();
        ArrayList<Integer> freePositions = new ArrayList<>();
        ArrayList<Integer> listOfPositions = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7));
        int s = listOfPositions.size();
        Set<Integer> positionsUnderAttack = new HashSet<>();

        for (int i = 0; i < indexOfQueenToBePlaced; i++) {
            positionsUnderAttack.add(this.positions[i] + indexOfQueenToBePlaced - i);
            positionsUnderAttack.add(this.positions[i] - indexOfQueenToBePlaced - i);

        }
        for (Integer avalPos : listOfPositions) {
            if (!positionsUnderAttack.contains(avalPos)) {
                freePositions1.add(avalPos);
            }
        }
        for (Integer integer : freePositions1) {
            for (int k = 0; k < indexOfQueenToBePlaced; k++) {
                if (integer == this.positions[k]) {
                    positionsUnderAttack.add(integer);
                }
            }
        }

        for (Integer avalPos : listOfPositions) {
            if (!positionsUnderAttack.contains(avalPos)) {
                freePositions.add(avalPos);
            }
        }
        return freePositions;

    }

}


