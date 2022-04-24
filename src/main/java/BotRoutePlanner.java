import structures.Product;
import structures.Result;
import structures.Vector2D;

import java.util.List;

import static java.lang.Math.max;

public class BotRoutePlanner {

    private String [][] modules;
    private List<Product> desiredProductList;
    private int x;
    private int y;
    private int n;
    private Vector2D start;
    private Vector2D finish;

    private double [][] adjMatrix;
    private int v;
    double inf = Double.POSITIVE_INFINITY;


    public BotRoutePlanner(Parser parser) {
        this.modules = parser.getModules();
        this.desiredProductList = parser.getProductList().filterByProduct(parser.getDesiredProduct());
        this.x = parser.getX();
        this.y = parser.getY();
        this.n = parser.getN();
        this.start = parser.getStartPoint();
        this.finish = parser.getFinishPoint();
        makeAdjMatrix();
    }

    public Result findFastestPath(){
        int [] fastestParentsFromStart = null;
        int [] fastestParentsFromProduct = null;

        double fastestFullPathCost = Double.POSITIVE_INFINITY;
        Product fastestProduct = null;

        for (Product product:desiredProductList) {
            double [] shortestDistancesFromStart;
            double [] shortestDistancesFromProduct;
            int [] parentsFromStart;
            int [] parentsFromProduct;
            double fullPathCost;
            int startingVertex = start.getY()*x + start.getX();
            int stationVertex = finish.getY()*x + finish.getX();
            int productLocation = product.getY()*x + product.getX();

            Dijkstra dijkstra = new Dijkstra(adjMatrix, v);
            dijkstra.setStartingVertex(startingVertex);
            dijkstra.alghorithm();

            shortestDistancesFromStart = dijkstra.getShortestDistances();
            parentsFromStart = dijkstra.getParents();

            dijkstra.setStartingVertex(productLocation);
            dijkstra.alghorithm();

            shortestDistancesFromProduct = dijkstra.getShortestDistances();
            parentsFromProduct = dijkstra.getParents();

            fullPathCost = shortestDistancesFromStart[productLocation] + shortestDistancesFromProduct[stationVertex]
                    + getExtractionTime(modules[product.getY()][product.getX()], product.getN());
            if(fullPathCost < fastestFullPathCost){
                fastestFullPathCost = fullPathCost;
                fastestParentsFromStart = parentsFromStart;
                fastestParentsFromProduct = parentsFromProduct;
                fastestProduct = product;
            }
        }
        return new Result(fastestFullPathCost,fastestParentsFromStart, fastestParentsFromProduct, x, y, fastestProduct, finish);
    }

