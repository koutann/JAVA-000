/**
 * Copyright (C), 2015-2020, 京东
 * FileName: NettyServerTest
 * Author:   huangdan6
 * Date:     2020/11/18 下午11:01
 * Description: 测试类
 */
package Week_05.gateway;


import Week_05.gateway.inbound.HttpInboundServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 测试类
 *
 * @author huangdan6
 * @date 2020/11/18 23:01
 * @since 1.0.0
 */
@SpringBootTest(classes = NettyServerApplication.class)
@RunWith(SpringRunner.class)
public class NettyServerTest {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    @Autowired
    private HttpInboundServer server;


    @Test
    public  void test() {
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
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run(port, proxyServers);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
