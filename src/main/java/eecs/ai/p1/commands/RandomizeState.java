package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import eecs.ai.p1.Board;
import eecs.ai.p1.BoardState;
import eecs.ai.p1.Directions;

public class RandomizeState extends Command {

    private final int numberMoves;

    private RandomizeState(int numberMoves) {
        initLegalMoves();

        this.numberMoves = numberMoves;
    }

    /**
     * Builds a new instance of the randomize state command
     * @param numberMoves The number of random moves you want to make
     * @return The new instance of the command to randomize the state
     */
    public static final RandomizeState of(int numberMoves) {
        return new RandomizeState(numberMoves);
    }

    /**
     * Executes the randomization of the board based on the number of moves, and a passed in board.
     */
    @Override
    public final void execute(Board gameBoard) {

        //Removes previous visited states, treat as a new starting point
        gameBoard.getVisited().clear();

        Random numberGenerator;
        
        if (gameBoard.toPrint()){
            
            System.out.println("RandomizeState: " + numberMoves);

            //Generate based off a seed (print evaluation)
            numberGenerator = new Random(391);
        }
        else {
            //Else, generate a completely random random (statistical analysis).
            numberGenerator = new Random();
        }
        
        for (int i = 0; i < numberMoves; i++) {
            BoardState boardState = gameBoard.getState();

            ArrayList<Directions> legalMoves = getLegalMoves(boardState.getPosition());
            gameBoard.addVisited(boardState.hashCode());

            List<Directions> possibleMoves = legalMoves.stream()
                    .filter(move -> !gameBoard.checkVisited(boardState.peekNext(move)))
                    .collect(Collectors.toList());

            //If no more unrepeated states, make a random move to satisfy n moves
            if (possibleMoves.isEmpty()) {
                Move.of(legalMoves.get(numberGenerator.nextInt(legalMoves.size()))).execute(gameBoard);
            }
            else{
                Move.of(possibleMoves.get(numberGenerator.nextInt(possibleMoves.size()))).execute(gameBoard);
            }
            
        }
    }
}