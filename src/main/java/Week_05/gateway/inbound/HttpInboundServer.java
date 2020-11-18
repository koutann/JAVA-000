package Week_05.gateway.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HttpInboundServer {

    @Autowired
    private HttpInboundInitializer inboundInitializer;
    public void run(int port, List proxyServers){
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 128) // 服务端顺序处理客户端链接，同一个时间只能处理一个，将客户端请求放在队列中，队列的最大长度
                    .option(ChannelOption.TCP_NODELAY, true) // 启用或关闭Nagle算法，尽可能传递大包：设置为true关闭Nagle算法
                    .option(ChannelOption.SO_KEEPALIVE, true)//2小时没有数据通讯，TCP会发送探活
                    .option(ChannelOption.SO_REUSEADDR, true) // 表示允许重复使用本地地址和端口
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024) //接收缓冲区大小
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)  //发送缓冲区大小
                    .option(EpollChannelOption.SO_REUSEPORT, true)//支持多个线程/进程绑定同一个端口
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);//指定内存池

            inboundInitializer.setProxyServers(proxyServers);
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)//绑定bossGroup和workerGroup，指定用NIOchannel
                    //加上处理handler
                    .handler(new LoggingHandler(LogLevel.INFO)).childHandler(inboundInitializer);

            Channel ch = b.bind(port).sync().channel();
            log.info("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } catch (Exception e){
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
