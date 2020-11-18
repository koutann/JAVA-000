package Week_05.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFilter {

    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders header = fullRequest.headers(); // 获取http头信息

        header.set("NIO", "koutann");
    }
}
