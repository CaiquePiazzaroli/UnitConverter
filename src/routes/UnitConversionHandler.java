package routes;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import model.ConversionRequest;
import services.UnitConversionService;
import util.ConversionRequestParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class UnitConversionHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        // Validando o metodo da requisição
        if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
            sendResponse(exchange, "Method Not Allowed", 405);
            return;
        }

        // Recebendo o body da requisição e colocando em um InputStream
        try (InputStream is = exchange.getRequestBody()) {

            // Convertendo de bytes do body para String legível
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

            // Verificando se o json é valido
            if (isValidJson(json)) {
                // Mapeando o json String para A classe ConversionRequest
                ConversionRequest request = ConversionRequestParser.parse(json);

                // Convertento o valor recebido
                double result = UnitConversionService.convert(request);

                // Estruturando a resposta
                String response = String.format("{\"result\": %.2f}", result);

                // Enviando a resposta para o client
                sendResponse(exchange, response, 200);
            } else {
                sendResponse(exchange, "{\"error\": \"Invalid request payload\"}", 400);
            }
        } catch (Exception e) {
            sendResponse(exchange, "{\"error\": \"Internal Server Error\"}", 500);
        }
    }

    private boolean isValidJson(String body) {

        // Verifica se existe dados
        if (body == null || body.isBlank()) return false;

        // Verifica se a estrutura do json é correta
        return body.contains("\"value\"") &&
                body.contains("\"from\"") &&
                body.contains("\"to\"") &&
                body.contains("\"unit\"");
    }

    private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException {

        // Transforma a string em bytes (Processo inverso)
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        // Configurando o header content-type para appplication/json
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        // Enviando a resposta (Statuscode)
        exchange.sendResponseHeaders(statusCode, bytes.length);

        // Escrevendo o resultado no corpo da resposta
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}