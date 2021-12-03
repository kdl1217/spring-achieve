package com.kon.controller;

import com.kon.entity.OperatorLog;
import com.kon.service.OperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ES 控制层
 *
 * @author com.kon, created on 2021/12/2T14:32.
 * @version 1.0.0-SNAPSHOT
 */
@RestController
public class ElasticController {

    @Autowired
    private OperatorLogService operatorLogService;

    @GetMapping("/es/save")
    public String save() {
        return operatorLogService.save();
    }

    @GetMapping("/es/get/{id}")
    public OperatorLog get(@PathVariable String id) {
        return this.operatorLogService.get(id);
    }

    @GetMapping("/es/search/{describe}")
    public List<OperatorLog> search(@PathVariable String describe) {
        return this.operatorLogService.search(describe);
    }

    @GetMapping("/es/search2/{describe}")
    public List<OperatorLog> search2(@PathVariable String describe) {
        return this.operatorLogService.search2(describe);
    }
}
