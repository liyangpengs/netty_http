package http.web.init;

import http.header.HttpMethod;
import http.web.annotion.Path;
import http.web.annotion.Servlet;
import http.web.core.ApplicationContext;
import http.web.entity.RequestMapping;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author:liyangpeng
 * @date:2019/3/27 14:54
 */
public class ContextInit {

    public void init(String packageName) throws Exception{
        Long startTime=System.currentTimeMillis();
        System.out.println("start init context.....");
        ScannerBean scan=new ScannerBean();
        List<Class<?>> listClass=scan.getClass(packageName);
        setRequestMapping(listClass);
        System.out.println("end init context.....");
        Long endTime=System.currentTimeMillis();
        System.out.println("启动耗时:"+(endTime-startTime)+"ms");
    }

    /**
     * 反射或者对应注解添加到控制中心
     * @param listClass
     * @throws Exception
     */
    private void setRequestMapping(List<Class<?>> listClass) throws Exception{
        for (Class<?> cls: listClass) {
            //取出标了Servlet注解的类 没标Servlet直接过滤掉
            Servlet servlet=cls.getAnnotation(Servlet.class);
            if(servlet==null){
                continue;
            }
            Path basePath=cls.getAnnotation(Path.class);
            Method [] methods=cls.getDeclaredMethods();
            for (Method method: methods) {
                Path methodPath=method.getAnnotation(Path.class);
                http.web.annotion.Method requesrMethod=method.getAnnotation(http.web.annotion.Method.class);
                //过滤没有带path注解的方法
                if(methodPath==null){
                    continue;
                }
                addRequest(basePath,methodPath,requesrMethod,cls,method);
            }
        }
    }

    /**
     * 添加请求映射
     * @param basePath
     * @param methodPath
     * @param requesrMethod
     * @param Instance
     * @param method
     */
    private void addRequest(Path basePath,Path methodPath,http.web.annotion.Method requesrMethod,Class<?> Instance,Method method) throws Exception{
        //请求控制bean
        RequestMapping requestMapping=new RequestMapping();
        //判断方法上是否指定了请求方式 如果没指定请求方式则默认为Get请求
        if(requesrMethod==null){
            requestMapping.setRequestMethod(HttpMethod.GET);
        }else{
            requestMapping.setRequestMethod(requesrMethod.value());
        }
        //判断Controller是否有Path注解 有Path注解则拼接请求参数
        //请求路径匹配未  /xxxxx--GET 或者 /xxxx--POST
        if(basePath==null){
            requestMapping.setPath(methodPath.value()+"--"+requestMapping.getRequestMethod());
        }else{
            requestMapping.setPath(basePath.value()+methodPath.value()+"--"+requestMapping.getRequestMethod());
        }
        requestMapping.setInstance(Instance);
        requestMapping.setMethod(method);
        //取出同同路劲请求类
        RequestMapping ExistsrequestMapping=ApplicationContext.getRequestMapping(requestMapping.getPath());
        if(ExistsrequestMapping==null){
            ApplicationContext.setRequestMapping(requestMapping.getPath(),requestMapping);
        }else{
            throw new Exception("存在同样请求路径的控制器:"+requestMapping.getPath()+" ------>:"+Instance.getClass()+" and "+ExistsrequestMapping.getInstance());
        }
    }
}
