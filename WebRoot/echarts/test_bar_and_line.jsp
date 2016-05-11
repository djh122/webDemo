<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath}/ja/jquery-2.0.3.min.js"></script>
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
	   	text:'my echart example'
   	},
    tooltip:{
        trigger:'axis'
    },
    /*
    toolbox:{
        feature:{
            dataView:{show:true,readOnly:false},
            magicType:{show:true,type:['line','bar']},
            restore:{show:true},
            //saveAsImage:{show:true}
        }
    },
    */
    /*
    legend:{
        data:['蒸发量','降水量','平均温度']
    },
    */
    xAxis:[
        {
            //type:'category',
            data:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
        }
    ],
    yAxis:[
        {
            type:'value',
            name:'水量',
            min:0,
            max:250,
            interval:50,
            axisLabel:{
                formatter:'{value}ml'
            }
        }
        ,
        {
            type:'value',
            name:'温度',
            min:0,
            max:25,interval:5,
            axisLabel:{
                formatter:'{value}°C'
            }
        }
    ],
    series:[
        {
            name:'蒸发量',
            type:'bar',
            data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
        },
        {
            name:'降水量',
            type:'bar',
            yAxixIndex:0,
            data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
        },
        {
            name:'平均温度',
            //折线图和柱状图横纵坐标设置，数据基本一致，只需要改个type就能直接用
            type:'line',
            yAxisIndex:1,
            data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]
        }
        
    ]   
};

   // 使用刚指定的配置项和数据显示图表。
   myChart.setOption(option);
</script>
</body>
</html>