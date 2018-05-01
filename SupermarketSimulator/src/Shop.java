import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Shop implements Supermarket {

    private Logger logger;
    private List<Customer> customers;
    private Basket superBasket;
    private Random rn;
    private CashDesk cashDesk;

    public Shop() {
        this.logger = new Logger();
        this.customers = new ArrayList<>();
        this.superBasket = new Basket();
        this.rn = new Random();
        this.cashDesk = new CashDesk();
    }

    public void start() {
        Discount discount = new Discount();
        discount.add(CustomerType.RETIRED, RETIRED_DISCOUNT);
        cashDesk.setDiscount(discount);
        cashDesk.setBonusPercent(BONUS_PERCENT);
        importProduct();
        work();
    }

    private void importProduct() {
        logger.productsFormed(PRODUCT_IMPORT_TIME);
        int count = rn.nextInt(MAX_PRODUCTS - MIN_PRODUCTS) + MIN_PRODUCTS;
        for (int i = 0; i < count; ++i) {
            Product product = ProductGenerator.generate();
            double value = rn.nextDouble() * (MAX_PRODUCT_COUNT - MIN_PRODUCT_COUNT)
                    + MIN_PRODUCT_COUNT;
            BigDecimal quantity = DecimalUtils.toQuantity(product.getType(), value);
            superBasket.add(product, quantity);
        }
        superBasket.getProducts().forEach(((product, quantity) ->
                logger.printProductInfo(product, quantity)));
    }

    private void work() {
        logger.supermarketOpen(OPEN_TIME);
        LocalTime currTime = nextTime(OPEN_TIME);
        while (currTime.compareTo(CLOSE_TIME) < 0) {
            actCustomer(currTime);
            currTime = nextTime(currTime);
        }
        serveRemaining();
        logger.supermarketClose(CLOSE_TIME);
        logger.printReport(cashDesk.getReport());
    }

    private LocalTime nextTime(LocalTime prevTime) {
        int minStep = MIN_TIME_STEP.toSecondOfDay();
        int maxStep = MAX_TIME_STEP.toSecondOfDay();
        int step = rn.nextInt(maxStep - minStep) + minStep;
        return prevTime.plusSeconds(step);
    }

    private void actCustomer(LocalTime currTime) {
        CustomerAction[] actions = CustomerAction.values();
        CustomerAction action = actions[rn.nextInt(actions.length)];
        Customer customer = getRandomCustomer();
        switch (action) {
            default:
            case NONE:
                break;
            case ARRIVE:
                addCustomer(currTime);
                break;
            case TAKE:
                if (customer != null) {
                    processTake(currTime, customer);
                }
                break;
            case PAY:
                if (customer != null) {
                    processPay(currTime, customer);
                }
                break;
        }
    }

    private void addCustomer(LocalTime time) {
        Customer customer = CustomerGenerator.generate();
        customers.add(customer);
        logger.customerArrived(time, customer.getName(), customer.getType());
    }

    private void processTake(LocalTime time, Customer customer) {
        List<Map.Entry<Product, BigDecimal>> entries =
                new ArrayList<>(superBasket.getProducts().entrySet());
        Map.Entry<Product, BigDecimal> entry =
                entries.get(rn.nextInt(entries.size()));
        Product product = entry.getKey();
        if (!customer.getBasket().getProducts().containsKey(product)) {
            double maxQuantity = Math.floor(entry.getValue().doubleValue());
            double value = rn.nextDouble() * (maxQuantity - MIN_PRODUCT_COUNT)
                    + MIN_PRODUCT_COUNT;
            BigDecimal quantity = DecimalUtils.toQuantity(product.getType(), value);
            transferProduct(superBasket, customer.getBasket(), product, quantity);
            logger.customerTake(time, customer.getName(), product.getName(), quantity);
        }
    }

    private void processPay(LocalTime time, Customer customer) {
        Bill bill = cashDesk.getBill(customer);
        while (!customer.getBasket().isEmpty() && !customer.canPay(bill)) {
            List<Map.Entry<Product, BigDecimal>> entries =
                    new ArrayList<>(customer.getBasket().getProducts().entrySet());
            Map.Entry<Product, BigDecimal> entry =
                    entries.get(rn.nextInt(entries.size()));
            transferProduct(customer.getBasket(), superBasket,
                    entry.getKey(), entry.getValue());
            bill = cashDesk.getBill(customer);
        }
        if (!customer.getBasket().isEmpty()) {
            PaymentMethod method = customer.pay(bill);
            cashDesk.pay(bill);
            logger.customerAtCashDesk(time, customer.getName(),
                    bill.getTotalPrice());
            logger.customerPay(time, bill.getTotalPrice(), method);
        }
        customers.remove(customer);
        logger.customerLeft(time, customer.getName());
    }

    private void transferProduct(Basket from, Basket to,
                                 Product product, BigDecimal quantity) {
        from.remove(product, quantity);
        to.add(product, quantity);
    }

    private Customer getRandomCustomer() {
        Customer customer = null;
        if (!customers.isEmpty()) {
            customer = customers.get(rn.nextInt(customers.size()));
        }
        return customer;
    }

    private void serveRemaining() {
        while (!customers.isEmpty()) {
            processPay(CLOSE_TIME, customers.get(0));
        }
    }
}
