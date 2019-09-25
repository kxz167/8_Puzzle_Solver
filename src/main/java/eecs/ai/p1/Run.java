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