package http.web.annotion;

import java.lang.annotation.*;

/**
 * @author:liyangpeng
 * @date:2019/3/27 13:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Servlet {

}
