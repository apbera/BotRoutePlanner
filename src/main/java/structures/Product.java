package structures;

public class Product {
    private String name;
    private int x;
    private int y;
    private int n;

    public Product(String name,int x,int y,int n){
        this.name = name;
        this.x = x;
        this.y = y;
        this.n = n;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    public String getName() {
        return name;
    }
}
