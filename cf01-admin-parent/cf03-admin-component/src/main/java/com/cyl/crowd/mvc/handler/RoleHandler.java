package com.cyl.crowd.mvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.crowd.entity.Role;
import com.cyl.crowd.service.api.RoleService;
import com.cyl.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;

@Controller
public class RoleHandler {
	
	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping("/role/remove/by/role/id/array.json")
	public ResultEntity<String> removeByRoleIdArray(@RequestBody List<Integer> roleIdList) {
		
		roleService.removeRole(roleIdList);
		
		return ResultEntity.successWithoutData();
	}
	
	@ResponseBody
	@RequestMapping("/role/update.json")
	public ResultEntity<String> updateRole(Role role) {
		roleService.updateRole(role);
		System.out.println("role name is " + role.getName() + "id is " + role.getId());
		return ResultEntity.successWithoutData();
	}
	
	@ResponseBody
	@RequestMapping("/role/save.json")
	public ResultEntity<String> saveRole(Role role) {
		
		roleService.saveRole(role);
		
		
		return ResultEntity.successWithoutData();
	}
	
	@PreAuthorize("hasRole('supervisor')")
    @ResponseBody
	@RequestMapping("role/get/page/info.json")
	public ResultEntity<PageInfo<Role>> getPageInfo(
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
			@RequestParam(value="keyword", defaultValue="") String keyword) {
		
		PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
		
		//package result into resultentity. (if any exception occurs, 
		// (e.g. db connection issue) exception handler will take care)
		return ResultEntity.successWithData(pageInfo);
		
	}
}
