package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;

public class RandomizeState extends Command {

    private final int numberMoves;

    private RandomizeState(int numberMoves){
        initLegalMoves();
        this.numberMoves = numberMoves;
    }

    public static final RandomizeState of (int numberMoves){
        return new RandomizeState(numberMoves);
    }


    @Override
    public final void execute(Board gameBoard){
        gameBoard.getVisited().clear();

        if(gameBoard.toPrint())
            System.out.println("RandomizeState: " + numberMoves);

        for (int i = 0; i < numberMoves; i++){
            ArrayList<Directions> legalMoves = getLegalMoves(gameBoard.getState().getPosition());
            gameBoard.addVisited(gameBoard.getState().hashCode());
            
            Random numberGenerator;
            if(gameBoard.toPrint()){
                numberGenerator = new Random(391);
            }
            else{
                numberGenerator = new Random();
            }
            
            List<Directions> possibleMoves = legalMoves.stream()
                    .filter(move -> !gameBoard.checkVisited(gameBoard.getState().peekNext(move)))
                    .collect(Collectors.toList());


            System.out.println(Math.max(possibleMoves.size(),0));
            Move.of(
                possibleMoves.get(
                    numberGenerator.nextInt(
                        possibleMoves.size()
                    )
                )
            ).execute(gameBoard);
        }
    }
}