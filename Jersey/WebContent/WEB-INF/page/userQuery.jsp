<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="utf-8">
<title>layui</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="../layui/css/layui.css" media="all">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="../layui/layui.js"></script>
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<script type="text/javascript">
    layui.use('form', function () {
        var $ = layui.jquery, form = layui.form();
        //全选
        form.on('checkbox(allChoose)', function (data) {
            var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
            child.each(function (index, item) {
                item.checked = data.elem.checked;
            });
            form.render('checkbox');
        });

    });

    //分页
    layui.use(['laypage', 'layer'], function () {
        var laypage = layui.laypage
            , layer = layui.layer;
        laypage({
            cont: 'demo1'
            , pages: 100 //总页数
            , groups: 5 //连续显示分页数
        });
    });
    var nums = 5; //每页出现的数据量
    //模拟渲染
    var render = function (data, curr) {
        var arr = []
            , thisData = data.concat().splice(curr * nums - nums, nums);
        layui.each(thisData, function (index, item) {
            arr.push('<li>' + item + '</li>');
        });
        return arr.join('');
    };
    $(function () {
        $("#icon tr td").each(function (i, n) {
            var check = '<span><input type="radio" name="images" id="" /></span>';
            $(n).find("span").append(check);
        })
    })
</script>
<body>

<!--  啊啊当前登录信息：姓名 ${annatationUser.name} --地址  ${annatationUser.address}-->

<form class="layui-form" action="" style="margin-top: 10px;">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入姓名题"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址 </label>
        <div class="layui-input-block">
            <input type="text" name="address" lay-verify="required" placeholder="请输入地址" autocomplete="off"
                   class="layui-input">
        </div>

        <div class="layui-form-item" style="margin-top: 10px;">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

    </div>
</form>

<div class="layui-form">
    <table class="layui-table" style="width: 100%;margin: 5px 5px 5px 5px;">
        <%--  <caption>${title}</caption>--%>
        <colgroup>
            <col width="50">
            <col width="250">
            <col width="250">
            <col width="250">
            <col width="250">
            <col width="250">
            <col width="250">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
            <th>姓名</th>
            <th>地址</th>
            <th>性别</th>
            <th>生日</th>
            <th>年龄</th>
            <th>部门</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="it">
            <tr>
                <td><input type="checkbox" name="" lay-skin="primary"></td>
                <td>${it.name}</td>
                <td>${it.address}</td>
                <td>${it.sex }</td>
                <td><fmt:formatDate value="${it.birthday }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${it.age }</td>
                <td>${it.dep }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div style="margin-left: 60%;">
        <div id="demo1"></div>
    </div>
    <%-- <c:forEach var="map" items="${mapping}">
        ${fn:split(map.key,'')[0]}
    </c:forEach> --%>
</div>

<button type="button" class="btn btn-primary" data-toggle="collapse"
        data-target="#icon">
    简单的可折叠组件
</button>

