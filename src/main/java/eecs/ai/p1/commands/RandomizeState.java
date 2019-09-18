package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;

public class RandomizeState extends Command {

    public RandomizeState(ArrayList<Command> commandList, Board gameBoard){
        // super(commandList, gameBoard);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}