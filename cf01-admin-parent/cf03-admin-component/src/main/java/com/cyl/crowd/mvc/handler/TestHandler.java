package com.cyl.crowd.mvc.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.util.ResultEntity;
import com.cyl.crowd.util.CrowdUtil;
@Controller
public class TestHandler {

	@Autowired 
	AdminService adminService;
	
	Logger logger = LoggerFactory.getLogger(TestHandler.class);
	
	@RequestMapping("/test/ssm.html")
	public String testSSM(ModelMap modelMap, HttpServletRequest request) {
		
		boolean judgeResult = CrowdUtil.judgeRequestType(request);
		logger.info("judge result : " + judgeResult);
		
		List<Admin> adminList = adminService.getAll();
		modelMap.addAttribute("adminList", adminList);
		
	//	System.out.println(10/0);
		
		return "target";
	}
	
	@ResponseBody
	@RequestMapping("/send/array.html")
	public String testReceiveArray(@RequestParam("array[]") List<Integer> array) {
		
		
		
		for(Integer element : array) {
			logger.info("number : "  + element );
		}
		
		return "target";
	}
	
	
	  
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/send/array2.json") public String
	 * testReceiveArray2(@RequestBody List<Integer> array) { Logger logger =
	 * LoggerFactory.getLogger(TestHandler.class);
	 * 
	 * for(Integer element : array) { logger.info("number : " + element ); }
	 * 
	 * return "target"; }
	 */
	 
	
	  @ResponseBody	  
	  @RequestMapping("/send/array2.json") 
	  public ResultEntity<List> testReceiveArray2(@RequestBody List<Integer> array, HttpServletRequest request ) {
		//  Logger logger = LoggerFactory.getLogger(TestHandler.class);
		  boolean judgeResult = CrowdUtil.judgeRequestType(request);
			logger.info("judge result : " + judgeResult);
	 
	  
	  return ResultEntity.successWithData(array); 
	  
	  }
	
	
	/*
	 * @RequestMapping("/send/array2.json") public String
	 * testReceiveArray2(@RequestBody List<Integer> array) {
	 * 
	 * return "target"; }
	 */
	
	
	}
	
	
	
	
	

