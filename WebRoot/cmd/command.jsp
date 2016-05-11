<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>命令式编程</title>
    <script src="<%=request.getContextPath() %>/js/jquery-2.0.3.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/jquery.form.js"></script>
<style>
.main-cls {
	margin-top: 20px;
	/* margin-left: 50px; */
	margin: 0 auto;
	width: 500px;
}
.p-cls {
	width: 500px;
	height: 300px;
	font-size: 14px;
	border:solid 1px gray;
	overflow-y :auto;
	overflow-y :yes;
}
.input-cls{
	font-size: 14px;
	height: 100px;
	width: 500px;
}
</style>

</head>
<body style="background-color: #eeeeee;">
<div class="main-cls">
	<div class="p-cls" id="show_id" >
		<p>console,执行结果输出框</p><br/>
	</div>
	<form id="form_id">
	<div>
		<textarea id="cmd_id" class="input-cls" name="command"></textarea>
		
	</div>
	</form>
</div>
<script type="text/javascript">
	//回车事件绑定
	$("#cmd_id").bind('keyup', function(event) {
	    if (event.keyCode == "13") {
	        //回车执行查询
            $("#form_id").ajaxSubmit({  
                type:"post",  //提交方式  
                url:"testAction_command", //请求url  
                success:function(data){ //提交成功的回调函数  
                	var cmd = '<p>命令>>'+$("#cmd_id").val()+'</p>';
                	var text = '<p>结果：'+data+'</p>';
                	$("#show_id").append(cmd).append(text).append('<br/>');
                	//show_id.scrollIntoView(); 
                	$('#show_id').scrollTop( $('#show_id')[0].scrollHeight )
                	$("#cmd_id").val("");
                },
                error: function(XmlHttpRequest, textStatus, errorThrown){  
                    alert( "error"+errorThrown+"||"+textStatus+"||"+XmlHttpRequest);  
                } 
            });  
            return false; //不刷新页面  
	    }
	});
</script>

</body>
</html>