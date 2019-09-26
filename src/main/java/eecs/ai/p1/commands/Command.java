package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;

public abstract class Command{

    private List<ArrayList<Directions>> legalMoves;
    private BoardState goalState;
    
    /**
     * Initializes a list of legal moves based on the position
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


    /**
     * Initializes the reference goal state for use within commands
     */
    public final void initGoalState(){
        List<Integer> goalList = new ArrayList<>();
        List<Integer> range = IntStream.rangeClosed(0, 8).boxed().collect(Collectors.toList());
        goalList.addAll(range);
        this.goalState = BoardState.of(goalList);
    }

    //TODO Move these to board?

    /**
     * Accessor for the goal state of the 8-puzzle
     * @return the BoardState representing the goal state
     */
    public final BoardState getGoalState(){
        return this.goalState;
    }

    /**
     * Get the possible legal moves given the board position
     * @param position
     * @return
     */
    public final ArrayList<Directions> getLegalMoves(int position){
        return legalMoves.get(position);
    }

    //Method requirement for execute method.
    public abstract void execute(Board board);
    
}