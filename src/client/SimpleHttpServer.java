package client;

import com.sun.net.httpserver.HttpServer;
import routes.UnitConversionHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public void runServer(Integer port)
    {
        try {

            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/conversions", new UnitConversionHandler());

            server.setExecutor(null);
            server.start();

            System.out.println("Server is running on port " + port);
            System.out.println("http://localhost:" + port);

        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }
    }
}