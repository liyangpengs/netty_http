package http.handler.controller;

import http.web.annotion.Path;
import http.web.annotion.Servlet;

/**
 * @author:liyangpeng
 * @date:2019/3/27 14:06
 */
@Servlet
@Path("/hello")
public class TestController {

    @Path("/test")
    public String Success(String name){
        System.out.println("收到参数为:"+name);
        return "success";
    }
}