<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <!-- self-defined questionnaire.css -->
    <link rel="stylesheet" href="style/questionnaire.css" />
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
                            <li><a href="index.html"><span>主页</span></a></li>
                            <li><a href="#">填问卷</a></li>
                            <li class="active"><a href="#">建问卷</a></li>
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
            <div class="col-md-offset-1 col-md-2" id="questionType">
                <ul class="list-group">
                    <li class="list-group-item">题型选择</li>
                    <li class="list-group-item question-type" id="single">
                        <img src="images/single.png" alt="单选题" />
                        单选题
                    </li>
                    <li class="list-group-item question-type" id="multiple">
                        <img src="images/multiple.png" alt="多选题" />
                        多选题
                    </li>
                    <li class="list-group-item question-type" id="qanda">
                        <img src="images/qanda.png" alt="问答题" />
                        问答题
                    </li>
                </ul>
                <button type="submit" class="btn btn-default" id="preview">预览</button>
            </div>
            <div class="col-md-8" id="questionnaire">
                <div class="qnaire-out qnaire-main-title">
                    <div contenteditable="true" class="text-center qnaire">
                        问卷名字（请不要超过17个字）
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-des">
                    <div contenteditable="true" class="qnaire">
                        在这里输入问卷描述信息，方便受调查者了解你的调查范围和目的
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-single-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">1</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一个单选题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="radio" name="radio" />
                        <div contenteditable="true" class="qnaire qnaire-option">选项1</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="radio" name="radio"/>
                        <div contenteditable="true" class="qnaire qnaire-option">选项2</div>
                    </div>
                    <div class="add">
                        <span onclick="addOption(this, true);" title="增加新选项"></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="question-up" title="上移题目" onclick="questionUp(this, true);"></li>
                            <li class="question-down" title="下移题目" onclick="questionUp(this,false);"></li>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-multiple-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">1</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一个多选题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="checkbox" name="checkbox" />
                        <div contenteditable="true" class="qnaire qnaire-option">选项1</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="checkbox" name="checkbox"/>
                        <div contenteditable="true" class="qnaire qnaire-option">选项2</div>
                    </div>
                    <div class="add">
                        <span onclick="addOption(this, false);" title="增加新选项"></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="question-up" title="上移题目" onclick="questionUp(this, true);"></li>
                            <li class="question-down" title="下移题目" onclick="questionUp(this,false);"></li>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-qanda-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">1</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一问答题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <div contenteditable="true" class="qnaire qnaire-content"></div>
                    </div>
                    <div class="pse-add">
                        <span></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
            </div>
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
                            <small>由大尹帝国创建 !!!</small>
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