import java.util.HashSet;
import java.util.Set;

public class Product {
	
	private String name;
	private int unit;
	private double price;

	private Set<Edge> edges;

	/*
	 * This class represent a vertex of the graph like a product
	 */
	public Product(String name, int unit, double price){
		this.name = name;
		this.unit = unit;
		this.price = price;
		edges = new HashSet<>();
	}

	public void addEdge(Edge edge) {
        for (int i = 0; i < edges.size(); i++) {
            if (getEdge(i).equals(edge)) {
                return;
            }
        }
        edges.add(edge);
    }

    public void remove(Edge edge) {
        for (int i = 0; i < edges.size(); i++) {
            if (getEdge(i).equals(edge)) {
                edges.remove(edge);
            }
        }
    }

	/*
	 * Assign the name of the product
	 */
	public void setName(String name){
		this.name = name;
	}

	/*
	 * Assign the unit of the product
	 */
	public void setUnit(int unit){
		this.unit = unit;
	}

	/*
	 * Assign the price of the product
	 */
	public void setPrice(double price){
		this.price = price;
	}

	/*
	 * Return the name of the product
	 */
	public String getName(){
		return this.name;
	}

	/*
	 * Return the unit of the product
	 */
	public int getUnit(){
		return this.unit;
	}

	/*
	 * Return the price of the product
	 */
	public double getPrice(){
		return this.price;
	}

	/*
	 * Return edges that connects this product to others
	 */
    public Set<Edge> getEdges() { return edges; }

    public Edge getEdge(int i) {
        return (Edge) edges.toArray()[i];
    }
	
	public String toString(){

	    return "Name: " + this.name + ", Unit: " + this.unit + ", Price: " + this.price;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return unit == product.unit &&
               Double.compare(product.price, price) == 0 &&
               (name != null ? name.equals(product.name) : product.name == null);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + unit;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}