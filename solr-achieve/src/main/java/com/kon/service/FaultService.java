package com.kon.service;

import com.kon.entity.FaultData;
import com.kon.repository.FaultRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service
 *
 * @author kon, created on 2021/12/2T15:29.
 * @version 1.0.0-SNAPSHOT
 */
@Service
public class FaultService {

    @Autowired
    private FaultRepository faultRepository;

    @Autowired
    private SolrTemplate solrTemplate;

    public List<FaultData> findByFaultDataVin(String vin) {
        return faultRepository.findByFaultDataVin(vin);
    }

    public Page<FaultData> findPageable(int pageNum, int pageSize) {
        return faultRepository.findAllWithPageable(PageRequest.of(pageNum - 1, pageSize));
    }

    public Page<FaultData> findPageList(int pageNum, int pageSize, String vin) {
        // 利用SolrTemplate进行搜索。
        Query query = new SimpleQuery("*:*");
        if (StringUtils.isNotBlank(vin)) {
            query.addCriteria(Criteria.where("fault_data_vin").is(vin));
        }
        query.addSort(Sort.by("fault_data_receiveTime").ascending());

        query.setPageRequest(PageRequest.of(pageNum - 1, pageSize));
        return solrTemplate.queryForPage("faultData", query, FaultData.class);
    }
}
