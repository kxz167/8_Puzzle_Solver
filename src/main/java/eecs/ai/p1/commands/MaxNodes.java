package eecs.ai.p1.commands;

import eecs.ai.p1.Board;

public class MaxNodes extends Command {

    private final int maxNodes;

    private MaxNodes(int maxNodes){
        this.maxNodes = maxNodes;
    }

    /**
     * Builds a MaxNodes command that takes in an integer and returns the command
     * @param max The maximum number of visited nodes.
     * @return The new MaxNodes command
     */
    public static final MaxNodes of (int max){
        return new MaxNodes(max);
    }
    
    /**
     * Executing the maxNodes commands will output to console as well as set the parameter in the board.
     */
    @Override
    public void execute(Board board){
        if(board.toPrint())
            System.out.println("MaxNodes: "+ maxNodes);
            
        board.setMaxNodes(maxNodes);
    }

}