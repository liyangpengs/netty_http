package http.server;

import http.header.HttpMethod;
import http.header.HttpMethodHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author:liyangpeng
 * @date:2019/3/27 10:23
 */
public class HttpParse {
    /**
     * 请求解析
     * @param request
     */
    public void ParseHttp(ChannelHandlerContext ctx,FullHttpRequest request){
        //匹配请求
        switch (request.method().toString().toLowerCase()){
            case HttpMethod.GET:
                HttpMethodHandler.doGet(ctx,request);
                break;
            case HttpMethod.POST:
                HttpMethodHandler.doPost(ctx,request);
                break;
            case HttpMethod.PUT:
                HttpMethodHandler.doPut(ctx,request);
                break;
            case HttpMethod.DELETE:
                HttpMethodHandler.doDelete(ctx,request);
                break;
            case HttpMethod.OPTION:
                HttpMethodHandler.doOption(ctx,request);
                break;
        }
    }
}
