package eecs.ai.p1;

public class SearchNode{
    private final BoardState currentState;
    private final SearchNode previousNode;

    private final Directions traveledDirection;

    private int pathCost;

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