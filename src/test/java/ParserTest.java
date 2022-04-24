import org.junit.jupiter.api.Test;
import structures.Product;
import structures.Vector2D;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void shouldParseCorrectly(){
        //Given

        String [][] expectedModules = {
                {"H", "H", "S", "H"},
                {"H", "B", "H", "H"},
                {"H", "H", "O", "S"}
        };
        int expected_x = 4;
        int expected_y = 3;
        int expected_n = 3;

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product("P1",3,2,1));
        expectedProducts.add(new Product("P1",0,2,2));
        expectedProducts.add(new Product("P2",1,1,2));

        Vector2D expectedStartPoint = new Vector2D(1,1);
        Vector2D expectedFinishPoint = new Vector2D(0,0);

        String expectedDesiredProduct = "P1";

        //When

        Parser parser = new Parser("src\\main\\resources\\grid-1.txt", "src\\main\\resources\\job-1.txt");
        int parsed_x = parser.getX();
        int parsed_y = parser.getY();
        int parsed_n = parser.getN();
        String [][] parsedModules = parser.getModules();
        List<Product> parsedProducts = parser.getProductList().getProducts();

        Vector2D parsedStartPoint = parser.getStartPoint();
        Vector2D parsedFinishPoint = parser.getFinishPoint();
        String parsedDesiredProduct = parser.getDesiredProduct();

        //Then

        assertEquals(expected_x, parsed_x);
        assertEquals(expected_y, parsed_y);
        assertEquals(expected_n, parsed_n);

        for (int i=0; i<3; i++){
            for (int j=0; j<4; j++){
                assertEquals(expectedModules[i][j], parsedModules[i][j]);
            }
        }

        assertEquals(expectedProducts.size(), parsedProducts.size());

        for (int i=0; i<expectedProducts.size(); i++){
            Product expectedProduct = expectedProducts.get(i);
            Product parsedProduct = parsedProducts.get(i);
            assertEquals(expectedProduct.getX(), parsedProduct.getX());
            assertEquals(expectedProduct.getY(), parsedProduct.getY());
            assertEquals(expectedProduct.getN(), parsedProduct.getN());
            assertEquals(expectedProduct.getName(), parsedProduct.getName());
        }

        assertEquals(expectedStartPoint.getX(), parsedStartPoint.getX());
        assertEquals(expectedStartPoint.getY(), parsedStartPoint.getY());
        assertEquals(expectedFinishPoint.getX(), parsedFinishPoint.getX());
        assertEquals(expectedFinishPoint.getY(), parsedFinishPoint.getY());

        assertEquals(expectedDesiredProduct, parsedDesiredProduct);

    }
}
