import java.util.ArrayList;
import java.util.List;

public class Edge {

	private Product first;
    private Product second;

    /**
     * Array list to save both origin and destination vertices of an edge
     */
	private final List<Product> vertices = new ArrayList<>();

    /**
     * Default edge constructor, first and second are just names
     * doesn't set any kind of direction.
     * @param first Product 1 of the edge
     * @param second Product 2 of the edge
     */
	public Edge(Product first, Product second) {

	    if (first == null || second == null) {
	        throw new NullPointerException("Neither first or second can be null value.");
        }

		this.first = first;
		this.second = second;

		vertices.add(first);
		vertices.add(second);

		first.addEdge(this);
		second.addEdge(this);
	}

	public Product getFirst() {
		return first;
	}

	public void setFirst(Product first) {
		this.first = first;
	}

	public Product getSecond() {
		return second;
	}

	public void setSecond(Product second) {
		this.second = second;
	}

    public List<Product> getVertices() {
        return vertices;
    }
}