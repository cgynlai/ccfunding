package com.cyl.crowd.mvc.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.cyl.crowd.entity.Admin;

/**
 * User 对像仅包含帐号和密码，为了能方便地获取到原始地Admin对象，因此创建一个SecurityAdmin类，继承User。
 * @author user
 *
 */

public class SecurityAdmin extends User{
	private static final long serialVersionUID = 1L;
	
	//原始的 Admin对像，包含 Admin对像的全部属性
	private Admin originalAdmin;

	public SecurityAdmin(Admin originalAdmin, List<GrantedAuthority> authorities) {
		super(originalAdmin.getLoginAcct(), originalAdmin.getUserPswd() , authorities);
		this.originalAdmin = originalAdmin;
	}
    //对外提供获取Admin对像的get方法。
	public Admin getOriginalAdmin() {
		return originalAdmin;
	}

	public void setOriginalAdmin(Admin originalAdmin) {
		this.originalAdmin = originalAdmin;
	}
    
	
	
}
