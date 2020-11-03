package Week_03.gateway.inbound;

import Week_03.gateway.filter.HttpRequestFilter;
import Week_03.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private List<String> proxyServer;
    private HttpOutboundHandler handler;

//    @Autowired
//    private HttpRequestFilter httpRequestFilter;

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest fullRequest = (FullHttpRequest) msg;
        HttpRequestFilter httpRequestFilter = new HttpRequestFilter();
        httpRequestFilter.filter(fullRequest, ctx);
        handler.handle(fullRequest, ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
