package com.kon.controller;

import com.kon.auth.UserDetailsUtil;
import com.kon.common.ApiData;
import com.kon.entity.Users;
import com.kon.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制层
 *
 * @author kon, created on 2021/12/10T16:32.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/currentUserName")
    public ApiData<String> currentUserName() {
        return new ApiData<String>(HttpServletResponse.SC_OK)
                .setData(UserDetailsUtil.getUserName());
    }

    @Secured("menu:system")
//    @PreAuthorize("hasAnyAuthority('menu:system')")
    @GetMapping("/getUsers")
    public ApiData<Users> getUsers() {
        return new ApiData<Users>(HttpServletResponse.SC_OK)
                .setData(usersService.getUsers(UserDetailsUtil.getUserId()));
    }

    @Secured("menu:user")
//    @PreAuthorize("hasAnyAuthority('menu:user')")
    @GetMapping("/getPartPermission")
    public ApiData<Users> getPartPermission() {
        return new ApiData<Users>(HttpServletResponse.SC_OK)
                .setData(usersService.getUsers(UserDetailsUtil.getUserId()));
    }
}