    private void makeAdjMatrix(){
        this.v = x*y;
        adjMatrix = new double[v][v];

        for (int i=0; i<v; i++){
            for (int j=0; j<v; j++){
                adjMatrix[i][j] = inf;
            }
        }

        for (int i=0; i<v; i++){
            adjMatrix[i][i] = 0;
        }

        //vertices with 4 neighbours
        for (int i = 1; i<y-1; i++){
            for (int j = 1; j<x-1; j++){
                if(!modules[i][j].equals("O")) {
                    double currentTime = getRouteTime(modules[i][j]);
                    adjMatrix[i*x + j][i*x + j + 1] = max(currentTime, getRouteTime(modules[i][j+1]));
                    adjMatrix[i*x + j][i*x + j - 1] = max(currentTime, getRouteTime(modules[i][j-1]));
                    adjMatrix[i*x + j][(i-1)*x + j] = max(currentTime, getRouteTime(modules[i-1][j]));
                    adjMatrix[i*x + j][(i+1)*x + j] = max(currentTime, getRouteTime(modules[i+1][j]));
                }
            }
        }

        //vertices with 3 neighbours (top and bottom)
        for (int i=1; i<x-1; i++){
            if(!modules[0][i].equals("O")){
                double currentTime = getRouteTime(modules[0][i]);
                adjMatrix[i][i + 1] = max(currentTime, getRouteTime(modules[0][i+1]));
                adjMatrix[i][i - 1] = max(currentTime, getRouteTime(modules[0][i-1]));
                adjMatrix[i][x + i] = max(currentTime, getRouteTime(modules[1][i]));
            }

            if(!modules[y-1][i].equals("O")){
                double currentTime = getRouteTime(modules[y-1][i]);
                adjMatrix[(y-1)*x + i][(y-1)*x + i + 1] = max(currentTime, getRouteTime(modules[y-1][i+1]));
                adjMatrix[(y-1)*x + i][(y-1)*x + i - 1] = max(currentTime, getRouteTime(modules[y-1][i-1]));
                adjMatrix[(y-1)*x + i][(y-2)*x + i] = max(currentTime, getRouteTime(modules[y-2][i]));
            }
        }

        //vertices with 3 neighbours (top and bottom)
        for (int i=1; i<y-1; i++){
            if(!modules[i][0].equals("O")){
                double currentTime = getRouteTime(modules[i][0]);
                adjMatrix[x*i][x*(i-1)] = max(currentTime, getRouteTime(modules[i-1][0]));
                adjMatrix[x*i][x*i + 1] = max(currentTime, getRouteTime(modules[i][1]));
                adjMatrix[x*i][x*(i+1)] = max(currentTime, getRouteTime(modules[i+1][0]));
            }

            if(!modules[i][x-1].equals("O")){
                double currentTime = getRouteTime(modules[i][x-1]);
                adjMatrix[x*(i+1) - 1][x*i - 1] = max(currentTime, getRouteTime(modules[i-1][x-1]));
                adjMatrix[x*(i+1) - 1][x*(i+2) - 1] = max(currentTime, getRouteTime(modules[i+1][x-1]));
                adjMatrix[x*(i+1) - 1][x*(i+1) - 2] = max(currentTime, getRouteTime(modules[i][x-2]));
            }
        }

        //vertices with 2 neighbours (corners)
        if(!modules[0][0].equals("O")){
            double currentTime = getRouteTime(modules[0][0]);
            adjMatrix[0][1] = max(currentTime, getRouteTime(modules[0][1]));
            adjMatrix[0][x] = max(currentTime, getRouteTime(modules[1][0]));
        }

        if(!modules[0][x-1].equals("O")){
            double currentTime = getRouteTime(modules[0][x-1]);
            adjMatrix[x-1][x-2] = max(currentTime, getRouteTime(modules[0][x-2]));
            adjMatrix[x-1][2*x - 1] = max(currentTime, getRouteTime(modules[1][x-1]));
        }

        if(!modules[y-1][0].equals("O")){
            double currentTime = getRouteTime(modules[y-1][0]);
            adjMatrix[(y-1) * x][(y-2) * x] = max(currentTime, getRouteTime(modules[y-2][0]));
            adjMatrix[(y-1) * x][(y-1) * x + 1] = max(currentTime, getRouteTime(modules[y-1][1]));
        }

        if(!modules[y-1][x-1].equals("O")){
            double currentTime = getRouteTime(modules[y-1][x-1]);
            adjMatrix[y * x - 1][y * x - 2] = max(currentTime, getRouteTime(modules[y-1][x-2]));
            adjMatrix[y * x - 1][(y - 1) * x - 1] = max(currentTime, getRouteTime(modules[y-2][x-1]));
        }
    }

    private double getRouteTime(String route){
        return switch (route) {
            case "H" -> 0.5;
            case "B" -> 1;
            case "S" -> 2;
            default -> inf;
        };
    }

    private double getExtractionTime(String type, int n){
        return switch (type) {
            case "H" -> 3*n + 4;
            case "B" -> 2*n + 2;
            case "S" -> n + 1;
            default -> inf;
        };
    }
}