<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/echarts.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/china.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echart/zhejiang.js"></script>

<title>图像展示</title>
</head>
<body>
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
function randomData() {
    return Math.round(Math.random()*1000);
}
   // 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
option = {
	    title: {
	        text: 'iphone销量',
	        subtext: '纯属虚构',
	        left: 'center'
	    },
	    tooltip: {
	        trigger: 'item'
	    },
	    legend: {
	        orient: 'vertical',
	        left: 'left',
	        data:['iphone3','iphone4','iphone5']
	    },
	    visualMap: {
	        min: 0,
	        max: 2500,
	        left: 'left',
	        top: 'bottom',
	        text: ['高','低'],           // 文本，默认为数值文本
	        calculable: true
	    },
	    toolbox: {
	        show: true,
	        orient: 'vertical',
	        left: 'right',
	        top: 'center',
	        feature: {
	            dataView: {readOnly: false},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    series: [
	        {
	            name: 'iphone3',
	            type: 'map',
	            mapType: 'zhejiang',
	            roam: false,
	            label: {
	                normal: {
	                    show: true
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            data:[
	             
	            ]
	        },
	        {
	            name: 'iphone4',
	            type: 'map',
	            mapType: 'zhejiang',
	            label: {
	                normal: {
	                    show: true
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            data:[
	               
	            ]
	        }
	    ]
	};
// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
</script>
</body>
</html>