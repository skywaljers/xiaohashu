package com.skywalker.framework.biz.operationlog.aspect;

import com.skywalker.framework.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author baijj
 * @date 2024-05-27
 */
@Aspect
@Slf4j
public class ApiOperationLogAspect {

    /** 以自定义 @ApiOperationLog 注解为切点，
     * 凡是添加 @ApiOperationLog 的方法，
     * 都会执行环绕中的代码
     */
    @Pointcut("@annotation(com.skywalker.framework.biz.operationlog.aspect.ApiOperationLog)")
    public void apiOperationLog(){}

    /**
     * 环绕
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        String jsonStringArgs = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(", "));

        String description = getApiOperationLogDescription(joinPoint);

        // 打印请求相关参数
        log.info("====== 请求开始: [{}], 入参: {}, 请求类: {}, 请求方法: {} =================================== ",
                description, jsonStringArgs, className, methodName);

        // 执行切点方法
        Object result = joinPoint.proceed();
        long executionTime = System.currentTimeMillis()-startTime;

        log.info("====== 请求结束: [{}], 耗时: {}ms, 返回值: {} =================================== ",
                description, executionTime, JsonUtils.toJsonString(result));

        return result;
    }

    /**
     * 转换为json字符串
     * @return
     */
    private Function<Object,String> toJsonStr() {
        return JsonUtils::toJsonString;
    }

    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //2. 使用 MethodSignature 获取当前被注解的 Method
        Method method = signature.getMethod();
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);
        return apiOperationLog.description();
    }
}
