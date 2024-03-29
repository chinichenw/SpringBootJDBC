package com.spring.data.demo;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
//        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
//        System.out.println();
        System.out.println(connection.getClass());
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("最大的连接数为："+druidDataSource.getMaxActive());
        System.out.println(connection);
    }



}
