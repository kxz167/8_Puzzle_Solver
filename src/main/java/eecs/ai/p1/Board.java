package eecs.ai.p1;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {


    private boolean print = true;
    private Integer maxNodes = null;
    
    private List<Integer> board;
    private BoardState state;
    private final HashSet<Integer> visitedStates = new HashSet<>();


    private final Recorder analysis;
    
    private Board(BoardState state){
        this.state = state;
        this.analysis = Recorder.of();
    }

    /**
     * Creates a new board. The new board, given no inputs, will be initialized to the goal state
     * @return The returned Board in the goal state
     */
    public static final Board of(){
        
        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());

        return new Board(BoardState.of(range));
    }

    /**
     * Builds a new board given a current state. This avoids running through collectors if a state is already available.
     * @param state The state the board should replicate
     * @return Returns the newly build Board with a given state.
     */
    public static final Board of(BoardState state){
        return new Board(state);
    }

    /**
     * Boolean indicator as to whether the Board should have typical printouts (For commands)
     * @return The returned boolean, true if prints are desired, false otherwise.
     */
    public final boolean toPrint(){
        return this.print;
    }

    /**
     * Initiates the board to record statistical information.
     */
    public final void record(){
        this.print = false;
    }

    /**
     * Gives the current position of the empty space in the board
     * @return The integer index of the current position.
     */
    public final int position(){
        return board.indexOf(0);
    }

    public final void setState(BoardState state){
        this.state = state;
    }

    public final BoardState getState(){
        return this.state;
    }

    public final void setMaxNodes(int maxNodes){
        this.maxNodes = maxNodes;
    }

    public final Integer getMaxNodes(){
        return this.maxNodes;
    }

    public final void addVisited(Integer state){
        this.visitedStates.add(state);
    }

    public final HashSet<Integer> getVisited(){
        return this.visitedStates;
    }

    public final boolean checkVisited(int state){
        return visitedStates.contains(state);
    }

    public Recorder getAnalysis() {
        return this.analysis;
    }

}