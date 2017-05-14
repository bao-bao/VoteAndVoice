<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
    String u_id = (String)request.getAttribute("u_id");
    String qn_id = (String)request.getAttribute("qn_id");
    String title = (String)request.getAttribute("title");
	String des = (String)request.getAttribute("des");
	String order = (String)request.getAttribute("order");
	String single = (String)request.getAttribute("single");
	String multiple = (String)request.getAttribute("multiple");
	String qanda = (String)request.getAttribute("qanda");
%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><%=title %></title>
    <link rel="stylesheet" href="style/answer.css">
    <script src="script/jquery.min.js"></script>
    <script src="script/main.js"></script>
    <script>
        var u_id = '<%=u_id%>';
        var qn_id = '<%=qn_id%>';
        var jiathis_config = {
            url: "u_id=" + u_id + "&qn_id=" + qn_id,
            title: "问卷分享",
            summary: "分享问卷，让大家一起来填"
        }
    </script>

</head>
<body>
<div id="ckepop">
    <span class="jiathis_txt">分享到：</span>
    <a class="jiathis_button_weixin">微信</a>
    <a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jiathis_separator jtico jtico_jiathis"
       target="_blank">更多</a>
    <a class="jiathis_counter_style"></a>
</div>

<!-- 问卷框架 -->
<div class="wjContent clear" id="survey_page">
    <!-- 问卷标题 -->
    <div class="content" id="begin_content" style="">
        <div class="wjtitle mtop project_title">
            <h1 id="main-title"><%=title %></h1>
        </div>
        <div class="wjintro mtop desc_begin">
            <p id="main-des"><%=des %></p>
        </div>
        <div class="wjhr mtop"></div>
    </div>
    <!-- 问题列表 -->
    <div id="question_box">
    </div>
</div>
<!-- 脚注 -->
<div class="footer_mini">
    <div class="footer">
        <div class="instructions">
            <div class="footer_logo">
                <div class='wjtext'><a href='index.jsp' target='_blank'>Vote&Voice </a><span class="footernotice">&nbsp;Return To Index</span></div>
            </div>
        </div>
    </div>
</div>
<script>
	var order = "<%=order %>";
	var single = "<%=single.replaceAll("\"","\'") %>";
	var multiple = "<%=multiple.replaceAll("\"","\'") %>";
	var qanda = "<%=qanda.replaceAll("\"","\'") %>";
	processQnaire(order, single, multiple, qanda);

</script>
<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1" charset="utf-8"></script>
<script src="http://v2.jiathis.com/code/jiathis_r.js?move=0"></script>
</body>
</html>
