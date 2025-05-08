import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {
    private final Scanner scanner;
    private final CurrencyConverter converter;

    public MenuManager(Scanner scanner, CurrencyConverter converter) {
        this.scanner = scanner;
        this.converter = converter;
    }

    public void start() {
        while (true) {
            showMenu();
            int option = getOption();

            if (option == 6) break;
            if (!isValidOption(option)) continue;

            processConversion(option);
        }
    }

    private void showMenu() {
        System.out.println("\n=== Sea bienvenido/a al Conversor de Moneda ===");
        System.out.println("1. USD a EUR");
        System.out.println("2. USD a COP");
        System.out.println("3. USD a BRL");
        System.out.println("4. EUR a USD");
        System.out.println("5. USD a ARS");
        System.out.println("6. Salir");
        System.out.print("Elija una opción válida: ");
    }

    private int getOption() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido");
            scanner.nextLine();
            return -1;
        }
    }

    private boolean isValidOption(int option) {
        return option >= 1 && option <= 6;
    }

    private void processConversion(int option) {
        try {
            double amount = getAmount();
            double result = performConversion(option, amount);
            System.out.printf("Valor convertido: %.2f%n", result);
        } catch (InputMismatchException e) {
            System.out.println("Error: Valor numérico inválido");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private double getAmount() throws InputMismatchException {
        System.out.print("Ingrese el valor a convertir: ");
        return scanner.nextDouble();
    }

    private double performConversion(int option, double amount) {
        return switch (option) {
            case 1 -> converter.convert(amount, "USD", "EUR");
            case 2 -> converter.convert(amount, "USD", "COP");
            case 3 -> converter.convert(amount, "USD", "BRL");
            case 4 -> converter.convert(amount, "EUR", "USD");
            case 5 -> converter.convert(amount, "USD", "ARS");
            default -> throw new IllegalArgumentException("Opción inválida");
        };
    }
}
