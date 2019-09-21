package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;

public class Move extends Command {
    private final Directions direction;

    private Move(Directions direction){
        this.direction = direction;
        // super(commandList, gameBoard);
    }

    public static final Move of(String direction){
        // EnumMap<Directions, Integer> newDirection = new EnumMap<>(Directions.class);
        Directions newDirection;

        switch (direction.toLowerCase()){
            case "up":
                newDirection = Directions.UP;
                break;
            case "down":
                newDirection = Directions.DOWN;
                break;
            case "left":
                newDirection = Directions.LEFT;
                break;
            default:
                newDirection = Directions.RIGHT;
                break;
        }

        return new Move(newDirection);
    }

    public static final Move of(Directions direction){
        return new Move(direction);
    }

    // public static final Move of(Directions direction){
    //     EnumMap<Directions, Integer> newDirection = new EnumMap<>(Directions.class);

    //     switch (direction){
    //         case UP:
    //             newDirection.put(Directions.UP, -3);
    //             break;
    //         case DOWN:
    //             newDirection.put(Directions.DOWN, 3);
    //             break;
    //         case LEFT:
    //             newDirection.put(Directions.LEFT, -1);
    //             break;
    //         case RIGHT:
    //             newDirection.put(Directions.RIGHT, 1);
    //             break;
    //     }

    //     return new Move(newDirection);
    // }

    // public static final Move of(){

    // }

    @Override
    public final boolean execute(Board board){
        if(board.getLegalMoves(board.getState().getPosition()).contains(direction))
            if(!board.getVisited().contains(board.getState().peekNext(direction))){
                Collections.swap(board.getState().getBoardState(), board.getState().getPosition(), board.getState().getPosition() + this.direction.getValue());
                
                board.addVisited(board.getState().getBoardState().hashCode());
            }

        return true;
    }

    // @Override
    // public final boolean execute(){
    //     return true;
    // }
}