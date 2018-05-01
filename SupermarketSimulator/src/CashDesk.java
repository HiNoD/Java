import java.math.BigDecimal;

public class CashDesk {

    private Discount discount;
    private Report report;
    private BigDecimal bonusPercent;

    public CashDesk() {
        report = new Report();
        bonusPercent = BigDecimal.ZERO;
        discount = new Discount();
    }

    public Bill getBill(Customer customer) {
        Basket basket = customer.getBasket();
        BigDecimal discountValue = discount.get(customer.getType());
        Bill bill = new Bill();
        basket.getProducts().forEach(((product, quantity) ->
                bill.addProduct(product, quantity, discountValue, bonusPercent)));
        return bill;
    }

    public void pay(Bill bill) {
        report.addBill(bill);
    }

    public Report getReport() {
        return report;
    }

    public void setBonusPercent(BigDecimal bonusPercent) {
        this.bonusPercent = bonusPercent;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
