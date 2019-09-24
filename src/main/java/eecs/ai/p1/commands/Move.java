package eecs.ai.p1.commands;

import java.util.Collections;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;

public class Move extends Command {
    private final Directions direction;

    private Move(Directions direction) {
        initLegalMoves();
        this.direction = direction;
        // super(commandList, gameBoard);
    }

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

    public static final Move of(Directions direction) {
        return new Move(direction);
    }

    @Override
    public final void execute(Board board) {
        if (getLegalMoves(board.getState().getPosition()).contains(direction)) {
            Collections.swap(board.getState().getBoardState(), board.getState().getPosition(),
                    board.getState().getPosition() + this.direction.getValue());
        }
    }
}