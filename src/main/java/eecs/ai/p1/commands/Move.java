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
            case "down":
                newDirection.put(Directions.DOWN, 3);
            case "left":
                newDirection.put(Directions.LEFT, -1);
            case "right":
                newDirection.put(Directions.RIGHT, 1);
        }

        return new Move(newDirection);
    }

    @Override
    public final boolean execute(Board board){
        for(Directions dir : direction.keySet()){
            switch (board.position()){
                // case board.position():
                    // ;
            }
            Collections.swap(board.getBoard(), board.position(), board.position() + direction.get(dir));
        }

        return true;
        
    }

    // @Override
    // public final boolean execute(){
    //     return true;
    // }
}