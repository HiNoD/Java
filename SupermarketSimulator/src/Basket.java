import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Basket {

    private Map<Product, BigDecimal> products;

    public Basket() {
        this.products = new HashMap<>();
    }

    public Map<Product, BigDecimal> getProducts() {
        return products;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product, BigDecimal quantity) {
        BigDecimal currQuantity = products.getOrDefault(product, BigDecimal.ZERO);
        products.put(product, currQuantity.add(quantity));
    }

    public void remove(Product product, BigDecimal quantity) {
        products.put(product, products.get(product).subtract(quantity));
        if (products.get(product).compareTo(BigDecimal.ZERO) == 0) {
            products.remove(product);
        }
    }

}
