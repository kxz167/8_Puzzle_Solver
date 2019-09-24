package eecs.ai.p1;

import eecs.ai.p1.commands.PrintState;
import eecs.ai.p1.commands.SetState;

import java.io.FileNotFoundException;

import eecs.ai.p1.commands.*;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        // Board newBoard = Board.of();
        // PrintState.of().execute(newBoard);

        Commander.of("src\\main\\resources\\input").execute();
        // Move.of(Directions.UP).execute(newBoard);
        // Move.of(Directions.LEFT).execute(newBoard);
        // Move.of(Directions.RIGHT).execute(newBoard);
        // RandomizeState.of(20).execute(newBoard);
        // PrintState.of().execute(newBoard);
    }
}
