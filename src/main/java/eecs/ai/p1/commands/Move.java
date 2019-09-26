package eecs.ai.p1.commands;

import java.util.Collections;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;

public class Move extends Command {
    private final Directions direction;

    private Move(Directions direction) {
        initLegalMoves();

        this.direction = direction;
    }

    /**
     * Takes in the value delegated from the commander and creates a new move command with the corresponding direction
     * @param direction The string representation of the direction to be parsed.
     * @return The new Move command with the given direction
     */
    public static final Move of(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                return new Move(Directions.UP);
            case "down":
                return new Move(Directions.DOWN);
            case "left":
                return new Move(Directions.LEFT);
            default:
                return new Move(Directions.RIGHT);
        }
    }

    /**
     * Overloading of the build command in the case of passing directly in a direction.
     * This avoids having to check through cases
     * @param direction The direction that is to be traveled
     * @return The new move based on the passed in direction
     */
    public static final Move of(Directions direction) {
        return new Move(direction);
    }


    /**
     * Executes the move command if the desired direction is in the next legal moves.
     * Prints out corresponding information if printing is required
     */
    @Override
    public final void execute(Board board) {
        BoardState state = board.getState();
        int statePosition = state.getPosition();

        if (getLegalMoves(statePosition).contains(this.direction)) {
            Collections.swap(state.getBoardState(), statePosition, statePosition + this.direction.getValue());
        }

        if(board.toPrint()){
            System.out.println("Move: " + this.direction);
            System.out.println(board.getState());
        }
    }
}