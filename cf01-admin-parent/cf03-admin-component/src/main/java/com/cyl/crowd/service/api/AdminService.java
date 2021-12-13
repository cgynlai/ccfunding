package com.cyl.crowd.service.api;

import java.util.List;

import com.cyl.crowd.entity.Admin;
import com.github.pagehelper.PageInfo;


public interface AdminService {

	public void saveAdmin(Admin admin);

	public List<Admin> getAll();

	public Admin getAdminByLoginAcct(String loginAcct, String userPswd);
	
	PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

	public void remove(Integer adminId);

	public Admin getAdminById(Integer adminId);

	public void update(Admin admin);

	
}
