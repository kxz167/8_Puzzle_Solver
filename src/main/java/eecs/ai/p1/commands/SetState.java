package eecs.ai.p1.commands;

import java.util.ArrayList;

public class SetState extends Command {

    public SetState(ArrayList<Command> commandList){
        super(commandList);
    }


    @Override
    public final boolean execute(){
        return true;
    }
}