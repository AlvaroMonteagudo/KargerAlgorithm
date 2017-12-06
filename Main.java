import java.util.Random;

public class Main{
	private static Random randomGenerator ;
	private static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	//Number of products
	private static int n = 100;
	private static boolean buyTogether[][] = new boolean[n][n];
	
	/*
	 * Return a random string
	 */
	public static String stringRandom(){
		StringBuilder sb = new StringBuilder();
		char c;
		for (int i = 0; i < 8; i++) {
		    c = chars[randomGenerator.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		return output;
	}
	
	/*
	 * Return a random boolean
	 */
	public static boolean booleanRandom(){
		return randomGenerator.nextBoolean();
	}
	
	/*
	 * Return a random integer
	 */
	public static int integerRandom(){
	    return randomGenerator.nextInt(10);
	}
	
	/*
	 * Return a random double
	 */
	public static double doubleRandom(){
		return  Math.floor(randomGenerator.nextDouble() * 100) / 100;
	}
	
	public static void main(String[] args){
		randomGenerator = new Random();
		Graph graph = new Graph();
		Vertex p = new Vertex(stringRandom(),integerRandom(),doubleRandom());
		//TODO : FALTA AÑADIR LOS PRODUCTOS CREADOS EN EL GRAFO
		
		//Initialize the boolean matrix buyTogether
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				buyTogether[i][j]=booleanRandom();
			}
		}
	}
}