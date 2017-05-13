<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@page 
 	import="vo.*"
 	import="java.util.*"
 	import="dao.*"
 %>
 <%
 @SuppressWarnings("unchecked")
 	ArrayList<ExDbquestionnaire> qnaire = (ArrayList<ExDbquestionnaire>)request.getAttribute("qnaire");
  %>
<%@include file="indexHeader.jsp" %>
<%
		String u_id = null;
		if(user == null) request.getRequestDispatcher("index.jsp").forward(request, response);
		else u_id = user.get_transU_id();
%>
    <div class="container">
        <!-- 搜索栏 -->
        <div class="row">
            <div class="col-md-12 col-sm-12 column">
                <div class="search">
                    <form action="<%=response.encodeURL("FillIndex") %>" name="searchform" id="searchform"  method="post">
                        <input type="text" class="text gray" placeholder="输入您想填写的问卷关键字" name="key" ID="key_S"/>
                        <a href="javascript:void(0);" class="del-keywords"></a>
                        <input type="submit" class="sbtn" value="搜索">
                    </form>
                </div>
            </div>
        </div>
        <!-- 主体 -->
        <div class="row">
            <!-- 问卷列表 -->
            <div class="col-md-8 col-sm-8 column">
                <%
                for(int i = 0; qnaire != null && i < qnaire.size(); ++ i){
                	Dbquestionnaire qn = qnaire.get(i).getQuestionnaire();
                  %>
                <div class="event-page">
                    <div class="row">
                        <div class="col-xs-3 col-sm-3">
                            <div class="event-img">
                                <a href="#">
                                    <img class="img-responsive" src="images/so_icon.png">
                                </a>
                                <div class="over-image"></div>
                            </div>
                        </div>
                        <div class="col-xs-9 col-sm-9 event-desc">
                            <div class="row">
                                <div class="col-xs-9 col-sm-9">
                                 	<h2><a href="#"  id="qn_title" class="questionnaire-list"><%=qn.get_transQn_title() %></a></h2>
                                </div>
                                <div class="col-xs-3 col-sm-3">
                                    <h2><span class="label label-success"><%= qn.get_transQn_state() %></span></h2>
                                </div>
                            </div>
                            <div class="event_right">
                                <div class="event-info-middle">
                                    <p><strong>描述: &nbsp;</strong><%=qn.get_transQn_des() %></p>
                                    <p style="display:inline;"><strong>类型 :&nbsp;</strong></p>
                                    <ul class="event-speakers" style="display:inline">
                                        <li><a href="#" class="link-font"><%=qn.get_transQn_type() %></a></li>
                                        <li><p><%=qn.get_transQn_tag() %></p></li>
                                    </ul>
                                    <p><strong>问题数量 : &nbsp;</strong><%=qn.get_transQn_q_count() %>个</p>
                                    <p style="margin-top: 4px"><strong>收集数量 : &nbsp;</strong><%=qn.get_transQn_a_count() %>份</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%} %>
                <div id="page"></div>
                <script type="text/javascript">
                <%if(qnaire != null){%>
                var size =<%=qnaire.size()%>;
                if(size > 11){
                    $(function () { 
                        $("#page").Page({
                            totalPages: 11,//分页总数
                            liNums: size / 11,//分页的数字按钮数(建议取奇数)
                            activeClass: 'activP', //active 类样式定义
                            callBack: function (page) {
                            }
                        });
                    });
                }
                <%}%>
                </script>
            </div>
            <!-- 功能栏 -->
            <div class="col-md-4 col-sm-4 column">
                <div class="function-list">
                    <h4><a>分类搜索：</a></h4>
                </div>
                <div id="function-list">
                  		<div class="user-button  type1">
                            <input type="checkbox">
                            <label>体育</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>健康</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>娱乐</label>
                        </div>
                        <div class="user-button type1">
                            <input type="checkbox">
                            <label>商业</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>政治</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>教育</label>
                        </div>
                    	<div class="user-button  type1">
                            <input type="checkbox">
                            <label>文化</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>旅游</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>法律</label>
                        </div>
                    	 <div class="user-button  type1">
                            <input type="checkbox">
                            <label>生活</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>科技</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>艺术</label>
                        </div>
                </div>
            </div>
        </div>
	</div>
    </div>
	<form action="<%=response.encodeURL("SearchType") %>" method="post">
		<input type="hidden" name="keyType"  id="keyType"/>
		<button type="submit" id="typeClick" style="display:none"></button>
	</form>
	<form action="" method="post" id="fetchingQuestionnaire">
		<%
			session.setAttribute("questionnaire", qnaire);
			session.setAttribute("u_id", u_id);
		%>
		<input type="submit"  id="fetch-questionnaire"/>
	</form>
    <footer id="footer" role="contentinfo">
        <div class="container">
            <div class="row">
                <div class="col-md-12 text-center">
                    <div class="footer-widget border">
                        <p class="pull-left">
                            <small>&copy;所有权利保留</small>
                        </p>
                        <p class="pull-right">
                            <small>By Vote&Voice Team !!!</small>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- END: footer -->
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="script/jquery.page.js"></script>
    <script src="script/global.js"></script>
    
    <link rel="stylesheet" href="style/search.css">
</body>
</html>