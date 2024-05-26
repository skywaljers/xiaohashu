package com.skywalker.framework.biz.operationlog.aspect;

/**
 * @author baijj
 * @date 2024-05-27
 */
public @interface ApiOperationLog {
    String description() default "";
}
