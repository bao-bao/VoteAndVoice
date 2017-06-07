<%@ page import="vo.ExDbquestionnaire" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

	<%@ include file="creatingQuestionnaireHeader.jsp" %>
<%
    ArrayList<ExDbquestionnaire> exQnList = (ArrayList<ExDbquestionnaire>)session.getAttribute("templateList");
%>
    <!-- 页面主要内容 -->
    <div id="main" class="container">
        <div class="row">
            <div class="col-md-offset-2 col-md-8">
                <div>
                    <span class="order">1</span>
                    <span class="order">2</span>
                    <span class="order">3</span>
                    <span class="order">4</span>
                    <span class="order"><a href="#">5</a></span>
                    <span class="order-title">创建成功</span>
                </div>
                <hr/>
                <div id="success">
                    <section id="work">
                        <row class="template-form-row">
                            <%for(ExDbquestionnaire exQn : exQnList){%>
                            <form action="QuestionnaireFromTemplate" method="post">
                                <row>
                                    <label class="col-md-8"><%=exQn.getQuestionnaire().get_transQn_title()%></label>
                                    <input type="submit" class="btn btn-success" value="开始">
                                </row>
                                <row><p><%=exQn.getQuestionnaire().get_transQn_des()%></p></row>
                                <input type="hidden" name="qn_id" value="<%=exQn.getQuestionnaire().get_transQn_id()%>">
                            </form>
                            <%}%>
                        </row>
                    </section>
                </div>
            </div>
        </div>
    </div>
    <!-- END: 页面主要内容 -->
	<%@ include file="creatingQuestionnaireFooter.jsp" %>
