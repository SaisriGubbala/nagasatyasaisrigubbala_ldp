import java.util.*;

public class TopologicalSort {
    private int vertices; // Number of vertices
    private LinkedList<Integer>[] adjList; // Adjacency list
    private int[] inDegree; // Array to store in-degrees of vertices

    // Constructor
    TopologicalSort(int v) {
        vertices = v;
        adjList = new LinkedList[v];
        inDegree = new int[v];
        for (int i = 0; i < v; ++i) {
            adjList[i] = new LinkedList<>();
            inDegree[i] = 0;
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adjList[v].add(w);
        inDegree[w]++;
    }

    // Function to print all topological sorts
    void allTopologicalSorts() {
        boolean[] visited = new boolean[vertices];
        LinkedList<Integer> stack = new LinkedList<>();

        allTopologicalSortsUtil(visited, stack);
    }

    // Recursive utility function for topological sort
    private void allTopologicalSortsUtil(boolean[] visited, LinkedList<Integer> stack) {
        boolean flag = false;

        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && inDegree[i] == 0) {
                for (int adj : adjList[i]) {
                    inDegree[adj]--;
                }

                stack.add(i);
                visited[i] = true;
                allTopologicalSortsUtil(visited, stack);

                visited[i] = false;
                stack.removeLast();
                for (int adj : adjList[i]) {
                    inDegree[adj]++;
                }

                flag = true;
            }
        }

        if (!flag) {
            stack.forEach(v -> System.out.print(v + " "));
            System.out.println();
        }
    }

    // Function to print all incoming edges of nodes
    void printIncomingEdges() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Node " + i + ": ");
            for (int j = 0; j < vertices; j++) {
                if (adjList[j].contains(i)) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();

        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();

        TopologicalSort graph = new TopologicalSort(V);

        System.out.println("Enter the edges (source destination):");
        for (int i = 0; i < E; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph.addEdge(v, w);
        }

        System.out.println("All Topological Sorts:");
        graph.allTopologicalSorts();

        System.out.println("Incoming Edges of Nodes:");
        graph.printIncomingEdges();

        scanner.close();
    }
}
