package com.myself.com.myself.boot.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@Qualifier("testService")
public class TestService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getTestString(){
        return "testString";
    }

    public Integer getTabelSize() {
        return jdbcTemplate.queryForObject("select count(1) from test.T_TABLE_TEST",Integer.class);
    }
}
