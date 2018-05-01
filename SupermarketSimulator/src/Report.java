import java.math.BigDecimal;

public class Report {

    private int purchases;
    private BigDecimal profit;

    public Report() {
        profit = BigDecimal.ZERO;
    }

    public void addBill(Bill bill) {
        profit = profit.add(bill.getTotalPrice());
        ++purchases;
    }

    @Override
    public String toString() {
        return "[Report]" +
                "\n  Number of purchases: " + purchases +
                "\n  Profit:              " + profit;
    }
}
