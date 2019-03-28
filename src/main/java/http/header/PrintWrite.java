package http.header;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

/**
 * @author:liyangpeng
 * @date:2019/3/27 15:39
 */
public class PrintWrite {

    /**
     * 输出404
     * @param ctx
     */
    public static void Print404(ChannelHandlerContext ctx){
        StringBuffer buffer=new StringBuffer();
        buffer.append("<!DOCTYPE html><html lang=\"zh\"><head><meta charset=\"UTF-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
        buffer.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        buffer.append("</head><body><h1 align=\"center\">404</h1></body></html>");
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND, Unpooled.wrappedBuffer(buffer.toString().getBytes()));
        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, ""+response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        headers.add(HttpHeaderNames.SERVER,"lyp_io.netty_1.0.0");
        ctx.writeAndFlush(response);
        ctx.flush();
        ctx.close();

    }

    /**
     * 输出接口结果
     * @param ctx
     * @param result
     */
    public static void PrintResult(ChannelHandlerContext ctx,Object result){
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(result.toString().getBytes()));
        HttpHeaders headers = response.headers();
        headers.add(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
        headers.add(HttpHeaderNames.CONTENT_LENGTH, ""+response.content().readableBytes());
        headers.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        headers.add(HttpHeaderNames.SERVER,"lyp_io.netty_1.0.0");
        ctx.writeAndFlush(response);
        ctx.flush();
        ctx.close();
    }
}
