<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="creatingQuestionnaireHeader.jsp" %>
    <%
    	String info = (String)request.getAttribute("isCreated");
    	if(info == null || info == "false"){
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
                <button class="btn btn-default" id="preview" onclick="onClickPreview()">预览</button>
            </div>
            <div class="col-md-8" id="questionnaire">
                <div class="qnaire-out qnaire-main-title">
                    <div contenteditable="true" class="text-center qnaire">
                        问卷名字（请不要超过17个字）
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-des">
                    <div contenteditable="true" class="qnaire">
                        在这里输入问卷描述信息，方便受调查者了解你的调查范围和目的
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-single-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">1</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一个单选题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="radio" name="radio" />
                        <div contenteditable="true" class="qnaire qnaire-option">选项1</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="radio" name="radio"/>
                        <div contenteditable="true" class="qnaire qnaire-option">选项2</div>
                    </div>
                    <div class="add">
                        <span onclick="addOption(this, true);" title="增加新选项"></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="question-up" title="上移题目" onclick="questionUp(this, true);"></li>
                            <li class="question-down" title="下移题目" onclick="questionUp(this,false);"></li>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-multiple-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">2</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一个多选题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="checkbox" name="checkbox" />
                        <div contenteditable="true" class="qnaire qnaire-option">选项1</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <input type="checkbox" name="checkbox"/>
                        <div contenteditable="true" class="qnaire qnaire-option">选项2</div>
                    </div>
                    <div class="add">
                        <span onclick="addOption(this, false);" title="增加新选项"></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="question-up" title="上移题目" onclick="questionUp(this, true);"></li>
                            <li class="question-down" title="下移题目" onclick="questionUp(this,false);"></li>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
                <div class="error-tips"></div>
                <div class="qnaire-out qnaire-qanda-question">
                    <div class="qnaire-out-inside">
                        <span class="qorder">3</span>
                        <div  contenteditable="true" class="qnaire qnaire-title">这是一问答题</div>
                    </div>
                    <div class="qnaire-out-inside">
                        <div contenteditable="true" class="qnaire qnaire-content"></div>
                    </div>
                    <div class="pse-add">
                        <span></span>
                    </div>
                    <div class="operate">
                        <ul>
                            <li class="set-logic" title="逻辑设置" onclick="setLogic(this)"></li>
                            <li class="question-copy" title="复制" onclick="questionCopy(this)"></li>
                            <li class="question-delete" title="删除" onclick="questionDelete(this)"></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END: 页面主要内容 -->
    
<%@ include file="creatingQuestionnaireFooter.jsp" %>
<%}
    else if(info == "true")
	request.getRequestDispatcher("questionnaire.jsp").forward(request, response);
	%>
