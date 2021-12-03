package com.kon.controller;

import com.kon.entity.User;
import com.kon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * note
 *
 * @author kon, created on 2021/12/2T17:27.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/jdbc/find")
    public List<User> find(@RequestParam String name) {
        return userService.findByName(name);
    }

    @GetMapping("/jdbc/save")
    public User save() {
        return userService.save();
    }
}
