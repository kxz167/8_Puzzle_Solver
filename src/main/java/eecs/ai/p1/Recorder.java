package eecs.ai.p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Recorder {
    private TreeMap<Integer, List<Run>> recordedAStarH1 = new TreeMap<>();
    private TreeMap<Integer, List<Run>> recordedAStarH2 = new TreeMap<>();
    private TreeMap<Integer, List<Run>> recordedBeam = new TreeMap<>();

    private Recorder() {

    }

    public static final Recorder of() {
        return new Recorder();
    }

    public final void searchCost() {
        recordedAStarH1.forEach((steps, runs) ->{
            int sum = 0;
            
            for(Run run : runs){
                sum += run.getNumNodes();
            }

            System.out.println("Steps for H1: " + steps + ", Has an average of: " + (sum / runs.size()) + " nodes, with SS of " + runs.size());
        }); 
        recordedAStarH2.forEach((steps, runs) ->{
            int sum = 0;

            for(Run run : runs){
                sum += run.getNumNodes();
            }

            System.out.println("Steps for H2: " + steps + ", Has an average of: " + (sum / runs.size()) + " nodes, with SS of " + runs.size());
        });
        recordedBeam.forEach((steps, runs) ->{
            int sum = 0;

            for(Run run : runs){
                sum += run.getNumNodes();
            }

            System.out.println("Steps for Beam: " + steps + ", Has an average of: " + (sum / runs.size()) + " nodes, with SS of " + runs.size());
        });
    }

    public void searchSuccessRate(){
        
        recordedAStarH1.forEach((steps, runs) -> {
            int failure = 0;    
            for(Run run : runs){
                if(!run.solveable()){
                    failure ++;
                }
                
            }
            System.out.println("Failures in H1: " + failure + ", fraction of: " + (double)failure / runs.size() + " fails to successes");
        }); 
        recordedAStarH2.forEach((steps, runs) -> {
            int failure = 0;    
            for(Run run : runs){
                if(!run.solveable()){
                    failure ++;
                }
                
            }
            System.out.println("Failures in H2: " + failure + ", fraction of: " + (double)failure / runs.size() + " fails to successes");
        }); 
        recordedBeam.forEach((steps, runs) -> {
            int failure = 0;    
            for(Run run : runs){
                if(!run.solveable()){
                    failure ++;
                }
                
            }
            System.out.println("Failures in beam: " + failure + ", fraction of: " + (double)failure / runs.size() + " fails to successes");
        }); 
    }

    public final void add(Run recordedRun) {
        switch (recordedRun.getType()) {
        case "A-star h1":
            initStarter(recordedAStarH1, recordedRun);
            break;
        case "A-star h2":
            initStarter(recordedAStarH2, recordedRun);
            break;
        default:
            initStarter(recordedBeam, recordedRun);
        }
    }

    private final void initStarter(TreeMap<Integer, List<Run>> target, Run run) {
        List<Run> targetList = target.get(run.getNumSteps());
        if (targetList == null) {
            List<Run> newList = new ArrayList<Run>();
            newList.add(run);
            target.put(run.getNumSteps(), newList);
        }
        else{
            targetList.add(run);
        }
    }
}