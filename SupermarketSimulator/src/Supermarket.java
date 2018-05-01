import java.math.BigDecimal;
import java.time.LocalTime;

public interface Supermarket {

    int MAX_PRODUCTS = 20;
    int MIN_PRODUCTS = 1;
    double MAX_PRODUCT_COUNT = 50;
    double MIN_PRODUCT_COUNT = 1;
    BigDecimal BONUS_PERCENT = new BigDecimal(0.1);
    BigDecimal RETIRED_DISCOUNT = new BigDecimal(0.1);
    LocalTime PRODUCT_IMPORT_TIME = LocalTime.of(9, 0);
    LocalTime OPEN_TIME = LocalTime.of(10, 0);
    LocalTime CLOSE_TIME = LocalTime.of(20, 0);
    LocalTime MAX_TIME_STEP = LocalTime.of(0, 30);
    LocalTime MIN_TIME_STEP = LocalTime.of(0, 1);

    public void start();
}
