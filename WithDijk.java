import java.util.Arrays;

public class WithDijk {

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private int numVertices;
    private int[][] graph;

    public WithDijk(int numVertices) {
        this.numVertices = numVertices;
        this.graph = new int[numVertices][numVertices];
    }

    public void addEdge(int u, int v, int weight) {
        graph[u][v] = weight;
        graph[v][u] = weight;
    }

    public void dijkstra(int src) {
        boolean[] visited = new boolean[numVertices];
        int[] dist = new int[numVertices];
        Arrays.fill(dist, MAX_VALUE);

        dist[src] = 0;

        for (int i = 0; i < numVertices - 1; i++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    private int minDistance(int[] dist, boolean[] visited) {
        int minDist = MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < numVertices; v++) {
            if (!visited[v] && dist[v] < minDist) {
                minDist = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < numVertices; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        WithDijk router = new WithDijk(6);

        router.addEdge(0, 1, 2);
        router.addEdge(1, 2, 5);
        router.addEdge(1, 3, 1);
        router.addEdge(2, 4, 3);
        router.addEdge(3, 4, 2);
        router.addEdge(3, 5, 5);
        router.addEdge(4, 5, 1);

        router.dijkstra(0);
    }
}
