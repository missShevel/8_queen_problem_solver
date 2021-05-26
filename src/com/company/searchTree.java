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

    public StateNode BFS(StateNode root) throws CloneNotSupportedException {

        Queue<StateNode> queue = new LinkedList<>();
        Queue<StateNode> queue1 = new LinkedList<>();

        if (checkState(root)) {
            return root;
        }
        queue.add(root);
        while(true) {
            StateNode v;
            while (!queue.isEmpty()) {
                v = queue.poll();
                if (checkState(v)) {
                    return v;
                }
                v.generateChildren();
                for (StateNode child : v.children) {
                    queue1.add(child);
                }
            }

            while(!queue1.isEmpty()){
                queue.add(queue1.poll());
            }

        }
    }

    public DLSReturn LDFS(int max_depth, StateNode root) throws CloneNotSupportedException {
        if(checkState(root)){
            return new DLSReturn(root, DLSResult.SOLUTION);
        }
        else if(max_depth == 0){
            return new DLSReturn(DLSResult.CUTOFF);
        }
        else {
            boolean cutoff_occurred = false;
            root.generateChildren();
            for (StateNode child : root.children){
                DLSReturn result = LDFS(max_depth - 1, child);
                if(result.getResult() == DLSResult.CUTOFF){
                    cutoff_occurred = true;
                }
                else if(result.getResult() != DLSResult.FAILURE){
                    return result;
                }
            }
            if(cutoff_occurred){
                return new DLSReturn(DLSResult.CUTOFF);
            }
            return new DLSReturn(DLSResult.FAILURE);
        }
    }

    static class DLSReturn {
        private final StateNode state;
        private final DLSResult result;

        DLSReturn(DLSResult res){
            this.result = res;
            this.state = null;
        }

        DLSReturn(StateNode state, DLSResult res){
            this.state = state;
            this.result = res;
        }

        public StateNode getState(){
            return this.state;
        }
        public DLSResult getResult(){
            return this.result;
        }
    }

    private enum DLSResult {
        SOLUTION, CUTOFF, FAILURE
    }

    public DLSReturn IDS(StateNode root) throws CloneNotSupportedException {
        DLSReturn cutoff = new DLSReturn(DLSResult.CUTOFF);
        for (int i = 1; i < Integer.MAX_VALUE; i++){
            DLSReturn result = LDFS(i, root);
            if(result.getResult() != cutoff.getResult()){
                return result;
            }
        }
        return new DLSReturn(DLSResult.FAILURE);
    }

    public LinkedList<StateNode> recreatePath(StateNode state){
        LinkedList<StateNode> path = new LinkedList<>();
        if(state == null){
            return path;
        }
        path.add(state);
        while(state.parent != null) {
            path.add(state.parent);
            state = state.parent;
        }
        path.add(state);
        return path;
    }


}
    /** TODO:


     * IDS()
     *
     */

