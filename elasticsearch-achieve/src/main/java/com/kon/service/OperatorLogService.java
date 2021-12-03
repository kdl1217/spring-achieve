package com.kon.service;

import com.kon.entity.OperatorLog;
import com.kon.repository.OperatorLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Service
 *
 * @author kon, created on 2021/12/2T15:39.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class OperatorLogService {

    @Autowired
    private OperatorLogRepository operatorLogRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public String save() {
        String id = UUID.randomUUID().toString();
        OperatorLog operatorLog = new OperatorLog();
        operatorLog.setId(id);
        operatorLog.setDescribe("这是一个描述" + ThreadLocalRandom.current().nextInt());
        operatorLog.setTime(Date.from(Instant.now()));
        this.elasticsearchRestTemplate.save(operatorLog);
        return id;
    }

    public OperatorLog get(String id) {
        return this.elasticsearchRestTemplate.get(id, OperatorLog.class);
    }

    public List<OperatorLog> search(String describe) {
        Criteria criteria = new Criteria("describe").is(describe);

        Query query = new CriteriaQuery(criteria);
        return this.elasticsearchRestTemplate.search(query, OperatorLog.class)
                .stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


    public List<OperatorLog> search2(String describe) {
        return this.operatorLogRepository.findByDescribeLike(describe);
    }
}
