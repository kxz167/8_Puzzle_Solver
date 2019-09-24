package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;
import eecs.ai.p1.SearchNode;

public class SolveAStar extends Command {

    private final int heuristic;

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

    public static final SolveAStar of(String heuristic) {
        if (heuristic.equals("h1")) {
            return new SolveAStar(1);
        } else {
            return new SolveAStar(2);
        }
    }

    public final List<Directions> solve(Board currentBoard) {
        SearchNode result = loop(currentBoard);
        
        return processSolution(result);
    }

    private final SearchNode loop(Board currentBoard){
        Integer count = currentBoard.getMaxNodes();
        
        SearchNode currentNode = new SearchNode(null, currentBoard.getState(), null, 0);
        discoveryQueue.add(currentNode);

        while (!discoveryQueue.isEmpty()) {
            currentNode = discoveryQueue.poll();

            BoardState currentState = currentNode.getCurrentState();
            currentBoard.addVisited(currentState.hashCode());

            if (currentState.hashCode() == getGoalState().hashCode()) {
                return currentNode;
            }
            if (count != null && --count < 0)
                return null;

            List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());
            for (Directions move : nextMoves) {
                if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                    discoveryQueue.add(new SearchNode(move, BoardState.of(currentState, move), currentNode,
                            currentNode.getNextPathCost()));
                }
            }
        }

        return null;
    }

    private final List<Directions> processSolution(SearchNode finalNode) {

        List<Directions> solutionDirection = new ArrayList<Directions>();
        SearchNode consideredNode = finalNode;

        if(finalNode != null){
            while (consideredNode.hasPrevious()) {
                solutionDirection.add(consideredNode.getTraveleDirections());
                consideredNode = consideredNode.getPreviousNode();
            }
    
            Collections.reverse(solutionDirection);
        }
        
        System.out.println(solutionDirection);

        return solutionDirection;
    }

    @Override
    public final void execute(Board gameBoard) {
        List<Directions> solution = solve(gameBoard);

        for(Directions direction : solution){
            Move.of(direction).execute(gameBoard);
        }
    }

    public final int heuristicOne(BoardState state) {
        List<Integer> consideredState = state.getBoardState();

        int sumMissplaced = 0;

        for (int i = 1; i < consideredState.size(); i++) {
            int consideredValue = consideredState.get(i);

            if (consideredValue != i - 1 && consideredValue != 0)
                sumMissplaced++;
        }

        return sumMissplaced;
    }

    public final int heuristicTwo(BoardState state) {
        List<Integer> consideredState = state.getBoardState();

        int sumMoves = 0;

        for (int i = 1; i < consideredState.size(); i++) {
            int moveCount = 0;
            int difference = 0;

            if (consideredState.get(i) != 0) {
                difference = Math.abs((consideredState.get(i) + 1) - i);
            }

            while (difference != 0) {
                if (difference >= 3) {
                    difference -= 3;
                } else {
                    difference--;
                }
                moveCount++;
            }

            sumMoves += moveCount;
        }
        return sumMoves;
    }
}