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

public class SolveAStar extends Command {

    private final int heuristic;
    private final List<Command> moves = new ArrayList<>();
    private final BoardState goalState;

    //Discovery queue for processing.
    private final Queue<SearchNode> discoveryQueue = new PriorityQueue<SearchNode>(new Comparator<SearchNode>() {
        //Override the comparator based on the heuristic
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

        //Possibly enable this in command
        List<Integer> goalList = IntStream.rangeClosed(0, 8).boxed().collect(Collectors.toList());
        this.goalState = BoardState.of(goalList);

    }
    
    
    public static final SolveAStar of(String heuristic) {
        if (heuristic.equals("h1")) {
            return new SolveAStar(1);
        } else {
            return new SolveAStar(2);
        }
    }

    public final List<Directions> solve(Board currentBoard) {

        
        BoardState state = currentBoard.getState();
        SearchNode firstNode = new SearchNode(null, state, null, 0);
        Integer count = currentBoard.getMaxNodes();


        discoveryQueue.add(firstNode);
        SearchNode currentNode = discoveryQueue.peek();

        DISCOVERY: 
        while (!discoveryQueue.isEmpty()){
            currentNode = discoveryQueue.poll();

            BoardState currentState = currentNode.getCurrentState();
            currentBoard.addVisited(currentState.hashCode());

            if (currentState.hashCode() == goalState.hashCode()) {
                break DISCOVERY;
            }
            if (count != null && --count < 0)
                break DISCOVERY;


            List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());
            for (Directions move : nextMoves) {
                if (!currentBoard.checkVisited(currentState.peekNext(move))) {
                    discoveryQueue.add(new SearchNode(move, BoardState.of(currentState, move), currentNode,
                            currentNode.getNextPathCost()));
                }
            }
        }

        return processSolution(currentNode);

    }

    private final List<Directions> processSolution(SearchNode finalNode){

        List<Directions> solutionDirection = new ArrayList<Directions>();
        Stack<SearchNode> solution = new Stack<>();

        while (finalNode.hasPrevious()) {
            solution.push(finalNode);
            finalNode = finalNode.getPreviousNode();
        }

        while (!solution.isEmpty()) {
            solutionDirection.add(solution.pop().getTraveleDirections());
        }

        System.out.println(solutionDirection);

        return solutionDirection;

    }

    @Override
    public final boolean execute(Board gameBoard) {
        //Get list of moves
        solve(gameBoard);

        //Print out the list

        return true;
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