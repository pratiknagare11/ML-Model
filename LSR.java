import java.util.*;

public class LSR {
    private static final int NUM_NODES = 5;
    private static final int INFINITY = Integer.MAX_VALUE;

    private int[][] adjacencyMatrix;
    private int[] costs;
    private boolean[] visited;

    public LSR() {
        adjacencyMatrix = new int[NUM_NODES][NUM_NODES];
        costs = new int[NUM_NODES];
        visited = new boolean[NUM_NODES];
    }

    public void addLink(int source, int destination, int cost) {
        adjacencyMatrix[source][destination] = cost;
        adjacencyMatrix[destination][source] = cost;
    }

    public List<Integer> findShortestPath(int source, int destination) {
        Arrays.fill(costs, INFINITY);
        Arrays.fill(visited, false);

        costs[source] = 0;

        for (int i = 0; i < NUM_NODES; i++) {
            int currentNode = getNextNode();
            visited[currentNode] = true;

            for (int j = 0; j < NUM_NODES; j++) {
                if (!visited[j] && adjacencyMatrix[currentNode][j] != 0) {
                    int newCost = costs[currentNode] + adjacencyMatrix[currentNode][j];

                    if (newCost < costs[j]) {
                        costs[j] = newCost;
                    }
                }
            }
        }

        return buildPath(source, destination);
    }

    private int getNextNode() {
        int minCost = INFINITY;
        int nextNode = -1;

        for (int i = 0; i < NUM_NODES; i++) {
            if (!visited[i] && costs[i] < minCost) {
                minCost = costs[i];
                nextNode = i;
            }
        }

        return nextNode;
    }

    private List<Integer> buildPath(int source, int destination) {
        List<Integer> path = new ArrayList<>();
        int current = destination;

        while (current != source) {
            path.add(current);

            for (int i = 0; i < NUM_NODES; i++) {
                if (adjacencyMatrix[current][i] != 0 && costs[current] - adjacencyMatrix[current][i] == costs[i]) {
                    current = i;
                    break;
                }
            }
        }

        path.add(source);
        Collections.reverse(path);

        return path;
    }

    public static void main(String[] args) {
        LSR router = new LSR();

        router.addLink(0, 1, 2);
        router.addLink(0, 2, 5);
        router.addLink(1, 3, 1);
        router.addLink(2, 3, 3);
        router.addLink(2, 4, 7);
        router.addLink(3, 4, 2);

        int source = 0;
        int destination = 4;

        List<Integer> path = router.findShortestPath(source, destination);

        if (path.isEmpty()) {
            System.out.println("No path found from node " + source + " to node " + destination);
        } else {
            System.out.println("Shortest path from node " + source + " to node " + destination + ":");
            for (int node : path) {
                System.out.print(node + " ");
            }
        }
    }
}

