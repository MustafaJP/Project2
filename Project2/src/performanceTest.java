import java.io.IOException;

public class performanceTest {
    public static void main(String[] args) throws Exception {
        testDijkstraArray();
        empiricalTest(300, 1000, 500, 1000);
        empiricalTestSparse(1000, 1000, 500, 1000);
    }

    private static void testDijkstraArray() {
        System.out.println("testDijkstraArray: ");
        Graph g = new Graph(5);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 2);
        g.addEdge(0, 3, 6);
        g.addEdge(0, 4, 8);
        g.addEdge(1, 3, 4);
        g.addEdge(1, 4, 3);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 1, 1);
        g.addEdge(3, 4, 3);
        g.printGraph();
        DijkstraAlgo dijkstraTest = new DijkstraAlgo(5);
        dijkstraTest.dijkstraArrayStart(5, 0, g.adjMatrix);
        DijkstraAlgo.printResult(5, 0);
    }

    private static void empiricalTest(int maxVertices, int maxWeight, int graphCount, int averageTimes)
            throws IOException {
        // Arrays to store the results of the empirical test.
        long[] vertexCounts = new long[graphCount];
        long[] edgeCounts = new long[graphCount];
        long[] dijkstraArrayTimes = new long[graphCount];
        long[] dijkstraListTimes = new long[graphCount];

        for (int i = 0; i < graphCount; i++) {
            System.out.println("Generating graph " + (i + 1) + "...");
            Graph graph = new Graph(maxVertices, maxWeight, 0);

            // Store the vertex and edge counts for the current graph.
            vertexCounts[i] = graph.V;
            edgeCounts[i] = graph.E;

            // Calculate the total runtime of the Dijkstra algorithm using the
            // adjMatrix+Array approach.
            long totalDijkstraArrayTime = 0;
            for (int j = 0; j < averageTimes; j++) {
                DijkstraAlgo dijkstraTest = new DijkstraAlgo(graph.V);
                long startTime = System.nanoTime();
                dijkstraTest.dijkstraArrayStart(graph.V, 0, graph.adjMatrix);
                long endTime = System.nanoTime();
                totalDijkstraArrayTime += endTime - startTime;
            }

            // Calculate the average runtime of the Dijkstra algorithm using the
            // adjMatrix+Array approach.
            dijkstraArrayTimes[i] = totalDijkstraArrayTime / averageTimes;

        }

        // Generate CSV files containing the results of the empirical test.
        makeCSV.CSVprinter(vertexCounts, "vertice_counts.csv");
        makeCSV.CSVprinter(edgeCounts, "edge_counts.csv");
        makeCSV.CSVprinter(dijkstraArrayTimes, "dijkstra_array_times.csv");
        makeCSV.CSVprinter(dijkstraListTimes, "dijkstra_list_times.csv");

    }

    private static void empiricalTestSparse(int maxVertices, int maxWeight, int graphCount, int averageTimes)
            throws IOException {

        // Arrays to store the results of the empirical test.
        long[] vertexCounts = new long[graphCount];
        long[] edgeCounts = new long[graphCount];
        long[] dijkstraArrayTimes = new long[graphCount];

        for (int i = 0; i < graphCount; i++) {
            System.out.println("Generating graph " + (i + 1) + "...");
            Graph graph = new Graph(maxVertices, maxWeight, 1);

            // Store the vertex and edge counts for the current graph.
            vertexCounts[i] = graph.V;
            edgeCounts[i] = graph.E;

            // Calculate the total runtime of the Dijkstra algorithm using the
            // adjMatrix+Array approach.
            long totalDijkstraArrayTime = 0;
            for (int j = 0; j < averageTimes; j++) {
                DijkstraAlgo dijkstraTest = new DijkstraAlgo(graph.V);
                long startTime = System.nanoTime();
                dijkstraTest.dijkstraArrayStart(graph.V, 0, graph.adjMatrix);
                long endTime = System.nanoTime();
                totalDijkstraArrayTime += endTime - startTime;
            }

            // Calculate the average runtime of the Dijkstra algorithm using the
            // adjMatrix+Array approach.
            dijkstraArrayTimes[i] = totalDijkstraArrayTime / averageTimes;

        }

        // Generate CSV files containing the results of the empirical test.
        makeCSV.CSVprinter(vertexCounts, "vertex_counts_sparse.csv");
        makeCSV.CSVprinter(edgeCounts, "edge_counts_sparse.csv");
        makeCSV.CSVprinter(dijkstraArrayTimes, "dijkstra_array_times_sparse.csv");

    }

}
