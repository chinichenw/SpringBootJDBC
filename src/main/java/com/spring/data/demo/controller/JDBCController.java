package com.spring.data.demo.controller;

import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/getall")
    public List<Map<String,Object>> getALl() {
        String sql = "select * from user";
//        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);
        List<Map<String, Object>> res = jdbcTemplate.queryForList(sql);

        return res;
    }

    @GetMapping("/getbyId/{id}")
    public List<Map<String,Object>> getById(@PathVariable("id") Integer id){
        String sql = "select * from user where id=" + id;
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("exclusions","*.js,*.css,/druid/*,/jdbc/*");
        filterRegistrationBean.setInitParameters(initParameters);

        return  filterRegistrationBean;
    }
}
