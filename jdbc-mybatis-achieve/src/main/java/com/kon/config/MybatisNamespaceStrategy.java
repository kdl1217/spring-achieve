package com.kon.config;

import org.springframework.data.jdbc.mybatis.NamespaceStrategy;

/**
 * NamespaceStrategy
 *
 * @author kon, created on 2021/12/6T15:39.
 * @version 1.0.0-SNAPSHOT
 */
public class MybatisNamespaceStrategy implements NamespaceStrategy {

    @Override
    public String getNamespace(Class<?> domainType) {
        return "com.kon.dao.".concat(domainType.getSimpleName()).concat("Dao");
    }
}
