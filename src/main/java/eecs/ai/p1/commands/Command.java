package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Iterator;

import eecs.ai.p1.Board;

public class Command implements Iterator<Command>{

    private ArrayList<Command> commandList;
    private int position;
    
    protected final Board gameBoard;

    public Command(ArrayList<Command> commandList, Board gameBoard){
        this.commandList = commandList;
        this.gameBoard = gameBoard;
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