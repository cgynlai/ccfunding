package com.cyl.crowd.service.api;

import java.util.List;

import com.cyl.crowd.entity.Auth;

public interface AuthService {

	List<Auth> getAll();

	List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

}
