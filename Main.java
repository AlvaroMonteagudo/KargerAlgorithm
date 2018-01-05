import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main{

    private static boolean debug = false, weighted = false;
    private static int products = 5, tests = 1;
    private static String sFichero = "initGraph.txt";
	public static void main(String[] args){

	    parseArguments(args);

	    Graph graph = new Graph(products, debug, weighted);
	    graph.fill();
        File fichero = new File(sFichero);
        graph.writeFile(sFichero,fichero);
        for (int i = 0; i < 1; i++) {
        	//Se copia este grafo en un fichero 

            Graph test = new Graph(products, debug, weighted);
            //Se copia el grafo del fichero en test
            graph.readFile(sFichero,fichero,test);
            graph.printGraph();
            test.printGraph();
            //test.minCutKarger();
            graph.printGraph();
            //test.printGraph();
        }
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