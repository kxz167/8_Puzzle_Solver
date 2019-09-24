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

public class SolveBeam extends Command {

    private final int k;

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

    public static final SolveBeam of(int k) {

        return new SolveBeam(k);
    }

    @Override
    public final void execute(Board gameBoard) {
        solveBeam(gameBoard);
    }

    public final List<Directions> solveBeam(Board currentBoard) {
        SearchNode result = loop(currentBoard);
        
        return processSolution(result);
    }

    private final SearchNode loop(Board currentBoard){
        Integer count = currentBoard.getMaxNodes();

        SearchNode currentNode = new SearchNode(null, currentBoard.getState(), null, 0);
        discoveryQueue.add(currentNode);

        while (!discoveryQueue.isEmpty()) {

            Queue<SearchNode> allPossibilities = new PriorityQueue<>(comparer);

            for (SearchNode node : discoveryQueue) {

                BoardState currentState = node.getCurrentState();
                currentBoard.addVisited(currentState.hashCode());

                if (currentState.hashCode() == getGoalState().hashCode())
                    return node;

                if(count != null && --count < 0)
                    return node;

                    
                // Discover what is to be searched
                List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());

                for (Directions move : nextMoves) {

                    if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                        allPossibilities.add(
                                new SearchNode(move, 
                                                BoardState.of(currentState, move), 
                                                node, 
                                                node.getNextPathCost()
                                            )
                        );
                    }
                }
            }

            discoveryQueue.clear();

            int finalSize = allPossibilities.size();
            // System.out.println(finalSize);

            for (int i = 0; i < (Math.min(this.k, finalSize)); i++) {
                discoveryQueue.add(allPossibilities.poll());
            }
            // System.out.println(discoveryQueue.size());

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

    public final int heuristic(BoardState state) {
        List<Integer> consideredState = state.getBoardState();
        int sumMoves = 0;

        for (int i = 1; i < consideredState.size(); i++) {
            int moveCount = 0;
            int difference = 0;

            if (consideredState.get(i) != 0)
                difference = Math.abs((consideredState.get(i) + 1) - i);

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