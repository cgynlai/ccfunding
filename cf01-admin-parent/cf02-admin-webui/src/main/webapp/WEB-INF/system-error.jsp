<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base
	href="http://${pageContext.request.serverName }:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<title>Insert title here</title>
</head>

<body>
     <h1>System Error !!!</h1>
     
     ${requestScope.exception.message}
     
     
     <button style="width: 300px; margin: 0px auto;"
			class="btn btn-lg btn-success btn-block">back to previous page</button>
<script src="jquery/jquery-2.1.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	$(function() {
		$("button").click(function() {
			// 调用back()方法类似于点击浏览器的后退按钮
			window.history.back();
		});
	});
</script>
</body>
</html>