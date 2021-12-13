package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author kon, created on 2021/12/9T13:47.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class Users implements Serializable {

    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
