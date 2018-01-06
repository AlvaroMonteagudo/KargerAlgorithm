import java.io.File;
import java.io.IOException;

public class Main{

    private static boolean debug = false, weighted = true;
    private static int products = 5, tests = 10;
    private static String file = "graph";

	public static void main(String[] args){

	    parseArguments(args);

        System.out.println("Running " + tests + " tests in a " + products + " vertices 70% connected graph.");

	    Graph graph = new Graph(products, debug, weighted);
        graph.fill();
    	graph.saveGraph(file);

    	//StringBuilder stats = new StringBuilder();
        StringBuilder statsWeighted = new StringBuilder();

        //stats.append("No weighted ");
        statsWeighted.append("Weighted \t");
        System.out.print("Iteration");
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + (i + 1));
            //Graph test = new Graph(products, debug, false);
            Graph weighted = new Graph(products, debug, true);
            graph.makeCopy(file, weighted);

            //graph.makeCopy(file, weighted);
            //weighted.minCutKarger();
            //weighted.printGraph();

            //stats.append(test.minCutKarger()).append("\t");
            statsWeighted.append(weighted.minCutKarger()).append("\t");
        }
        System.out.println();
        //System.out.println(stats.toString());
        System.out.println(statsWeighted.toString());
    }
	
    private static void parseArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    debug = true;
                    break;
                case "-w":
                    weighted = true;
                    break;
                case "-num":
                    ++i;
                    try {
                        products = Integer.parseInt(args[i]);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "-tests":
                    ++i;
                    try {
                        tests = Integer.parseInt(args[i]);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case "-f":
                    ++i;
                    file = args[i] + ".txt";
                    File f = new File(file);
                    if (!f.exists()){
                        try {
                            f.createNewFile();
                            if (!f.exists()){
                                System.err.println("Unable to create a file to save graph");
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "-h":
                    printUsage();
                    break;
                default:
                    System.out.println("Argument not supported. Argument -h to print usage of the program");
                    break;
            }
        }

    }

    private static void printUsage() {
        System.out.println("./Main [-d] [-w] [-num] [-tests] [-f] [-h] ");
        System.out.println("Available options:");
        System.out.println("    -d: debug messages will be printed while executing");
        System.out.println("    -w: graph will be weighted");
        System.out.println("    -num <INTEGER>: number of vertices for the graph");
        System.out.println("    -tests <INTEGER>: number of tests");
        System.out.println("    -f <STRING>: file name to sabe graph");
        System.out.println("    -h: this helpful message");
    }
}