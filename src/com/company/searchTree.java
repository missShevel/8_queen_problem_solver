package com.company;

import java.util.*;

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

    public LinkedList<StateNode> BFS(StateNode root) throws CloneNotSupportedException {
       // int depth = 0;
        int depth = 0;
        Queue<StateNode> queue = new LinkedList<>();
        Queue<StateNode> queue1 = new LinkedList<>();

        if (checkState(root)) {
            return recreatePath(root);
        }
        queue.add(root);
        while(true) {
            StateNode v = null;
            while (!queue.isEmpty()) {
                v = queue.poll();
                if (checkState(v)) {
                    return recreatePath(v);
                }
                v.generateChildren(depth);
                for (StateNode child : v.children) {
                    queue1.add(child);
                }
            }

//            if(path.size() == v.children.size()){
//                depth++;
//            }
            //  path.add(v);
            while(!queue1.isEmpty()){
                queue.add(queue1.poll());
            }
            depth++;
        }
    }

    private LinkedList<StateNode> recreatePath(StateNode state){
        LinkedList<StateNode> path = new LinkedList<>();
        path.add(state);
            path.add(state.parent);

        return path;
    }
}
    /** TODO:
     * BFS()
     * LDFS()
     * IDS()
     *
     */

