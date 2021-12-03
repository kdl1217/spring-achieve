package com.kon.controller;

import com.kon.entity.FaultData;
import com.kon.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Solr控制层
 *
 * @author kon, created on 2021/12/2T15:30.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class SolrController {

    @Autowired
    private FaultService faultService;

    @GetMapping("/solr/findByFaultDataVin/{vin}")
    public List<FaultData> findByFaultDataVin(@PathVariable String vin) {
        return faultService.findByFaultDataVin(vin);
    }

    @GetMapping("/solr/findPageable/{pageNum}/{pageSize}")
    public Page<FaultData> findPageable(@PathVariable int pageNum,
                                        @PathVariable int pageSize) {
        return faultService.findPageable(pageNum, pageSize);
    }

    @GetMapping("/solr/findPageList/{pageNum}/{pageSize}")
    public Page<FaultData> findPageList(@PathVariable int pageNum,
                                        @PathVariable int pageSize,
                                        @RequestParam(required = false) String vin) {
        return faultService.findPageList(pageNum, pageSize, vin);
    }
}
