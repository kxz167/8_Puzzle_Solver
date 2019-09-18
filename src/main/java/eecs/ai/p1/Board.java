package eecs.ai.p1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private ArrayList<Integer> board;

    public Board(){
        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());
        
        this.board = new ArrayList<>(range);

    }

    //TODO Make a method which takes in a string for the state and sets it as the state
    public final boolean setBoard(String board){
        ArrayList<Integer> newBoard = new ArrayList<Integer>();
        
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


    public final ArrayList<Integer> getBoard(){
        return this.board;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for(int i = 1; i <= board.size(); i++)
        {
            builder.append(board.get(i - 1) == 0 ? " " : board.get(i - 1));
            builder.append("   ");

            if(i % 3 == 0)
            {
                builder.append(System.getProperty("line.separator"));
                builder.append(System.getProperty("line.separator"));
            }
        }

        return builder.toString();
    }


}