<table id="icon" class="table table-bordered table-hover collapse">
    <tr>
        <td><span class="glyphicon glyphicon-asterisk"> Asterik</span></td>
        <td><span class="glyphicon glyphicon-plus"> Plus</span></td>
        <td><span class="glyphicon glyphicon-euro"> Euro</span></td>
        <td><span class="glyphicon glyphicon-envelope"> Envelope</span></td>
        <td><span class="glyphicon glyphicon-pencil"> Pencil</span></td>
        <td><span class="glyphicon glyphicon-glass"> Glass</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-music"> Music</span></td>
        <td><span class="glyphicon glyphicon-search"> Search</span></td>
        <td><span class="glyphicon glyphicon-heart"> Heart</span></td>
        <td><span class="glyphicon glyphicon-star"> Star</span></td>
        <td><span class="glyphicon glyphicon-star-empty"> Empty</span></td>
        <td><span class="glyphicon glyphicon-user"> User</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-film"> Film</span></td>
        <td><span class="glyphicon glyphicon-th-large"> Th large</span></td>
        <td><span class="glyphicon glyphicon-th"> Th</span></td>
        <td><span class="glyphicon glyphicon-th-list"> List</span></td>
        <td><span class="glyphicon glyphicon-ok"> Okay</span></td>
        <td><span class="glyphicon glyphicon-remove"> Remove</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-zoom-in"> Zoom in</span></td>
        <td><span class="glyphicon glyphicon-zoom-out"> Zoom out</span></td>
        <td><span class="glyphicon glyphicon-off"> Off</span></td>
        <td><span class="glyphicon glyphicon-signal"> Signal</span></td>
        <td><span class="glyphicon glyphicon-cog"> Cog</span></td>
        <td><span class="glyphicon glyphicon-trash"> Trash</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-home"> Home</span></td>
        <td><span class="glyphicon glyphicon-file"> File</span></td>
        <td><span class="glyphicon glyphicon-time"> Time</span></td>
        <td><span class="glyphicon glyphicon-road"> Road</span></td>
        <td><span class="glyphicon glyphicon-download-alt"> Download alt</span></td>
        <td><span class="glyphicon glyphicon-download"> Download</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-upload"> Upload</span></td>
        <td><span class="glyphicon glyphicon-inbox"> Inbox</span></td>
        <td><span class="glyphicon glyphicon-play-circle"> Play circle</span></td>
        <td><span class="glyphicon glyphicon-repeat"> Repeat</span></td>
        <td><span class="glyphicon glyphicon-refresh"> Refresh</span></td>
        <td><span class="glyphicon glyphicon-list-alt"> List alt</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-lock"> Lock</span></td>
        <td><span class="glyphicon glyphicon-flag"> Flag</span></td>
        <td><span class="glyphicon glyphicon-headphones"> Headphones</span></td>
        <td><span class="glyphicon glyphicon-volume-off"> Volume-off</span></td>
        <td><span class="glyphicon glyphicon-volume-down"> Volume-down</span></td>
        <td><span class="glyphicon glyphicon-volume-up"> Volume-up</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-qrcode"> Qrcode</span></td>
        <td><span class="glyphicon glyphicon-barcode"> Barcode</span></td>
        <td><span class="glyphicon glyphicon-tag"> Tag</span></td>
        <td><span class="glyphicon glyphicon-tags"> Tags</span></td>
        <td><span class="glyphicon glyphicon-book"> Book</span></td>
        <td><span class="glyphicon glyphicon-bookmark"> Bookmark</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-print"> Print</span></td>
        <td><span class="glyphicon glyphicon-camera"> Camera</span></td>
        <td><span class="glyphicon glyphicon-font"> Font</span></td>
        <td><span class="glyphicon glyphicon-bold"> Bold</span></td>
        <td><span class="glyphicon glyphicon-italic"> Italic</span></td>
        <td><span class="glyphicon glyphicon-text-height"> Text-height</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-text-width"> Text-width</span></td>
        <td><span class="glyphicon glyphicon-align-left"> Align-left</span></td>
        <td><span class="glyphicon glyphicon-align-center"> Align-center</span></td>
        <td><span class="glyphicon glyphicon-align-right"> Align-right</span></td>
        <td><span class="glyphicon glyphicon-align-justify"> Align-justify</span></td>
        <td><span class="glyphicon glyphicon-list">   List</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-indent-left"> Indent-left</span></td>
        <td><span class="glyphicon glyphicon-indent-right"> Indent-right</span></td>
        <td><span class="glyphicon glyphicon-facetime-video"> Facetime-video</span></td>
        <td><span class="glyphicon glyphicon-picture">  Picture</span></td>
        <td><span class="glyphicon glyphicon-map-marker"> Map-marker</span></td>
        <td><span class="glyphicon glyphicon-adjust">   Adjust</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-tint"> Tint</span></td>
        <td><span class="glyphicon glyphicon-edit"> Edit</span></td>
        <td><span class="glyphicon glyphicon-share"> Share</span></td>
        <td><span class="glyphicon glyphicon-check">  Check</span></td>
        <td><span class="glyphicon glyphicon-move"> Move</span></td>
        <td><span class="glyphicon glyphicon-step-backward"> Step-backward</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-fast-backward"> Fast-backward</span></td>
        <td><span class="glyphicon glyphicon-backward"> Backward</span></td>
        <td><span class="glyphicon glyphicon-play"> Play</span></td>
        <td><span class="glyphicon glyphicon-pause"> Pause</span></td>
        <td><span class="glyphicon glyphicon-stop"> Stop</span></td>
        <td><span class="glyphicon glyphicon-forward"> Forward</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-fast-forward"> Fast-forward</span></td>
        <td><span class="glyphicon glyphicon-step-forward"> Step-forward</span></td>
        <td><span class="glyphicon glyphicon-eject"> Eject</span></td>
        <td><span class="glyphicon glyphicon-chevron-left"> Chevron-left</span></td>
        <td><span class="glyphicon glyphicon-chevron-right"> Chevron-right</span></td>
        <td><span class="glyphicon glyphicon-plus-sign">  Plus-sign</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-minus-sign"> Minus-sign</span></td>
        <td><span class="glyphicon glyphicon-remove-sign"> Remove-sign</span></td>
        <td><span class="glyphicon glyphicon-ok-sign"> Ok-sign</span></td>
        <td><span class="glyphicon glyphicon-question-sign"> Question-sign</span></td>
        <td><span class="glyphicon glyphicon-info-sign"> Info-sign</span></td>
        <td><span class="glyphicon glyphicon-screenshot">  Screenshot</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-remove-circle"> Remove-circle</span></td>
        <td><span class="glyphicon glyphicon-ok-circle"> Ok-circle</span></td>
        <td><span class="glyphicon glyphicon-ban-circle"> Ban-circle</span></td>
        <td><span class="glyphicon glyphicon-arrow-left"> Arrow-left</span></td>
        <td><span class="glyphicon glyphicon-arrow-right"> Arrow-right</span></td>
        <td><span class="glyphicon glyphicon-arrow-up">  Arrow-up</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-arrow-down"> Arrow-down</span></td>
        <td><span class="glyphicon glyphicon-share-alt"> Share-alt</span></td>
        <td><span class="glyphicon glyphicon-exclamation-sign"> Exclamation-sign</span></td>
        <td><span class="glyphicon glyphicon-gift"> Gift</span></td>
        <td><span class="glyphicon glyphicon-leaf"> Leaf</span></td>
        <td><span class="glyphicon glyphicon-fire"> Fire</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-eye-open"> Eye-open</span></td>
        <td><span class="glyphicon glyphicon-eye-close"> Eye-close</span></td>
        <td><span class="glyphicon glyphicon-warning-sign"> Warning-sign</span></td>
        <td><span class="glyphicon glyphicon-plane"> Plane</span></td>
        <td><span class="glyphicon glyphicon-calendar"> Calendar</span></td>
        <td><span class="glyphicon glyphicon-random"> Random</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-comment"> Comment</span></td>
        <td><span class="glyphicon glyphicon-magnet"> Magnet</span></td>
        <td><span class="glyphicon glyphicon-chevron-up"> Chevron-up</span></td>
        <td><span class="glyphicon glyphicon-chevron-down"> Chevron-down</span></td>
        <td><span class="glyphicon glyphicon-retweet"> Retweet</span></td>
        <td><span class="glyphicon glyphicon-shopping-cart"> Shopping-cart</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-folder-close"> Folder-close</span></td>
        <td><span class="glyphicon glyphicon-folder-open"> Folder-open</span></td>
        <td><span class="glyphicon glyphicon-resize-vertical"> Resize-vertical</span></td>
        <td><span class="glyphicon glyphicon-resize-horizontal"> Resize-horizontal</span></td>
        <td><span class="glyphicon glyphicon-hdd"> Hdd</span></td>
        <td><span class="glyphicon glyphicon-bullhorn"> Bullhorn</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-bell"> Bell</span></td>
        <td><span class="glyphicon glyphicon-certificate"> Certificate</span></td>
        <td><span class="glyphicon glyphicon-thumbs-up"> Thumbs-up</span></td>
        <td><span class="glyphicon glyphicon-thumbs-down"> Thumbs-down</span></td>
        <td><span class="glyphicon glyphicon-hand-right"> Hand-right</span></td>
        <td><span class="glyphicon glyphicon-hand-left"> Hand-left</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-hand-up"> Hand-up</span></td>
        <td><span class="glyphicon glyphicon-hand-down"> Hand-down</span></td>
        <td><span class="glyphicon glyphicon-circle-arrow-right"> Circle-arrow-right</span></td>
        <td><span class="glyphicon glyphicon-circle-arrow-left"> Circle-arrow-left</span></td>
        <td><span class="glyphicon glyphicon-circle-arrow-up"> Circle-arrow-up</span></td>
        <td><span class="glyphicon glyphicon-circle-arrow-down"> Circle-arrow-down</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-globe"> Globe</span></td>
        <td><span class="glyphicon glyphicon-wrench"> Wrench</span></td>
        <td><span class="glyphicon glyphicon-tasks"> Tasks</span></td>
        <td><span class="glyphicon glyphicon-filter"> Filter</span></td>
        <td><span class="glyphicon glyphicon-briefcase"> Briefcase</span></td>
        <td><span class="glyphicon glyphicon-fullscreen"> glyphicon-fullscreen</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-dashboard"> Dashboard</span></td>
        <td><span class="glyphicon glyphicon-paperclip"> Paperclip</span></td>
        <td><span class="glyphicon glyphicon-heart-empty"> Heart-empty</span></td>
        <td><span class="glyphicon glyphicon-link"> Link</span></td>
        <td><span class="glyphicon glyphicon-phone"> Phone</span></td>
        <td><span class="glyphicon glyphicon-pushpin"> Pushpin</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-usd"> Usd</span></td>
        <td><span class="glyphicon glyphicon-gbp"> GBP</span></td>
        <td><span class="glyphicon glyphicon-sort"> Sort</span></td>
        <td><span class="glyphicon glyphicon-sort-by-alphabet"> Sort-by-alphabet</span></td>
        <td><span class="glyphicon glyphicon-sort-by-alphabet-alt"> Sort-by-alphabet-alt</span></td>
        <td><span class="glyphicon glyphicon-sort-by-order"> Sort-by-order</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-sort-by-order-alt"> Sort-by-order-alt</span></td>
        <td><span class="glyphicon glyphicon-sort-by-attributes"> Sort-by-attributes</span></td>
        <td><span class="glyphicon glyphicon-sort-by-attributes-alt"> Sort-by-attributes-alt</span></td>
        <td><span class="glyphicon glyphicon-unchecked"> Unchecked</span></td>
        <td><span class="glyphicon glyphicon-expand"> Expand</span></td>
        <td><span class="glyphicon glyphicon-collapse-down"> Collapse-down</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-collapse-up"> Collapse-up</span></td>
        <td><span class="glyphicon glyphicon-log-in"> Log-in</span></td>
        <td><span class="glyphicon glyphicon-flash"> Flash</span></td>
        <td><span class="glyphicon glyphicon-log-out"> Log-out</span></td>
        <td><span class="glyphicon glyphicon-new-window"> New-window</span></td>
        <td><span class="glyphicon glyphicon-record"> Record</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-save"> Save</span></td>
        <td><span class="glyphicon glyphicon-open"> Open</span></td>
        <td><span class="glyphicon glyphicon-saved"> Saved</span></td>
        <td><span class="glyphicon glyphicon-import"> Import</span></td>
        <td><span class="glyphicon glyphicon-export"> Export</span></td>
        <td><span class="glyphicon glyphicon-send"> Send</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-floppy-disk"> Floppy-disk</span></td>
        <td><span class="glyphicon glyphicon-floppy-saved"> Floppy-saved</span></td>
        <td><span class="glyphicon glyphicon-floppy-remove"> Floppy-remove</span></td>
        <td><span class="glyphicon glyphicon-floppy-save"> Floppy-save</span></td>
        <td><span class="glyphicon glyphicon-floppy-open"> Floppy-open</span></td>
        <td><span class="glyphicon glyphicon-credit-card"> Credit-card</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-transfer"> Transfer</span></td>
        <td><span class="glyphicon glyphicon-cutlery"> Cutlery</span></td>
        <td><span class="glyphicon glyphicon-header"> Header</span></td>
        <td><span class="glyphicon glyphicon-compressed"> Compressed</span></td>
        <td><span class="glyphicon glyphicon-earphone"> Earphone</span></td>
        <td><span class="glyphicon glyphicon-phone-alt"> Phone-alt</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-tower"> Tower</span></td>
        <td><span class="glyphicon glyphicon-stats"> Stats</span></td>
        <td><span class="glyphicon glyphicon-sd-video"> Sd-video</span></td>
        <td><span class="glyphicon glyphicon-hd-video"> Hd-video</span></td>
        <td><span class="glyphicon glyphicon-subtitles"> Subtitles</span></td>
        <td><span class="glyphicon glyphicon-sound-stereo"> Sound-stereo</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-sound-dolby"> Sound-dolby</span></td>
        <td><span class="glyphicon glyphicon-sound-dolby"> Sound-dolby</span></td>
        <td><span class="glyphicon glyphicon-sound-5-1"> Sound-5-1</span></td>
        <td><span class="glyphicon glyphicon-sound-6-1"> Sound-6-1</span></td>
        <td><span class="glyphicon glyphicon-sound-7-1"> Sound-7-1</span></td>
        <td><span class="glyphicon glyphicon-copyright-mark"> Copyright-mark</span></td>
    </tr>
    <tr>
        <td><span class="glyphicon glyphicon-registration-mark"> Registration-mark</span></td>
        <td><span class="glyphicon glyphicon-cloud-download"> Cloud-download</span></td>
        <td><span class="glyphicon glyphicon-cloud-upload"> Cloud-upload</span></td>
        <td><span class="glyphicon glyphicon-tree-conifer"> Tree-conifer</span></td>
        <td><span class="glyphicon glyphicon-tree-deciduous"> Tree-deciduous</span></td>
        <td></td>
    </tr>
</table>

</body>
</html>