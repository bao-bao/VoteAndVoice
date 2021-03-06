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
</head>
<script>
	console.log(window.screen.width);


    if(window.screen.width<900){
        document.getElementsByTagName("head")[0].innerHTML+='<style>.wjContent{font-size\:32px;}.WJButton{font-size:24px;}.title{font-size: 36px;}.wjintro,.wjintro{font-size:36px;}.wjtitle h1{font-size:36px;}body,input,button,select,textarea,th,td{font-size:32px;}.option{width:24px;height:24px;}</style>'
    }
	var is_submit = false;
    var user_id = "<%=u_id%>";
    var questionnaire_id = "<%=qn_id%>";

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

    function getJSONTempArray() {
        var answer = {};
        var order = [];
        var qList = document.getElementsByClassName("question");
        var an = 0;

        for(var i  = 0; i < qList.length; ++ i){
            var is_answered = false;
            switch(qList[i].getAttribute("questiontype")){
                case '1':{
                    var single = qList[i].getElementsByTagName("input");
                    for(var j = 0; j < single.length; ++ j){
                        if(single[j].checked){
                            answer[an ++] = j;
                            is_answered = true;
                            break;
                        }
                    }
                    break;
                }
                case '2':{
                    var multiple = qList[i].getElementsByTagName("input");
                    var checked = [];
                    for(var k = 0; k < multiple.length; ++ k){
                        if(multiple[k].checked){
                            is_answered = true;
                            checked.push(k);
                        }
                    }
                    answer[an ++] = checked;
                    break;
                }
                case '3':{
                    var qanda = qList[i].lastElementChild.lastElementChild;
                    if( qanda.value){
                        answer[an ++] = qanda.value;
                        is_answered = true;
                    }
                    break;
                }
            }
            if(is_answered) {
                order.push(i);
            }
        }
        var janswer = JSON.stringify(answer);
        var jorder = JSON.stringify(order);
        if(order.length > 0){
            localStorage.setItem('janswer', encodeURI(janswer));
            localStorage.setItem('jorder', encodeURI(jorder));
        }
    }
    function finishQuestionnaire(){
        var user_id = "<%=u_id%>";
        var questionnaire_id = "<%=qn_id%>";
        var janswer = getJSONAnswer();
        is_submit = true;
        localStorage.clear();
        $.post(
            '/VoteAndVoice/submit',
            {
                u_id: user_id,
                qn_id: questionnaire_id,
                answer: janswer
            },
            function(data){
                var jobj = eval('(' + data + ')');
                //alert(jobj.errorMessage);
                if(jobj.errorMessage == "success"){
                    if (confirm("提交成功关闭本页")){
                        window.opener=null;
                        window.open('','_self');
                        window.close();
                    }
                }
                is_submit = false;
            }
        )
    }

    function saveAnswer() {
        if (!is_submit) {
            getJSONTempArray();
            localStorage.setItem('user_id', encodeURI(user_id));
            localStorage.setItem('questionnaire_id', encodeURI(questionnaire_id));
        }
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
				<div class='wjtext'><a href='index.jsp' class='footerlinktoindex' target='_blank' onclick="saveAnswer();">Vote&Voice </a>
					<span class="footernotice">&nbsp;Return To Index</span>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
    function processQnaire(order, single, multiple, qanda){
        var jorder = eval('(' + order + ')' );
        var jsingle = eval( '(' + single + ')' );
        var jmultiple =  eval( '(' + multiple + ')' );
        var jqanda = eval( '(' + qanda + ')' );
        console.log(order)///
        console.log(single)///
        console.log(multiple)///
        console.log(qanda)///
        var isingle = 0;
        var imultiple = 0;
        var iqanda = 0;

        for(var i = 0; i < jorder.order.length; ++ i){
            switch(jorder.order[i]){
                case 'single':{
                    var question = jsingle.single[isingle ++];
                    addSingle(question, i + 1);
                    break;
                }
                case 'multiple':{
                    var question = jmultiple.multiple[imultiple ++];
                    addMultiple(question, i + 1);
                    break;
                }
                case 'qanda':{
                    var question = jqanda.qanda[iqanda ++];
                    addQandA(question, i + 1);
                    break;
                }
            }
        }
    }
    //增加单选题
    function addSingle(question, num){
        addSingleOrMultiple(question, num, "1");
    }
    //增加多选题
    function addMultiple(question, num){
        addSingleOrMultiple(question, num, "2");
    }
    function addSingleOrMultiple(question, num, type){
        var root = document.createElement("div");
        root.setAttribute("class", "wjques maxtop question jqtransformdone");
        root.setAttribute("questiontype", type);

        var title = document.createElement("div");
        title.setAttribute("class", "title");

        var qorder = document.createElement("span");
        qorder.setAttribute("class", "qorder");
        qorder.appendChild(document.createTextNode(num + " "));

        title.appendChild(qorder);
        title.appendChild(document.createTextNode(question[0]));

        root.appendChild(title);

        var matrix = document.createElement("div");
        matrix.setAttribute("class","matrix");

        for(var i  = 1; i < question.length; ++ i){
            var checkbox = document.createElement("div");
            checkbox.setAttribute("class", "icheck_box");

            var wrapper = document.createElement("span");
            wrapper.setAttribute("class", "jqTransformRadioWrapper");

            var input =  document.createElement("input");
            if(type == "1"){
                input.setAttribute("type", "radio");
                var name = "typename" + num;
                input.setAttribute("name", name);
            }
            else if(type == "2") input.setAttribute('type', "checkbox");
            input.setAttribute("class", "option jqTransformHidden");

            wrapper.appendChild(input);
            wrapper.appendChild(document.createTextNode(" " + question[i]));

            checkbox.appendChild(wrapper);
            matrix.appendChild(checkbox);
        }
        root.appendChild(matrix);
        document.getElementById("question_box").appendChild(root);
    }

    //增加问答题
    function addQandA(question, num){
        var root = document.createElement("div");
        root.setAttribute("class", "wjques maxtop question jqtransformdone");
        root.setAttribute("questiontype", "3");

        var title = document.createElement("div");
        title.setAttribute("class", "title");

        var qorder = document.createElement("span");
        qorder.setAttribute("class", "qorder");
        qorder.appendChild(document.createTextNode(num + " "));

        title.appendChild(qorder);
        title.appendChild(document.createTextNode(question[0]));

        root.appendChild(title);

        var matrix = document.createElement("div");
        matrix.setAttribute("class", "matrix");

        var textarea = document.createElement("textarea");
        textarea.setAttribute("class", "blankoption");
        textarea.setAttribute("cols", "40");
        textarea.setAttribute("rows", "5");

        matrix.appendChild(textarea);

        root.appendChild(matrix);
        document.getElementById("question_box").appendChild(root);
    }

    var order = "<%=order%>";
    var single = "<%=single%>";
    var multiple = "<%=multiple%>";
    var qanda = "<%=qanda%>";
    processQnaire(order, single, multiple, qanda);

    function fillAnswer(order, answer){
        var objan = JSON.parse(answer);
        var arror = JSON.parse(order);

        var qList = document.getElementsByClassName("question");
        for(var i = 0; i < arror.length; ++ i){
            var index = arror[i];
            switch(qList[index].getAttribute("questiontype")) {
                case '1':{
                    var single = qList[index].getElementsByTagName("input");
                    single[ objan[i] ].checked = true;
                    break;
                }
                case '2':{
                    var multiple = qList[index].getElementsByTagName("input");
                    var checked_arr = objan[i];
                    for (var k = 0; k < checked_arr.length; ++ k) {
                        multiple[ checked_arr[k] ].checked = true;
                    }
                    break;
                }
                case '3':{
                    var qanda = qList[index].lastElementChild.lastElementChild;
                    qanda.value = objan[i];
                    break;
                }
            }
        }
    }
    var st_user_id = localStorage.getItem('user_id');
    var st_qn_id = localStorage.getItem('questionnaire_id');
    var janswer = localStorage.getItem('janswer');
    var jorder = localStorage.getItem('jorder');

    st_user_id = decodeURI(st_user_id);
    st_qn_id = decodeURI(st_qn_id);

    if (st_user_id && st_qn_id && janswer && jorder) {
        var answer = decodeURI(janswer);
        var order = decodeURI(jorder);
        if (st_user_id === user_id && st_qn_id === questionnaire_id) {
            fillAnswer(order, answer);
        }
    }

</script>
</body>
</html>
