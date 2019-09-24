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

    public final void initGoalState(){
        List<Integer> goalList = new ArrayList<>();
        List<Integer> range = IntStream.rangeClosed(0, 8).boxed().collect(Collectors.toList());
        goalList.addAll(range);
        this.goalState = BoardState.of(goalList);
    }

    public final BoardState getGoalState(){
        return this.goalState;
    }

    public final ArrayList<Directions> getLegalMoves(int position){
        return legalMoves.get(position);
    }

    public void execute(Board board);
    
}