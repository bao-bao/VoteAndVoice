<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@page
    import = "java.util.*"
 %>
<%
	String title = (String)request.getAttribute("title");
	String des = (String)request.getAttribute("des");
	String order = (String)request.getAttribute("order");
	String single = (String)request.getAttribute("single");
	String multiple = (String)request.getAttribute("multiple");
	String qanda = (String)request.getAttribute("qanda");
	String answer = (String)request.getAttribute("answer");
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Result</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="script/global.js"></script>
    <script type="text/javascript" src="script/jquery.page.js"></script>
    <script src="script/echarts.js"></script>
    <link rel="stylesheet" href="style/answer.css">
    <link rel="stylesheet" href="style/graph.css">
</head>
<body>
<div class="wjContent clear" id="survey_page">
    <!-- 问卷标题 -->
    <div class="content" id="begin_content" style="">
        <div class="wjtitle mtop project_title">
            <span>出自：</span>
            <h1><%=title %></h1>
        </div>
        <div class="wjintro mtop desc_begin">
            <p><%=des %></p>
        </div>
        <div class="wjhr mtop"></div>
    </div>
    <!-- 问题列表 -->
    <div id="question_box">
        <!-- 问卷中的问题在这里！！！使用js，通过数据库返回信息生成！！！ -->
        <!-- 选择题模板 -->
        <div class="row">
            <!-- 图表 -->
            <div class="col-xs-12 col-sm-12 column" id="resultChart">
            <!--  
                <div id="chart"></div>
                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('chart'));
                    // 指定图表的配置项和数据
                    option = {
                        color: ['#3398DB'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '选择人数',
                                type: 'bar',
                                barWidth: '60%',
                                data: [10, 52, 200, 334, 390, 330, 220]
                            }
                        ]
                    };
                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                </script>
                <div id="chart-pie"></div>
                <script type="text/javascript">
                    var myChart = echarts.init(document.getElementById('chart-pie'));
                    option = {
                        backgroundColor: '#fff',

                        title: {
                            text: '你是否曾见过或接触过自闭症儿童？',
                            left: 'center',
                            top: 20,
                            textStyle: {
                                color: '#333'
                            }
                        },

                        tooltip : {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },

                        visualMap: {
                            show: false,
                            min: 80,
                            max: 600,
                            inRange: {
                                colorLightness: [0, 1]
                            }
                        },
                        series : [
                            {
                                name:'人数',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '50%'],
                                data:[
                                    {value:335, name:'直接访问'},
                                    {value:310, name:'邮件营销'},
                                    {value:274, name:'联盟广告'},
                                    {value:235, name:'视频广告'},
                                    {value:400, name:'搜索引擎'}
                                ].sort(function (a, b) { return a.value - b.value}),
                                roseType: 'angle',
                                label: {
                                    normal: {
                                        textStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        lineStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        },
                                        smooth: 0.2,
                                        length: 10,
                                        length2: 20
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        color: '#c23531',
                                        shadowBlur: 200,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    myChart.setOption(option);
                </script>
                 -->
            </div>
        </div>
    </div>
</div>
<script>
	var answer = '<%=answer%>';
	var order = "<%=order%>";
	var single = "<%=single%>";	
	var multiple = "<%=multiple%>";
	var qanda = "<%=qanda%>";
	generateResult(order, single, multiple, qanda, answer);
</script>
<div class="footer_mini">
    <div class="footer">
        <div class="instructions">
            <div class="footer_logo">
                <div class='wjtext'><a href='index.jsp' class='footerlinktoindex' target='_blank'>Vote&Voice </a><span class="footernotice">&nbsp;Return To Index</span></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

