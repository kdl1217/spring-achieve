package com.kon.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 资源目录信息
 *
 * @author kon, created on 2021/12/9T13:49.
 * @version 1.0.0-SNAPSHOT
 */
@Data
@NoArgsConstructor
public class Menu implements Serializable {

    /**
     * ID
     */
    @Id
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * URL地址
     */
    private String url;
    /**
     * 父节点ID
     */
    private Long parentId;
    /**
     * 许可
     */
    private String permission;
}
