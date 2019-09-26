package eecs.ai.p1;

public class Run{
    private String type;
    private boolean solveable;
    private int generatedNodes;
    private int stepsInSolution;

    private Run(String type, boolean solveable, int generatedNodes, int stepsInSolution){
        this.type = type;
        this.solveable = solveable;
        this.generatedNodes = generatedNodes;
        this.stepsInSolution = stepsInSolution;
    }

    /**
     * Creates a new run datatype. A run represents a single execution of a search algorithm
     * @param type The type of algorithm in string form of either beam and which heuristic was utilized
     * @param solveable A boolean representing whether the state was solveable (solution found)
     * @param generatedNodes The number of nodes which were generated in the search process
     * @param stepsInSolution The steps in the proposed solution.
     * @return
     */
    public static final Run of(String type, boolean solveable, int generatedNodes, int stepsInSolution){
        return new Run(type, solveable, generatedNodes, stepsInSolution);
    }

    public final boolean solveable(){
        return solveable;
    }

    public int getNumNodes(){
        return generatedNodes;
    }

    public int getNumSteps(){
        return stepsInSolution;
    }

    public String getType(){
        return this.type;
    }
}