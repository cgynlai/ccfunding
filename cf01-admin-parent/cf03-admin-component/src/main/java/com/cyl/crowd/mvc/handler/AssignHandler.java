package com.cyl.crowd.mvc.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyl.crowd.entity.Role;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.service.api.RoleService;

@Controller
public class AssignHandler {
   
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/assign/to/assign/role/page.html")
	public String toAssignRolePage(
			@RequestParam("adminId") Integer adminId,
			ModelMap modelMap
			) {
		//1. query assigned role
//		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		
		//2. query unassigned role
//		List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
		
		//3. store in model(equiv. request.setAttribute("attrName", attrValue)
//		modelMap.addAttribute("unAssignedRoleList", unAssignedRoleList);
//		modelMap.addAttribute("assignedRoleList", assignedRoleList);
		
		return "assign-role";
	}
	
}
