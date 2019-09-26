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

public class SolveAStar extends Solve {

    private final int heuristic;
    private int nodesGenerated = 0;

    // Discovery queue for processing.
    private final Queue<SearchNode> discoveryQueue = new PriorityQueue<SearchNode>(new Comparator<SearchNode>() {
        // Override the comparator based on the heuristic
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            if (heuristic == 1)
                return (heuristicOne(o1.getCurrentState()) + o1.getPathCost())
                        - (heuristicOne(o2.getCurrentState()) + o2.getPathCost());

            return (heuristicTwo(o1.getCurrentState()) + o1.getPathCost())
                    - (heuristicTwo(o2.getCurrentState()) + o2.getPathCost());
        }
    });

    private SolveAStar(int heuristic) {

        this.heuristic = heuristic;

        initLegalMoves();
        initGoalState();

    }

    /**
     * Build method to create a SolveAStar command based off of a string input
     * 
     * @param heuristic The utilized heuristic type to be used
     * @return Returns the new command with the heuristic type set
     */
    public static SolveAStar of(String heuristic) {
        if (heuristic.equals("h1")) {
            return new SolveAStar(1);
        } else {
            return new SolveAStar(2);
        }
    }

    /**
     * Executes the process of solving with AStar, Printing the required information
     * Making the corresponding moves to the gameboard.
     */
    @Override
    public final void execute(Board gameBoard) {

        gameBoard.getVisited().clear();

        List<Directions> solution = solve(gameBoard);
        int solutionSize = 0;

        if (solution != null) {
            solutionSize = solution.size();

            if (gameBoard.toPrint()) {
                System.out.println("Solve A-star h" + heuristic);
                System.out.println("Solution of length: " + solutionSize);
                System.out.println(solution);
            }

            for (Directions direction : solution) {
                Move.of(direction).execute(gameBoard);
            }
        } else if (gameBoard.toPrint()) {
            System.out.println("Solve A-star h" + heuristic);
            System.out.println("No solution was found");
        }

        gameBoard.getAnalysis().add(Run.of("A-star h" + heuristic, solution != null, nodesGenerated, solutionSize));
    }

    /**
     * Implements the A* algorithm based on given heuristic functions through a priority queue
     * @param currentBoard The current board to be solved with the A* algorithm
     * @return Returns the node of the solution, null if none exists
     */
    @Override
    public final SearchNode loop(Board currentBoard) {
        //Restrictive count, null if no restriction
        Integer count = currentBoard.getMaxNodes();

        SearchNode currentNode = new SearchNode(null, currentBoard.getState(), null, 0);
        discoveryQueue.add(currentNode);

        while (!discoveryQueue.isEmpty()) {
            currentNode = discoveryQueue.poll();

            BoardState currentState = currentNode.getCurrentState();
            currentBoard.addVisited(currentState.hashCode());

            //Goal state reached
            if (currentState.hashCode() == getGoalState().hashCode()) {
                return currentNode;
            }
            else if (count != null && --count < 0)
                return null;

            //Discovery: look through next moves
            List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());

            for (Directions move : nextMoves) {
                
                if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                    //Statistical counts
                    nodesGenerated++;

                    discoveryQueue.add(new SearchNode(move, BoardState.of(currentState, move), currentNode,
                            currentNode.getNextPathCost()));
                }
            }
        }
        //Nothing left in discovery queue, return no solution
        return null;
    }

    /**
     * The function to calculate heuristic one
     * @param state The current board state
     * @return The integer representing the missplaced number of tiles
     */
    public final int heuristicOne(BoardState state) {
        List<Integer> consideredState = state.getBoardState();

        int sumMissplaced = 0;

        for (int i = 1; i < consideredState.size(); i++) {
            int consideredValue = consideredState.get(i);

            //Check if in the right index, and if not 0 (don't count missplaced 0 tile)
            if (consideredValue != i - 1 && consideredValue != 0)
                sumMissplaced++;
        }

        return sumMissplaced;
    }


    /**
     * The function to calculate the heuristic value two based on a state
     * @param state The input board state to calculate the heuristic on
     * @return The integer representing the manhatten distance of the board.
     */
    public final int heuristicTwo(BoardState state) {
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