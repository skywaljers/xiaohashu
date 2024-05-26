package com.skywalker.framework.common.exception;

import lombok.Data;

/**
 * @author baijj
 * @date 2024-05-27
 */
@Data
public class BizException extends RuntimeException{
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
