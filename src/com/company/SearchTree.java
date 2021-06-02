package com.company;
import java.util.*;
import static com.company.StateNode.buildRoot;

    public class SearchTree {
    StateNode root;
    int depthForLDFS;
    String solutionMethod;

    /* Ініціалізація дерева станів */
    public SearchTree(int[] pos, String solutionMethod) {
        this.root = buildRoot(pos);
        this.solutionMethod = solutionMethod;
    }

    /* Ініціалізація дерева станів заданої глибини */
    public SearchTree(int[] pos, String solutionMethod, int d) {
        this.root = buildRoot(pos);
        this.solutionMethod = solutionMethod;
        this.depthForLDFS = d;
    }
    /* Метод для знаходження правильного шляху у дереві вирішення задачі*/
    public ArrayList<ArrayList<Integer>> findSolution(ArrayList<int[]> traversal) throws CloneNotSupportedException {
        ArrayList<ArrayList<Integer>> solution= new ArrayList<>();
        LinkedList<StateNode> path = new LinkedList<>();
        if(!this.solutionMethod.isEmpty()){
            switch (this.solutionMethod) {
                case "BFS" -> path = recreatePath(BFS(this.root, traversal));
                case "LDFS" -> path = recreatePath(LDFS(this.depthForLDFS, this.root, traversal).getState());
                case "IDS" -> path = recreatePath(IDS(this.root, traversal).getState());
            }

            Collections.reverse(path);

            for(StateNode s : path){
                Board state = new Board();
                state.setFigures(s.positions);
                solution.add(state.boardToSquareNumbers());
            }
        }
        return solution;
    }
    /* Метод для перевірки поточного стану на правильність */
    private boolean checkState(StateNode currentState) {
        boolean solutionStatus = checkColumns(currentState);
        if (solutionStatus) {
            solutionStatus = checkDiagonals(currentState);
        }
        return solutionStatus;
    }
    /* Метод для певірки того, чи знаходяться будь-які фігури в одному стовбці */
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
    /* Метод для певірки того, чи знаходяться будь-які фігури на одній діагоналі */
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
    /* Метод для пошуку рішення у дереві за алгоритмом BFS */
    private StateNode BFS(StateNode root, ArrayList<int[]> traversal) throws CloneNotSupportedException {

        Queue<StateNode> queue = new LinkedList<>();
        Queue<StateNode> queue1 = new LinkedList<>();

        if (checkState(root)) {
            return root;
        }
        queue.add(root);
        traversal.add(root.positions);
        while (true) {
            StateNode v;
            while (!queue.isEmpty()) {
                v = queue.poll();
                traversal.add(v.positions);
                if (checkState(v)) {
                    traversal.add(new int[]{v.depth});
                    return v;
                }
                v.generateChildren();
                queue1.addAll(v.children);
            }
            while (!queue1.isEmpty()) {
                queue.add(queue1.poll());
            }

        }
    }
    /* Метод для пошуку рішення у дереві за алгоритмом LDFS */
    private DLSReturn LDFS(int max_depth, StateNode root, ArrayList<int[]> traversal) throws CloneNotSupportedException {
        if (checkState(root)) {
            traversal.add(root.positions);
            traversal.add(new int[]{root.depth});
            return new DLSReturn(root, DLSResult.SOLUTION);
        } else if (/*max_depth == 0*/max_depth == root.depth) {
            return new DLSReturn(DLSResult.CUTOFF);
        } else {
            boolean cutoff_occurred = false;
            traversal.add(root.positions);
            root.generateChildren();
            for (StateNode child : root.children) {
                DLSReturn result = LDFS(max_depth/*-1*/, child, traversal);
                if (result.getResult() == DLSResult.CUTOFF) {
                    cutoff_occurred = true;
                } else if (result.getResult() != DLSResult.FAILURE) {
                    return result;
                }
            }
            if (cutoff_occurred) {
                return new DLSReturn(DLSResult.CUTOFF);
            }
            return new DLSReturn(DLSResult.FAILURE);
        }
    }
    /* Метод для пошуку рішення у дереві за алгоритмом IDS */
    private DLSReturn IDS(StateNode root, ArrayList<int[]> traversal) throws CloneNotSupportedException {
        DLSReturn cutoff = new DLSReturn(DLSResult.CUTOFF);
        for (int i = 1; i < Integer.MAX_VALUE; i++) {
            DLSReturn result = LDFS(i, root, traversal);
            if (result.getResult() != cutoff.getResult()) {
                return result;
            }
        }
        return new DLSReturn(DLSResult.FAILURE);
    }
    /* Метод для відтворення шляху від знайденого рішення до початкового стану */
    private LinkedList<StateNode> recreatePath(StateNode state) {
        LinkedList<StateNode> path = new LinkedList<>();
        if (state == null) {
            return path;
        }
        path.add(state);
        while (state.parent != null) {
            path.add(state.parent);
            state = state.parent;
        }
        path.add(state);
        return path;
    }
/* Внутрішній допоміжний клас для реалізації алгоритму LDFS / IDS */
    private static class DLSReturn {
        private final StateNode state;
        private final DLSResult result;

       /* Конструктор результату роботи алгоритму */
        DLSReturn(DLSResult res) {
            this.result = res;
            this.state = null;
        }
    /* Конструктор успішного результату роботи алгоритму та його кінцевого стану */
        DLSReturn(StateNode state, DLSResult res) {
            this.state = state;
            this.result = res;
        }
    /* Метод отримання стану із об'єкта результату */
        public StateNode getState() {
            return this.state;
        }
    /* Метод отримання статуса із об'єкта результату */
        public DLSResult getResult() {
            return this.result;
        }
    }
    /* Структура для поевернення результатів у алгоритмах LDFS / IDS */
    private enum DLSResult {
        SOLUTION, CUTOFF, FAILURE
    }

}

