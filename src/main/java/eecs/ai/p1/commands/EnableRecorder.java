package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class EnableRecorder extends Command {

    private EnableRecorder(){

    }

    public static EnableRecorder of(){
        return new EnableRecorder();
    }

    @Override
    public void execute(Board board) {
        board.record();
        
    }
    
}