package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;

public class SetState extends Command {

    private final BoardState state;

    private SetState(BoardState state){
        this.state = state;
    }

    public static final SetState of(String state){
        
        ArrayList<Integer> newState = new ArrayList<Integer>();
        char[] characters = state.toCharArray();

        for(char c : characters){
            if(c == 'b')
                newState.add(0);
            else{
                newState.add(Character.getNumericValue(c));
            }
        }

        return new SetState(BoardState.of(newState));
    }

    @Override
    public final void execute(Board board){
        board.setState(this.state);
    }
}