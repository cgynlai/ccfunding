package com.cyl.crowd.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.service.api.AdminService;

@Controller
public class TestHandler {

	@Autowired AdminService adminService;
	
	
	@RequestMapping("/test/ssm.html")
	public String testSSM() {
		List<Admin> adminList = adminService.getAll();
		return "target";
	}
}
