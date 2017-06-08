<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="creatingQuestionnaireHeader.jsp" %>
    <%
        String info = (String)request.getAttribute("isCreated");
        if(info == null || info == "false"){
        String u_id = (String)request.getAttribute("u_id");
        String qn_id = (String)request.getAttribute("qn_id");
        String title = (String)request.getAttribute("title");
        String des = (String)request.getAttribute("des");
        String order = (String)request.getAttribute("order");
        String single = (String)request.getAttribute("single");
        String multiple = (String)request.getAttribute("multiple");
        String qanda = (String)request.getAttribute("qanda");
    %>
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
                <button class="btn btn-default" id="preview" onclick="onClickPreview()">发布</button>
                <button class="btn btn-default" id="save" onclick="onClickSave()">保存</button>
            </div>
            <div class="col-md-8" id="questionnaire">
                <div class="qnaire-out qnaire-main-title">
                    <div contenteditable="true" class="text-center qnaire">
                        <%=title %>
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-des">
                    <div contenteditable="true" class="qnaire">
                        <%=des %>
                    </div>
                </div>
                <div class="error-tips"></div>

            </div>

        </div>
    </div>
    <!-- END: 页面主要内容 -->
<script>
    var order = "<%=order%>";
    var single = "<%=single%>";
    var multiple = "<%=multiple%>";
    var qanda = "<%=qanda%>";
    reprocessQnaire(order, single, multiple, qanda);
    console.log("!!!!!");

</script>
<%@ include file="creatingQuestionnaireFooter.jsp" %>
<%}
else if(info == "true")
    request.getRequestDispatcher("questionnaire.jsp").forward(request, response);
%>

