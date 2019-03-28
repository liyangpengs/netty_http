package http.web.core;

import java.util.Date;

/**
 * @author:liyangpeng
 * @date:2019/3/27 17:09
 */
public class TypeCovert {
    /**
     * 类型转换
     * @param type
     * @param data
     * @return
     */
    public static Object covert(Class<?> type,Object data){
        if(type.getName().equals("java.lang.String")){
            return String.valueOf(data);
        }
        else if(type.getName().equals("java.util.Date")){
            return (Date)data;
        }
        else if(type.getName().equals("java.lang.Integer")){
            return Integer.parseInt(String.valueOf(data));
        }
        return null;
    }
}
