/**
 * Created by Xu on 2016/11/26.
 */
/****************************This is  for the user.html *****************************************/
function insertAfter(newElement,targetElement) {
    var parent = targetElement.parentNode;
    if (parent.lastChild == targetElement) {
        parent.appendChild(newElement);
    } else {
        parent.insertBefore(newElement,targetElement.nextSibling);
    }
}
function addLoadEvent(func) {
    var oldonload = window.onload;
    if (typeof window.onload != 'function') {
        window.onload = func;
    } else {
        window.onload = function() {
            oldonload();
            func();
        }
    }
}
function toggle(){
	var friend = document.getElementById("friends-management");
	var cName = "list-group-item";
	var list = [];
	var linkList = [];
	var text = ["最新动态", "我的关注", "我的好友", "关注我的", "搜索好友"];
    var textNode  = [];

	for(var i = 0; i < 5; ++ i){
		list[i] = document.createElement("li");
		list[i].setAttribute("class", cName);
        textNode[i] = document.createTextNode(text[i]);
        linkList[i] = document.createElement("a");
        linkList[i].setAttribute("href", "#");
        list[i].appendChild(textNode[i]);
        list[i].appendChild(linkList[i]);
        if(i == 0) insertAfter(friend, list[i]);
        else insertAfter(list[i - 1], list[i]);
	}
}

$(function(){
    $('.select').click(function(){
        $('.select_pop').stop().toggle();
    });
    $('.search .button').hover(function(){
        $(this).addClass('button_hover');
    },function(){
        $(this).removeClass('button_hover');
    });
    $("#s_all a").click(function(){
        var sval=$(this).find("span").html();
        var stype=$(this).find("span").attr("class");
        $("#lm").html(sval);
        $("#stype").val(stype);
    })

});

function check(){
    if($("#stype").val()=="zz"){
        $("#searchform").attr("action","/search.php");
    }
    else if($("#stype").val()=="mb"){
        $("#searchform").attr("action","/search_mj.php");
    }
    else if($("#stype").val()=="jc"){
        $("#searchform").attr("action","/search_jc.php");
    }
}


function prepareSearchType(){
    var list = document.getElementsByClassName("user-button");
    if(list == null) return;
    for(var i  = 0; i < list.length; ++ i){
        list[i].onclick = function(){
            submitType(this);
        }
    }
}
addLoadEvent(prepareSearchType);
function submitType(btn){
    var label = btn.getElementsByTagName("label")[0];
    if(label == null) return;
    label = label.innerText;

    var keyType = document.getElementById("keyType");
    var typeClick = document.getElementById("typeClick");

    keyType.value = label;
    typeClick.click();
}
/******************************This is for the searchQuestionnaire*************************************************/
function getChosenValue() {
	var selist = document.getElementsByTagName("select");
	if (selist == null)
		return;
	document.getElementById("orderby").value = selist[0].value;
	document.getElementById("totalNum").value = selist[1].value;
	document.getElementById("qType").value = selist[2].value;
	document.getElementById("collectNum").value = selist[3].value;
}

