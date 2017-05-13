<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
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
                        <span>尹天王</span>
                    </div>
                </div>
                <hr/>
                <div id="function-list">
                    <div class="user-button" id="basic">
							<a> 
								<input type="checkbox"> <label>基本信息</label>
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