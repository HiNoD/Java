import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Discount {

    private Map<CustomerType, BigDecimal> discounts;

    public Discount() {
        this.discounts = new HashMap<>();
    }

    public void add(CustomerType type, BigDecimal value) {
        discounts.put(type, value);
    }

    public BigDecimal get(CustomerType type) {
        return discounts.getOrDefault(type, BigDecimal.ZERO);
    }
}
