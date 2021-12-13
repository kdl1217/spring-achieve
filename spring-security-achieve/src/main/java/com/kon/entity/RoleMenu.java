package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 权限目录关系
 *
 * @author kon, created on 2021/12/9T13:58.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class RoleMenu implements Serializable {

    /**
     * 资源ID
     */
    private Long mid;
    /**
     * 权限ID
     */
    private Long rid;
}
