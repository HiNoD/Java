import java.math.BigDecimal;

public class Customer {

    private String name;
    private CustomerType type;
    private Basket basket;
    private MoneyHolder cash;
    private MoneyHolder card;
    private MoneyHolder bonuses;

    public Customer(String name, CustomerType type,
                    BigDecimal cash, BigDecimal card, BigDecimal bonuses) {
        this.name = name;
        this.type = type;
        this.basket = new Basket();
        this.cash = new MoneyHolder(cash);
        this.card = new MoneyHolder(card);
        this.bonuses = new MoneyHolder(bonuses);
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    public Basket getBasket() {
        return basket;
    }

    public boolean canPay(Bill bill) {
        BigDecimal price = bill.getTotalPrice();
        BigDecimal bonusesBalance = bonuses.getBalance()
                .add(bill.getBonuses());
        BigDecimal cashBalance = cash.getBalance();
        BigDecimal cardBalance = card.getBalance();
        return (bonusesBalance.compareTo(price) >= 0
                || cashBalance.compareTo(price) >= 0
                || cardBalance.compareTo(price) >= 0);
    }

    public PaymentMethod pay(Bill bill) {
        PaymentMethod method = null;
        BigDecimal price = bill.getTotalPrice();
        bonuses.increase(bill.getBonuses());
        if (bonuses.getBalance().compareTo(price) >= 0) {
            bonuses.decrease(price);
            method = PaymentMethod.BONUSES;
        } else if (cash.getBalance().compareTo(price) >= 0) {
            cash.decrease(price);
            method = PaymentMethod.CASH;
        } else if (card.getBalance().compareTo(price) >= 0) {
            card.decrease(price);
            method = PaymentMethod.CARD;
        }
        return method;
    }
}
