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
<script language="JavaScript"> 
	function myrefresh() 
	{ 
		location.href="kaijiang";
		window.location.reload(); 
	} 
	setTimeout('myrefresh()',2500); //指定1秒刷新一次 
</script> 
<title>勇往六合</title>
</head>
<body>
	<div class="container">
	    <%--引用了信息 --%>
		<%@include file="/base/title.jsp"%>
		<!-- 大标题 -->
		<div class="jumbotron text-center">
			<h3><font size="15"><span style="color: #ff3300">[勇往六合]</span></font></h3>
			<div class="container">
				<table class="table table-bordered table-hover text-center">
					<td>
						 <div style="font-size:16px; background: #c8ffc8; line-height:30px; height:30px">
		                 <span id="q">${q}</span>期开奖结果:
		                 
		                 <span id="w1" class="m0" style="background-color: rgb(255, 0, 0); color: rgb(255, 255, 255);">
		                         <font id="m1" class="m0">${w1}</font>
		                 </span>&nbsp;
		                 
		                 <span id="w2" class="m0" style="background-color: rgb(0, 0, 255); color: rgb(255, 255, 255);">
		                         <font id="m2" class="m0">${w2}</font>
		                 </span>&nbsp;
		                 
		                  <span id="w3" class="m0" style="background-color: rgb(0, 153, 0); color: rgb(255, 255, 255);">
		                         <font id="m3" class="m0">${w3}</font>
		                  </span>&nbsp;
		                  
		                  <span id="w4" class="m0" style="background-color: rgb(0, 0, 255); color: rgb(255, 255, 255);">
		                          <font id="m4" class="m0">${w4}</font>
		                  </span>&nbsp;
		                  
		                  <span id="w5" class="m0" style="background-color: rgb(0, 0, 255); color: rgb(255, 255, 255);">
		                          <font id="m5" class="m0">${w5}</font>
		                  </span>&nbsp;
		                  
		                  <span id="w6" class="m0" style="background-color: rgb(255, 0, 0); color: rgb(255, 255, 255);">
		                          <font id="m6" class="m0">${w6}</font>
		                  </span>&nbsp;
		                  
		                                            特码：<span id="w7" class="m0" style="background-color: rgb(0, 153, 0); color: rgb(255, 255, 255);">
		                          <font id="s1">${w7}</font></span>
		                  <a href="kaijiang" style="margin-left:10px">手动刷新</a></div><br/><br/><br/><br/><br/><br/>
		                  
		                  
						   第<span id="nextQiShu" style="color:#FF0000">${n1}</span>期开奖时间<span id="nextMonth" style="color:#FF0000">${n2}</span>月<span id="nextDay" style="color:#FF0000">${n3}</span>日 星期<span id="nextWeek" style="color:#FF0000">${n4}</span> <span id="nextTime">${n5}</span>
					</td>
				</table>
			</div>
		</div>
	</div>
</body>
</html>