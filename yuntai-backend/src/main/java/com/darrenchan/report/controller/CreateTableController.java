package com.darrenchan.report.controller;

import com.darrenchan.report.bean.SqlQuery;
import com.darrenchan.report.service.CreateTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class CreateTableController {
    @PostMapping("createClickHouseTable")
    public String createClickHouseTable(@RequestBody SqlQuery sqlQuery) throws Exception {
        return CreateTableService.createClickHouseTable(sqlQuery.getSql());
    }

    @PostMapping("createMySQLTable")
    public String createMySQLTable(@RequestBody SqlQuery sqlQuery) throws Exception {
        return CreateTableService.createMySQLTable(sqlQuery.getSql());
    }
}
