package com.cyl.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.crowd.entity.Role;
import com.cyl.crowd.mapper.RoleMapper;
import com.cyl.crowd.service.api.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired
  private RoleMapper roleMapper;

@Override
public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
	
	//1. activate pagination function
	PageHelper.startPage(pageNum, pageSize);
	
	//2. execute query
	List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
	
	//3. package as PageInfo object
	return new PageInfo<>(roleList);
	
	
}
}
