import java.util.*;

public class Main{

    private static boolean debug = false, weighted = false;
    private static int products = 5;

	public static void main(String[] args){

	    parseArguments(args);

	    Graph graph = new Graph(products, debug, weighted);
	    graph.fill();
	    //graph.printProductsConnection();
	    //graph.printProducts();
        //graph.printGraph();
	    graph.minCutKarger();
        graph.printGraph();
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
                case "-n":
                    ++i;
                    try {
                        products = Integer.parseInt(args[i]);
                    } catch (NumberFormatException | NullPointerException e) {
                        System.err.println(e.getMessage());
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

	}
}