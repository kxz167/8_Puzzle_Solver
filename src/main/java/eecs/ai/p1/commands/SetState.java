package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;

public class SetState extends Command {

    private final BoardState state;
    private final String stringState;

    private SetState(BoardState state, String stringState){
        this.state = state;
        this.stringState = stringState;
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

        return new SetState(BoardState.of(newState), state);
    }

    @Override
    public final void execute(Board board){
        if(board.toPrint())
            System.out.println("SetState: " + stringState);
        board.setState(this.state);
    }
}