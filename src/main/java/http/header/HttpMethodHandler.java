package http.header;

import http.web.core.ApplicationContext;
import http.web.core.InvokeMethod;
import http.web.entity.RequestMapping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:liyangpeng
 * @date:2019/3/27 11:43
 */
public class HttpMethodHandler {

    public static void doGet(ChannelHandlerContext ctx,FullHttpRequest request){
        String url=request.uri();
        //get请求 路径跟参数分隔
        String [] pathAndParam=url.split("\\?");
        //请求路径
        String path=pathAndParam[0];
        //指定对应控制器
        Invoke(path,request.method().toString().toLowerCase(),ctx,pathAndParam);
    }

    public static void doPost(ChannelHandlerContext ctx,FullHttpRequest request){

    }

    public static void doPut(ChannelHandlerContext ctx,FullHttpRequest request){

    }

    public static void doDelete(ChannelHandlerContext ctx,FullHttpRequest request){

    }

    public static void doOption(ChannelHandlerContext ctx,FullHttpRequest request){

    }

    /**
     * 执行请求对应的handler
     * @param path
     * @param method
     * @param ctx
     * @param pathAndParam
     */
    public static void Invoke(String path,String method,ChannelHandlerContext ctx,String [] pathAndParam){
        RequestMapping mapping=ApplicationContext.getRequestMapping(path+"--"+method);
        if(mapping==null){
            PrintWrite.Print404(ctx);
            return;
        }
        InvokeMethod invokeMethod=new InvokeMethod();
        Object Response=null;
        if(pathAndParam.length==1){
            Response=invokeMethod.Invoke(mapping,null);
        }else{
            Response=invokeMethod.Invoke(mapping,paramSortOut(pathAndParam[1].split("\\&")));
        }
        PrintWrite.PrintResult(ctx,Response);
    }

    /**
     * 参数整理
     * @return
     */
    public static Map<String,String> paramSortOut(String [] params){
        Map<String,String> paramMap=new HashMap<>();
        for (String param: params) {
            paramMap.put(param.split("=")[0],param.split("=")[1]);
        }
        return paramMap;
    }
}
