public class Main{

	public static void main(String[] args){

	    RandomGenerator rnd = new RandomGenerator();

	    Graph graph = new Graph(15, rnd, false);
	    graph.fill();
	    graph.printBoughtTogether();
	    graph.printGraph();
	    graph.printProducts();
	}
}