package com.cyl.crowd.mvc.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.service.api.AdminService;

@Controller
public class TestHandler {

	@Autowired AdminService adminService;
	
	
	@RequestMapping("/test/ssm.html")
	public String testSSM(ModelMap modelMap) {
		List<Admin> adminList = adminService.getAll();
		modelMap.addAttribute("adminList", adminList);
		return "target";
	}
	
	@ResponseBody
	@RequestMapping("/send/array.html")
	public String testReceiveArray() {
		
		Logger logger = LoggerFactory.getLogger(TestHandler.class);
		
		
		return "target";
	}
	
	
	
}
