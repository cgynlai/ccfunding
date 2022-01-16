package com.cyl.crowd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.crowd.entity.Menu;
import com.cyl.crowd.entity.MenuExample;
import com.cyl.crowd.mapper.MenuMapper;
import com.cyl.crowd.service.api.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
@Autowired
private MenuMapper menuMapper;

@Override
public List<Menu> getAll() {

   return menuMapper.selectByExample(new MenuExample());
	
}

@Override
public void saveMenu(Menu menu) {
	
	menuMapper.insert(menu);
}

}
