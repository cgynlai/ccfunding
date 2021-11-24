package com.cyl.crowd.service.api;

import java.util.List;

import com.cyl.crowd.entity.Admin;


public interface AdminService {

	 public void saveAdmin(Admin admin);

	 public List<Admin> getAll();
}
