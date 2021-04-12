<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%--静态包含 base标签，css样式，jQuery文件--%>
	<%@include file="/pages/common/head.jsp"%>
	
	<script type="text/javascript">
		$(function () {
			//给输入框绑定onChange内容发生改变事件
			$(".updateStatus").change(function () {
				var orderId = $(this).attr("orderId");
				var status = $(this).val();
				if (confirm("确定要将该订单号为【" +orderId+ "】的状态改为【" +status+ "】吗？")){
					location.href = "${basePath}orderServlet?action=sendOrder&status="+status+"&orderId="+orderId;
				}else {
					this.value = this.defaultValue;
				}
			});

		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

			<%--静态包含 manager管理模块的菜单--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
			</tr>

			<c:forEach items="${requestScope.orders}" var="order">
			<tr>
				<td>${order.createTime}</td>
				<td>${order.price}</td>
				<td><a href="#">查看详情</a></td>

				<c:if test="${order.status == 0}">
					<c:set var="status" value="未发货"/>
				</c:if>
				<c:if test="${order.status == 1}">
					<c:set var="status" value="已发货"/>
				</c:if>
				<c:if test="${order.status == 2}">
					<c:set var="status" value="已签收"/>
				</c:if>
				<td>
					<input class="updateStatus" style="width: 80px;"
						   orderId="${order.orderId}"
						   type="text" value="${status}">
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>


	<%--	静态包含 页脚内容--%>
	<%@include file="/pages/common/footer.jsp"%>
</body>
</html>