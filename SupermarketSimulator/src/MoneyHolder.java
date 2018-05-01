import java.math.BigDecimal;

public class MoneyHolder {

    private BigDecimal balance;

    public MoneyHolder(BigDecimal balance) {
        this.balance = DecimalUtils.normalizeMoney(balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void increase(final BigDecimal value) {
        assert (value.compareTo(BigDecimal.ZERO) >= 0);
        balance = balance.add(value);
    }

    public void decrease(final BigDecimal value) {
        assert (value.compareTo(BigDecimal.ZERO) >= 0);
        balance = balance.subtract(value);
    }
}
