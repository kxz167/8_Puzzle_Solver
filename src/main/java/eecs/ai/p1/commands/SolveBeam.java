package eecs.ai.p1.commands;

import java.util.ArrayList;

public class SolveBeam extends Command {

    public SolveBeam(ArrayList<Command> commandList){
        super(commandList);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}