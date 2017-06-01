<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

啊啊当前登录信息：姓名 ${annatationUser.name} --地址  ${annatationUser.address}
	
		<table class="table table-hover table-bordered">
			<caption>${title}</caption>
			<thead>
					<tr>
						<th>姓名</th>
						<th>地址</th>
					</tr>
				<c:forEach items="${list}" var="it">
					<tr class="warning">
						<td>${it.name}</td>
			            <td>${it.address}</td>
					</tr>
				</c:forEach>
			</thead>
		</table>
		<c:forEach var="map" items="${mapping}">
			${fn:split(map.key,'')[0]}
		</c:forEach>
	
</body>
</html>