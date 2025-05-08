
import java.util.List;
import java.util.Map;

public class CurrencyConverter {
    private final ExchangeRateProvider rateProvider;
    private Map<String, Double> rates;

    public CurrencyConverter(ExchangeRateProvider rateProvider) throws ExchangeRateException {
        this.rateProvider = rateProvider;
        loadRates();
    }

    private void loadRates() throws ExchangeRateException {
        rates = rateProvider.getExchangeRates();
        validateRequiredCurrencies();
    }

    private void validateRequiredCurrencies() throws ExchangeRateException {
        List<String> required = List.of("USD", "EUR", "COP", "BRL");
        required.forEach(currency -> {
            if (!rates.containsKey(currency)) {
                try {
                    throw new ExchangeRateException("Divisa faltante: " + currency);
                } catch (ExchangeRateException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public double convert(double amount, String fromCurrency, String toCurrency) {
        validateCurrency(fromCurrency);
        validateCurrency(toCurrency);

        double fromRate = rates.get(fromCurrency);
        double toRate = rates.get(toCurrency);
        return amount * (toRate / fromRate);
    }

    private void validateCurrency(String currency) {
        if (!rates.containsKey(currency)) {
            throw new IllegalArgumentException("Divisa no soportada: " + currency);
        }
    }
}

