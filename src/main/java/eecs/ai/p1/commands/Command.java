package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import eecs.ai.p1.Board;
import eecs.ai.p1.CommandType;
import eecs.ai.p1.Directions;

public abstract class Command{

    private ArrayList<Command> commandList;
    private int position;
    private List<ArrayList<Directions>> legalMoves;
    
    /**
     * Initializes a list of legal moves based on the position
     * @return the array of all legal moves given position
     */
    public final void initLegalMoves(){
        List<ArrayList<Directions>> legalMoves = new ArrayList<>();
        legalMoves.add(null);

        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.LEFT, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.LEFT,Directions.DOWN)));
        
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.DOWN, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.DOWN)));
        
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT, Directions.RIGHT)));
        legalMoves.add(new ArrayList<Directions>(Arrays.asList(Directions.UP, Directions.LEFT)));

        this.legalMoves = legalMoves;
    }

    public final ArrayList<Directions> getLegalMoves(int position){
        return legalMoves.get(position);
    }

    // protected final Board gameBoard;
    // public Command(ArrayList<Command> commandList, Board gameBoard){
    //     this.commandList = commandList;
    //     this.gameBoard = gameBoard;
    // }

    // public static final Command of (String inputs){
    //     // return constructor.get()

    //     final EnumMap<CommandType, Function<String, Command>> myConstructor = new EnumMap<>(Map.of(
    //             CommandType.MOVE, inputs -> new Move(inputs),
    //             CommandType.PRINTSTATE, inputs-> new PrintState(),
    //             CommandType.RANDOMIZESTATE, inputs -> new RandomizeState(inputs),
    //             CommandType.SETSTATE, inputs -> new SetState(inputs),
    //             CommandType.SOLVEASTAR, inputs -> new SolveAStar(inputs),
    //             CommandType.SOLVEBEAM, inputs -> new SolveBeam(inputs)
    //         ));

    // }

    public boolean execute(Board board){
        return true;
    }
    
    // @Override
    // public boolean hasNext(){
    //     if(position < commandList.size())
    //         return true;
    //     else{
    //         return false;
    //     }
    // }

    // @Override
    // public Command next(){

    //     Command nextCommand = commandList.get(position++);
    //     return nextCommand;
    // }

}