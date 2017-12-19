import java.util.*;

public class Graph {

    private static RandomGenerator rnd;

    private int PRODUCTS;
    private boolean buyTogether[][];
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, ArrayList<Product>> graph;
    private Set<Edge> graphEdges = new HashSet<>();



    public Graph(int n, RandomGenerator rnd) {
        PRODUCTS = n;
        buyTogether = new boolean[n][n];
        graph = new HashMap<>();
        Graph.rnd = rnd;
    }

    public void fill() {

        //Initialize random products
        for (int i = 0; i < PRODUCTS; i++) {

            products.put(i, new Product(rnd.stringRandom(), rnd.getRnd().nextInt(10) + 1,
                    Math.round(rnd.getRnd().nextDouble() * 100.0)));

            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < PRODUCTS; i++) {
            for (int j = i; j < PRODUCTS; j++) {
                // A product always is bought with itself
                if (i == j) {
                    buyTogether[i][j] = true;
                } else {
                    // If i is bought with j -> j is bought with i
                    buyTogether[i][j] = rnd.getRnd().nextBoolean();
                    buyTogether[j][i] = buyTogether[i][j];

                    Edge edge = new Edge(products.get(i), products.get(j));
                    graphEdges.add(edge);
                }
            }
        }
    }

    public void minCutKarger() {
        while (graph.size() > 2) {
            int index1 = rnd.getRnd().nextInt(PRODUCTS);
            Product p1 = products.get(index1);

            if (p1.getEdges().size() != 0) {
                int index2 = rnd.getRnd().nextInt(PRODUCTS);
                while (index2 == index1) {
                    index2 = rnd.getRnd().nextInt(PRODUCTS);
                }

                Product p2 = products.get(index2);
                
                merge(p1, p2);

            } else System.err.println("Graph must be fully connected");
        }
    }

    private void merge(Product p1, Product p2) {

    }

    public void addProduct(Product p) {
        ++PRODUCTS;
        products.put(PRODUCTS, p);
        reset();
    }

    public void reset() {
        graph.clear();
        for (int i = 0; i < PRODUCTS ; i++) {
            graph.put(i, new ArrayList<>());
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