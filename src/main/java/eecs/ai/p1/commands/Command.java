package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Iterator;

public class Command implements Iterator<Command>{

    private ArrayList<Command> commandList;
    private int position;

    public Command(ArrayList<Command> commandList){
        this.commandList = commandList;
    }

    public boolean execute(){
        return true;
    }
    
    @Override
    public boolean hasNext(){
        if(position < commandList.size())
            return true;
        else{
            return false;
        }
    }

    @Override
    public Command next(){

        Command nextCommand = commandList.get(position++);
        return nextCommand;
    }

}