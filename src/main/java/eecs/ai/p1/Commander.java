package eecs.ai.p1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import eecs.ai.p1.commands.*;

//implements Iterable<Command>
public final class Commander{
    // private final File filename;
    private final ArrayList<Command> commands;
    private final Board gameBoard;

    

    private Commander (ArrayList<Command> commands, Board gameBoard){
        this.commands = commands;
        this.gameBoard = gameBoard;
    }
    
    public final Commander of(String filename, Board gameBoard){
        

        Scanner fileScanner = new Scanner(filename);

        
        ArrayList<Command> commands = new ArrayList<>();

        while(fileScanner.hasNext()){
            switch(fileScanner.next()){
                case "setState":
                    commands.add(SetState.of(
                        fileScanner.next() + fileScanner.next() + fileScanner.next()
                    ));
                case "printState":

                case "move":

                case "randomizeState":

                case "solve":

                case "maxNodes":
            }
        }

        return new Commander(commands, gameBoard);
    }

    // @Override
    // public Iterator<Command> iterator() {
    //     return new Command(this.commands, this.gameBoard);
    // }

    // public void add (Command command){
    //     this.commands.add(command);
    // }
}