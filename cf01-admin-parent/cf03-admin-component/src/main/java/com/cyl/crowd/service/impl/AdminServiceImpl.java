package com.cyl.crowd.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.crowd.constant.CrowdConstant;
import com.cyl.crowd.entity.Admin;
import com.cyl.crowd.entity.AdminExample;
import com.cyl.crowd.entity.AdminExample.Criteria;
import com.cyl.crowd.exception.LoginFailedException;
import com.cyl.crowd.mapper.AdminMapper;
import com.cyl.crowd.service.api.AdminService;
import com.cyl.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public void saveAdmin(Admin admin) {

		adminMapper.insert(admin);

	}

	@Override
	public List<Admin> getAll() {

		return adminMapper.selectByExample(new AdminExample());
	}

	@Override
	public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
		// 1. 根据登录账号查询admin对象
		  // a. create AdminExample instance
		  AdminExample adminExample = new AdminExample(); 
		  // b. create Criteria instance
		  Criteria criteria = adminExample.createCriteria();
		  
		  // c. encapsulate check criteria in Criteria insance
		  criteria.andLoginAcctEqualTo(loginAcct);
		  
		  // d. call mapper to inquire
		  List<Admin> list = adminMapper.selectByExample(adminExample);
		  
		
		// 2. 判断admin对象是否为null
		  if(list == null || list.size() == 0) {
			  throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
		  }
		  
		  if(list.size() > 1) {
			  throw new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
		  }
		  
		  Admin admin = list.get(0);
		  
		  // 3. 如果Admin对象为null则抛出异常
		  if(admin == null) {
			  throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
			  
		  }
		    
		   // 4. 如果Admin不为null则将数据库密码从Admin对象中取出
			String userPswdDB = admin.getUserPswd();
			
			// 5. 将表单提交的明文密码进行加密
			String userPswdForm = CrowdUtil.md5(userPswd);
		
			// 6. 对密码进行比较
			if(!Objects.equals(userPswdForm, userPswdDB)) {
			// 7. 如果比较结果是不一致则抛出异常
				throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
			}
		
			// 8. 如果一直则返回Admin对象
			return admin;
		
	}

	@Override
	public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
		
		// 1. 调用pageHelper的静态方法开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		
		// 2. execute inquiry
		List<Admin> list = adminMapper.selectAdminByKeyword(keyword);
		
		// 3. encap result into PageInfo object 
		return new PageInfo<>(list) ;
	}
	
	

	
}
