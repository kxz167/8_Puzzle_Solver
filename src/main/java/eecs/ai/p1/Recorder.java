package eecs.ai.p1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Stream;

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
        final int[] failures = {0,0,0};
        final int[] totalRuns = {0,0,0};

        Stream.of(recordedAStarH1);
        recordedAStarH1.forEach((steps, runs) -> {
            for(Run run : runs){
                if(!run.solveable()){
                    failures[0]++;
                }
                totalRuns[0]++;
            }
            
        }); 
        System.out.println("Failures in H1: " + failures[0] + ", fraction of: " + (double)failures[0] / totalRuns[0] + " failure rate");

        recordedAStarH2.forEach((steps, runs) -> {  
            for(Run run : runs){
                if(!run.solveable()){
                    failures[1]++;
                }
                totalRuns[1]++;
            }
        }); 
        System.out.println("Failures in H2: " + failures[1] + ", fraction of: " + (double)failures[1] / totalRuns[1] + " failure rate");

        recordedBeam.forEach((steps, runs) -> {
            for(Run run : runs){
                if(!run.solveable()){
                    failures[2]++;
                }
                totalRuns[2]++;
            }
        }); 
        System.out.println("Failures in beam: " + failures[2] + ", fraction of: " + (double)failures[2] / totalRuns[2] + " failure rate");
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