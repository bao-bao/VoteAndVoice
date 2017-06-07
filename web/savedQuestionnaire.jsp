<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@
        page import = "dao.*"
             import = "vo.*"
             import = "java.util.*"
%>
<%
    ArrayList<ExDbquestionnaire> savedQuestionnaireList = (ArrayList<ExDbquestionnaire>)request.getAttribute("savedQuestionnaireList");
%>

<%@ include file="user.jsp" %>
<%
    String u_id = user.get_transU_id();
%>
<!-- 页面的主要内容 -->
<div id="q-content" class="col-md-offset-1 col-md-8">
    <div class="content">
        <%
            for (int i = 0; i < savedQuestionnaireList.size(); ++i)
            {%>
        <div class="panel panel-warning questionnaire-search-list">
            <div class="panel-heading">
                <h3 clas="panel-title"><%=savedQuestionnaireList.get(i).getQuestionnaire().get_transQn_title() %></h3>
            </div>
            <div class="panel-body">
                <form action="ContinueQuestionnaire" method="post">
                    <%=savedQuestionnaireList.get(i).get_transS_name() + ' ' + savedQuestionnaireList.get(i).getQuestionnaire().get_transQn_state() %>
                    <%
                        String state = savedQuestionnaireList.get(i).get_transQuestionnaire().get_transQn_state();
                        if(state.equals("未发布")) {
                    %>

                    <input type="hidden" name="qn_id" value="<%=savedQuestionnaireList.get(i).getQuestionnaire().getQn_id() %>" />
                    <input type="hidden" name="btntype" id="btntype<%=i%>" value="" />
                    <button type="submit" class="btn btn-success btn-lg" onclick="document.getElementById('btntype<%=i%>').value=0">继续编辑</button>
                    <button type="submit" class="btn btn-danger btn-lg" onclick="document.getElementById('btntype<%=i%>').value=1">删除</button>

                    <%
                        }
                    %>
                </form>
            </div>
            <div class="panel-footer"><%=savedQuestionnaireList.get(i).getQuestionnaire().get_transQn_starttime() %></div>
        </div>
        <%}
        %>
    </div>
</div>
<!-- END: 页面的主要内容 -->
</div>
</div>
<!-- END: 页面主要内容 -->
<%-- <form action="" method="post" id="fetchingQuestionnaire">
    <%
        session.setAttribute("questionnaire", savedQustionnaireList);
        session.setAttribute("u_id", u_id);
    %>
    <input type="submit"  id="fetch-questionnaire"/>
</form> --%>
<form action="" method="post" id="fetchingQuestionnaire">
    <%
        ArrayList<String> idList = new ArrayList<String>();
        if (savedQuestionnaireList != null) {
            for (int i = 0; i < savedQuestionnaireList.size(); ++i) {
                idList.add(savedQuestionnaireList.get(i).getQuestionnaire().get_transQn_id());
            }
        }
        session.setAttribute("questionnaire", idList);
    %>
    <input type="submit"  id="fetch-questionnaire" style="display:none"/>
</form>
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