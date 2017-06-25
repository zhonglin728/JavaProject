<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/css/bootstrap.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="lib/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
    <script src="http://cdn.static.runoob.com/libs/angular.js/1.4.6/angular.min.js"></script>
    <script src="https://cdn.bootcss.com/angular-ui-bootstrap/2.2.0/ui-bootstrap-tpls.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/js/bootstrap.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.3/js/bootstrap.min.js"></script>
    <script src="layer/layer.js"></script>
    <link rel="stylesheet" href="layer\mobile\need\layer.css"  media="all">
    <script src="lib/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
    <link href="https://cdn.bootcss.com/ionic/1.3.2/css/ionic.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/ionic/1.3.2/js/ionic.bundle.min.js"></script>
    
    <title>Java后端WebSocket的实现</title>
</head>
<body>

<div style="margin: 0 auto;width: 90%;margin-top: 100px;border:1px solid #000;border-radius: 10px;background-color: #E4E4DA;">
	后端WebSocket地址：ws://localhost:8080/Jersey/websocket
    <hr/>
    <button id="open" class="btn btn-success" onclick="openWebsocket(this)">打开websocket</button>
    <hr/>
    <input id="text" type="text" class="form-control" placeholder="请输入消息" required="required"  style="width:30%;"/>
    <hr/>
    <button id="send" class="btn btn-success" onclick="send()">发送消息</button>
    <hr/>
    <button id="clo" class="btn btn-info" onclick="closeWebSocket(this)">关闭连接</button>
    <hr/>
    <button id="clo" class="btn btn-info" onclick="clearWebSocket()"><span class="glyphicon glyphicon-user">清除结果</span></button>
    <hr/>
    <button id="clo" class="button button-energized" onclick="pachong()"><span class="glyphicon glyphicon-user">爬虫</span></button>
    <hr/>
    
    <div class="panel panel-default" style="border-radius: 6px;">
	    <div class="panel-body">
	     	<div id="message" style="font-family: serif;color: red;position: absolute;height: 400px;background-color: #13AFAF;overflow:scroll;margin: 0 auto;text-shadow: 1px 1px 1px #FF0000;text-align: center;"></div>
	    </div>
	</div>
    
</div>

<div class="layui-progress layui-progress-big">
  <div class="layui-progress-bar" lay-percent="100%"></div>
</div>

<!-- 表格 -->
<div class="l-loading" style="display:block" id="pageloading"></div> 
<div id="maingrid4" style="margin:0; padding:0"></div>
<div style="display:none;"></div>


</body>

<script type="text/javascript">
	$(function (){//页面加载时 触发打开websocket的开关！
		$("#open").click();
	})
	$(document).keyup(function(event){
	  //send();
	});
	var websocket = null;
	var height = 10;
	function openWebsocket(th){
	    //判断当前浏览器是否支持WebSocket
	    if ('WebSocket' in window) {
	        websocket = new WebSocket("ws://localhost:8080/Jersey/websocket");
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
	    	height = height + height;
	    	$("#message").scrollTop($("#message").height()+height);
	    	console.log(event);
	        setMessageInnerHTML(event.data);
	    }

	    //连接关闭的回调方法
	    websocket.onclose = function () {
	    	alert("close!");
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
    
	//今日头条数据
    function pachong(){
    	$("#message").hide();
		for(var i=0;i<1;i++){
			$.ajax({
	    	    url:"http://www.toutiao.com/api/article/feed/?category=essay_joke&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&tadrequire=true&as=A155E87DDC4B37E&cp=58DC4B13972E4E1",
	    	    type:'GET', //GET
	    	    async:true,    //或false,是否异步
	    	    data:{},
	    	    timeout:50000,    //超时时间
	    	    dataType:'jsonp',    //返回的数据格式：json/xml/html/script/jsonp/text
	    	    jsonp:"callback",
	    	    jsonpCallback:"jsonpCallback",
	    	    beforeSend:function(xhr){
	    	       
	    	    },
	    	    success:function(data,textStatus,jqXHR){
	    	    	var obj = {};
	    	    	var arr = data.data.slice(0,5);
	    	    	if(arr.length>0){
	    	    	obj.data = arr;
	    	        obj.type = "query";
	    	        //websocket = new WebSocket("ws://localhost:8080/Jersey/websocket");
	    	    	websocket.send(JSON.stringify(obj));
	    	    	setMessageInnerHTML(JSON.stringify(data));
	    	    	var res = [];
	    	    	$.each(data.data,function(i,n){
	    	    		delete n.comments;
	    	    		delete n.display_time;
	    	    		delete n.online_time;
	    	    		delete n.type;
	    	    		res.push(n.group);
	    	    	})
	    	    	var jsonObj = {"Rows":res};
	    	    	console.log(jsonObj);
		    	    	$("#maingrid4").ligerGrid({
		                    checkbox: true,
		                    columns: [
		                    { display: '主键', name: 'group_id', align: 'left', width: 120 },
		                    { display: '栏目', name: 'status_desc', minWidth: 60 },
		                    { display: '内容', name: 'content', width: 1000,align:'center' }, 
		                    { display: '地址', name: 'share_url' }
		                    ], pageSize:10,
		                    enabledEdit: true, isScroll: false, checkbox:true,rownumbers:true,
		                    data:jsonObj,
		                    width: '90%'
		                });
		    	    	$("#pageloading").hide(); 
	    	    	}
	    	    },
	    	    error:function(xhr,textStatus,error){
	    	        console.log(error)
	    	    },
	    	    complete:function(){
	    	        
	    	    }
	    	})
		}
    	
    }
    
    function copy(obj){
        var newobj = {};
        for ( var attr in obj) {
            newobj[attr] = obj[attr];
        }
        return newobj;
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
    	var obj = {};
        var message = $("input[type='text']").val();
        obj.data = message;
        obj.type = "query";
        websocket.send(JSON.stringify(obj));
    }
	function clearWebSocket(){
		$("#message").html("");
	}
	
	//拖动DIV
	var MoveDiv = function(){};
	MoveDiv.Move=function(id)
	{

	    var o=document.getElementById(id);



	    o.onselectstart = function()

	    {

	        return(false);

	    };



	    o.onmousedown = function(e) {

	        e = e||window.event;

	        var x=e.layerX||e.offsetX;

	        var y=e.layerY||e.offsetY;

	        x=x-document.body.scrollLeft;

	        y=y-document.body.scrollTop;

	        document.onmousemove = function(e)

	        {

	            e=e||window.event;

	            o.style.left=(e.clientX-x)+"px";

	            o.style.top=(e.clientY-y)+"px";
				console.log(o.style.left);
	        };



	        document.onmouseup=function()
	        {

	            document.onmousemove=null;

	        };

	    };

	}
	MoveDiv.Move('message');
</script>
</html>