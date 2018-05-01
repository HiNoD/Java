import java.math.BigDecimal;

public class DecimalUtils {

    private static final int MONEY_SCALE = 2;
    private static final int MASS_SCALE = 3;
    private static final int NUMBER_SCALE = 0;

    private DecimalUtils() {
    }

    public static BigDecimal normalizeMoney(BigDecimal value) {
        return value.setScale(MONEY_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal normalizeQuantity(ProductType type, BigDecimal value) {
        BigDecimal result;
        switch (type) {
            default:
            case ITEM:
                result = value.setScale(NUMBER_SCALE, BigDecimal.ROUND_HALF_EVEN);
                break;
            case MASS:
                result = value.setScale(MASS_SCALE, BigDecimal.ROUND_HALF_EVEN);
                break;
        }
        return result;
    }

    public static BigDecimal normalizeNumber(BigDecimal value) {
        return value.setScale(NUMBER_SCALE, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal toMoney(double value) {
        return normalizeMoney(new BigDecimal(value));
    }

    public static BigDecimal toQuantity(ProductType type, double value) {
        return normalizeQuantity(type, new BigDecimal(value));
    }

    public static BigDecimal toNumber(double value) {
        return normalizeNumber(new BigDecimal(value));
    }
}
