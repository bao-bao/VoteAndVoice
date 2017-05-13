<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page
    import = "vo.*"
    import = "java.util.*"
    import ="dao.*"
 %>
<%
	String u_id = (String)session.getAttribute("u_id");
	ArrayList<ExDbquestionnaire> exQnList = (ArrayList<ExDbquestionnaire>)session.getAttribute("questionnaire");
	int pos = Integer.parseInt(request.getParameter("pos"));
	ExDbquestionnaire thisQn = exQnList.get(pos);
	Dbquestionnaire qn = thisQn.getQuestionnaire();
	
	String title = qn.getQn_title();
	String des = qn.getQn_des();
	String qn_id = qn.get_transQn_id();
	
	ArrayList<ExDbquestionnaire> newExQnList = new ArrayList<ExDbquestionnaire>();
	int message = DAOFactory.getGetResultDAO().getQnResultByQnId(qn_id, newExQnList);
	ArrayList<ExDbquestion> qList = newExQnList.get(0).get_transExQuestionList();
	
	String order = "{'order': [ ";/////加了个空格
	String single = "{'single':[ ";/////加了个空格
	String multiple = "{'multiple':[ ";/////加了个空格
	String qanda = "{'qanda':[ ";/////加了个空格
	
	int length = qList.size();
	System.out.println(length);
	for(int i = 0; i < length; ++ i){
		Dbquestion thisq = qList.get(i).getQuestion();
		ArrayList<ExDbitem> itemList = qList.get(i).getExItemList();
		
		String type = thisq.getQ_type();
		String stem  =thisq.get_transQ_stem();
		
		if(type.equals("sin")){
			if(i != length - 1 ) order += "'single',";
			else order += "'single'";
			if(itemList.size() > 0) single += "['" + stem + "',";
			else  single += "['" + stem + "']";
			for(int j = 0; j < itemList.size(); ++ j){
				if(j != itemList.size() -1) single += "'" + itemList.get(j).getItem().get_transI_des() + "',";
				else single += "'" + itemList.get(j).getItem().get_transI_des() + "']";
			}
			single += ",";/////
		}
		else if( type.equals("mul")){
			if(i != length - 1) order += "'multiple',";
			else order += "'multiple'";
			if(itemList.size() > 0) multiple += "['" + stem + "',";
			else multiple += "['" + stem + "']";
			for(int j  = 0; j < itemList.size(); ++ j){
				if(j != itemList.size() - 1) multiple += "'" + itemList.get(j).getItem().get_transI_des() + "',";
				else multiple += "'" + itemList.get(j).getItem().get_transI_des() + "']";
			}
			multiple += ",";/////
		}
		else if(type.equals("que")){
			if(i != length - 1) order += "'qanda',";
			else order += "'qanda'";
			qanda += "['" + stem +"']";
			qanda += ",";/////
		}
	
	}
	single = single.substring(0, single.length() - 1);/////
	multiple = multiple.substring(0, multiple.length() - 1);/////
	qanda = qanda.substring(0, qanda.length() - 1);/////
	order += "]}";
	single += "]}";
	multiple += "]}";
	qanda += "]}";
	System.out.println(order);
	System.out.println(single);
	System.out.println(multiple);
	System.out.println(qanda);
%>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title></title>
    <link rel="stylesheet" href="style/answer.css">
    <script src="script/jquery.min.js"></script>
    <script src="script/main.js"></script>
</head>
<script>
	function getJSONAnswer(){
		var answer = {};
		var qList = document.getElementsByClassName("question");
		
		for(var i  = 0; i < qList.length; ++ i){
			switch(qList[i].getAttribute("questiontype")){
			case '1':{
				var single = qList[i].getElementsByTagName("input");
				for(var j = 0; j < single.length; ++ j){
					if(single[j].checked){
						answer[i] = j; 
						break;
					}
				}
				break;
			}
			case '2':{
				var multiple = qList[i].getElementsByTagName("input");
				var checked = [];
				for(var j = 0; j < multiple.length; ++ j){
					if(multiple[j].checked){
						checked.push(j);
					}
				}
				answer[i] = checked;
				break;
			}
			case '3':{
				var qanda = qList[i].lastElementChild.lastElementChild;
				answer[i] = qanda.value;
				break;
			}
			}
		}
		var janswer = JSON.stringify(answer);
		return janswer;
	}
	function finishQuestionnaire(){
		var user_id = "<%=u_id%>";
		var questionnaire_id = "<%=qn_id%>";
		var janswer = getJSONAnswer();
		$.post(
		'/VoteAndVoice/submit',
		{
			u_id: user_id,
			qn_id: questionnaire_id,
			answer: janswer
		},
		function(data){
			var jobj = eval('(' + data + ')');
			alert(jobj.errorMessage);
			if(jobj.errorMessage == "success"){
				if (confirm("提交成功关闭本页")){
					window.opener=null;
					window.open('','_self');
					window.close();
				}
			}
		}
		)
		
	}
</script>
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
    <div class="maxtop btns" id="control_panel">
           <div class="WJButton wj_white"  id="answer_btn"  onclick="finishQuestionnaire()">提交您的回答</div>
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
<script>
	var order = "<%=order%>";
	var single = "<%=single%>";	
	var multiple = "<%=multiple%>";
	var qanda = "<%=qanda%>";
	processQnaire(order, single, multiple, qanda);
</script>
</body>
</html>
