<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
<style type="text/css">
		
		
</style>
<script type="text/javascript">
	$(function (){
		layui.use('element', function(){
			  var element = layui.element();
			  
			  //一些事件监听
			  element.on('tab(demo)', function(data){
			    console.log(data);
			  });
			});
		
		
	})
</script>
</head>

<body>

<div style="width: 100%;height: 50px;background-color: #393D49">
	<h1 style="text-align: center;padding: 10px;font-size: 25px;">后台管理系统！</h1>
</div>

<div id="main">
	<div id="leftMenu" style="float: left;">
		<ul class="layui-nav layui-nav-tree" lay-filter="demo" id="demo" style="height: 700px;">
		  <li class="layui-nav-item">
		    <a href="javascript:;">用户管理</a>
		    <dl class="layui-nav-child">
		      <dd><a href="${pageContext.request.contextPath}/user/query" target="mainIfrm">查看用户</a></dd>
		      <dd><a href="${pageContext.request.contextPath}/angular.html" target="mainIfrm">angular</a></dd>
		      <dd><a href="${pageContext.request.contextPath}/picture.html" target="mainIfrm">查看美女图片</a></dd>
		      <dd><a href="http://www.sina.com.cn/" target="mainIfrm">新浪</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item">
		    <a href="javascript:;">解决方案</a>
		    <dl class="layui-nav-child">
		      <dd><a href="">移动模块</a></dd>
		      <dd><a href="">后台模版</a></dd>
		      <dd><a href="">电商平台</a></dd>
		    </dl>
		  </li>
		  <li class="layui-nav-item"><a href="">云市场</a></li>
		  <li class="layui-nav-item"><a href="">社区</a></li>
		</ul>
	</div>
	
	<div id="rightMain" style="float: left;width: 85%;height:700px;">
		<iframe name="mainIfrm" style="width:100%;height:100%;">
		
		</iframe>
	</div>
</div>


</body>
</html>