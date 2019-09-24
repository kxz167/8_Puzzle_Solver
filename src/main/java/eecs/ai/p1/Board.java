package eecs.ai.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private BoardState state;
    private List<ArrayList<Directions>> legalMoves;
    private Integer maxNodes = null;

    private List<Integer> board;
    // private List<Integer> legalMoves;
    private final HashSet<Integer> visitedStates = new HashSet<>();

    private Board(BoardState state){

        this.initLegalMoves();

        this.state = state;

    }

    public static final Board of(){
        
        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());

        return new Board(BoardState.of(range));
    }

    public static final Board of(BoardState state){
        return new Board(state);
    }

    /**
     * Initializes a list of legal moves based on the position
     * @return the array of all legal moves given position
     */
    public final void initLegalMoves(){
        List<ArrayList<Directions>> legalMoves = new ArrayList<>();
        legalMoves.add(null);

        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.LEFT, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.LEFT,Directions.DOWN)));
        
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.DOWN)));
        
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT)));

        this.legalMoves = legalMoves;
    }

    //TODO Make a method which takes in a string for the state and sets it as the state
    public final void setState(BoardState state){
        this.state = state;
    }

    public final void setMaxNodes(int maxNodes){
        this.maxNodes = maxNodes;
    }

    public final Integer getMaxNodes(){
        return this.maxNodes;
    }

    public final BoardState getState(){
        return this.state;
    }

    // public final List<Integer> getBoard(){
    //     return this.board;
    // }

    public final int position(){
        return board.indexOf(0);
    }

    public final ArrayList<Directions> getLegalMoves(int position){
        return legalMoves.get(position);
    }

    public final HashSet<Integer> getVisited(){
        return this.visitedStates;
    }

    public final void addVisited(Integer state){
        this.visitedStates.add(state);
    }

    public final boolean checkVisited(int state){
        return visitedStates.contains(state);
    }

    // public final String getState(){
    //     return this.board.stream().skip(1).map( n -> n.toString()).collect(Collectors.joining());
    // }
}