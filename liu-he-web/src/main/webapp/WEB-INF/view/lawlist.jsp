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
			<h3><font size="15"><span style="color: #ff3300">勇往六合规律</span></font></h3>
			<div class="container">
				<table class="table table-bordered table-hover text-center">
					<c:forEach var="i" begin="1" end="20">
						<tr>
							<td>
								<a href="raw/${i}"><font size="4"><span>勇往六合规律${i}</span></font></a><br/>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td>
						    <font size="4"><span style="color: #ff3300">发财联系唯一QQ:2962504996</span></font>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>
</html>