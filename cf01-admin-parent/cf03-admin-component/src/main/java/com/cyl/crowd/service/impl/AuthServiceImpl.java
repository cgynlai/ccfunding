package com.cyl.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.crowd.entity.Auth;
import com.cyl.crowd.entity.AuthExample;
import com.cyl.crowd.mapper.AuthMapper;
import com.cyl.crowd.service.api.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Override
	public List<Auth> getAll() {
		
		return authMapper.selectByExample(new AuthExample());
	}

	@Override
	public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
		
		return authMapper.selectAssignedAuthIdByRoleId(roleId);
	}
}
