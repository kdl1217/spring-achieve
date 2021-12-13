package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 角色
 *
 * @author kon, created on 2021/12/9T13:56.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class Role implements Serializable {

    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 名称
     */
    private String name;
}
