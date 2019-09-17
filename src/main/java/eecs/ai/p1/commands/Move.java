package eecs.ai.p1.commands;

import java.util.ArrayList;

public class Move extends Command {

    public Move(ArrayList<Command> commandList){
        super(commandList);
    }

    @Override
    public final boolean execute(){
        return true;
    }
}