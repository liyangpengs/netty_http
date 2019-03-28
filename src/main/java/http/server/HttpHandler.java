package http.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author:liyangpeng
 * @date:2019/3/26 18:01
 */
public class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final HttpParse httpParse=new HttpParse();

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
          httpParse.ParseHttp(ctx,msg);
    }
}
