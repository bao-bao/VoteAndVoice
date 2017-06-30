<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@
	page import = "dao.*"
		 import = "vo.*"
		 import = "java.util.*"
 %>
 <%
 	ArrayList<Dbuser> followingList = (ArrayList<Dbuser>)request.getAttribute("followingList");
 %>

<%@ include file="user.jsp" %>

            <!-- 页面的主要内容 -->
            <div id="i-content" class="col-md-offset-1 col-md-8">
                <div class="content">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title"><%=user.get_transU_id() %>关注的人</h3>
                        </div>
                        <ul class="list-group">
                        <%
                        for (int i = 0; i < followingList.size(); ++i)
                        {%>
                        	<form action="<%=response.encodeURL("IFollow") %>" method="post">
                            <li class="list-group-item">
                                <%=followingList.get(i).get_transU_name() %>#<%=followingList.get(i).get_transU_id()%>
                                <input type="hidden" name="del_fing_id" value="<%=followingList.get(i).get_transU_id() %>"/>
                                <button type="submit" aria-hidden="true" class="close">&times;UNFOLLOW</button>
                            </li>
                            </form>
                        <%}
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