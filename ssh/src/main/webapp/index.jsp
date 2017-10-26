<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ssh整合</title>

</head>
<body>
	<form action="save" method="post">
		name:<input name="name" value="我是一个好人"/><br/>
		age:<input name="age" value="66"/><br/>
		<input type="submit" value="增加数据"/>
	</form>
	<hr/>
	<form action="delete" method="post">
		id:<input name="id" value="44"/><br/>
		<input  type="hidden" name="_method" value="delete"/>
		<input type="submit" value="删除数据"/>
	</form>
	<hr/>
	<form action="update" method="post">
		id:<input name="id" value="44"/><br/>
		name:<input name="name" value="我是一个好人"/><br/>
		age:<input name="age" value="66"/><br/>
		<input  type="hidden" name="_method" value="put"/>
		<input type="submit" value="更新数据"/>
	</form>
	<hr/>
	<a href="get">查询数据</a><br/>
</body>
</html>