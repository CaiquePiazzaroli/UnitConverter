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

        InputStream is = exchange.getRequestBody();
        String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);

        String response = "";

        if(checkRequest(json)) {
            response = "OK";
            exchange.sendResponseHeaders(200, response.length());
            ConversionRequest request = ConversionRequestParser.parse(json);
            UnitConversionService.convert(request);
        } else {
            response = "Bad request 404";
            exchange.sendResponseHeaders(400, response.length());
        }

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private boolean checkRequest(String bodyRequest) {
        return bodyRequest.contains("value") && bodyRequest.contains("from") && bodyRequest.contains("to") && bodyRequest.contains("unit");
    }
}
