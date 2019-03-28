package http.web.core;

import http.web.entity.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:liyangpeng
 * @date:2019/3/27 13:47
 */
public class ApplicationContext {
    /**
     * 接口请求路径对应控制器
     */
    private static final Map<String, RequestMapping> RequestMapping=new HashMap<>();

    /**
     * 根据请求路径映射对应请求包装类
     * @param path
     * @return
     */
    public static RequestMapping getRequestMapping(String path){
        return RequestMapping.get(path);
    }

    /**
     *实例化请求
     * @param path
     * @param requestMappings
     * @return
     */
    public static void setRequestMapping(String path,RequestMapping requestMappings){
        RequestMapping.put(path,requestMappings);
    }

    public static void print(){
        System.out.println(RequestMapping);
    }
}
