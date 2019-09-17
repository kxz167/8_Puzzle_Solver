package eecs.ai.p1.commands;

import java.util.ArrayList;

public class SolveAStar extends Command {

    public SolveAStar(ArrayList<Command> commandList){
        super(commandList);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}