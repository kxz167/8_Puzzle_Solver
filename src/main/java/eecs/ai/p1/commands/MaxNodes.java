package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class MaxNodes extends Command {

    private final int maxNodes;

    private MaxNodes(int maxNodes){
        this.maxNodes = maxNodes;
    }

    public static final MaxNodes of (int max){
        return new MaxNodes(max);
    }

    @Override
    public boolean execute(Board board){
        board.setMaxNodes(maxNodes);
        return true;
    }

}