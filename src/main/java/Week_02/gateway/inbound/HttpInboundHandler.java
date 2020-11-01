package Week_02.gateway.inbound;

import Week_02.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private String proxyServer;
    private HttpOutboundHandler handler;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServer);
    }
}
