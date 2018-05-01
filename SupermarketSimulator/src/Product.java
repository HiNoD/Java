import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private String name;
    private ProductType type;
    private BigDecimal price;
    private boolean forAdult;

    public Product(String name, ProductType type, BigDecimal price, boolean forAdult) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.forAdult = forAdult;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isForAdult() {
        return forAdult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return forAdult == product.forAdult &&
                Objects.equals(name, product.name) &&
                type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, forAdult);
    }

    @Override
    public String toString() {
        return "[product \'" + name + "\' details]" +
                "\n  Type:        " + type +
                "\n  Price:       " + price +
                "\n  isAdultOnly: " + forAdult;
    }
}
