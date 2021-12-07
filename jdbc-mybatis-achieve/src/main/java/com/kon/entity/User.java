package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * User Entity
 *
 * @author kon, created on 2021/12/2T16:40.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    private Integer age;

    private String name;

    private String password;

    private String text;
}

