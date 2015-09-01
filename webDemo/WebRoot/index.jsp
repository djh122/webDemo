<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<!--<link rel="stylesheet" type="text/css" href="styles.css">-->
</head>

<body>
	<p id="hello">hello world</p>
	用户名：<input type="text" id="username" name="username"></input><br/><br/>
	密&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="password"></input><br/><br/>
	<button id="sbm">提交</button>
	<p id="msg" style="color:red"></p>
</body>
<script type="text/javascript">
	$(document).ready(
		$("#sbm").click(function(){
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/login.do",
				dataType : "json",
				data : {
					"username" : $("#username").val(),
					"password": $("password").val()
				},
				success : function(data) {
					//console.info(data);
					$("#msg").append(data.message);
				},
				error : function(XMLHttpRequest, textStatus,
						errorThrown) {
				}
			});
		})
	);
	var submitAction = function(){
		$.ajax({
			type : "post",
			url : "${pageContext.request.contextPath}/login.do",
			dataType : "json",
			data : {
				"username" : $("#username").val(),
				"password": $("password").val()
			},
			success : function(data) {
				alert(data[message]);
				$("#msg").append(data[message]);
			},
			error : function(XMLHttpRequest, textStatus,
					errorThrown) {
				//alert(errorThrown + "异常");
			}
		});
	};
	
		
	
	
</script>
</html>
