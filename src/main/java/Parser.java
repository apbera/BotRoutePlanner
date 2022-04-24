import structures.Product;
import structures.ProductList;
import structures.Vector2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {

    private String [][] modules;
    private ProductList productList;
    private int x;
    private int y;
    private int n;

    private Vector2D startPoint;
    private Vector2D finishPoint;
    private String desiredProduct;

    public Parser(String gridFile, String jobFile)  {
        try {
            parseGrid(gridFile);
            parseJob(jobFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    private void parseGrid(String filename) throws FileNotFoundException {
        File file  = new File(filename);
        Scanner scanner = new Scanner(file);
        productList = new ProductList();

        int lineCounter = 0;
        while(scanner.hasNextLine()){
            String textLine = scanner.nextLine();
            if(lineCounter == 0){
                String [] dimensions = textLine.split(" ");
                this.x = Integer.parseInt(dimensions[0]);
                this.y = Integer.parseInt(dimensions[1]);
                this.n = Integer.parseInt(dimensions[2]);
                modules = new String[this.y][x];
            } else if (lineCounter <= this.y){
                modules[lineCounter-1] = textLine.split("");
            } else {
                String [] productData = textLine.split(" ");
                Product product = new Product(productData[0],Integer.parseInt(productData[1]),Integer.parseInt(productData[2]),Integer.parseInt(productData[3]));
                productList.add(product);
            }
            lineCounter++;
        }
    }

    private void parseJob(String filename) throws FileNotFoundException {
        File file  = new File(filename);
        Scanner scanner = new Scanner(file);

        String textLine = scanner.nextLine();
        String [] start = textLine.split(" ");
        this.startPoint = new Vector2D(Integer.parseInt(start[0]),Integer.parseInt(start[1]));

        textLine = scanner.nextLine();
        String [] finish = textLine.split(" ");
        this.finishPoint = new Vector2D(Integer.parseInt(finish[0]),Integer.parseInt(finish[1]));

        this.desiredProduct = scanner.nextLine().trim();
    }

    public String[][] getModules() {
        return modules;
    }

    public ProductList getProductList() {
        return productList;
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

    public Vector2D getStartPoint() {
        return startPoint;
    }

    public Vector2D getFinishPoint() {
        return finishPoint;
    }

    public String getDesiredProduct() {
        return desiredProduct;
    }
}
