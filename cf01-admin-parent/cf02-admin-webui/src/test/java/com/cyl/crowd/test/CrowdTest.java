package com.cyl.crowd.test;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.entity.Role;
import com.cyl.crowd.mapper.AdminMapper;
import com.cyl.crowd.mapper.RoleMapper;
import com.cyl.crowd.service.api.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {
	
	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Test
	public void testAddrole() {
		for(int i=0; i<235; i++) {
			roleMapper.insert(new Role(null, "role"+i));
		}
	}
	
	@Test
	public void test() {
		for(int i=0; i<238; i++) {
			adminMapper.insert(new Admin(null,"loginAcct"+i, "userPswd"+i, "userName"+i, "email"+i, null));
		}
	}
	
	@Test
	public void testTx() {
		Admin admin = new Admin(null, "Jery", "544353", "Jery", "Jer@qq.com",null);
		adminService.saveAdmin(admin);
	}
	
	@Test
	public void testLog() {
		Logger logger = LoggerFactory.getLogger(CrowdTest.class);
		logger.debug("Hello from debug level !!");
		logger.info("hello fr info level !!");
		logger.error("helloo fr error level");
		logger.debug("Hello from debug level !!");
		logger.info("hello fr info level !!");
		logger.error("helloo fr error level");
	}
	
	
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
