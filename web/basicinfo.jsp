<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@	page 
         import = "dao.*"
		 import = "vo.*"
		 import = "java.util.*"
 %>
 <%
	Dbuser InfoUser = (Dbuser)request.getAttribute("InfoUser");
 %>

<%@ include file="user.jsp" %>

            <!-- 页面的主要内容 -->
            <div id="info-content" class="col-md-offset-1 col-md-8">
                <div id="v-info">
                    <form action="" method="post">
                        <section class="v-item">
                            <label for="user-state">用户状态：</label>
                            <input id="user-state" name="user-state" type="text" value="<%=InfoUser.get_transU_validity() %>" disabled />
                        </section>
                        <section class="v-item">
                            <label for="v-degree">V等级：</label>
                            <input id="v-degree" name="fave" type="text" value="<%=InfoUser.get_transV_level() %>" disabled />
                        </section>
                        <section class="v-item">
                            <label for="v-experience">V经验：</label>
                            <input id="v-experience" name="v-experience" type="text" value="<%=InfoUser.get_transV_exp() %>" disabled />
                        </section>
                        <secion class="v-item">
                            <label for="v-coin">V币余额：<span class="coin">&nbsp;</span></label>
                            <input id="v-coin" name="v-coin" type="text" value="<%=InfoUser.get_transV_coin() %>" disabled />
                        </secion>
                        <p>
                            <a data-toggle="modal" data-target="#charge"><span class="btn btn-success">充值</span></a>
                        </p>
                    </form>
                </div>
                <hr/>
                <div id="b-info">
                    <form action="<%=response.encodeURL("BasicInfo") %>" method="post">
                    	<input type="hidden" name="del_fing_id" value="<%=InfoUser.get_transU_id() %>"/>
                        <section class="b-item">
                            <label for="user_name">姓名：</label>
                            <input type="text" id="user_name" name="user_name" value="<%=InfoUser.get_transU_name() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_sex">性别：</label>
                            <input type="text" id="user_sex" name="user_sex" value="<%=InfoUser.get_transU_gender() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_tel">电话：</label>
                            <input type="text" id="user_tel" name="user_tel" value="<%=InfoUser.get_transU_phone() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_nation">国家：</label>
                            <input type="text" id="user_nation" name="user_nation" value="<%=InfoUser.get_transU_ad_country() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_province">省份：</label>
                            <input type="text" id="user_province" name="user_province" value="<%=InfoUser.get_transU_ad_province() %>" disabled />
                        </section>
                        <section class="b-item">
                            <label for="user_city">城市：</label>
                            <input type="text" id="user_city" name="user_city" value="<%=InfoUser.get_transU_ad_city() %>" disabled />
                        </section>
                        <section class="b-item">
                        	<label for="user_street">住址：</label>
                        	<input type="text" id="user_street" name="user_street" value="<%=InfoUser.get_transU_ad_street() %>" disabled />
                    	</section>
                        <p>
                        	<input type="hidden" name="submitWay" id="submitWay"/>
                            <button class="btn btn-warning" type="button" onclick="editUserInfo(true); return false">修改</button>
                        </p>
                    </form>
                </div>
            </div>
            <!-- END: 页面的主要内容 -->
        </div>
    </div>
    <!-- END: 页面主要内容 -->
    <!-- 充值模态框 -->
    <div class="modal fade" id="charge" tabindex="-1" role="dialog" aria-labelledby="charge-title" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="charge-title">注册</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="charge-value" class="col-sm-3 control-label">充值V币</label>
                            <div class="col-sm-7">
                                <input type="number" min="0" class="form-control" id="charge-value" value="10"
                                       name="charge-value"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-btn" data-dimiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">充值</button>
                </div>
            </div>
            <!--modal-content -->
        </div>
        <!-- modal -->
    </div>
    <!-- END:充值模态框 -->
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