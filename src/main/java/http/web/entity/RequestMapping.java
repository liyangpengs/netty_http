package http.web.entity;

import java.lang.reflect.Method;

/**
 * @author:liyangpeng
 * @date:2019/3/27 14:59
 */
public class RequestMapping {
    /**
     * 请求路径
     */
    private String path;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 对应的Controller
     */
    private Class<?> Instance;

    private Method method;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Class<?> getInstance() {
        return Instance;
    }

    public void setInstance(Class<?> instance) {
        Instance = instance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
