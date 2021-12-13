package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户权限关系
 *
 * @author kon, created on 2021/12/9T14:00.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class RoleUser implements Serializable {

    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 权限ID
     */
    private Long rid;
}
