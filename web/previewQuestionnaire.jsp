<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String info = (String)request.getAttribute("previewInfo");
%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>预览页面</title>
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
            <h1 id="main-title"></h1>
        </div>
        <div class="wjintro mtop desc_begin">
            <p id="main-des"></p>
        </div>
        <div class="wjhr mtop"></div>
    </div>
    <!-- 问题列表 -->
    <div id="question_box">
    </div>
     <div class="maxtop btns" id="control_panel">
        <a class="WJButton wj_blue modal-btn"   id="back_btn"  href="javascript:window.opener=null;window.close();">返回</a>
        <div class="WJButton wj_orange" id="confirm_btn" >提 交</div>
    </div>
</div>
<!-- 脚注 -->
<div class="footer_mini">
    <div class="footer">
        <div class="instructions">
            <div class="footer_logo">
                <div class='wjtext'><a href='index.jsp' class='footerlinktoindex' target='_blank'>Vote&Voice </a><span class="footernotice">&nbsp;Return To Index</span></div>
            </div>
        </div>
    </div>
</div>
<form action="<%=response.encodeURL("Genquestionnaire") %>" method="post">
	<input name="title"  id="title" type="hidden" />
	<input name="des"   id="des" type="hidden" />
	<input name="order" id="order" type="hidden" />
	<input name="single" id="single" type="hidden" />
	<input name="multiple" id="multiple" type="hidden" />
	<input name="qanda" id="qanda" type="hidden" />
	<button type="submit" id="submit_qn"></button>
</form>
<script>
    var title = decodeURI(localStorage.getItem('title'));
    var des = decodeURI(localStorage.getItem('des'));
    var order = decodeURI(localStorage.getItem('order'));
    var single = decodeURI(localStorage.getItem('single'));
    var multiple = decodeURI(localStorage.getItem('multiple'));
    var qanda = decodeURI(localStorage.getItem('qanda'));


    console.log(order)///
	console.log(single)///
	console.log(multiple)///
	console.log(qanda)///
	
	$('#main-title').text(title);
	$('#main-des').text(des);
	processQnaire(order, single, multiple, qanda);

</script>
</body>
</html>
