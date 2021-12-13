package com.kon.handler;

import com.kon.common.ApiData;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


/**
 * 异常控制处理器
 *
 * @author kon, created on 2021/12/13T14:01.
 * @version 1.0.0-SNAPSHOT
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthorizationServiceException.class)
    public ApiData<String> authorizationServiceException() {
        return ApiData.getSuccess(HttpServletResponse.SC_OK, "无权限访问");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AuthenticationException.class)
    public ApiData<String> authenticationException() {
        return ApiData.getSuccess(HttpServletResponse.SC_OK, "授权认证异常");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(AccessDeniedException.class)
    public ApiData<String> accessDeniedException() {
        return ApiData.getSuccess(HttpServletResponse.SC_OK, "拒绝访问异常");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public ApiData<String> exception() {
        return ApiData.getSuccess(HttpServletResponse.SC_OK, "系统异常");
    }
}
