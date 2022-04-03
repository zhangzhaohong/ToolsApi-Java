package com.koala.tools.http.annotation;

import java.lang.annotation.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 11:21
 * @description
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MixedHttpRequest {
}
