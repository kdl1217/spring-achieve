package com.kon.controller;

import com.kon.common.ApiData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Login 控制层
 *
 * @author kon, created on 2021/12/9T17:03.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/failure")
    public ApiData<String> failure() {
        return ApiData.getSuccess(HttpServletResponse.SC_UNAUTHORIZED, "登陆失败");
    }

    @GetMapping("/success")
    public ApiData<String> success() {
        return ApiData.getSuccess(HttpServletResponse.SC_OK, "登陆成功");
    }
}
