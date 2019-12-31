<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>基础配置</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#appId").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/applet/configuration/form">基础配置</a></li>
    <li><a href="${ctx}/applet/information/form">信息模板</a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="baseSetUp" action="${ctx}/applet/baseSetUp/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">小程序AppID</label>
        <div class="controls">
            <form:input path="appId" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">小程序AppSecret</label>
        <div class="controls">
            <form:input path="appSecret" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">微信支付商户号</label>
        <div class="controls">
            <form:input path="merchantId" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">微信支付Api密钥</label>
        <div class="controls">
            <form:input path="apiKey" htmlEscape="false" maxlength="200" class="input-xlarge"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">微信支付<br />apiclient_cert.pem</label>
        <div class="controls">
            <form:textarea path="apiClientCert" rows="4" maxlength="200" class="input-xxlarge"></form:textarea>
            <span class="help-inline" style="display: block;">使用文本编辑器打开apiclient_cert.pem文件，将文件的全部内容复制进来</span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">微信支付<br />apiclient_key.pem</label>
        <div class="controls">
            <form:textarea path="apiClientKey" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"></form:textarea>
            <span class="help-inline" style="display: block;">使用文本编辑器打开apiclient_key.pem文件，将文件的全部内容复制进来</span>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="applet:baseSetUp:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/></shiro:hasPermission>
    </div>
</form:form>
</body>
</html>
