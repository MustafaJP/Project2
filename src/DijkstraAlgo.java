import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DijkstraAlgo {
    static int[] dist; // distance
    static int[] predecessor;
    static boolean[] visited;
    static PriorityQueue<Edge> pq;

    public DijkstraAlgo(int V) {
        dist = new int[V];
        predecessor = new int[V];
        visited = new boolean[V];
        pq = new PriorityQueue<>(V, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                if (o1.weight > o2.weight)
                    return 1;
                else if (o1.weight < o2.weight)
                    return -1;
                else
                    return 0;
            }
        });
    }

    private static void initialization(int vertice, int src) {
        for (int i = 0; i < vertice; i++) {
            dist[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
            visited[i] = false;
        }
        dist[src] = 0;
    }

    public void dijkstraArrayStart(int vertice, int src, int[][] adjMatrix) {
        initialization(vertice, src);
        int[] priorityQueue = new int[vertice]; // Array-based priority queue
        for (int i = 0; i < vertice; i++) {
            priorityQueue[i] = i;
        }

        while (true) {
            int u = extractMin(priorityQueue, vertice); // function to extract the minimum
            if (u == -1)
                break; // All nodes visited or unreachable
            visited[u] = true;

            for (int v = 0; v < vertice; v++) {
                if (!visited[v] && adjMatrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + adjMatrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjMatrix[u][v];
                    predecessor[v] = u;
                    // Update the priorityQueue array to maintain the order
                    // based on the new tentative distances.
                }
            }
        }
    }

    private int extractMin(int[] priorityQueue, int vertice) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < vertice; i++) {
            int node = priorityQueue[i];
            if (!visited[node] && dist[node] < minValue) {
                minIndex = i;
                minValue = dist[node];
            }
        }

        if (minIndex != -1) {
            int minNode = priorityQueue[minIndex];

            // Remove the minimum node from the priority queue by shifting elements
            for (int i = minIndex; i < vertice - 1; i++) {
                priorityQueue[i] = priorityQueue[i + 1];
            }

            return minNode;
        }

        return -1; // All nodes visited or unreachable
    }

    public void dijkstraPQStart(int vertice, int src, LinkedList<Edge>[] adjList) {
        initialization(vertice, src);
        pq.add(new Edge(src, src, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int u = edge.dest;
            if (visited[u])
                continue;
            visited[u] = true;
            for (Edge e : adjList[u]) {
                int v = e.dest;
                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]) {
                    dist[v] = dist[u] + e.weight;
                    predecessor[v] = u;
                    pq.add(new Edge(u, v, dist[v]));
                }
            }
        }
    }

    private static void printPath(int src, int dest) {
        if (dest == src) {
            System.out.print(src + " ");
            return;
        }
        printPath(src, predecessor[dest]);
        System.out.print(dest + " ");
    }

    public static void printResult(int vertice, int src) {
        for (int i = 0; i < vertice; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("No path from " + src + " to " + i);
            } else {
                System.out.println("Shortest weight from " + src + " to " + i + " is " + dist[i]);
                System.out.print("Path is: ");
                printPath(src, i);
                System.out.println();
            }
        }
    }

}
