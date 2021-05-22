package com.company;

import static com.company.StateNode.buildRoot;

public class searchTree {
    StateNode root;

    public searchTree(int [] pos){
        this.root = buildRoot(pos);
    }
//•	generateChildren (сurrentStateNode, queenIndex, freePositions)
//1.	Для усіх positionNumber у freePositions
//1.1.	newStateInfo = currentStateNode.stateInfo;
//1.2.	newStateInfo[queenIndex] = positionNumber;
//1.3.	Node newState = newNode (parent –> currentStateNode, stateInfo -> newStateInfo);
//1.4.	currentStateNode.children.add(newState);



    /** TODO:
     * BFS()
     * LDFS()
     * IDS()
     *
     */
}
