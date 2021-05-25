package com.company;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import static com.company.StateNode.buildRoot;

public class searchTree {
    StateNode root;

    public searchTree(int[] pos) {
        this.root = buildRoot(pos);
    }
//•	generateChildren (сurrentStateNode, queenIndex, freePositions)
//1.	Для усіх positionNumber у freePositions
//1.1.	newStateInfo = currentStateNode.stateInfo;
//1.2.	newStateInfo[queenIndex] = positionNumber;
//1.3.	Node newState = newNode (parent –> currentStateNode, stateInfo -> newStateInfo);
//1.4.	currentStateNode.children.add(newState);


    private boolean checkState(StateNode currentState) {
        boolean solutionStatus = checkColumns(currentState);
        if (solutionStatus) {
            solutionStatus = checkDiagonals(currentState);
        }
        return solutionStatus;
    }

    private boolean checkColumns(StateNode currentState) {
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 8; j++) {
                if (currentState.positions[i] == currentState.positions[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkDiagonals(StateNode currentState) {
        for (int i = 0; i < 8; i++) {
            int[] checkedItem = {i, currentState.positions[i]};
            for (int j = i + 1; j < 8; j++) {
                int[] comparison = {j, currentState.positions[j]};
                if (Math.abs(checkedItem[0] - comparison[0]) == Math.abs(checkedItem[1] - comparison[1])) {
                    return false;
                }
            }
        }
        return true;
    }

    public Queue<StateNode> BFS(StateNode root, int depth, Queue<StateNode> queue) throws CloneNotSupportedException {
       // int depth = 0;
        if (checkState(root)) {
            queue.add(root);
            return queue;
        }
        if(queue.isEmpty()) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            StateNode v = queue.remove();
            if (checkState(v)) {
               // queue.add(v);
                return queue;
            }
            v.generateChildren(depth);

            depth++;
            for (StateNode child : v.children) {
                queue.add(child);

            }
            BFS(queue.peek(), depth, queue);

        }
        return queue;
    }
}
    /** TODO:
     * BFS()
     * LDFS()
     * IDS()
     *
     */

