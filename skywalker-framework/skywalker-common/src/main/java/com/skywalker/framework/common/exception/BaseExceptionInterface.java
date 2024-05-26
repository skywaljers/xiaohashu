package com.skywalker.framework.common.exception;

/**
 * @author baijj
 * @date 2024-05-27
 */
public interface BaseExceptionInterface {
    // 获取异常码
    String getErrorCode();

    // 获取异常信息
    String getErrorMessage();
}
