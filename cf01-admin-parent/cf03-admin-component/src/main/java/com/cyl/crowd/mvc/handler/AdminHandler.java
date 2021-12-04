package com.cyl.crowd.mvc.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cyl.crowd.constant.CrowdConstant;
import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {
	
	@Autowired
	private AdminService adminService;
	
	
	@RequestMapping("/admin/do/login.html")
	public String doLogin(
			@RequestParam("loginAcct") String loginAcct,
			@RequestParam("userPswd") String userPswd,
			HttpSession session
			) {
		
		// 调用service方法执行登录检查，如果能够返回admin说明登录成功，否则抛出异常
		Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
		
		// 保存用户对象到session中
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		
		return "admin-page";
	}
	
	@RequestMapping("/admin/get/page.html")
	public String getPageInfo(
			@RequestParam(value="keyword", defaultValue="") String keyword,
			
			@RequestParam(value="pageNum", defaultValue="1") Integer pageNum,
			
			@RequestParam(value="pageSize", defaultValue="5") Integer pageSize,
			ModelMap modelMap
			) {
		       PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
		       //put pageInfo into Model
		       modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		    return "admin-page";
	}
	

	

}
