package com.kon.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;

/**
 * JDBC监听
 *
 *      BeforeDeleteEvent   在删除聚合根之前。
 *      AfterDeleteEvent    删除聚合根后。
 *      BeforeConvertEvent  在保存聚合根之前（即插入或更新，但在决定是否更新或删除它之后）。
 *      BeforeSaveEvent     在保存聚合根之前（即插入或更新，但在决定是否更新或删除它之后）。
 *      AfterSaveEvent      在聚合根被保存（即，插入或更新）之后。
 *      AfterLoadEvent      在从数据库创建聚合根ResultSet并设置其所有属性之后。
 *
 * @author kon, created on 2021/12/2T17:52.
 * @version 1.0.0-SNAPSHOT
 */
@Slf4j
@Configuration
public class JdbcListener {

    @Bean
    public ApplicationListener<BeforeSaveEvent<Object>> loggingSaves() {
        return event -> {
            Object entity = event.getEntity();
            log.info("{} is getting saved.", entity);
        };
    }


}
