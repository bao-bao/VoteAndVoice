<%@ page language="java"  contentType="text/html; charset=utf-8"
pageEncoding="utf-8"  %>

<%@page
	import="vo.Dbuser" 
%>
<%
	Dbuser user = (Dbuser)session.getAttribute("loginUser");
%>
<!DOCTYPE HTML>
<html  lang="en">
<head>
<meta charset="utf-8">
<title>Voice&Vote</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- Animate.css -->
<link rel="stylesheet" href="style/animate.css" />
<!-- Icomoon Icon Fonts -->
<link rel="stylesheet" href="style/icomoon.css" />
<!-- Bootstrap -->
<link rel="stylesheet" href="style/bootstrap.css" />
<!-- self-defined css file -->
<link rel="stylesheet" href="style/style.css" />
<link rel="stylesheet" href="style/questionnaire.css" />
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
<script>
function login_btn() {
	$.post('/VoteAndVoice/LoginServlet',
	{
		login_name : $("#login_name").val(),
		login_pw : $("#login_pw").val()
	}, 

function(data) {
		var jobj = eval('(' + data + ')');
		if (jobj != null) {
			$("#login_errorMessage").text(jobj.errorMessage);
			if (jobj.errorMessage.indexOf("用户") >= 0) {
				$("#login_name").val("");
				$("#login_name").focus();
			}
			if (jobj.errorMessage.indexOf("密码") >= 0) {
				$("#login_pw").val("");
				$("#login_pw").focus();
			}
			if(jobj.errorMessage.indexOf("成功") >= 0){
				$('#login_errorMessage').attr("class", "successMessage");
				$('#login').modal('hide');
				location.reload();
			}
		}					
	});
}

function register_btn(){
	if($('#register_pw').val() != $('#register_pw_confirm').val()) {
		$('#register_errorMessage').text("*两次密码输入不匹配，请重新输入");
		$('#register_pw_confirm').val("");
		$('#register_pw_confirm').focus();
		return;
	}
	var pw = $('#register_pw').val();
	if(pw.length < 5){
		$('#register_errorMessage').text("*密码长度必须超过五个字符");
		$('#register_pw_confirm').val("");
		$('#register_pw_confirm').focus();
		return;
	}		
	if(pw.length > 100){
		$('#register_errorMessage').text("*密码长度太长");
		$('#register_pw').val("");
		$('#register_pw').focus();
		return;
	}	
	$.post(
			'/VoteAndVoice/RegisterServlet',
			{
				register_name: $('#register_name').val(),
				register_pw: $('#register_pw').val(),
				register_pw_confirm: $('#register_pw_confirm').val()
			},
			function(data){
				var jobj = eval('(' + data + ')');
				if(jobj != null){
					error = jobj.errorMessage;
					$('#register_errorMessage').text(error);
					if(error.indexOf("成功") >= 0){
						$('#register').modal('hide');
						$('#login_errorMessage').attr("class", "successMessage");
						$('#login_errorMessage').text("注册成功，请重新登陆");
						$('#login').modal('show');
						$('#login_errorMessage').attr("class", "errorMessage");
					}
					if(error.indexOf("抢注") >= 0){
						$('#register_name').val("");
						$('#register_name').focus();
					}
				}
			});
}
</script>
</head>
<body>
	<div class="box-wrap">
		<header role="banner" id="fh5co-header">
			<div class="container">
				<nav class="navbar navbar-default">
					<div class="row">
						<div class="col-md-3">
							<div class="fh5co-navbar-brand">
								<span class="logo"><a href="index.jsp">Vote&Voice</a></span>
							</div>
						</div>
						<div class="col-md-6">
							<ul class="nav text-center">
								<li><a href="index.jsp"><span>主页</span></a></li>
								<li><a href="fill.jsp">填问卷</a></li>
								<li class="active"><a href="/VoteAndVoice/CreateQuestionnaire_type">建问卷</a></li>
								<li><a href="searchQuestionnaire.jsp">搜问卷</a></li>
							</ul>
						</div>
						<div class="col-md-3">
							<ul class="social">
							<%if(user == null){ %>
								<li><a data-toggle="modal" data-target="#register"><span
										class="glyphicon glyphicon-log-in">注册</span></a></li>
								<li><a data-toggle="modal" data-target="#login"><span
										class="glyphicon glyphicon-user">登陆</span></a></li>
										<%}else{ %>
										 <li>
										 <a href="/VoteAndVoice/BasicInfo">
                                <img src="images/1.jpg" class="img-rounded user-profile"  alt="avatar" width="40" height="40" />
                                <span class="user-name"><%=user.get_transU_id() %></span>
                                </a>
                            </li>
                                <li><a href="LogOut"><span class="glyphicon glyphicon-log-in">退出</span></a></li>
                            <%} %>
							</ul>
						</div>
					</div>
				</nav>
			</div>
		</header>
		<!--END: header -->
		<hr />
		<!-- 登陆模态框 -->
		<div class="modal fade" id="login" tabindex="-1" role="dialog"
			aria-labelledby="register-title" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="register-title">登陆</h4>
						<span id="login_errorMessage" class="errorMessage"></span>
					</div>
					<div class="modal-body">
						<div class="form-horizontal" role="form" >
							<div class="form-group">
								<label for="login_name" class="col-sm-2 control-label">名字</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="login_name"
										name="login_name" placeholder="用户名" required>
								</div>
							</div>
							<div class="form-group">
								<label for="login_pw" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="login_pw"
										name="login_pw" placeholder="您的密码" required>
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default modal-btn"
									data-dimiss="modal">关闭</button>
								<button class="btn btn-primary" id="login_btn"
									onclick="login_btn();">登陆</button>
							</div>
						</div>
					</div>

				</div>
				<!--modal-content -->
			</div>
			<!-- modal -->
		</div>
		<!--END: 登陆模态框 -->
		<!-- 注册模态框 -->
		<div class="modal fade" id="register" tabindex="-1" role="dialog"
			aria-labelledby="login-title" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="login-title">注册</h4>
						<span id="register_errorMessage" class="errorMessage"></span>
					</div>
					<div class="modal-body">
						<div class="form-horizontal" role="form" >
							<div class="form-group">
								<label for="register_name" class="col-sm-2 control-label">名字</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="register_name"
										required name="register_name" placeholder="用户名">
								</div>
							</div>
							<div class="form-group">
								<label for="register_pw" class="col-sm-2 control-label">密码</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="register_pw"
										required name="register_pw" placeholder="您的密码">
								</div>
							</div>
							<div class="form-group">
								<label for="register_pw_confirm" class="col-sm-2 control-label">确认</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" required
										name="register_pw_confirm" id="register_pw_confirm"
										placeholder="确认您的密码">
								</div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default modal-btn"
									data-dimiss="modal">关闭</button>
								<button class="btn btn-primary" id="register_btn" onclick="register_btn();">注册</button>
							</div>
						</div>
					</div>
				</div>
				<!--modal-content -->
			</div>
			<!-- modal -->
		</div>
		<!-- END：注册模态框 -->
	