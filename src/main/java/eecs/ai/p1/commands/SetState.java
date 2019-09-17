package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;

public class SetState extends Command {

    public SetState(ArrayList<Command> commandList, Board gameBoard){
        super(commandList, gameBoard);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}