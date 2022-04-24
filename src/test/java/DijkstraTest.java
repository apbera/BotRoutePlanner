import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DijkstraTest {

    @Test
    public void shouldFindShortestPath(){
        double Infinity = Double.POSITIVE_INFINITY;

        //Given
        double [][] graph = new double[][] {
                { 0.0, 0.5, Infinity, Infinity, 0.5, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity },
                { 0.5, 0.0, 2.0, Infinity, Infinity, 1.0, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity},
                { Infinity, 2.0, 0.0, 2.0, Infinity, Infinity, 2.0, Infinity, Infinity, Infinity, Infinity, Infinity  },
                { Infinity, Infinity, 2.0, 0.0, Infinity, Infinity, Infinity, 0.5, Infinity, Infinity, Infinity, Infinity },
                { 0.5, Infinity, Infinity, Infinity, 0.0, 1.0, Infinity, Infinity, 0.5, Infinity, Infinity, Infinity  },
                { Infinity, 1.0, Infinity, Infinity, 1.0, 0.0, 1.0, Infinity, Infinity, 1.0, Infinity, Infinity  },
                { Infinity, Infinity, 2.0, Infinity, Infinity, 1.0, 0.0, 0.5, Infinity, Infinity, Infinity, Infinity },
                { Infinity, Infinity, Infinity, 0.5, Infinity, Infinity, 0.5, 0.0, Infinity, Infinity, Infinity, 2.0 },
                { Infinity, Infinity, Infinity, Infinity, 0.5, Infinity, Infinity, Infinity, 0.0, 0.5, Infinity, Infinity  },
                {Infinity, Infinity, Infinity, Infinity, Infinity, 1.0, Infinity, Infinity, 0.5, 0.0, Infinity, Infinity  },
                { Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, 0.0, Infinity },
                { Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, Infinity, 2.0, Infinity, Infinity, Infinity, 0.0  }
        };
        int v = 12;
        int startingVertex = 5;
        int finishVertex = 11;

        //When

        Dijkstra dijkstra = new Dijkstra(graph, v);
        dijkstra.setStartingVertex(startingVertex);
        dijkstra.alghorithm();
        double [] distances = dijkstra.getShortestDistances();

        //Then

        assertEquals(3.5, distances[finishVertex]);

    }
}
