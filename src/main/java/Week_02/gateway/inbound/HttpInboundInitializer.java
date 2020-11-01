package Week_02.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer;
    @Override
    protected void initChannel(SocketChannel socketChannel)  {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        channelPipeline.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
