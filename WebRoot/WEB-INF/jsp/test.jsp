<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>my test</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   		<h2>hello world</h2>
  </body>
  <script type="text/javascript">
  	wx.config({
	    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	    appId: ${config.appId}, // 必填，公众号的唯一标识
	    timestamp: ${config.timestamp}, // 必填，生成签名的时间戳
	    nonceStr: ${config.nonceStr}, // 必填，生成签名的随机串
	    signature: ${config.signature},// 必填，签名，见附录1
	    jsApiList: ${config.jsApiList} // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
  
  </script>
</html>
