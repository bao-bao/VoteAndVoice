<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="indexHeader.jsp" %>
<%@page
	import="vo.Dbuser"
%>
<%
	Dbuser muser = (Dbuser)session.getAttribute("loginUser");
%>
<link rel="stylesheet"  href="style/user.css" />
    <!-- 页面主要内容 -->
    <div  id="main" class="container">
        <div class="row">
            <!-- 功能side bar -->
            <div class="col-md-offset-1 col-md-2" id="side">
                <div id="profie">
                    <img src="images/1.jpg" class="img-rounded"  alt="avatar" width="80" height="80"/>
                    <div id="user-name">
                        <span><%=muser.get_transU_id() %></span>
                    </div>
                </div>
                <hr/>
                <div id="function-list">
                    <div class="user-button" id="basic">
                    	<a>
                        <input type="checkbox">
                        <label>基本信息</label>
                        </a>
                    </div>
                    <div class="user-button" id="questionnaire">
                    	<a>
                        <input type="checkbox">
                        <label>我的问卷</label>
                        </a>
                    </div>
                    <div class="user-button" id="safe">
                    	<a>
                        <input type="checkbox" >
                        <label>安全设置</label>
                        </a>
                    </div>
                    <div class="user-button dropdown" id="friend">
                        <input id="friend-toggle" type="checkbox">
                        <label for="friend-toggle" class="animate">好友社区</label>
                        <ul class="animate">
                            <li class="animate"><a>最新动态</a></li>
                            <li class="animate"><a>我的好友</a></li>
                            <li class="animate"><a>我关注的</a></li>
                            <li class="animate"><a>关注我的</a></li>
                            <li class="animate"><a>搜索好友</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- END：功能side bar -->