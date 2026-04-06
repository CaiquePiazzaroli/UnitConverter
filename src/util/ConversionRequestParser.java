package util;

import com.google.gson.Gson;
import model.ConversionRequest;

public class ConversionRequestParser {
    public static ConversionRequest parse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ConversionRequest.class);
    }
}
