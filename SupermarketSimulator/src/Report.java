import java.math.BigDecimal;
import java.util.Map;

public class Report {

    private int purchases;
    private BigDecimal profit;
    private Map<Product, BigDecimal> products;

    public Report() {
        profit = BigDecimal.ZERO;
    }

    public void addBill(Bill bill) {
        profit = profit.add(bill.getTotalPrice());
        ++purchases;
    }

    public void addProducts(Product product, BigDecimal quantity) {
        products.put(product, quantity);
    }

    @Override
    public String toString() {
        return "[Report]" +
                "\n  Number of purchases: " + purchases +
                "\n  Profit:              " + profit +
                "\n Product was buyed:" + products;
    }
}
