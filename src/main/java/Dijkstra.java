public class Dijkstra {
    private int startingVertex;
    private double [][] graph;
    private int v;

    double[] shortestDistances;
    int[] parents;

    private static final int NO_PARENT = -1;

    public Dijkstra(double[][] graph, int v) {
        this.graph = graph;
        this.v = v;
    }

    public void alghorithm(){

        shortestDistances = new double[v];

        boolean[] added = new boolean[v];

        for (int vertexIndex = 0; vertexIndex < v; vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        shortestDistances[startingVertex] = 0;
        parents = new int[v];
        parents[startingVertex] = NO_PARENT;

        for (int i = 1; i < v; i++)
        {
            int nearestVertex = -1;
            double shortestDistance = Double.POSITIVE_INFINITY;
            for (int vertexIndex = 0; vertexIndex < v; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
            added[nearestVertex] = true;

            for (int vertexIndex = 0; vertexIndex < v; vertexIndex++)
            {
                double edgeDistance = graph[nearestVertex][vertexIndex];

                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                }
            }
        }
    }

    public double[] getShortestDistances() {
        return shortestDistances;
    }

    public int[] getParents() {
        return parents;
    }

    public void setStartingVertex(int startingVertex) {
        this.startingVertex = startingVertex;
    }
}
