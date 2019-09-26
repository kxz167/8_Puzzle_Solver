package eecs.ai.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardState{
    private final List<Integer> state;

    private BoardState(List<Integer> newState){
        
        this.state = newState;
    }

    /**
     * Creates a new BoardState with the given state representation in an integer array.
     * @param state Is the state representation as a list of integers. The array starts at index 0;
     * @return The new built boardstate initialized to a given state.
     */
    public static final BoardState of (List<Integer> state){

        List<Integer> newState = new ArrayList<Integer>();

        newState.add(-1);
        newState.addAll(state);

        return new BoardState(newState);
    }

    /**
     * Builds a new board state in the direction given from the current one
     * @param state The previous board state that should be altered
     * @param direction The direction to move the previous boardState
     * @return The new board state which has been moved
     */
    public static final BoardState of (BoardState state, Directions direction){
        List<Integer> boardState = state.getBoardState();

        //Creates a deep copy of the original state
        ArrayList<Integer> copy = new ArrayList<Integer>(boardState.size());
        for(Integer i : boardState){
            copy.add(Integer.valueOf(i));
        }

        BoardState returnedState = new BoardState(copy);
        returnedState.move(direction);

        return returnedState;
    }

    /**
     * Discovers the next state through creating a new boardState utilized for checking visited
     * @param direction The direction that we want to peek the next state for
     * @return The integer representing the hashcode of the next state. 
     */
    public final int peekNext(Directions direction){
        ArrayList<Integer> copy = new ArrayList<Integer>(state.size());
        
        for(Integer i : state){
            copy.add(Integer.valueOf(i));
        }

        BoardState nextState = new BoardState(copy);
        nextState.move(direction);
        return nextState.hashCode();
    }

    /**
     * Moves the current position on the board state in a direction
     * @param direction The direction to be moved
     */
    public void move(Directions direction){
        Collections.swap(state, this.getPosition(), this.getPosition() + direction.getValue());
    }

    
    public final List<Integer> getBoardState(){
        return this.state;
    }

    public final int getPosition(){
        return this.state.indexOf(0);
    }

    @Override
    public int hashCode(){
        return state.toString().hashCode();
    }

    /**
     * Override for the toString to print out a good representation of the board.
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 1; i < state.size(); i++)
        {
            builder.append(state.get(i) == 0 ? " " : state.get(i));
            builder.append("   ");

            if(i == 3 || i == 6)
            {
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
            }
        }

        return builder.toString();
    }
}
