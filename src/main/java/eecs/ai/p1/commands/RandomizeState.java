package eecs.ai.p1.commands;

import java.util.ArrayList;

public class RandomizeState extends Command {

    public RandomizeState(ArrayList<Command> commandList){
        super(commandList);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}