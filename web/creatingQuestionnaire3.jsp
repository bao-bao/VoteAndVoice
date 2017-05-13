<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.*, vo.*, dao.*" %>
<%!
String current_u_id = null;
Dbuser current_user = new Dbuser();
%>
<%
session = request.getSession(true);
Dbuser loginUser = (Dbuser) session.getAttribute("loginUser");
if(loginUser != null) {
	current_u_id = loginUser.get_transU_id();
}
if(current_u_id == null) {
	response.getWriter().append("have not logined");
	return;
}
ArrayList<Dbuser> userList = new ArrayList<Dbuser>();
int message = DAOFactory.getUserInfoDAO().getUserInfo(current_u_id, userList);
if(message < 0) {
	response.getWriter().append("mysql exception");
	return;
}
else {
	current_user = userList.get(0);
%>

	<%@ include file="creatingQuestionnaireHeader.jsp" %>
    <!-- 页面主要内容 -->
    <div id="main" class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div>
                    <span class="order">1</span>
                    <span class="order">2</span>
                    <span class="order"><a href="#">3</a></span>
                    <span class="order-title">V币支付</span>
                    <span class="order">4</span>
                    <span class="order">5</span>
                </div>
                <hr/>
                <div>
                    <section>
                        <p>您现在有V币：<span class="coin">&nbsp;</span><%=current_user.get_transV_coin() %></p>
                        <p>创建此次私有问卷，将花费您<span class="coin">&nbsp;</span>20，是否继续？</p>
                        <div class="col-md-offset-4 col-md-8 next-button">
                            <form action="CreateQuestionnaire_owns" method="post">
                            <a href="creatingQuestionnaire2.jsp">
                                <button type="button" class="btn btn-primary btn-lg">
                                    <span class="glyphicon glyphicon-chevron-left"></span>返回
                                </button>
                            </a>
                            <a>
                                <button type="submit" class="btn btn-primary btn-lg">
                                    <span class="glyphicon glyphicon-chevron-right"></span>确认
                                </button>
                            </a>
                            </form>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
    <!-- END: 页面主要内容 -->
	<%@ include file="creatingQuestionnaireFooter.jsp" %>
	<%}%>
