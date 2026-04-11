import config.ServerConfig;
import client.SimpleHttpServer;

public class Main {
    public static void main(String[] args) {
        Integer port = ServerConfig.port;
        SimpleHttpServer server = new SimpleHttpServer();
        server.runServer(port);
    }
}
