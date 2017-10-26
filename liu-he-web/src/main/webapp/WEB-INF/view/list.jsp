<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%--引用了头部信息 --%>
<%@include file="/base/hand.jsp"%>
<title>勇往六合</title>
</head>
<body>
	<div class="container">
		<%--引用了信息 --%>
		<%@include file="/base/title.jsp"%>
		<!-- 大标题 -->
		<div class="jumbotron text-center">
			<h3><font size="15"><span style="color: #ff3300">祝君中奖,年年发大财</span></font></h3>
			<div class="container">
				<table class="table table-bordered table-hover text-center">
					<c:if test="${not empty datalist }">
						<c:forEach items="${datalist }" var="data">
							<tr>
								${data }
							</tr>
							<tr>
								<td>
								    <font size="4"><span style="color: #ff3300">发财联系唯一QQ:2962504996</span></font>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty datalist }">
						<tr>
							<td>
							    <font size="4"><span style="color: #ff3300"><a href="liuhe">没有数据返回主页</a></span></font>
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
</html>