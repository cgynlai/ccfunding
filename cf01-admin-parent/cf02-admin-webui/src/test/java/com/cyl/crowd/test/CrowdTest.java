package com.cyl.crowd.test;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.mapper.AdminMapper;

import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Test
	public void testInserAdmin() {
		Admin admin = new Admin(null, "tom", "121121", "TOM", "tom@qq.com",null);
		int count = adminMapper.insert(admin);
	    System.out.println("affected row : " + count);
	}
	
	@Test
	public void testConnection() throws SQLException {
		Connection connection = dataSource.getConnection();
		System.out.println("db ==== " + connection);
	}

}
