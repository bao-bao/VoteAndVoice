<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String error = (String)request.getAttribute("errorMessage");
%>
<%@include file="indexHeader.jsp" %>
   <!--  页面主要内容 -->
    <div class="container">
    <%if(error != null){ %>
        <div class="error"><%=error %></div>
        <%} %>
        <!-- 搜索栏 -->
        <div class="row">
            <div class="col-md-12 col-sm-12 column">
                <div class="search">
                    <form action="<%=response.encodeURL("FillIndex") %>" name="searchform" id="searchform"  method="post" >
                        <input type="text" class="text gray" placeholder="输入您想填写的问卷关键字" name="key" ID="key_S" required/>
                        <a href="javascript:void(0);  del-keywords"></a>
                        <input type="submit" class="sbtn" value="搜索">
                    </form>
                </div>
            </div>
        </div>
        <!-- 主体 -->
        <div class="row">
                <div class="type-header">
                    <h4><a>分类搜索：</a></h4>
                </div>
                <div id="type-list">
                    <div class="col-md-3 col-sm-3 type-group">
                        <div class="user-button  type1">
                            <input type="checkbox">
                            <label>体育</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>健康</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>娱乐</label>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 type-group">
                        <div class="user-button type1">
                            <input type="checkbox">
                            <label>商业</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>政治</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>教育</label>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 type-group">
                        <div class="user-button  type1">
                            <input type="checkbox">
                            <label>文化</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>旅游</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>法律</label>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-3 type-group">
                        <div class="user-button  type1">
                            <input type="checkbox">
                            <label>生活</label>
                        </div>
                        <div class="user-button  type2">
                            <input type="checkbox">
                            <label>科技</label>
                        </div>
                        <div class="user-button  type3">
                            <input type="checkbox">
                            <label>艺术</label>
                        </div>
                    </div>
                    <!-- 
                    <div class="user-button  type1">
                        <input type="checkbox">
                        <label>其他</label>
                    </div>
                    -->
            </div>
        </div>

    </div>
    <form action="<%=response.encodeURL("SearchType") %>" method="post">
		<input type="hidden" name="keyType"  id="keyType"/>
		<button type="submit" id="typeClick" style="display:none"></button>
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
<script src="script/main.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="script/global.js"></script>
    <script type="text/javascript" src="script/jquery.page.js"></script>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="style/style.css"/>
    <link rel="stylesheet" href="style/search.css" />
</body>
</html>
</html>