package com.cyl.crowd.service.api;

import com.cyl.crowd.entity.Role;
import com.github.pagehelper.PageInfo;

public interface RoleService {

	  PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);
	  
}
