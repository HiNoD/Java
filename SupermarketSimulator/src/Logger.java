import java.math.BigDecimal;
import java.time.LocalTime;

public class Logger {

    private static final String MSG_FORMAT = "[%s] %s\n";

    private static final String PRODUCTS_FORMED = "Supermarket products have been formed";
    private static final String SUPERMARKET_OPEN = "Supermarket is opened";
    private static final String SUPERMARKET_CLOSE = "The supermarket closed";

    public void productsFormed(LocalTime time) {
        printMessage(time, PRODUCTS_FORMED);
    }

    public void printProductInfo(Product product, BigDecimal quantity) {
        System.out.println(product.toString());
        System.out.println("  Quantity: " + quantity);
    }

    public void supermarketOpen(LocalTime time) {
        printMessage(time, SUPERMARKET_OPEN);
    }

    public void supermarketClose(LocalTime time) {
        printMessage(time, SUPERMARKET_CLOSE);
    }

    public void customerArrived(LocalTime time, String name, CustomerType type) {
        String msg = "New customer '" + name + "' (" + type.toString().toLowerCase() + ") arrived";
        printMessage(time, msg);
    }

    public void customerTake(LocalTime time, String name, String productName, BigDecimal quantity) {
        String msg = "Customer '" + name + "' picked up " + quantity + " units of " + productName;
        printMessage(time, msg);
    }

    public void customerAtCashDesk(LocalTime time, String name, BigDecimal price) {
        String msg = "Customer '" + name + "' at the cash desk, amount to pay: " + price;
        printMessage(time, msg);
    }

    public void customerPay(LocalTime time, BigDecimal price, PaymentMethod method) {
        String msg = "Customer paid " + price + " by " + method.toString().toLowerCase();
        printMessage(time, msg);
    }

    public void customerLeft(LocalTime time, String name) {
        String msg = "Customer '" + name + "' has left the supermarket";
        printMessage(time, msg);
    }

    public void printReport(Report report) {
        System.out.println(report);
    }

    private void printMessage(LocalTime time, String msg) {
        System.out.printf(MSG_FORMAT, time.toString(), msg);
    }
}
