package http.web.annotion;

import java.lang.annotation.*;

/**
 * @author:liyangpeng
 * @date:2019/3/27 12:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Method {
    String value();
}
