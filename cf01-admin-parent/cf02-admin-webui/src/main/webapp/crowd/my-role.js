//to show Auth tree directory in modal 
function fillAuthTree() {
	// send Ajax request
	var ajaxReturn = $.ajax({
		"url": "assign/get/all/auth.json",
		"type": "post",
		"dataType": "json",
        "async": false
	});
	
	if(ajaxReturn.status != 200){
		layer.msg("ajax request failed! response status: " + ajaxReturn.status + "error text: " +
		ajaxReturn.statusText);
		return;
	}
	//2. list gotten from server response in Json format and will not construct in tree structure.
	// let zTree to do it
				var authList = ajaxReturn.responseJSON.data;
				
				//3.prepare zTree setting for Json obj
				var setting = {
					data: {
						simpleData: {
							//enable simple json function
							enable: true,
							//use "categoryId" to relate to parent node instead of pId
							pIdKey: "categoryId"
						},
						key: {
							//use 'title' property to display node name instead of 'name' 
							name: "title"
						}
					},
					//display checkbox
					check: {
						enable: true
					}
				}
				
				//4.construct tree directory
				//<ul id="authTreeDemo" class="ztree">
				$.fn.zTree.init($("#authTreeDemo"), setting, authList)
				
				//obtain zTreeObj object
				var zTreeObj = $.fn.zTree.getZTreeObj("authTreeDemo");
				//call function to expand node
				zTreeObj.expandAll(true);
				
				alert("window.roleId b4 ajax " + window.roleId);
	           //5.query assigned Auth which will be in id list
	           ajaxReturn = $.ajax({
		         "url": "assign/get/assigned/auth/id/by/role/id.json",
		         "type": "post",
		         "data": {
			         "roleId": window.roleId
		          },
		          "dataType": "json",
		          "async": false
	            });
	            
	            if(ajaxReturn.status != 200){
		            layer.msg("ajax request failed! response status 123: " + ajaxReturn.status + "error text: " +
		            ajaxReturn.statusText);
		            return;
	                }
	                
	         var authIdArray = ajaxReturn.responseJSON.data;
	         alert(authIdArray);
	           //6.'ticked' checkbox according to authIdArray list
}

//
function showConfirmModal(roleArray) {
	
	// show modal
	$("#confirmModal").modal("show");
	
	//clear old modal content
	$("#roleNameDiv").empty();
	
	// 1a. create global array to store role id from for-loop
	window.roleIdArray = [];
	
	//iterate roleArray
	for(var i=0; i<roleArray.length; i++) {
		var role = roleArray[i];
		var roleName = role.roleName;
		$("#roleNameDiv").append(roleName + "<br />");
		
		//store id to global array
		var roleId = role.roleId;
		window.roleIdArray.push(roleId);
	}
	
}


// 执行分页，生成分页效果
function generatePage() {
	
	
	var pageInfo = getPageInfoRemote();
	
	// pageInfo有效，使用pageInfo的list填充tbody
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
		
		
		//关闭异步模式，使用同步
		"async": false,
		/*"success": function(response){
			
		},
		"error": function(response){
			
		}*/
		
		
	});
	
	console.log(ajaxResult);
	
	// 取得当前的响应状态码
	var statusCode = ajaxResult.status;
	
	// 判断当前状态码是不是200，不是200表示发生错误，通过layer提示错误消息
	if(statusCode !=200) {
		layer.msg("failed to load, status = " + statusCode + "detail : " + ajaxResult.statusText);
		return null;
	}
	 
	
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
     
      
      
      
     
      
     
      
      
      
       
	 
	 
}


function fillTableBody(pageInfo) {
	
	// 清除tbody中的旧内容
	    $("#rolePageBody").empty();
	    //$("#Pagination").empty();
	     // 使无查询结果时，不显示导航条
	    $("#Pagination").empty();
	
	 // 判断pageInfo对象是否有效
	  if(pageInfo == null || pageInfo == undefined || pageInfo.list == null || pageInfo.list.length == 0) {
		$("#rolePageBody").append("<tr><td colspan='4'> sorry, no data found!</td></tr>")
		return;
	   }
	   
	    // 根据PageInfo填充表格tbody
 for(var i=0; i<pageInfo.list.length; i++) {
		    var role = pageInfo.list[i];
		    var roleId = role.id;
		    var roleName = role.name;
		    
		    var numberTd = "<td>" + (i+1) + "</td>";
		    var checkboxTd = "<td><input id='" + roleId + "' class='itemBox' type='checkbox'></td>";
		   // var checkboxTd = "<td><input type='checkbox' id='"+roleId+"' class='itemBox'/></td>";
		    var roleNameTd = "<td>" + roleName + "</td>"; 
		    
		    var checkBtn = "<button type='button' id='" + roleId + "' class='btn btn-success btn-xs checkBtn'><i class='glyphicon glyphicon-check'></i></button>";
		    // 铅笔按钮用于修改role信息。用id属性（也可以是其他属性）携带当前的角色的id，class添加一个pencilBtn，方便添加响应函数
		    /*var pencilBtn = "<button type='button' roleId='"
				+ roleId
				+ "' class='btn btn-primary btn-xs pencilBtn'><i class='glyphicon glyphicon-pencil'></i></button>";*/
		   var pencilBtn = "<button type='button' id='" + roleId + "' class='btn btn-primary btn-xs pencilBtn'><i class='glyphicon glyphicon-pencil'></i></button>";
		    var removeBtn = "<button id='" + roleId + "' type='button' class='btn btn-danger btn-xs removeBtn'><i class='glyphicon glyphicon-remove'></i></button>";
		    
		    var buttonTd = "<td>" + checkBtn + " " + pencilBtn + " " + removeBtn
				+ "</td>";
				
		    var tr = "<tr>" + numberTd + checkboxTd + roleNameTd + buttonTd
				+ "</tr>";
		     $("#rolePageBody").append(tr);
		
	     }
	     
	     // 进行生成分页页码导航条
	     generateNavigator(pageInfo);  
	     
	     //get number of checked itemBox
			var checkedBoxCount = $(".itemBox:checked").length;
			
			//get total number of itemBox
			var totalBoxCount = $(".itemBox").length;
	     
	   //get boolean result by comparing above two checkbox number
			$("#summaryBox").prop("checked", checkedBoxCount == totalBoxCount);
}


function generateNavigator(pageInfo) {
	
	//获取分页数据中的总记录数
    var totalRecord = pageInfo.total;

    //声明Pagination设置属性的JSON对象
    var properties = {
        "num_edge_entries": 3,                                //边缘页数
        "num_display_entries": 5,                             //主体页数
        "callback": paginationCallback,                       //点击各种翻页反扭时触发的回调函数（执行翻页操作）
        "current_page": pageInfo.pageNum - 1,                 //当前页码
        prev_text: "Previous",                               //在对应上一页操作的按钮上的文本
        next_text: "Next",                                //在对应下一页操作的按钮上的文本
        "items_per_page": pageInfo.pageSize   //每页显示的数量
    };

    $("#Pagination").pagination(totalRecord,properties);
	
}


// 翻页时的回调函数
function paginationCallback(pageIndex, jQuery){

    // pageIndex是当前页码的索引，因此比pageNum小1
    window.pageNum = pageIndex+1;

    // 重新执行分页代码
    generatePage();

    // 取消当前超链接的默认行为
    return false;

}
