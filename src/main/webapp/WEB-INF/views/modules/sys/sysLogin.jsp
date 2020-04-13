<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>${fns:getConfig('productName')} 登录</title>
    <meta name="decorator" content="blank"/>
    <style type="text/css">

        html, body, table {
            overflow-y: hidden !important;
            background-color: #f5f5f5;
            width: 100%;
            text-align: center;
            height: 100%;
            padding: 0;
            margin: 0;
            background: url('${ctxStatic}/images/new.png') no-repeat;
            background-size: cover;
            line-height: 0px !important;
        }

        .form-signin-heading {

            height: 120px;
            padding: 20px;
            font-family: Helvetica, Georgia, Arial, sans-serif, 微软雅黑;
            font-size: 60px;
            position: absolute;
            letter-spacing: 10px;
            left: 20%;
            bottom: 30%;
            background: linear-gradient(to top, #209cff, #68e0cf);
            -webkit-background-clip: text;
            color: transparent;
            text-align: center;
        }

        .form-signin {
            position: relative;
            text-align: left;
            width: 350px;
            height: 280px;
            float: right;
            padding: 25px 29px 29px;
            margin: 12.5% 8% 20px;
            background-color: rgba(23,50,97,0.4);
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .checkbox {
            margin-bottom: 10px;
            color: #0663a2;
        }

        .form-signin .input-label {
            width: 25%;
            float: left;
            font-size: 15px;
            line-height: 23px;
            color: #fff;
        }
        .form-signin .login-info {
            width: 100%;
            letter-spacing: 5px;
            font-size:25px;
            line-height: 40px;
            color: #fff;
            margin-bottom: 25px;
        }
        .form-signin .input-block-level {
            width: 75%;
            float: left;
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px;
            *width: 283px;
            *padding-bottom: 0;
            _padding: 7px 7px 9px 7px;
        }

        .form-signin .btn.btn-large {
            width: 100%;
            font-size: 16px;
        }

        .form-signin #themeSwitch {
            position: absolute;
            right: 15px;
            bottom: 10px;
        }

        .form-signin div.validateCode {
            padding-bottom: 15px;
        }

        .mid {
            vertical-align: middle;
        }

        .header {
            height: 80px;
            padding-top: 20px;
        }

        .alert {
            position: relative;
            width: 300px;
            margin: 0 auto;
            *padding-bottom: 0px;
        }

        label.error {
            background: none;
            width: 270px;
            font-weight: normal;
            color: inherit;
            margin: 0;
        }

        .footer {
            position: absolute;
            bottom: 40px;
            left: 0;
            right: 0;
            width: auto;
            color: #000000;
            text-align: center;
            font-size: 16px;
        }
        #pass-input{
            letter-spacing: 10px;
        }

        .logo-box{
            position: absolute;
            left: 8%;
            top: 33%;
            width: 39.9%;
            height: 26.2%;
            background-image: url("${ctxStatic}/images/logo.png");
            background-size: 100% 100%;
            background-repeat: no-repeat;
        }
        .logo-box img{
            width: 100%;
            height: 100%;
        }
        .box{
            width: 90%;
            height: 90%;
            margin: auto;
        }
        .bigbox{

        }

    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#loginForm").validate({
                rules: {
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "请填写用户名."},password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
            alert('未登录或登录超时。请重新登录，谢谢！');
            top.location = "${ctx}";
        }
    </script>
</head>
<body>
<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
<div class="header">
    <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
        <label id="loginError" class="error">${message}</label>
    </div>
    <div class="bigbox">
</div>
    <div class="logo-box">
    <%--<img id="input-logo" src="${ctxStatic}/images/logo.png">--%>
    </div>
<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
    <div class="box">
    <label class="login-info" for="username">欢迎登录</label>
    <label class="input-label" for="username" style="letter-spacing: 3px;">用户名:</label>
    <input type="text" id="username" name="username" class="input-block-level required" value="${username}">
    <label id="pass-input" class="input-label" for="password">密码:</label>
    <input type="password" id="password" name="password" class="input-block-level required">
    <c:if test="${isValidateCodeLogin}"><div class="validateCode">
        <label class="input-label mid" for="validateCode">验证码</label>
        <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
    </div></c:if><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
    <label for="rememberMe" title="下次不需要再登录" style="color:white; padding-left: 25%"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）</label>
    <input class="btn btn-large btn-primary" type="submit"  value="登 录" style="width: 100%; font-size:18px;letter-spacing: 5px;"/>&nbsp;&nbsp;
    </div>
    <div id="themeSwitch" class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">${fns:getDictLabel(cookie.theme.value,'theme','默认主题')}<b class="caret"></b></a>
        <ul class="dropdown-menu">
            <c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
        </ul>
        <!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
    </div>
</form>
</div>
<div class="footer">
    <%--${fns:getConfig('copyrightYear')}
            Copyright &copy; 2012-${fns:getConfig('copyrightYear')} <a href="${pageContext.request.contextPath}${fns:getFrontPath()}">${fns:getConfig('productName')}</a> - Powered By <a href="http://jeesite.com" target="_blank">JeeSite</a> ${fns:getConfig('version')}
    --%>
    Copyright &copy; 2018-2020 ${fns:getConfig('productName')} - Powered By Sayee ${fns:getConfig('version')}
</div>
<script src="${ctxStatic}/flash/zoom.min.js" type="text/javascript"></script>
</body>
</html>