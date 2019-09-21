package eecs.ai.p1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BoardState{
    private final List<Integer> state;

    private BoardState(List<Integer> state){
        List<Integer> newState = new ArrayList<Integer>();

        newState.add(-1);
        newState.addAll(state);

        this.state = newState;
    }

    /**
     * Creates a new BoardState with the given state representation in an integer array.
     * @param state Is the state representation as a list of integers. The array starts at index 0;
     * @return
     */
    public static final BoardState of (List<Integer> state){
        return new BoardState(state);
    }

    //TODO Return state (list)
    public final List<Integer> getBoardState(){
        return this.state;
    }

    public final int getPosition(){
        return this.state.indexOf(0);
    }

    public final int peekNext(Directions direction){
        BoardState nextState = new BoardState(state);
        nextState.move(direction);
        return nextState.hashCode();
    }

    public void move(Directions direction){
        Collections.swap(state, this.getPosition(), this.getPosition() + direction.getValue());
    }

    //TODO Return next legal moves
    
    //TODO equals override to check if two states are equal; Might not need if using hashset;

    //TODO hashcode overrides to use the string's hashcode;
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

            if(i % 3 == 0)
            {
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
            }
        }

        return builder.toString();
    }
}
