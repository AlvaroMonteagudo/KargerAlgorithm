import java.util.Random;

public class Main{


	public static void main(String[] args){

	    RandomGenerator rnd = new RandomGenerator();

	    Graph graph = new Graph(5, rnd);
	    graph.fill();
	    graph.printBoughtTogether();
	    graph.printGraph();
	    graph.printProducts();
	}
}