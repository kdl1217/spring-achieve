package com.kon.repository;

import com.kon.entity.FaultData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository
 *
 * @author kon, created on 2021/12/2T15:27.
 * @version 1.0.0-SNAPSHOT
 */
@Repository
public interface FaultRepository extends SolrCrudRepository<FaultData, String> {

    /**
     * 查询所以匹配VIN
     * @param vin   车架号
     * @return 数据信息
     */
    List<FaultData> findByFaultDataVin(String vin);

    /**
     * 分页查询
     *
     * @param pageable  分页对象
     * @return 分页数据信息
     */
    @Query("*:*")
    Page<FaultData> findAllWithPageable(Pageable pageable);
}
