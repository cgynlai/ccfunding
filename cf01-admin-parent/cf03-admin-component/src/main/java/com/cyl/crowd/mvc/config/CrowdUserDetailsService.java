package com.cyl.crowd.mvc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.entity.Role;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.service.api.AuthService;
import com.cyl.crowd.service.api.RoleService;

@Component
public class CrowdUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthService authService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       //1.query Admin by account name
		Admin admin = adminService.getAdminByLoginAcct(username);
		
	   //2. get admin id
		Integer adminId = admin.getId();
		
		//3. query role by adminId
		List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
		
		//4. query auth by adminId
		List<String> authNameList = authService.getAssignedAuthNameByAdminId(adminId);
		
		//5. create list to store GrantedAuthority
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//6. iterate assignedRoleList and store role in authorities
		for(Role role : assignedRoleList) {
			String roleName = "ROLE_" + role.getName();
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
			
			authorities.add(simpleGrantedAuthority);
		}
		
		//7. iterate authNameList to store in authorities
		for (String authName  : authNameList) {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authName);
			authorities.add(simpleGrantedAuthority);
		}
		
		//8.encapsulate SecurityAdmin instance
		SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
		
		return securityAdmin;
	}

}
