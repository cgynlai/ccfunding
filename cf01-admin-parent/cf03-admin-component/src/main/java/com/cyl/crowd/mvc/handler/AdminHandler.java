package com.cyl.crowd.mvc.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping("/admin/to/edit/page.html")
	public String toEditPage(
			@RequestParam("admindId") Integer adminId,
			ModelMap modelMap
			) {
		
		
		return "admin-edit";
	}
	
	@RequestMapping("/admin/save.html")
	public String save(Admin admin) {
		adminService.saveAdmin(admin);
		return "redirect:/admin/get/page.html?pageNum=" + Integer.MAX_VALUE;
	}
	
	@RequestMapping("/admin/remove/{adminId}/{pageNum}/{keyword}.html")
	public String remove(@PathVariable("adminId") Integer adminId,
		                 @PathVariable("pageNum") Integer pageNum,
		                 @PathVariable("keyword") String keyword
		                 ) {
		adminService.remove(adminId);
		
		//go back to page and keep original search result page
		return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
	}
	
	@RequestMapping("/admin/do/logout.html")
	public String doLogout(HttpSession session) {
		
		//
		session.invalidate();
		
		return "redirect:/admin/to/login/page.html";
		
	}
	
	
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
		
	// return "admin-main";
		return "redirect:/admin/to/main/page.html";
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
