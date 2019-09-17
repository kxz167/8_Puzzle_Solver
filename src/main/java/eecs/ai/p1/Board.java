package eecs.ai.p1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private ArrayList<Integer> board;

    private Board(){
        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());
        
        this.board = new ArrayList<>(range);

    }

    public final ArrayList<Integer> getBoard(){
        return this.board;
    }



}