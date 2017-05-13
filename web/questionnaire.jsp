<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
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
</head>
<body>
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
	var order = "<%=order%>";
	var single = "<%=single%>";
	var multiple = "<%=multiple%>";
	var qanda = "<%=qanda%>";
	processQnaire(order, single, multiple, qanda);

</script>
</body>
</html>
