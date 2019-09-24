package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;
import eecs.ai.p1.SearchNode;

public class SolveBeam extends Command {

    private final int k;

    private final List<Command> moves = new ArrayList<>();

    private final BoardState goalState;

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

        List<Integer> goalList = new ArrayList<>();
        // goalList.add(-1);

        List<Integer> range = IntStream.rangeClosed(0, 8).boxed().collect(Collectors.toList());

        goalList.addAll(range);
        this.goalState = BoardState.of(goalList);

        this.k = k;
    }

    public static final SolveBeam of(int k) {

        return new SolveBeam(k);
    }

    @Override
    public final boolean execute(Board gameBoard) {
        solveBeam(gameBoard);
        return true;
    }

    public final List<Directions> solveBeam(Board currentBoard) {
        // begin at the first direction
        List<Directions> solutionDirection = new ArrayList<Directions>();
        Stack<SearchNode> solution = new Stack<>();
        BoardState state = currentBoard.getState();
        SearchNode firstNode = new SearchNode(null, state, null, 0);

        SearchNode finalNode = new SearchNode(null, null, null, 0);

        discoveryQueue.add(firstNode);

        //Can be null
        Integer count = currentBoard.getMaxNodes();

        DISCOVERY: 
        while (!discoveryQueue.isEmpty()) {

            Queue<SearchNode> allPossibilities = new PriorityQueue<>(comparer);

            for (SearchNode node : discoveryQueue) {

                // Prepare for searching
                BoardState currentState = node.getCurrentState();

                currentBoard.addVisited(currentState.hashCode());


                if (currentState.hashCode() == goalState.hashCode()){
                    // System.out.println(currentState);
                    finalNode = node;
                    break DISCOVERY;
                }

                if(count != null && --count < 0)
                        break DISCOVERY;

                    
                // Discover what is to be searched
                List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());

                for (Directions move : nextMoves) {

                    if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                        allPossibilities.add(
                                new SearchNode(move, BoardState.of(currentState, move), node, node.getNextPathCost()));
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

        while(finalNode.hasPrevious()){
            solution.push(finalNode);
            finalNode = finalNode.getPreviousNode();
        }
        
        while(!solution.isEmpty()){
            solutionDirection.add(solution.pop().getTraveleDirections());
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