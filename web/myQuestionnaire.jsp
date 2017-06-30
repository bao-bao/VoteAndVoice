<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@
	page import = "dao.*"
		 import = "vo.*"
		 import = "java.util.*"
 %>
 <%
 	ArrayList<ExDbquestionnaire> myQuestionnaireList = (ArrayList<ExDbquestionnaire>)request.getAttribute("myQustionnaireList");
 %>

<%@ include file="user.jsp" %>
<%
	String u_id = user.get_transU_id();
%>
            <!-- 页面的主要内容 -->
            <div id="q-content" class="col-md-offset-1 col-md-8">
                <div class="content">
                    <script>
                        var copyInnerHTML = function (e) {
                            e = e.previousElementSibling;
                            var text = e.getAttribute("value");
                            var clipboardData = window.clipboardData;
                            if(clipboardData){
                                window.clipboardData.setData("Text", text);
                                alert("已将网址复制到剪贴板");
                            }
                            else{
                                e.select();
                                document.execCommand("Copy");
                                alert("已将网址复制到剪贴板");
                            }
                        }
                    </script>
                    <%
                    for (int i = 0; i < myQuestionnaireList.size(); ++i)
                    {%>
                    <div class="panel panel-warning ">
                        <div class="panel-heading">
                            <h3 clas="panel-title" style="margin:0">
                                <label class="questionnaire-search-list"><%=myQuestionnaireList.get(i).getQuestionnaire().get_transQn_title() %></label>
                                <input type="text" class="urltoshow" style="font-size: small;" readonly
                                value="<%= myQuestionnaireList.get(i).getQuestionnaire().get_transQn_id()%>"/>
                                <input type="button" class="btn btn-primary" onclick="copyInnerHTML(this);" value="复制链接" />
                            </h3>
                        </div>
                        <div class="panel-body">
                            <form action="EndQuestionnaire" method="post">
                            <%=myQuestionnaireList.get(i).get_transS_name() + ' ' + myQuestionnaireList.get(i).getQuestionnaire().get_transQn_state() %>
                            <%
                            String state = myQuestionnaireList.get(i).get_transQuestionnaire().get_transQn_state();
                            if(state.equals("进行中")) {
                            %>
                    		    <input type="hidden" name="qn_id" value="<%=myQuestionnaireList.get(i).getQuestionnaire().getQn_id() %>" />
                                <button type="submit" class="btn btn-warning btn-lg">结束问卷</button>
                            <%
                            }
                            %>
                            </form>
                        </div>
                        <div class="panel-footer">
                            <form action="DeleteQuestionnaire" method="post">
                                <%=myQuestionnaireList.get(i).getQuestionnaire().get_transQn_starttime() %>
                                <input type="hidden" name="qn_id" value="<%=myQuestionnaireList.get(i).getQuestionnaire().getQn_id() %>" />
                                <button type="submit" class="btn btn-danger btn-lg">删除问卷</button>
                            </form>
                        </div>
                    </div>
                    <%}
                    %>
                </div>
            </div>
            <!-- END: 页面的主要内容 -->
        </div>
    </div>
<script>
    var urlPrefix = window.location.href;
    var parts = urlPrefix.split("//");
    urlPrefix = parts[0] + "//" + parts[1].split("/")[0] + "/VoteAndVoice/AnswerByQnId?qn_id=";
    $(".urltoshow").each(function(index){
        $(this).val(urlPrefix + $(this).val());
    })
</script>
    <!-- END: 页面主要内容 -->
	<%-- <form action="" method="post" id="fetchingQuestionnaire">
		<%
			session.setAttribute("questionnaire", myQuestionnaireList);
			session.setAttribute("u_id", u_id);
		%>
		<input type="submit"  id="fetch-questionnaire"/>
	</form> --%>
	<form action="" method="post" id="fetchingQuestionnaire">
		<%
			ArrayList<String> idList = new ArrayList<String>();
			if (myQuestionnaireList != null) {
				for (int i = 0; i < myQuestionnaireList.size(); ++i) {
					idList.add(myQuestionnaireList.get(i).getQuestionnaire().get_transQn_id());
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