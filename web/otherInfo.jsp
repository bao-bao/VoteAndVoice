<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@
	page import = "dao.*"
		 import = "vo.*"
		 import = "java.util.*"
 %>
 <%
	Dbuser OtherUser = (Dbuser)request.getAttribute("OtherUser");
 	int ifFollow = (int)request.getAttribute("ifFollow");
 %>

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>Voice&amp;Vote</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <!-- Animate.css -->
    <link rel="stylesheet" href="style/animate.css"/>
    <!-- Icomoon Icon Fonts -->
    <link rel="stylesheet" href="style/icomoon.css"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="style/bootstrap.css"/>
    <!-- self-defined main css file -->
    <link rel="stylesheet" href="style/style.css"/>
    <!-- self-defined user-template css file -->
    <link rel="stylesheet" href="style/user.css" />
</head>
<body>
<div class="box-wrap">
    <header role="banner" id="fh5co-header">
        <div class="container">
            <nav class="navbar navbar-default">
                <div class="row">
                    <div class="col-md-3">
                        <div class="fh5co-navbar-brand">
                            <span class="logo"><a href="index.html">Vote&Voice</a></span>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <ul class="nav text-center">
                            <li class="active"><a href="index.html"><span>主页</span></a></li>
                            <li><a href="#">填问卷</a></li>
                            <li><a href="#">建问卷</a></li>
                            <li><a href="#">搜问卷</a></li>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <ul class="social">
                            <li><a data-toggle="modal" data-target="#register"><span class="glyphicon glyphicon-log-in">注册</span></a>
                            </li>
                            <li><a data-toggle="modal" data-target="#login"><span
                                    class="glyphicon glyphicon-user">登陆</span></a></li>
                        </ul>
                    </div>
                </div>
            </nav>
        </div>
    </header>
    <!--END: header -->
    <hr/>
    <!-- 登陆注册模态框 -->
    <!-- 注册模态框 -->
    <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="register-title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="register-title">注册</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="login_name" class="col-sm-2 control-label">名字</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="login_name" placeholder="用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="login_pw" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="login_pw" placeholder="您的密码"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn" data-dimiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">登陆</button>
                </div>
            </div>
            <!--modal-content -->
        </div>
        <!-- modal -->
    </div>
    <!--END: 注册模态框 -->
    <!-- 登陆模态框 -->
    <div class="modal fade" id="register" tabindex="-1" role="dialog" aria-labelledby="login-title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="login-title">注册</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="register_name" class="col-sm-2 control-label">名字</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="register_name" placeholder="用户名"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="register_pw" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="register_pw" placeholder="您的密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="register_pw_confirm" class="col-sm-2 control-label">确认</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control" id="register_pw_confirm"
                                       placeholder="确认您的密码"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn" data-dimiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">注册</button>
                </div>
            </div>
            <!--modal-content -->
        </div>
        <!-- modal -->
    </div>
    <!-- END：登陆模态框 -->

    <!-- 页面主要内容 -->
    <div  id="main" class="container">
        <div class="row">
            <!-- 功能side bar -->
            <div class="col-md-offset-1 col-md-2" id="side">
                <div id="profie">
                    <img src="images/git.jpg" class="img-rounded"  alt="avatar" width="80" height="80"/>
                    <div id="user-name">
                        <span><%=OtherUser.get_transU_name() %></span>
                    </div>
                </div>
                <hr/>
            </div>
            <!-- END：功能side bar -->

            <!-- 页面的主要内容 -->
            <div id="info-content" class="col-md-offset-1 col-md-8">
                <div id="v-info">
                    <form action="" method="post">
                        <section class="v-item">
                            <label for="user-state">用户状态：</label>
                            <input id="user-state" name="user-state" type="text" value="<%=OtherUser.get_transU_validity() %>" disabled />
                        </section>
                        <section class="v-item">
                            <label for="v-degree">V等级：</label>
                            <input id="v-degree" name="fave" type="text" value="<%=OtherUser.get_transV_level() %>" disabled />
                        </section>
                        <section class="v-item">
                            <label for="v-experience">V经验：</label>
                            <input id="v-experience" name="v-experience" type="text" value="<%=OtherUser.get_transV_exp() %>" disabled />
                        </section>
                    </form>
                </div>
                <hr/>
                <form action="<%=response.encodeURL("OtherInfo") %>" method="post">
                <div id="b-info">
                        <section class="b-item">
                            <label for="user_name">姓名：</label>
                            <input id="user_name" name="user_name" type="text" value="<%=OtherUser.get_transU_name() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_sex">性别：</label>
                            <input id="user_sex" name="user_sex" type="text" value="<%=OtherUser.get_transU_gender() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_tel">电话：</label>
                            <input id="user_tel" name="user_tel" type="text" value="<%=OtherUser.get_transU_phone() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_nation">国家：</label>
                            <input id="user_nation" name="user_nation" type="text" value="<%=OtherUser.get_transU_ad_country() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_province">省份：</label>
                            <input id="user_province" name="user_province" type="text" value="<%=OtherUser.get_transU_ad_province() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_city">城市：</label>
                            <input id="user_city" name="user_city" type="text" disabled value="<%=OtherUser.get_transU_ad_city() %>" disabled />
                        </section>
                        <section class="b-item">
                        	<label for="user_street">住址：</label>
                        	<input id="user_street" name="user_street" type="text" disabled value="<%=OtherUser.get_transU_ad_street() %>" disabled />
                    	</section>
                        <p>
                            <%
                            String followway = "on";
                        	String bfollowway = "关注";
                        	if (ifFollow == 1)
                        	{
                        		followway = "off";
                        		bfollowway = "取关";
                        	}
                        	%>
                        	<input type="hidden" name="followway" value="<%=followway %>"/>
                        	<input type="hidden" name="followway_u_id" value="<%=OtherUser.get_transU_id() %>"/>
                        	<button class="btn btn-warning" type="submit"><%=bfollowway %></button>
                        </p>
                </div>
                </form>
            </div>
            <!-- END: 页面的主要内容 -->
        </div>
    </div>
    <!-- END: 页面主要内容 -->
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
</div>
<!-- END: box-wrap -->
<!-- jQuery -->
<script src="script/jquery.min.js"></script>
<!-- jQuery Easing -->
<script src="script/jquery.easing.1.3.js"></script>
<!-- Bootstrap -->
<script src="script/bootstrap.min.js"></script>
<!-- Waypoints -->
<script src="script/jquery.waypoints.min.js"></script>
<!-- Main JS (Do not remove) -->
<script src="script/main.js"></script>
</body>
</html>