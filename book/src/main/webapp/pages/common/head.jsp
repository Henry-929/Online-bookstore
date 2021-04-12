<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2021/4/7
  Time: 3:41 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme()
            +"://"
            +request.getServerName()
            +":"
            +request.getServerPort()
            +request.getContextPath()
            +"/";

    pageContext.setAttribute("basePath", basePath);
%>

<!--	<base href="http://localhost:8080/book/pages/user/regist.jsp">-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery.min.js"></script>
