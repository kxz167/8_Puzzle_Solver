package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;

public class SetState extends Command {

    private final String state;

    private SetState(String state){
        // super(commandList, gameBoard);
        this.state = state;
    }

    public static final SetState of(String state){
        return new SetState(state);
    }

    @Override
    public final boolean execute(Board board){
        board.setBoard(this.state);
        return true;
    }
}