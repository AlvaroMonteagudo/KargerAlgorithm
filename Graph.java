import java.util.*;

public class Graph {

    private static RandomGenerator rnd;

    private int PRODUCTS;
    private boolean DEBUG;
    private boolean buyTogether[][];
    private Map<Integer, Product> products = new HashMap<>();
    private Map<Integer, ArrayList<Product>> graph;
    private Set<Edge> graphEdges = new HashSet<>();



    public Graph(int n, RandomGenerator rnd, boolean debug) {
        PRODUCTS = n;
        DEBUG = debug;
        if (DEBUG) System.out.print("[DEBUG] Creating instances of needed data structures...");
        buyTogether = new boolean[n][n];
        graph = new HashMap<>();
        Graph.rnd = rnd;
        if (DEBUG) System.out.println("[OK] DONE.");
    }

    public void fill() {


        if (DEBUG) System.out.print("[DEBUG] Initializing random products...");
        //Initialize random products
        for (int i = 0; i < PRODUCTS; i++) {

            products.put(i, new Product(rnd.stringRandom(), rnd.getRnd().nextInt(10) + 1,
                    Math.round(rnd.getRnd().nextDouble() * 100.0)));

            graph.put(i, new ArrayList<>());
        }
        if (DEBUG) System.out.println("[OK] DONE.");

        if (DEBUG) System.out.print("[DEBUG] Matching products randomized, generating edges, filling graph...");
        for (int i = 0; i < PRODUCTS; i++) {
            for (int j = i; j < PRODUCTS; j++) {
                // A product always is bought with itself
                if (i == j) {
                    buyTogether[i][j] = true;
                } else {
                    // If i is bought with j -> j is bought with i
                    buyTogether[i][j] = rnd.getRnd().nextBoolean();
                    buyTogether[j][i] = buyTogether[i][j];

                    if (buyTogether[i][j]) {
                        // Generate edge of the graph and save in the corresponding structures
                        Edge edge = new Edge(products.get(i), products.get(j));
                        graphEdges.add(edge);
                        products.get(i).addEdge(edge);
                        products.get(j).addEdge(edge);
                    }
                }
            }
        }
        if (DEBUG) System.out.println("[OK] DONE.");
    }

    public void minCutKarger() {
        if (DEBUG) System.out.println("[DEBUG] Karger's algorithm in progress...");
        while (graph.size() > 2) {
            if (DEBUG) System.out.println("[DEBUG] Selecting random edge to be removed");
            Edge edgeToRemove = getEdge(rnd.getRnd().nextInt(graphEdges.size()));
            Product p1 = edgeToRemove.getFirst();
            Product p2 = edgeToRemove.getOppositeEnd(p1);

            if (DEBUG) System.out.print("[DEBUG] Removing selected edge...");
            graphEdges.remove(edgeToRemove);
            p1.getEdges().remove(edgeToRemove);
            p2.getEdges().remove(edgeToRemove);
            if (DEBUG) System.out.println("[OK] DONE.");

            merge(p1, p2);
        }
        if (DEBUG) System.out.println("[OK] DONE.");
    }

    private void merge(Product p1, Product p2) {
        if (DEBUG) System.out.print("[DEBUG] Merging vertices...");
        graph.remove(getKeyProduct(p2));
        p1.getEdges().addAll(p2.getEdges());
        graph.get(getKeyProduct(p1)).add(p2);
    }

    public void addProduct(Product p) {
        ++PRODUCTS;
        products.put(PRODUCTS, p);
        reset();
    }

    public void reset() {
        if (DEBUG) System.out.print("Clearing up graph...");
        graph.clear();
        for (int i = 0; i < PRODUCTS ; i++) {
            graph.put(i, new ArrayList<>());
        }
        if (DEBUG) System.out.println("DONE.");
    }

    private boolean wereBoughtTogether(int i, int j) {
        return buyTogether[i][j];
    }

    private Product getProduct(int id) {
        return products.get(id);
    }

    private int getKeyProduct(Product p) {
        for (Map.Entry<Integer, Product> e : products.entrySet()) {
            if (e.getValue().equals(p)) {
                return e.getKey();
            }
        }
        return 0;
    }

    private int getKeyGraph(ArrayList<Product> p) {
        for (Map.Entry<Integer, ArrayList<Product>> e : graph.entrySet()) {
            if (e.getValue().equals(p)) {
                return e.getKey();
            }
        }
        return 0;
    }

    private Edge getEdge(int i) {
        return ((Edge) graphEdges.toArray()[i]);
    }

    public void printGraph(){
        System.out.println("GRAPH");
        System.out.println("=====\n");
        StringBuilder stringToPrint = new StringBuilder();

        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).isEmpty()){
                stringToPrint.append("Not merged yet(").append(i).append(')');
            } else {
                stringToPrint.append("Merged(").append(getKeyGraph(graph.get(i))).append(',');
            }

            for (int j = 0; j < graph.get(i).size(); j++) {
                stringToPrint.append(getKeyProduct(graph.get(i).get(j))).append(',');
            }
            if (stringToPrint.toString().lastIndexOf(',') != -1) {
                stringToPrint.deleteCharAt(stringToPrint.toString().lastIndexOf(','));
            }

            if (graph.get(i).isEmpty()){
                stringToPrint.append(" : [");
            } else {
                stringToPrint.append(") : [");
            }

            for (int j = 0; j < products.get(i).getEdges().size(); j++) {
                stringToPrint.append(getKeyProduct(products.get(i).getEdge(j).getOppositeEnd(products.get(i)))).append(",");
            }

            if (stringToPrint.toString().lastIndexOf(',') != -1) {
                System.out.println(stringToPrint.toString().substring(0, stringToPrint.toString().length() - 1) + "]");
            } else {
                System.out.println(stringToPrint.toString() + "]");
            }

            stringToPrint.setLength(0);
        }

    }


    public void printInitialGraph(){
        System.out.println("INITIAL GRAPH");
        System.out.println("=============\n");
        StringBuilder stringToPrint = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            stringToPrint.append(i).append(": [");
            for (int j = 0; j < products.get(i).getEdges().size(); j++) {
                stringToPrint.append(getKeyProduct(products.get(i).getEdge(j).getOppositeEnd(products.get(i)))).append(",");
            }

            if (stringToPrint.toString().lastIndexOf(',') != -1) {
                System.out.println(stringToPrint.toString().substring(0, stringToPrint.toString().length() - 1) + "]");
            } else {
                System.out.println(stringToPrint.toString() + "]");
            }

            stringToPrint.setLength(0);
        }
    }

    public void printBoughtTogether(){
        System.out.println("PRODUCTS BOUGHT TOGETHER TABLE");
        System.out.println("==============================\n");
        for (boolean[] aBuyTogether : buyTogether) {
            for (int j = 0; j < buyTogether[0].length; j++) {
                int aux = aBuyTogether[j] ? 1 : 0;
                System.out.print(aux + "  ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public void printProducts(){
        System.out.println();
        System.out.println("PRODUCT LIST");
        System.out.println("============\n");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("Product_" + i + ": " + products.get(i).toString());
        }
        System.out.println();
    }
}