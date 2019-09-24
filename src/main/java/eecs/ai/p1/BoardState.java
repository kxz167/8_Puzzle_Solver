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
     * @return
     */
    public static final BoardState of (List<Integer> state){

        List<Integer> newState = new ArrayList<Integer>();

        newState.add(-1);
        newState.addAll(state);

        return new BoardState(newState);
    }

    public static final BoardState of (BoardState state, Directions direction){
        ArrayList<Integer> copy = new ArrayList<Integer>(state.getBoardState().size());
        for(Integer i : state.getBoardState()){
            copy.add(Integer.valueOf(i));
        }

        BoardState returnedState = new BoardState(copy);
        returnedState.move(direction);
        return returnedState;
    }

    public final List<Integer> getBoardState(){
        return this.state;
    }

    public final int getPosition(){
        return this.state.indexOf(0);
    }

    public final int peekNext(Directions direction){
        ArrayList<Integer> copy = new ArrayList<Integer>(state.size());
        for(Integer i : state){
            copy.add(Integer.valueOf(i));
        }

        BoardState nextState = new BoardState(copy);
        nextState.move(direction);
        return nextState.hashCode();
    }

    public void move(Directions direction){
        Collections.swap(state, this.getPosition(), this.getPosition() + direction.getValue());
    }

    @Override
    public int hashCode(){
        return state.toString().hashCode();
    }

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
