import java.util.HashMap;
import java.util.Map;

public class Graph {

    private static int PRODUCTS;

    private static boolean buyTogether[][];

    private final Map<Integer, Product> products = new HashMap<>();


    public Graph(int n) {
        PRODUCTS = n;
        buyTogether = new boolean[n][n];

    }

    public void fill(RandomGenerator rnd) {

        //Initialize the boolean matrix buyTogether
        for (int i = 0; i < PRODUCTS; i++) {

            products.put(i, new Product(rnd.stringRandom(), rnd.getRnd().nextInt(10) + 1,
                                        Math.round(rnd.getRnd().nextDouble()*100.0)));

            for (int j = i; j < PRODUCTS; j++) {
                // A product always is bought with itself
                if (i == j) {
                    buyTogether[i][j] = true;
                } else {
                    // If i is bought with j -> j is bought with i
                    buyTogether[i][j] = rnd.getRnd().nextBoolean();
                    buyTogether[j][i] = buyTogether[i][j];
                }
            }
        }
    }

    private boolean wereBoughtTogether(int i, int j) {
        return buyTogether[i][j];
    }

    private Product getProduct(int id) {
        return products.get(id);
    }

    private int getId(Product p) {
        for (Map.Entry<Integer, Product> e : products.entrySet()) {
            if (e.getValue().equals(p)) {
                return e.getKey();
            }
        }
        return 0;
    }

    /*
     * Add the Product <v> to the Graph
     */
    private void addVertex(Product v) {

    }

    public void printBoughtTogether(){
        System.out.println("PRODUCTS BOUGHT TOGETHER TABLE");
        System.out.println("==============================");
        System.out.println();
        for (int i = 0; i < buyTogether.length; i++) {
            for (int j = 0; j < buyTogether[0].length; j++) {
                int aux = buyTogether[i][j] ? 1 : 0;
                System.out.print( aux + "  ");
            }
            System.out.print("\n");
        }
    }

    public void printProducts(){
        System.out.println();
        System.out.println("PRODUCT LIST");
        System.out.println("============");
        System.out.println();
        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product_" + i + ": " + products.get(i).toString());
        }
    }
}