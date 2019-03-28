package http.web.core;

import http.web.entity.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

/**
 * @author:liyangpeng
 * @date:2019/3/27 15:52
 */
public class InvokeMethod {
    /**
     * 动态调用控制器方法
     * @param mapping
     * @param param
     * @return
     */
    public Object Invoke(RequestMapping mapping, Map<String,String> param){
        Method method=mapping.getMethod();
        method.setAccessible(true);
        Parameter [] paramters=method.getParameters();
        Object [] methodParam=new Object[paramters.length];
        for (int i = 0; i < paramters.length; i++) {
            methodParam[i]=TypeCovert.covert(paramters[i].getType(),param.get(paramters[i].getName()));
        }
        try {
            return method.invoke(mapping.getInstance().newInstance(),methodParam);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
