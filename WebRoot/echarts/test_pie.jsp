<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js"></script>
<title>图像展示</title>
</head>
<body>
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
   // 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
option = {
   title:{
	   text:'站点用户来源',
	   x:'center'
   },
   /*
   tooltip:{
	   trigger:'item',
	   formatter:"{a}<br/>{b} : {c} ({d}%)"
   },
   */
   legend:{
	   orient:'veitical',
	   left:'right',
	   data:['直接访问','邮件营销','广告联盟','视频广告','搜索引擎']
   },
   series:[
       {
    	   name:'访问来源',
    	   type:'pie',
    	   radius:'75%',
    	   center:['50%','60%'],
    	   data:[
    	       {value:335,name:'直接访问'},
    	       {value:310,name:'邮件营销'},
    	       {value:234,name:'广告联盟'},
    	       {value:135,name:'视频广告'},
    	       {value:1548,name:'搜索引擎'}
    	   ],
    	   itemStyle:{
    		   emphasis:{
    			   shadowBlur:10,
    			   //shadowOffsetX:0,
    			   shadowColor:'rgba(0,0,0,0.5)'
    		   }
    	   }
       }
   ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
</body>
</html>