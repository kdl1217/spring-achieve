package com.kon.controller;

import com.kon.entity.User;
import com.kon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * note
 *
 * @author kon, created on 2021/12/6T10:46.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/jdbc/mybatis/findById/{id}")
    public User findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/jdbc/findById/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
}
