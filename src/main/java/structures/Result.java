package structures;

public class Result {
    private double pathCost;
    private int [] parentsFromStart;
    private int [] parentsFromProduct;
    private int x;
    private int y;
    private Product product;
    private Vector2D finish;

    private static final int NO_PARENT = -1;

    public Result(double pathCost, int[] parentsFromStart, int[] parentsFromProduct, int x, int y, Product product, Vector2D finish) {
        this.pathCost = pathCost;
        this.parentsFromStart = parentsFromStart;
        this.parentsFromProduct = parentsFromProduct;
        this.x = x;
        this.y = y;
        this.product = product;
        this.finish = finish;
    }

    public void printResult() {
        if(parentsFromProduct!=null && parentsFromStart!=null){
            int hops = countParents(product.getY()*x + product.getX(), parentsFromStart)
                    + countParents(finish.getY()*x + finish.getX(), parentsFromProduct) - 2;
            System.out.println(hops);
            System.out.println(pathCost);
            printPath(parentsFromStart[product.getY()*x + product.getX()], parentsFromStart);
            printPath(finish.getY()*x + finish.getX(), parentsFromProduct);
        }

    }

    private int countParents(int currentVertex, int [] parents){
        if (currentVertex == NO_PARENT)
        {
            return 0;
        }

        return countParents(parents[currentVertex], parents) + 1;
    }

    private void printPath(int currentVertex, int [] parents){

        if (currentVertex == NO_PARENT)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        System.out.println(currentVertex%x + " " + currentVertex/x);
    }
}
