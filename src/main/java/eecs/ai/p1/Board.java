package eecs.ai.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private List<Integer> board;
    private List<Integer> legalMoves;

    public Board(){
        List<Integer> range = IntStream.rangeClosed(1, 9)
            .boxed()
            .collect(Collectors.toList());
        
        this.board = new ArrayList<>(range);

    }

    //TODO Make a method which takes in a string for the state and sets it as the state
    public final boolean setBoard(String board){
        ArrayList<Integer> newBoard = new ArrayList<Integer>();
        newBoard.add(-1);

        char[] characters = board.toCharArray();
        for(char c : characters){
            if(c == 'b')
                newBoard.add(0);
            else{
                newBoard.add(Character.getNumericValue(c));
            }
        }

        this.board = newBoard;
        return true;
    }


    public final List<Integer> getBoard(){
        return this.board;
    }

    public final int position(){
        return board.indexOf(0);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 1; i < board.size(); i++)
        {
            builder.append(board.get(i) == 0 ? " " : board.get(i));
            builder.append("   ");

            if(i % 3 == 0)
            {
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
            }
        }

        return builder.toString();
    }

    public final List<Directions> getLegalMoves(){
        int currentPosition = this.position();
        List<Directions> newLegalMoves = new ArrayList<>(Arrays.asList(Directions.values()));

        if(currentPosition % 3 == 1){
            newLegalMoves.remove(Directions.LEFT);
        }
        else if(currentPosition % 3 == 0){
            newLegalMoves.remove(Directions.RIGHT);
        }
        else{
            //In the middle horizontally
        }

        if(currentPosition <= 3){
            newLegalMoves.remove(Directions.UP);
        }
        else if(7 <= currentPosition){
            newLegalMoves.remove(Directions.DOWN);
        }
        else{
            //In the middle vertically.
        }

        return newLegalMoves;
    }


}