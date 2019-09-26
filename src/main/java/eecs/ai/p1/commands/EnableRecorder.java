package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class EnableRecorder extends Command {

    private EnableRecorder(){

    }

    /**
     * Build method for the command to enable recorder
     * @return The command of EnableRecorder
     */
    public static EnableRecorder of(){
        return new EnableRecorder();
    }

    /**
     * Executes the command and sets the board to a recording state.
     */
    @Override
    public void execute(Board board) {
        board.record();
        
    }
    
}