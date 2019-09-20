package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;

public class Move extends Command {
    private final EnumMap<Directions, Integer> direction;

    private Move(EnumMap<Directions, Integer> direction){
        this.direction = direction;
        // super(commandList, gameBoard);
    }

    public static final Move of(String direction){
        EnumMap<Directions, Integer> newDirection = new EnumMap<>(Directions.class);

        switch (direction.toLowerCase()){
            case "up":
                newDirection.put(Directions.UP, -3);
                break;
            case "down":
                newDirection.put(Directions.DOWN, 3);
                break;
            case "left":
                newDirection.put(Directions.LEFT, -1);
                break;
            case "right":
                newDirection.put(Directions.RIGHT, 1);
                break;
        }

        return new Move(newDirection);
    }

    public static final Move of(Directions direction){
        EnumMap<Directions, Integer> newDirection = new EnumMap<>(Directions.class);

        switch (direction){
            case UP:
                newDirection.put(Directions.UP, -3);
                break;
            case DOWN:
                newDirection.put(Directions.DOWN, 3);
                break;
            case LEFT:
                newDirection.put(Directions.LEFT, -1);
                break;
            case RIGHT:
                newDirection.put(Directions.RIGHT, 1);
                break;
        }

        return new Move(newDirection);
    }

    // public static final Move of(){

    // }

    @Override
    public final boolean execute(Board board){
        for(Directions dir : direction.keySet()){
            if(board.getLegalMoves().contains(dir) && direction.containsKey(dir)){
                Collections.swap(board.getBoard(), board.position(), board.position() + direction.get(dir));
                PrintState.of().execute(board);
                return true;
            }
            else{
                //Do nothing, the move is illegal
            }
        }
        return false;
    }

    // @Override
    // public final boolean execute(){
    //     return true;
    // }
}