public class Main{

	public static void main(String[] args){

	    RandomGenerator rnd = new RandomGenerator();

	    Graph graph = new Graph(5, rnd, true);
	    graph.fill();
	    //graph.printProducts();
        //graph.printGraph();
	    graph.minCutKarger();
	    graph.printGraph();
	}
}