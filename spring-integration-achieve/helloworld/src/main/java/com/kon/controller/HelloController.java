package com.kon.controller;

import com.kon.handler.InputChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * note
 *
 * @author com.kon, created on 2021/11/30T14:45.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class HelloController {

    @Autowired
    private InputChannel inputChannel;

    @GetMapping("/sendMessage/{message}")
    public String sendMessage(@PathVariable String message) {
        inputChannel.send(message);
        return "success :" + message;
    }
}
