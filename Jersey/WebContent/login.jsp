<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
    <style type="text/css">
        html {
            width: 100%;
            height: 100%;
            overflow: hidden;
            font-style: sans-serif;
        }

        body {
            width: 100%;
            height: 100%;
            font-family: 'Open Sans', sans-serif;
            margin: 0;
            background-image: url(http://pic1.win4000.com/wallpaper/3/57df47a1d7704.jpg);
            background-repeat: no-repeat;
            background-size: 100%;
        }

        #login {
            position: absolute;
            top: 50%;
            left: 50%;
            margin: -150px 0 0 -150px;
            width: 300px;
            height: 300px;
        }

        #login h1 {
            color: #fff;
            text-shadow: 0 0 10px;
            letter-spacing: 1px;
            text-align: center;
        }

        h1 {
            font-size: 2em;
            margin: 0.67em 0;
        }

        input {
            width: 278px;
            height: 18px;
            margin-bottom: 10px;
            outline: none;
            padding: 10px;
            font-size: 13px;
            color: #fff;
            text-shadow: 1px 1px 1px;
            border-top: 1px solid #312E3D;
            border-left: 1px solid #312E3D;
            border-right: 1px solid #312E3D;
            border-bottom: 1px solid #56536A;
            border-radius: 4px;
            background-color: #2D2D3F;
            margin-top: 5px;
        }

        .but {
            width: 300px;
            min-height: 20px;
            display: block;
            background-color: #4a77d4;
            border: 1px solid #3762bc;
            color: #fff;
            padding: 9px 14px;
            font-size: 15px;
            line-height: normal;
            border-radius: 5px;
            margin: 0;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            $(".but").click(function () {
                var par = $("form").serializeArray()[0];
                console.log(par);
                debugger;
                if ($("[name='userName']").val() == "admin") {
                    layer.alert('温馨提示！', {
                        icon: 2,
                        content: "请不要输入管理员账号！！",
                        anim: 3
                    });
                    return false;
                }
                layer.alert('温馨提示！', {
                    icon: 6,
                    //content:"你输入的账号：["+$("[name='userName']").val()+"]--["+$("[name='password']").val()+"]有误！其实这是一个单机程序还没有连数据库！",
                    content: "你再输错我一刀捅死你！",
                    anim: 3
                }, function () {
                    $("form").submit();
                });

            })

            $("input").focus(function (even) {
                if ($(this).val() == "") {
                    var tmp = $(this).attr('placeholder');
                    layer.tips(tmp + '必填！', $(this), {
                        tips: [3, '#78BA32']
                    });
                    //$(".but").attr({"disabled":true});
                } else {
                    //$(".but").removeAttr("disabled");
                }

            })


        })
    </script>
</head>

<body>


<div id="login">
    <h1>测试Web项目！</h1>
    <div style="margin-left: 54px;margin-bottom: 10px;">
        <embed wmode="transparent" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.swf"
               quality="high" bgcolor="#ffffff" width="160" height="70" name="honehoneclock" align="middle"
               allowscriptaccess="always" type="application/x-shockwave-flash"
               pluginspage="http://www.macromedia.com/go/getflashplayer">
    </div>
    <form method="post" action="${pageContext.request.contextPath}/main.jsp">
        <input type="text" required="required" placeholder="用户名" name="userName"></input>
        <input type="password" required="required" placeholder="密码" name="password"></input>
        <button class="but" type="button">登录</button>
    </form>
</div>

</body>
</html>