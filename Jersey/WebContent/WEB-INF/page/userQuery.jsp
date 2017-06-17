<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <meta charset="utf-8">
  <title>layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="../layui/css/layui.css" media="all">
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../layui/layui.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

啊啊当前登录信息：姓名 ${annatationUser.name} --地址  ${annatationUser.address}
	
		<table class="table table-hover table-bordered" style="width: 80%;">
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
		
		
		
		
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
  <legend>默认表格</legend>
</fieldset>
 
<div class="layui-form">
  <table class="layui-table">
    <colgroup>
      <col width="50">
      <col width="150">
      <col width="150">
      <col width="200">
      <col>
    </colgroup>
    <thead>
      <tr>
        <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
        <th>人物</th>
        <th>民族</th>
        <th>出场时间</th>
        <th>格言</th>
      </tr> 
    </thead>
    <tbody>
      <tr>
        <td><input type="checkbox" name="" lay-skin="primary"></td>
        <td>贤心</td>
        <td>汉族</td>
        <td>1989-10-14</td>
        <td>人生似修行</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="" lay-skin="primary"></td>
        <td>张爱玲</td>
        <td>汉族</td>
        <td>1920-09-30</td>
        <td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="" lay-skin="primary"></td>
        <td>Helen Keller</td>
        <td>拉丁美裔</td>
        <td>1880-06-27</td>
        <td> Life is either a daring adventure or nothing.</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="" lay-skin="primary"></td>
        <td>岳飞</td>
        <td>汉族</td>
        <td>1103-北宋崇宁二年</td>
        <td>教科书再滥改，也抹不去“民族英雄”的事实</td>
      </tr>
      <tr>
        <td><input type="checkbox" name="" lay-skin="primary"></td>
        <td>孟子</td>
        <td>华夏族（汉族）</td>
        <td>公元前-372年</td>
        <td>猿强，则国强。国强，则猿更强！ </td>
      </tr>
    </tbody>
  </table>
</div>
	
</body>
</html>