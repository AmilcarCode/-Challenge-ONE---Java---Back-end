import java.lang.reflect.Type;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class ApiExchangeRateProvider extends ExchangeRateProvider {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/api_key/latest/USD";

    @Override
    public Map<String, Double> getExchangeRates() throws ExchangeRateException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            validateApiResponse(jsonObject);

            return parseConversionRates(jsonObject);
        } catch (Exception e) {
            throw new ExchangeRateException("Error obteniendo tasas de cambio: " + e.getMessage());
        }
    }

    private void validateApiResponse(JsonObject jsonObject) throws ExchangeRateException {
        if (!jsonObject.get("result").getAsString().equals("success")) {
            throw new ExchangeRateException("Respuesta no exitosa de la API");
        }
    }

    private Map<String, Double> parseConversionRates(JsonObject jsonObject) {
        JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");
        Type type = new TypeToken<Map<String, Double>>(){}.getType();
        return new Gson().fromJson(conversionRates, type);
    }
}
