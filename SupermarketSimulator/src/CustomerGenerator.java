import java.math.BigDecimal;
import java.util.Random;

public class CustomerGenerator {

    private static final double MAX_CASH = 5000.00;
    private static final double MIN_CASH = 100.00;
    private static final double MAX_BONUSES = 100;
    private static final double MIN_BONUSES = 30;
    private static final String NAME_PATTERN = "Customer#";

    private static Random rn = new Random();
    private static int id = 0;

    public static Customer generate() {
        String name = getName();
        CustomerType type = getType();
        BigDecimal cash = getCash();
        BigDecimal card = getCash();
        BigDecimal bonuses = getBonuses();
        return new Customer(name, type, cash, card, bonuses);
    }

    private static String getName() {
        return NAME_PATTERN + (++id);
    }

    private static CustomerType getType() {
        CustomerType[] types = CustomerType.values();
        return types[rn.nextInt(types.length)];
    }

    private static BigDecimal getCash() {
        double value = rn.nextDouble() * (MAX_CASH - MIN_CASH) + MIN_CASH;
        return DecimalUtils.toMoney(value);
    }

    private static BigDecimal getBonuses() {
        double value = rn.nextDouble() * (MAX_BONUSES - MIN_BONUSES) + MIN_BONUSES;
        return DecimalUtils.toNumber(value);
    }
}
