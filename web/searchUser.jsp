<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page
	 import="vo.Dbuser"
	 import="java.util.ArrayList"
 %>
<%@include file="user.jsp" %>
<!-- 页面的主要内容 -->
            <div id="search-content" class="col-md-offset-1 col-md-8">
                <div class="content">
                    <form class="cf form-wrapper"  action="<%=response.encodeURL("searchUser") %>"  method="get">
                        <input type="text" name="name" placeholder="搜索平台用户..." required>
                        <button type="submit">Search</button>
                    </form>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><%=user.getU_id() %>查询结果</h3>
                        </div>
                        <ul class="list-group">
                        <%
                        	//System.out.println("mark0");
                            ArrayList<Dbuser> userList = (ArrayList<Dbuser>)request.getAttribute("nameList");
                            //System.out.println(userList);
                            if(userList != null) {
                            	for(Dbuser suser : userList) {
                            %>
                        	<form action="<%=response.encodeURL("searchUser") %>" method="post">
                            	<li class="list-group-item">
                                	<%=suser.get_transU_name() %>
                                	<input type="hidden" name="add_fed_id" value="<%=suser.get_transU_id() %>"/>
                                	<button type="submit" aria-hidden="true" class="close"><span class="glyphicon glyphicon-plus"></span>FOLLOW</button>
                            	</li>
                            </form>
                        <%
                           	 }
                            }
                        %>
                    </ul>
                    </div>
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