package com.liuran.web.utils.titan.repository.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/8/24.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TitanVertex {
    String label() default "";
}
