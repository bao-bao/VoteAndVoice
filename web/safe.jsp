<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ include file="user.jsp" %>

            <!-- 页面的主要内容 -->
            <div id="safe-content" class="col-md-offset-1 col-md-8">
                <div class="content">
                    <form class="form-horizontal" role="form" action="<%=response.encodeURL("Safe") %>" method="post">
                        <div class="form-group">
                            <label for="origin-pw" class="col-md-3 control-label">原密码：</label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="o_pwd" name="o_pwd" value = "">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="new-pw" class="col-md-3 control-label">新密码：</label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="n_pwd" name="n_pwd" value = "">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="new-pw-confirm" class="col-md-3 control-label">确认新密码：</label>
                            <div class="col-md-8">
                                <input type="password" class="form-control" id="nc_pwd" name="nc_pwd" value = "">
                            </div>
                        </div>
                        <div class="col-md-offset-6">
                            <button type="submit" class="btn btn-warning">确认</button>
                        </div>
                    </form>
                </div>
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