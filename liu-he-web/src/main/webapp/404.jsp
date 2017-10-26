<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="/base/hand.jsp" %>
<title>错误页面</title>
<link rel="stylesheet" href="css/notfound.css" type="text/css">
</head>

<body>
<div class="notFound">
	<div>前方在施工，<span id="time">5</span>秒后将回到<a id="addr" hidfocus="true" href="${pageContext.request.contextPath }/liuhe">首页</a></div>
</div>

<script type="text/javascript">
var el = document.getElementById("time");
var request = '/dfdfdf';
if(request.length > 0){
	var reg = /^\/(\w+)\//;
	var arr = request.match(reg);
	if(arr){
		switch(arr[1]){
			case "musicLibrary":
				request = "${pageContext.request.contextPath }/liuhe"; 
				break;
			case "mv":
				request = "${pageContext.request.contextPath }/liuhe"; 
				break;
			case "fm":
				request = "${pageContext.request.contextPath }/liuhe"; 
				break;
			case "musicrank2":
				request = "${pageContext.request.contextPath }/liuhe"; 
				break;
			case "musicrank3":
				request = "${pageContext.request.contextPath }/liuhe"; 
				break;
			case "musicrankk7":
				request = "${pageContext.request.contextPath }/liuhe";  
				break;
			case "musicranks":
				break;
			case "appstore":
				break;
			default:
				request = "${pageContext.request.contextPath }/liuhe";
		}
	} else {
		request = "${pageContext.request.contextPath }/liuhe";
	}
} else {
	request = "${pageContext.request.contextPath }/liuhe";
}
//alert(request)
document.getElementById("addr").href = request;

setInterval(function(){
	var val = Number(el.innerHTML);
	if(val <= 1){
		location.replace(request);	
	}else{
		el.innerHTML = val - 1;
	}
},1000)
</script>

</body></html>