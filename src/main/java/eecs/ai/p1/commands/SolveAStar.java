package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Arrays;
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
    private final Queue<SearchNode> discoveryQueue = new PriorityQueue<SearchNode>(new Comparator<SearchNode>() {
        @Override
        public int compare(SearchNode o1, SearchNode o2){
            if(heuristic == 1)
                return (heuristicOne(o1.getCurrentState()) + o1.getPathCost()) - (heuristicOne(o2.getCurrentState()) + o2.getPathCost());
            
            return (heuristicTwo(o1.getCurrentState()) + o1.getPathCost()) - (heuristicTwo(o2.getCurrentState()) + o2.getPathCost());
        }
    });

    private final BoardState goalState;

    private SolveAStar(int heuristic){
        this.heuristic = heuristic;
        initLegalMoves();

        List<Integer> goalList = new ArrayList<>();
        // goalList.add(-1);

        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());

        goalList.addAll(range);
        this.goalState = BoardState.of(goalList);



    }

    public static final SolveAStar of(String heuristic){
        if(heuristic.equals("h1")){
            return new SolveAStar(1);
        }
        else{
            return new SolveAStar(2);
        }
    }

    @Override
    public final boolean execute(Board gameBoard){
        solveH2(gameBoard);
        
        return true;
    }

    public final List<Directions> solveH2(Board currentBoard){
        List<Directions> solutionDirection = new ArrayList<Directions>();
        Stack<SearchNode> solution = new Stack<>();
        BoardState state = currentBoard.getState();
        SearchNode firstNode = new SearchNode(null, state, null, 0);

        // boolean goalReached = false;

        discoveryQueue.add(firstNode);

        // System.out.println(!discoveryQueue.isEmpty());

        SearchNode currentNode = discoveryQueue.peek();

        // int count = 7;

        DISCOVERY:
        while (!discoveryQueue.isEmpty() /*&& count != 0*/){
            // System.out.println();
            // System.out.println("Take off the queue");
            currentNode = discoveryQueue.poll();

            // System.out.println("Chosen direction: "+ currentNode.getTraveleDirections());
            
            // System.out.println(currentNode.getCurrentState());
            // System.out.println(goalState.hashCode());
            // System.out.println("Yes2");
            BoardState currentState = currentNode.getCurrentState();
            currentBoard.addVisited(currentState.hashCode());
            if(currentState.hashCode() == goalState.hashCode()){

                System.out.println("Break");
                break DISCOVERY;

            }

            List<Directions> nextMoves = this.getLegalMoves(currentState.getPosition());
            // System.out.println(nextMoves);

            // System.out.println("Goal State: " + goalState.hashCode());
            // System.out.println(goalState);
            // System.out.println("Current State: " + currentState.hashCode());

            // for(SearchNode nade: discoveryQueue){
            //     System.out.print(nade.getCurrentState().hashCode() + " ,");
                
            // }
            // System.out.println();
            // System.out.println(discoveryQueue);
            // System.out.println(currentState);
            // System.out.println("Visited: " + currentBoard.getVisited());
            for(Directions move : nextMoves){
                // System.out.println("If: " + move);
                // System.out.println("Target consideration: " + move + " Has " + currentState.peekNext(move));

                if(!currentBoard.checkVisited(currentState.peekNext(move))){
                    // System.out.println("I haven't been visited");
                    discoveryQueue.add(new SearchNode(move, BoardState.of(currentState, move), currentNode, currentNode.getNextPathCost()));
                }
            }
            // System.out.println(discoveryQueue);
            // System.out.println();
            // count --;
        }

        //TODO THis will run regardless of a solution or not

        while(currentNode.hasPrevious()){
            solution.push(currentNode);
            currentNode = currentNode.getPreviousNode();
        }
        

        while(!solution.isEmpty()){
            solutionDirection.add(solution.pop().getTraveleDirections());
        }

        // for(SearchNode node : solution){
        //     if (node != null){
        //         solutionDirection.add(node.getTraveleDirections());
        //     }
        // }

        System.out.println(solutionDirection);

        return solutionDirection;

    }

    // private final Directions solveH2Recurse(BoardState state){
    //     List<Directions> possibilites = this.getLegalMoves(state.getPosition());

    //     for(Directions move : possibilites){

    //     }
    //     Command nextMove = Move.of()
    // }

    // public final int heuristicOne(BoardState state){

    // }

    public final int heuristicTwo(BoardState state){
        // System.out.println("USE H2");
        List<Integer> consideredState = state.getBoardState();

        int sumMoves = 0;

        for(int i = 1; i < consideredState.size(); i++){
            int moveCount = 0;
            // System.out.println("Get: " + consideredState.get(i));
            // System.out.println("i: " + i);

            int difference = 0;

            if(consideredState.get(i) != 0){
                difference = Math.abs((consideredState.get(i) + 1 ) - i);
            }

            while(difference != 0){
                if(difference >= 3){
                    difference-=3;
                }
                else{
                    difference--;
                }
                moveCount++;
            }

            

            sumMoves += moveCount;
            // System.out.println("For: i = " + i + ", tile = " + consideredState.get(i) + ", has now: " + sumMoves + "Moves");

        }
        // System.out.println(state);
        // System.out.println(state.hashCode() + " Sum Moves: " + sumMoves);
        return sumMoves;
    }

    public final int heuristicOne(BoardState state){
        // System.out.println("USE H1");
        List<Integer> consideredState = state.getBoardState();

        int sumMissplaced = 0;

        for(int i = 1; i < consideredState.size(); i++){
            int consideredValue = consideredState.get(i);

            if(consideredValue != i - 1 && consideredValue != 0)
                sumMissplaced ++;
        }

        return sumMissplaced;
    }
}