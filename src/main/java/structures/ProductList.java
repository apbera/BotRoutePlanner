package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductList {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product p){
        products.add(p);
    }

    public ArrayList<Product> filterByProduct(String productName){
        return products.stream()
                .filter(p -> p.getName().equals(productName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
