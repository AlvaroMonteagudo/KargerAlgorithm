public class Vertex{
	
	private String name;
	private int unit;
	private double price;
	/*
	 * This class represent a vertex of the graph like a product
	 */
	public Vertex(String name,int unit,double price){
		this.name = name;
		this.unit = unit;
		this.price = price;
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
	
	public void printVertex(){
		System.out.println("Name: "+this.name+", Unit: "+this.unit+", Price: "+this.price);
	}
}