package eecs.ai.p1.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import eecs.ai.p1.Board;
import eecs.ai.p1.Directions;
import eecs.ai.p1.SearchNode;

public abstract class Solve extends Command {

    /**
     * Solves the current state with the search algorithm
     * @param currentBoard The current board to be solved
     * @return The node of the solution, if one exists. Null otherwise
     */
    public final List<Directions> solve(Board currentBoard) {

        SearchNode result = loop(currentBoard);
        return processSolution(result);

    }

    /**
     * Processes the final node and returns a solution list of Directions
     * @param finalNode The final node that is returned by running the algorithm
     * @return The new list of directions required to solve the BoardState
     */
    public final List<Directions> processSolution(SearchNode finalNode) {

        List<Directions> solutionDirection = null;
        SearchNode consideredNode = finalNode;

        if (finalNode != null) {
            solutionDirection = new ArrayList<Directions>();

            while (consideredNode.hasPrevious()) {
                solutionDirection.add(consideredNode.getTraveleDirections());
                consideredNode = consideredNode.getPreviousNode();
            }

            Collections.reverse(solutionDirection);
        }
        else{
            //No solution, list remains null
        }

        return solutionDirection;
    }

    protected abstract SearchNode loop(Board currentBoard);
}