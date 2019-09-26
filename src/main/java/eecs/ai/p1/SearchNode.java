package eecs.ai.p1;

public class SearchNode{
    private final BoardState currentState;
    private final SearchNode previousNode;

    private final Directions traveledDirection;

    private int pathCost;

    /**
     * Public constructor to create a new searchnode which acts as a datastructure within the search algorithm
     * @param traveledDirection The direction needed to travel to reach the stored state
     * @param currentState The currentState of the "board"
     * @param previousNode The previous node which led to the current state
     * @param pathCost The accumulated path cost to reach the current node.
     */
    public SearchNode (Directions traveledDirection, BoardState currentState, SearchNode previousNode, int pathCost){
        this.currentState = currentState;
        this.previousNode = previousNode;
        this.traveledDirection = traveledDirection;
        this.pathCost = pathCost;
    }

    public BoardState getCurrentState() {
        return this.currentState;
    }

    public SearchNode getPreviousNode() {
        return this.previousNode;
    }

    public Directions getTraveleDirections(){
        return this.traveledDirection;
    }

    public int getPathCost(){
        return this.pathCost;
    }

    public int getNextPathCost(){
        return this.pathCost + 1;
    }

    public boolean hasPrevious(){
        return previousNode != null;
    }

}