package eecs.ai.p1;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private BoardState state;
    private Integer maxNodes = null;

    private List<Integer> board;
    private final HashSet<Integer> visitedStates = new HashSet<>();

    private Board(BoardState state){
        this.state = state;
    }

    public static final Board of(){
        
        List<Integer> range = IntStream.rangeClosed(0, 8)
            .boxed()
            .collect(Collectors.toList());

        return new Board(BoardState.of(range));
    }

    public static final Board of(BoardState state){
        return new Board(state);
    }

    public final void setState(BoardState state){
        this.state = state;
    }

    public final BoardState getState(){
        return this.state;
    }

    public final void setMaxNodes(int maxNodes){
        this.maxNodes = maxNodes;
    }

    public final Integer getMaxNodes(){
        return this.maxNodes;
    }



    public final int position(){
        return board.indexOf(0);
    }



    public final void addVisited(Integer state){
        this.visitedStates.add(state);
    }

    public final HashSet<Integer> getVisited(){
        return this.visitedStates;
    }

    public final boolean checkVisited(int state){
        return visitedStates.contains(state);
    }

}