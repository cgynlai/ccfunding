// 执行分页，生成分页效果
function generatePage() {
	
	
	var pageInfo = getPageInfoRemote();
	
	
	fillTableBody(pageInfo);
	
	
}

// 从远程服务器端获取PageInfo数据
function getPageInfoRemote() {
	 // 调用$.ajax()函数发送请求，并用ajaxResult接收函数返回值
	 
	 
	var ajaxResult = $.ajax({
		"url": "role/get/page/info.json",
		"type": "post",
		"data": {
			"pageNum": window.pageNum,
			"pageSize": window.pageSize,
			"keyword": window.keyword
		},
		"dataType": "json",
		"async": false,
		/*"success": function(response){
			
		},
		"error": function(response){
			
		}*/
		
		
	});
	
	console.log(ajaxResult);
	
	var statusCode = ajaxResult.status;
	
	/*if(statusCode !=200) {
		layer.msg("failed to load, status = " + statusCode + "detail : " + ajaxResult.statusText);
	}*/
	 
	 //关闭异步模式，使用同步
	 
	  // 取得当前的响应状态码
	  
	   // 判断当前状态码是不是200，不是200表示发生错误，通过layer提示错误消息
	   
	   // 响应状态码为200，进入下面的代码
    // 通过responseJSON取得handler中的返回值
    var resultEntity = ajaxResult.responseJSON;
    console.log("resultEntity is +++ " + resultEntity);
    
    // 从resultEntity取得result属性
    var result = resultEntity.result;
    console.log("result is +++ " + result);
    
     // 判断result是否是FAILED
     if(result == "FAILED") {
	layer.msg(resultEnity.message);
	return null;
}
     
     // result不是失败时，获取pageInfo
     var pageInfo = resultEntity.data;
     
      // 返回pageInfo
     return pageInfo; 
      // 根据PageInfo填充表格
      
      // 清除tbody中的旧内容
      
      // 使无查询结果时，不显示导航条
      
      // 判断pageInfo对象是否有效
      
      // pageInfo有效，使用pageInfo的list填充tbody
      
       // 铅笔按钮用于修改role信息。用id属性（也可以是其他属性）携带当前的角色的id，class添加一个pencilBtn，方便添加响应函数
	 
	 // 进行生成分页页码导航条
}


function fillTableBody(pageInfo) {
	
}


function generateNavigator(pageInfo) {
	
}