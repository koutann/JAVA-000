package Week_03.gateway;

import Week_03.gateway.inbound.HttpInboundServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
        String proxyServer1 = System.getProperty("proxyServer1","http://localhost:8089");
        String proxyPort = System.getProperty("proxyPort","8888");

        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        List<String> proxyServers = new ArrayList<>();
        proxyServers.add(proxyServer);
        proxyServers.add(proxyServer1);
        HttpInboundServer server = new HttpInboundServer(port, proxyServers);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
