package eecs.ai.p1;

import java.io.File;
import java.io.FileNotFoundException;
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
    
    public static final Commander of(String filename) throws FileNotFoundException{

        Scanner fileScanner = new Scanner(new File(filename));
        
        ArrayList<Command> commands = new ArrayList<>();

        while(fileScanner.hasNext()){
            switch(fileScanner.next()){
                case "setState":
                    commands.add(SetState.of(
                        fileScanner.next() + fileScanner.next() + fileScanner.next()
                    ));
                    break;
                case "printState":
                    commands.add(PrintState.of());
                    break;
                case "move":
                    commands.add(Move.of(
                        fileScanner.next()
                    ));
                    break;
                case "randomizeState":
                    commands.add(RandomizeState.of(fileScanner.nextInt()));
                    break;
                case "solve":
                    String next = fileScanner.next();
                    if (next.equals("A-star")){
                        commands.add(SolveAStar.of(fileScanner.next()));
                    }
                    else if(next.equals("beam")){
                        commands.add(SolveBeam.of(fileScanner.nextInt()));
                    }
                    break;
                case "maxNodes":
                    // commands.add(MaxNodes.of(fileScanner.nextInt()));
                    break;
            }
        }

        fileScanner.close();

        Board gameBoard = Board.of();

        System.out.println(commands);
        return new Commander(commands, gameBoard);
    }

    public final void execute(){
        for(Command toDo : commands){
            toDo.execute(gameBoard);
        }
    }

    // @Override
    // public Iterator<Command> iterator() {
    //     return new Command(this.commands, this.gameBoard);
    // }

    // public void add (Command command){
    //     this.commands.add(command);
    // }
}