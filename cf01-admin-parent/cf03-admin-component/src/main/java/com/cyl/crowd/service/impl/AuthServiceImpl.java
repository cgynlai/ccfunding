package com.cyl.crowd.service.impl;

import java.util.List;
import java.util.Map;

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

	@Override
	public void saveRoleAuthRelationship(Map<String, List<Integer>> map) {
		
		// 1. get roleId value
		List<Integer> roleIdList = map.get("roleId");
		Integer roleId = roleIdList.get(0);
		
		// 2. delete old relationship
		authMapper.deleteOldRelationship(roleId);
		
		// 3. get authIdList
		List<Integer> authIdList = map.get("authIdArray");
		
		// 4. judge if authIdList from browser is valid
		if(authIdList !=null && authIdList.size()>0 ) {
			authMapper.insertNewRelationship(roleId, authIdList);
		}
		
	}
}
