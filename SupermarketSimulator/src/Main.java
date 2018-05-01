public class Main {

    private static final int ERROR_EXIT_CODE = 1;

    public static void main(String[] args) {
        try {
            Supermarket supermarket = new Shop();
            supermarket.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(ERROR_EXIT_CODE);
        }
    }
}