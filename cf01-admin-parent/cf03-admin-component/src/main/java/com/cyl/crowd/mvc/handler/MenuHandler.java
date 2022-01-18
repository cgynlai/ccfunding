package com.cyl.crowd.mvc.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.crowd.entity.Menu;
import com.cyl.crowd.service.api.MenuService;
import com.cyl.crowd.util.ResultEntity;

@Controller
public class MenuHandler {
	
  @Autowired	
  private MenuService menuService;
  
  @ResponseBody
  @RequestMapping("/menu/remove.json")
  public ResultEntity<String> removeMenu(@RequestParam("id") Integer id) {
	  
	 menuService.removeMenu(id);
	  
	  return ResultEntity.successWithoutData();
  } 
  
  
  @ResponseBody
  @RequestMapping("/menu/update.json")
  public ResultEntity<String> updateMenu(Menu menu) {
	  
	 menuService.updateMenu(menu);
	  
	  return ResultEntity.successWithoutData();
  }
  
  @ResponseBody
  @RequestMapping("/menu/save.json")
  public ResultEntity<String> saveMenu(Menu menu) throws InterruptedException{
	  
	  Thread.sleep(2000);
	  menuService.saveMenu(menu);
	  return ResultEntity.successWithoutData();
  }
  
  
  
  @ResponseBody
  @RequestMapping("/menu/get/whole/tree.json")
  public ResultEntity<Menu> getWholeTree() {
	  
	  List<Menu> menuList = menuService.getAll();
	  
	  Menu root = null;
	  
	  //create map to store id and menu element to become a single variable
	  //(<id, menu> , pid) instead of ( id, menu, pid)
	  //simplify searching process and no need to use nested for-loop wasting resource
	  Map<Integer, Menu> menuMap = new HashMap<>();
	  
	  //iterate menulist and fill in hashmap
      for(Menu menu : menuList) {
		  
		  Integer id = menu.getId();
		  menuMap.put(id, menu);
	  }
      
      //iterate menulist to search for root menu and pair parent-child r'ship
      for(Menu menu : menuList) {
    	  
    	  Integer pid = menu.getPid();
    	  
    	  if(pid == null) {
    		  root = menu;
    		//7. no need to check parent id for root menu,exit this loop
    		  continue;
    	  }
    	  
    	  Menu father = menuMap.get(pid);
    	  father.getChildren().add(menu);
      }
	  
	  return ResultEntity.successWithData(root);
	  
  }
  
  @ResponseBody
  @RequestMapping("/menu/get/whole/treeOld")
  public ResultEntity<Menu> getWholeTreeOld() {
	  
	  List<Menu> menuList = menuService.getAll();
	  
	  Menu root = null;
	  
	  for(Menu menu : menuList) {
		  
		  Integer pid = menu.getPid();
		  
		  //6.check if pid=null, then menu is root menu
		  if(pid == null) {
			  root = menu;
		  //7. no need to check parent id for root menu,exit this loop
			  continue;
		  }
		  
		  //8. use current pid to match iterated menu id to establish parent-child r'ship.
		  for(Menu maybeFather : menuList) {
			  
			  if(Objects.equals(maybeFather.getId(), pid)) {
				  maybeFather.getChildren().add(menu);
				  
				  // stop here after found the r'ship
				  break;
			  }
		  }
		  
	  }
	  
	  return ResultEntity.successWithData(root);
  }
  
}