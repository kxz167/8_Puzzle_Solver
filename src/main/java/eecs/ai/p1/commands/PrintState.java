package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class PrintState extends Command {

    private PrintState(){
    }

    /**
     * Creates a new printstate command
     * @return The returned printstate command
     */
    public static final PrintState of(){
        return new PrintState();
    }

    /**
     * The execution of the print state prints out the game's state
     */
    @Override
    public final void execute(Board gameBoard){
        System.out.println("PrintState:");
        System.out.println(gameBoard.getState());
    }
}