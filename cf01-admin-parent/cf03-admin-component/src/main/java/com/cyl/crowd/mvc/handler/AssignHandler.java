package com.cyl.crowd.mvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyl.crowd.entity.Role;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.service.api.AuthService;
import com.cyl.crowd.service.api.RoleService;

@Controller
public class AssignHandler {
   
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthService authService;
	
	
	@RequestMapping("/assign/do/role/assign.html")
	public String saveAdminRoleRelationship(
			@RequestParam("adminId") Integer adminId,
			@RequestParam("pageNum") Integer pageNum,
			@RequestParam("keyword") String keyword,
			//allow not to provide role list from browser
			@RequestParam(value="roleIdList", required=false) List<Integer> roleIdList
			) {
		adminService.saveAdminRoleRelationship(adminId, roleIdList);
		return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
	}
	
	@RequestMapping("/assign/to/assign/role/page.html")
	public String toAssignRolePage(
			@RequestParam("adminId") Integer adminId,
			ModelMap modelMap
			) {
		//1. query assigned role
		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		
		//2. query unassigned role
		List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
		
		//3. store in model(equiv. request.setAttribute("attrName", attrValue)
		modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);
		modelMap.addAttribute("assignedRoleList", assignedRoleList);
		
		return "assign-role";
		//return "target";
	}
	
}
