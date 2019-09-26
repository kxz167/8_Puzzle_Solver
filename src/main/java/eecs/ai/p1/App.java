package eecs.ai.p1;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        String fileLocation = "src\\main\\input\\";

        if(args.length != 0)
            Commander.of(fileLocation + args[0]).execute(); 

        //Uncomment for section 2.1: Testing Commands
        // Commander.of("src\\main\\resources\\2.1_input").execute();
        
        //Uncomment for section 2.2.1: Testing h1, h2 Optimality
        // Commander.of("src\\main\\resources\\2.2.1_h1_input").execute();
        // Commander.of("src\\main\\resources\\2.2.1_h2_input").execute();

        //Uncomment for section 2.2.2: Testing h1, h2 Failure Correctness
        // Commander.of("src\\main\\resources\\2.2.2_h1_input").execute();
        // Commander.of("src\\main\\resources\\2.2.2_h2_input").execute();

        //Uncomment for section 2.3.1: Testing beam Optimality and failure correctness
        // Commander.of("src\\main\\resources\\2.3.1_beam_input").execute();

        //Uncomment for section 3.1: Testing maxNodes;
        // Commander.of("src\\main\\resources\\3.1_input").execute();

        //Uncomment for section 3.2: Comparing h1, h2
        // Commander.of("src\\main\\resources\\3.2_h1_input").execute();
        // Commander.of("src\\main\\resources\\3.2_h2_input").execute();

        //Uncomment for section 3.3: 
        // Commander.of("src\\main\\resources\\3.3_input").execute();

    }
}
