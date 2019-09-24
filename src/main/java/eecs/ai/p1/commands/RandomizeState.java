package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Random;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;

public class RandomizeState extends Command {

    private final int numberMoves;

    public RandomizeState(int numberMoves){

        this.numberMoves = numberMoves;
    }

    public static final RandomizeState of (int numberMoves){
        return new RandomizeState(numberMoves);
    }


    @Override
    public final void execute(Board gameBoard){

        for (int i = 0; i < numberMoves; i++){
            ArrayList<Directions> legalMoves = getLegalMoves(gameBoard.getState().getPosition());

            Random numberGenerator = new Random();

            Move.of(
                legalMoves.get(
                    numberGenerator.nextInt(
                        legalMoves.size()
                    )
                )
            ).execute(gameBoard);
        }
    }
}