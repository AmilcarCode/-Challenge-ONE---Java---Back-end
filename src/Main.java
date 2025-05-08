
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            ExchangeRateProvider rateProvider = new ApiExchangeRateProvider();
            CurrencyConverter converter = new CurrencyConverter(rateProvider);
            MenuManager menuManager = new MenuManager(scanner, converter);
            menuManager.start();
        } catch (ExchangeRateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}