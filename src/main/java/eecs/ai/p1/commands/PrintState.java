package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;

public class PrintState extends Command {

    public PrintState(ArrayList<Command> commandList, Board board){
        // super(commandList, board);
    }

    
    @Override
    public final boolean execute(){
        System.out.println(gameBoard);
        return true;
    }
}