import java.math.BigDecimal;

public class Bill {

    private BigDecimal totalPrice;
    private BigDecimal bonuses;


    public Bill() {
        totalPrice = BigDecimal.ZERO;
        bonuses = BigDecimal.ZERO;
    }

    public void addProduct(Product product, BigDecimal quantity,
                           BigDecimal discount, BigDecimal bonusPercent) {
        BigDecimal productPrice = product.getPrice().multiply(quantity);
        totalPrice = totalPrice.add(productPrice);
        totalPrice = DecimalUtils.normalizeMoney(totalPrice
                .multiply(BigDecimal.ONE.subtract(discount)));
        bonuses = bonuses.add(totalPrice.multiply(bonusPercent));
        bonuses = DecimalUtils.normalizeNumber(bonuses);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public BigDecimal getBonuses() {
        return bonuses;
    }
}
