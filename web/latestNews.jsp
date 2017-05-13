<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vo.ExDbanswer" %>
<%@ page import="vo.ExDbquestionnaire" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ include file="user.jsp" %>
            <!-- 页面的主要内容 -->
            <div id="news-content" class="col-md-offset-1 col-md-8">
                <div class="content">
                    <%
                        String today = (String) request.getAttribute("today");
                        String yesterday = (String) request.getAttribute("yesterday");
                        List<String> dateList = (List<String>) request.getAttribute("dateList");
                        List<ArrayList<Object>> newsList = (List<ArrayList<Object>>) request.getAttribute("newsList");
                        for (int i = 0; i < dateList.size(); i++) {
                            if (newsList.get(i).size() == 0) {
                                continue;
                            }
                    %>

                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <%
                                if (today.equals(dateList.get(i))) {
                            %>
                            <h3 class="panel-title">今天</h3>
                            <%
                            } else if (yesterday.equals(dateList.get(i))) {
                            %>
                            <h3 class="panel-title">昨天</h3>
                            <%
                            } else {
                            %>
                            <h3 class="panel-title"><%=dateList.get(i)%>
                            </h3>
                            <%
                                }
                            %>
                        </div>
                        <%
                            for (Object news : newsList.get(i)) {
                                String show;
                                if (news instanceof ExDbanswer) {
                                    ExDbanswer answer = (ExDbanswer) news;
                                    show = answer.getU_name() + "在平台上完成了《" + answer.getQn_title() + "》问卷";
                                } else if (news instanceof ExDbquestionnaire) {
                                    ExDbquestionnaire questionnaire = (ExDbquestionnaire) news;
                                    show = questionnaire.getS_name() + "在平台上发布了" +
                                            questionnaire.getQuestionnaire().get_transQn_authority() + "问卷《" +
                                            questionnaire.getQuestionnaire().get_transQn_title() + "》";
                                } else {
                                    continue;
                                }
                        %>
                        <div class="panel-body">
                            <%=show%>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <%
                        }
                    %>
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