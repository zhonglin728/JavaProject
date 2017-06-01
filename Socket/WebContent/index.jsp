<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular-ui-bootstrap/2.2.0/ui-bootstrap-tpls.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/js/bootstrap.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js"></script>
    <script src="layer/layer.js"></script>
    <title>Java后端WebSocket的实现</title>
</head>
<body>

<div style="margin: 0 auto;width: 90%;margin-top: 100px">
	后端WebSocket地址：ws://localhost:8080/Socket/websocket
    <hr/>
    <button id="open" class="btn btn-success" onclick="openWebsocket(this)">打开websocket</button>
    <hr/>
    <input id="text" type="text" class="form-control" placeholder="请输入消息" required="required"  style="width:30%;"/>
    <hr/>
    <button id="send" class="btn btn-success" onclick="send()">发送消息</button>
    <hr/>
    <button id="clo" class="btn btn-info" onclick="closeWebSocket(this)">关闭连接</button>
    <hr/>
    <div id="message" style="font-family: serif;color: red;"></div>
</div>

</body>

<script type="text/javascript">
	$(function (){
		$("#open").click();
	})
	
	var websocket = null;
	function openWebsocket(th){
	    //判断当前浏览器是否支持WebSocket
	    if ('WebSocket' in window) {
	        websocket = new WebSocket("ws://localhost:8080/Socket/websocket");
	    }
	    else {
	        alert('当前浏览器 Not support websocket')
	    }

	    //连接发生错误的回调方法
	    websocket.onerror = function () {
	        setMessageInnerHTML("WebSocket连接发生错误");
	    };

	    //连接成功建立的回调方法
	    websocket.onopen = function () {
	        setMessageInnerHTML("WebSocket连接成功");
	    }

	    //接收到消息的回调方法
	    websocket.onmessage = function (event) {
	    	debugger;
	    	console.log(event);
	        setMessageInnerHTML(event.data);
	    }

	    //连接关闭的回调方法
	    websocket.onclose = function () {
	        setMessageInnerHTML("WebSocket连接关闭");
	    }

	    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	    window.onbeforeunload = function () {
	        closeWebSocket();
	    }
	    layer.msg("打开成功！");
	    $(th).attr({"disabled":"disabled"});
	    $("#clo").removeAttr("disabled");
	    $("#message").html("");
	}
    

    
    
    
    
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
    	var msg = $("#message").html();
    	$("#message").html(msg+"<br/>"+innerHTML);
    }

    //关闭WebSocket连接
    function closeWebSocket(th) {
        websocket.close();
        layer.msg("关闭成功！");
        $(th).attr({"disabled":"disabled"});
        $("#open").removeAttr("disabled");
        $("#message").html("");
        
    }

    //发送消息
    function send() {
    	if($("#open").attr("disabled")!="disabled"){
    		layer.alert("请先打开websocket!",{
    			skin: 'layui-layer-molv',
    			closeBtn: 0,
    			icon: 5
    		});
    		return;
    	}
    	if($("input[type='text']").val()==""){
    		layer.alert("请输入消息在发送!",{
    			skin: 'layui-layer-molv',
    			closeBtn: 0,
    			icon: 5
    		});
    		return;
    	}
        var message = $("input[type='text']").val();
        websocket.send(message);
    }

</script>
</html>