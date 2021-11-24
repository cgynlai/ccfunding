package com.cyl.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.entity.AdminExample;
import com.cyl.crowd.mapper.AdminMapper;
import com.cyl.crowd.service.api.AdminService;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

    
	@Override	
	public void saveAdmin (Admin admin) {
		
		adminMapper.insert(admin);
		
		
	}


	@Override
	public List<Admin> getAll() {
		
		return adminMapper.selectByExample(new AdminExample());
	}
}
