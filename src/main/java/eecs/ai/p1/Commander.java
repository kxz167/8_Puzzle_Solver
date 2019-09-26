package eecs.ai.p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import eecs.ai.p1.commands.*;

public final class Commander{
    private final ArrayList<Command> commands;
    private final Board gameBoard;

    private Commander (ArrayList<Command> commands, Board gameBoard){
        this.commands = commands;
        this.gameBoard = gameBoard;
    }
    
    /**
     * Builds a new commander to handle a given file
     * @param filename The name for the file holding the commands
     * @return A new commander which will handle the input file
     * @throws FileNotFoundException
     */
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
                    commands.add(MaxNodes.of(fileScanner.nextInt()));
                    break;
                case"recorder":
                    commands.add(EnableRecorder.of());
            }
        }

        fileScanner.close();

        Board gameBoard = Board.of();

        return new Commander(commands, gameBoard);
    }

    /**
     * Execution of the command runs through the commandlist
     */
    public final void execute(){
        for(Command toDo : commands){
            toDo.execute(gameBoard);
        }

        //In the case analytics are required
        if(!this.gameBoard.toPrint()){
            this.gameBoard.getAnalysis().searchCost();
            this.gameBoard.getAnalysis().searchSuccessRate();
        }
    }
}