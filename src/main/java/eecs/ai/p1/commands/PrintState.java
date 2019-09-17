package eecs.ai.p1.commands;

import java.util.ArrayList;

public class PrintState extends Command {
    public PrintState(ArrayList<Command> commandList){
        super(commandList);
    }

    
    @Override
    public final boolean execute(){
        return true;
    }
}