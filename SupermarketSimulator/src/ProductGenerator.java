import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProductGenerator {

    private static final double MAX_PRICE = 100.00;
    private static final double MIN_PRICE = 20.00;

    private static final List<String> ITEM = Arrays.asList(
            "Milk", "Bread", "Vodka", "Chocolate", "Ice-Cream",
            "Beer", "Wine", "Chips", "Cigarette", "Cigar"
    );

    private static final List<String> MASS = Arrays.asList(
            "Potato", "Chicken", "Sugar", "Turkey", "Banana",
            "Apple", "Orange", "Kiwi", "Sausage", "Salad"
    );

    private static final List<String> FOR_ADULT = Arrays.asList(
            "Vodka", "Beer", "Wine", "Cigarette", "Cigar"
    );

    private static Random rn = new Random();

    public static Product generate() {
        ProductType type = getType();
        String name = getName(type);
        BigDecimal price = getPrice();
        boolean forAdult = isForAdult(name);
        return new Product(name, type, price, forAdult);
    }

    private static ProductType getType() {
        ProductType[] types = ProductType.values();
        return types[rn.nextInt(types.length)];
    }

    private static String getName(ProductType type) {
        String name;
        switch (type) {
            default:
            case ITEM:
                name = ITEM.get(rn.nextInt(ITEM.size()));
                break;
            case MASS:
                name = MASS.get(rn.nextInt(MASS.size()));
                break;
        }
        return name;
    }

    private static BigDecimal getPrice() {
        double value = rn.nextDouble() * (MAX_PRICE - MIN_PRICE) + MIN_PRICE;
        return DecimalUtils.toMoney(value);
    }

    private static boolean isForAdult(String name) {
        return FOR_ADULT.contains(name);
    }
}