function getQChosenValue() {
	var selist = document.getElementsByTagName("select");
	if (selist == null)
		return;
	document.getElementById("questionType").value = selist[0].value;
	document.getElementById("questionnaireType").value = selist[1].value;
}
/*********************************For the result******************************************/
function addSingleChart(question, an){
	var pdiv = document.createElement("div");
	pdiv.style.width = 600 + "px";
	pdiv.style.height = 400 + "px";
	document.getElementById("resultChart").appendChild(pdiv);
	
	var schart = echarts.init(pdiv);
	var option = {};
	var colorarr = [];
	var tooltip = {};
	var axisPointer ={};
	var grid = {};
	
	colorarr.push('#3398DB');
	option['color'] = colorarr;
	
	tooltip['trigger'] = 'axis';
	axisPointer['type'] = 'shadow';
	tooltip['axisPointer'] = axisPointer;
	option['tooltip'] = tooltip;
	
	grid['left'] = '3%';
	grid['right'] = '4%';
	grid['bottom'] = '3%';
	grid['containLabel'] = true;
	option['grid'] = grid;
	
	var xAxis = [];
	var xAxisValue = {};
	var data = [];
	var axisTick = {};
	xAxisValue['type'] = 'category';
	xAxisValue['data'] = data;
	for(var i = 1; i < question.length; ++ i){
		data.push(question[i]);
	}
	axisTick['alignWithLabel'] = true;
	xAxisValue['axisTick'] = axisTick;
	xAxis.push(xAxisValue);
	option['xAxis'] = xAxis;
	
	var yAxis = [];
	var yAxisValue = {};
	yAxisValue['type'] = 'value';
	yAxis.push(yAxisValue);
	option['yAxis'] = yAxis;
	
	var series = [];
	var seriesValue = {};
	var sdata = [];
	for(var j = 0; j < an.length; ++ j){
		sdata.push(an[j]);
	}
	seriesValue['name'] = '选择人数';
	seriesValue['type'] = 'bar';
	seriesValue['barWidth'] = '60%';
	seriesValue['data'] = sdata;
	series.push(seriesValue);
	option['series'] = series;
	
	var title = {};
	var textStyle = {};
	textStyle['color'] = '#333';
	title['text'] = question[0];
	title['left'] = 'center';
	title['top'] = 20;
	title['textStyle'] = textStyle;
	option['title'] = title;
	
	schart.setOption(option);
}
function addMultipleChart(question, an){
	var pdiv =  document.createElement("div");
	pdiv.style.width = 600 + "px";
	pdiv.style.height = 400 + "px";
	document.getElementById("resultChart").appendChild(pdiv);
	
	var mchart = echarts.init(pdiv);
	
	var option = {};
	option['backgroundColor'] = '#fff';
	
	var title={};
	title['text'] = question[0];
	title['left'] = 'center';
	title['top'] = 20;
	var textStyle= {};
	textStyle['color'] = '#333';
	title['textStyle']= textStyle;
	option['title'] = title;
	
	var tooltip = {};
	tooltip['trigger'] = 'item';
	tooltip['formatter'] ="{a} <br/>{b}:{c}({d}%)";
	option['tooltip'] = tooltip;
	
	var visualMap = {};
	visualMap['show'] =false;
	visualMap['min'] = 80;
	visualMap['max'] = 600;
	var inRange = {}
	var colorLightness = [];
	colorLightness.push(0);
	colorLightness.push(1);
	inRange['colorLghtness'] = colorLightness;
	visualMap['inRange'] = inRange;
	option['visualMap'] = visualMap;
	
	var series = [];
	var svalue = {};
	
	svalue['name'] = '人数';
	svalue['type'] = 'pie';
	svalue['radius'] = '55%';
	var center = [];
	center.push('50%');
	center.push('50%');
	svalue['center'] = center;
 	var data = [].sort(function(a, b){return a.value - b.value});
 	
 	for(var i = 0; i < an.length; ++ i){
 		var item = {};
 		item['value'] =  an[i];
 		item['name'] = question[i + 1];
 		data.push(item);
 	}
 	svalue['data'] = data;
 	svalue['roseType'] = 'angle';
 	var label = {};
 	var normal = {};
 	var textStyle = {};
 	var color = {};
 	color['color'] = 'rgba(255, 255, 255, 0.3)';
 	textStyle['textStyle'] = color;
 	normal['normal'] = textStyle;
 	label['label'] = normal;
 	svalue['label'] = label;
 	
 	var labelLine = {};
 	var lnormal = {};
 	var lineStyle = {};
 	lineStyle['lineStyle'] = color;
 	lnormal['lineStyle'] = lineStyle;
 	labelLine['normal'] = lnormal;
 	labelLine['smooth'] = 0.2;
 	labelLine['length'] = 10;
 	labelLine['length2'] = 20;
 	svalue['labelLine'] = labelLine;
 	
 	var itemStyle = {};
 	var inormal = {};
 	inormal['color'] = '#c23531';
 	inormal['shadowBlur'] = 200;
 	inormal['shadowColor'] = 'rgba(0, 0, 0, 0.5)';
 	itemStyle['normal'] = inormal;
 	svalue['itemStyle'] = itemStyle;
 	
 	series.push(svalue);
 	option['series'] = series;
 	mchart.setOption(option);
}
function addQandAChart(question, an, order){
	var panel = document.createElement("div");
	var phead  = document.createElement("div");
	var ptitle = document.createElement("h4");
	var a = document.createElement("a");
	
	panel.setAttribute("class", "panel panel-default");
	phead.setAttribute("class", "panel-heading");
	ptitle.setAttribute("class", "panel-title");
	a.setAttribute("data-toggle", "collapse");
	a.setAttribute("data-parent", "#accordion");
	var id = "collapse" + order;
	a.setAttribute("href", "#" + id);
	a.appendChild(document.createTextNode(question[0]));
	ptitle.appendChild(a);
	phead.appendChild(ptitle);
	panel.appendChild(phead);
	
	var collapse = document.createElement("div");
	collapse.setAttribute("id", id);
	collapse.setAttribute("class", "panel-collapse collapse");
	for(var i = 0;i < an.length; ++ i){
		var body = document.createElement("div");
		body.setAttribute("class", "panel-body");
		body.appendChild(document.createTextNode(an[i]));
		collapse.appendChild(body);
	}
	panel.appendChild(collapse);
	
	document.getElementById("resultChart").appendChild(panel);
}

function generateResult(order, single, multiple, qanda, answer){
	var jorder = eval('(' + order + ')' );
	var jsingle = eval( '(' + single + ')' );
	var jmultiple =  eval( '(' + multiple + ')' );
	var jqanda = eval( '(' + qanda + ')' );
	var janswer = eval('(' + answer + ')');
	
	var isingle = 0;
	var imultiple = 0;
	var iqanda = 0;
	console.log(jorder.order.length)///
	for(var i = 0; i < jorder.order.length; ++ i){
		var an = janswer[i];
		switch(jorder.order[i]){
		case 'single':{
			var question = jsingle.single[isingle ++];
			console.log("isingle: " + isingle + "\nquestion: " + question)///
			addSingleChart(question, an);
			break;
		}
		case 'multiple':{
			var question = jmultiple.multiple[imultiple ++];
			console.log("imultiple: " + imultiple + "\nquestion: " + question)///
			addMultipleChart(question, an);
			break;
		}
		case 'qanda':{
			var question = jqanda.qanda[iqanda ++];
			console.log("iqanda: " + iqanda + "\nquestion: " + question)///
			addQandAChart(question, an);
			break;
		}
		}
	}
}


































