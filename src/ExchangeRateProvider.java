
import java.util.Map;

public abstract class ExchangeRateProvider {
     abstract Map<String, Double> getExchangeRates() throws ExchangeRateException;
}
