package Week_05.gateway.inbound;

import Week_05.gateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> proxyServers;
    @Autowired
    private HttpInboundHandler handler;
    @Autowired
    private HttpOutboundHandler outboundHandler;
    @Override
    protected void initChannel(SocketChannel socketChannel)  {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        handler.setProxyServer(proxyServers);
        outboundHandler.outBondHandler(proxyServers);
        channelPipeline.addLast(handler);
    }
}
