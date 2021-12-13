package com.kon.auth;

import com.kon.security.UserExtend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Spring Security UserDetails工具类
 *
 * @author kon, created on 2021/12/10T16:45.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
public final class UserDetailsUtil {

    /**
     * 获取用户扩展信息
     * @return  扩展用户信息
     */
    private static UserExtend getUserExtend() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (null != principal) {
            return (UserExtend) principal;
        }
        return null;
    }

    /**
     * 获取当前用户ID
     * @return 当前用户ID
     */
    public static Long getUserId() {
        UserExtend userExtend = getUserExtend();
        if (null != userExtend) {
            return userExtend.getId();
        }
        return null;
    }

    /**
     * 获取当前用户名
     * @return 当前用户名
     */
    public static String getUserName() {
        UserExtend userExtend = getUserExtend();
        if (null != userExtend) {
            return userExtend.getUsername();
        }
        return null;
    }
}
