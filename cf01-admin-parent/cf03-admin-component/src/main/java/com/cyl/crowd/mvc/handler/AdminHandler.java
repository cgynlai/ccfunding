package com.cyl.crowd.mvc.handler;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.crowd.constant.CrowdConstant;
import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.util.ResultEntity;
import com.github.pagehelper.PageInfo;

@Controller
public class AdminHandler {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/admin/update.html")
	public String update(Admin admin, @RequestParam("pageNum") Integer pageNum, @RequestParam("keyword") String keyword) {
		adminService.update(admin);
		return "redirect:/admin/get/page.html?pageNum=" + pageNum + "&keyword=" + keyword;
	}
	
	@RequestMapping("/admin/to/edit/page.html")
	public String toEditPage(
			@RequestParam("adminId") Integer adminId,
			ModelMap modelMap
			) {
		
		Admin admin = adminService.getAdminById(adminId);
		modelMap.addAttribute("admin", admin);
		
		
		return "admin-edit";
	}
	
	@PreAuthorize("hasAuthority('user:save')")
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
		
		// ??????service?????????????????????????????????????????????admin???????????????????????????????????????
		Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);
		
		// ?????????????????????session???
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
	
	// testing @PostAuthorize, not real implementation for project
	@ResponseBody
	@PostAuthorize("returnObject.data.loginAcct == principal.username")
	@RequestMapping("/admin/test/post/filter.json")
	public ResultEntity<Admin> getAdminById() {
		
		Admin admin = new Admin();
		admin.setLoginAcct("adminOperator");
		return ResultEntity.successWithData(admin);
	}
	
	@PreFilter(value = "filterObject%2 == 0")
	@ResponseBody
	@RequestMapping("/admin/test/pre/filter")
	public ResultEntity<List<Integer>> saveList(@RequestBody List<Integer> valueList) {
		
		return ResultEntity.successWithData(valueList);
	}
	
	

	

}
