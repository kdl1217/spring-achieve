package com.kon.repository;


import com.kon.entity.OperatorLog;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Repository
 *
 * @author kon, created on 2021/12/2T15:33.
 * @version 1.0.0-SNAPSHOT
 */
public interface OperatorLogRepository extends Repository<OperatorLog, String> {

    /**
     * 模糊查询
     *      参考： https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.mapping.meta-model
     *
     *      与JPA语法相似
     *
     * @param describe 描述
     * @return 数据信息集合
     */
    List<OperatorLog> findByDescribeLike(String describe);
}
