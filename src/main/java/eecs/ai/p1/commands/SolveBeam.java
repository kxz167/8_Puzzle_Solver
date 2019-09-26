package eecs.ai.p1.commands;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;
import eecs.ai.p1.Run;
import eecs.ai.p1.SearchNode;

public class SolveBeam extends Solve {

    private final int k;
    private int nodesGenerated = 0;

    private final Comparator<SearchNode> comparer = new Comparator<SearchNode>() {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return (heuristic(o1.getCurrentState()) + o1.getPathCost())
                    - (heuristic(o2.getCurrentState()) + o2.getPathCost());
        }
    };

    private final Queue<SearchNode> discoveryQueue = new PriorityQueue<SearchNode>(comparer);

    private SolveBeam(int k) {
        initLegalMoves();
        initGoalState();

        this.k = k;
    }

    /**
     * Build a new instance of SolveBeam given a set number of discovery nodes
     * 
     * @param k The number of nodes allowed to be kept from level to level
     * @return The returned SolveBeam command.
     */
    public static final SolveBeam of(int k) {
        return new SolveBeam(k);
    }

    /**
     * Execute the solveBeam on a gameboard.
     */
    @Override
    public final void execute(Board gameBoard) {

        gameBoard.getVisited().clear();

        List<Directions> solution = solve(gameBoard);
        int solutionSize = 0;

        if (solution != null) {
            solutionSize = solution.size();

            if (gameBoard.toPrint()) {
                System.out.println("Solve beam: " + k);
                System.out.println("Solution of length: " + solutionSize);
                System.out.println(solution);
            }

            for (Directions direction : solution) {
                Move.of(direction).execute(gameBoard);
            }
        } 
        else if (gameBoard.toPrint()) {
            System.out.println("Solve beam: " + k);
            System.out.println("No solution was found with given conditions");
        }

        gameBoard.getAnalysis().add(Run.of("Beam", solution != null, nodesGenerated, solutionSize));
    }

    /**
     * Executes the solveBeam algorithm on the current board
     * @param currentBoard The board to be solved with Beam search
     * @return the SearchNode which is the final node at the goal state. Otherwise null
     */
    @Override
    public final SearchNode loop(Board currentBoard) {
        Integer count = currentBoard.getMaxNodes();

        SearchNode currentNode = new SearchNode(null, currentBoard.getState(), null, 0);
        discoveryQueue.add(currentNode);

        while (!discoveryQueue.isEmpty()) {

            Queue<SearchNode> allPossibilities = new PriorityQueue<>(comparer);

            for (SearchNode node : discoveryQueue) {

                BoardState currentState = node.getCurrentState();
                currentBoard.addVisited(currentState.hashCode());

                if (currentState.hashCode() == getGoalState().hashCode()){
                    return node;
                }
                else if (count != null && --count < 0)
                    return null;

                // Discover what is to be searched
                List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());

                for (Directions move : nextMoves) {

                    if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                        //Statistical Count
                        nodesGenerated++;

                        allPossibilities.add(
                                new SearchNode(move, BoardState.of(currentState, move), node, node.getNextPathCost()));
                    }
                }
            }

            discoveryQueue.clear();

            int finalSize = allPossibilities.size();

            for (int i = 0; i < (Math.min(this.k, finalSize)); i++) {
                discoveryQueue.add(allPossibilities.poll());
            }

        }
        //No path was found
        return null;
    }

    /**
     * Calculates the heuristic used to determine a ranking for nodes
     * @param state The board state that is currently used to have the heuristic determined
     * @return The integer value of the heuritic, equal to the manhatten distance.
     */
    public final int heuristic(BoardState state) {
        List<Integer> consideredState = state.getBoardState();

        int sumMoves = 0;

        for (int i = 1; i < consideredState.size(); i++) {
            int moveCount = 0;
            int difference = 0;
            //If the state isn't 0 (current position), find the difference to tile's goal location
            if (consideredState.get(i) != 0) {
                difference = Math.abs((consideredState.get(i) + 1) - i);
            }

            //While there is a difference
            while (difference != 0) {
                //Jump rows by moving 3
                if (difference >= 3) {
                    difference -= 3;
                } else { //Shift left / right by moving 1
                    difference--;
                }
            
                moveCount++;
            }

            sumMoves += moveCount;
        }
        return sumMoves;
    }
}