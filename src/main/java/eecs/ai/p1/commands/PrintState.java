package eecs.ai.p1.commands;

import java.util.ArrayList;

import eecs.ai.p1.Board;

public class PrintState extends Command {

    private PrintState(){
        // super(commandList, board);
    }

    public static final PrintState of(){
        return new PrintState();
    }

    
    @Override
    public final boolean execute(Board gameBoard){
        System.out.println("- - - - - - - -" + System.getProperty("line.separator") + gameBoard.getState() + System.getProperty("line.separator") + "- - - - - - - -");
        return true;
    }
}