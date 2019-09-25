package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class PrintState extends Command {

    private PrintState(){
    }

    public static final PrintState of(){
        return new PrintState();
    }

    
    @Override
    public final void execute(Board gameBoard){
        System.out.println("PrintState:");
        System.out.println(gameBoard.getState());
    }
}