<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>信息模板</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#actionNotice").focus();
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
<form:form id="inputForm" modelAttribute="information" action="${ctx}/applet/information/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <ul class="nav nav-tabs">
        <li><a href="${ctx}/applet/baseSetUp/form">基础配置</a></li>
        <li class="active"><a href="${ctx}/applet/information/form">信息模板</a></li>
    </ul>
    <fieldset>
        <legend style="font-size: 14px;">患者信息模板</legend>
        <div class="control-group">
            <label class="control-label">工单受理通知</label>
            <div class="controls">
                <form:input path="actionNotice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">调解员受理患者提交的工单后向患者发送信息</span>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">支付成功通知</label>
            <div class="controls">
                <form:input path="paymentNotice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">患者支付成功后向患者发送信息</span>
            </div>
        </div>
    </fieldset>

    <fieldset>
        <legend style="font-size: 14px;">调解员信息模板</legend>

        <div class="control-group">
            <label class="control-label">新工单提醒</label>
            <div class="controls">
                <form:input path="newActionNotice" htmlEscape="false" maxlength="200" class="input-xlarge"/>
                <span class="help-inline">患者提交工单后向管理员或调解员发送信息</span>
            </div>
        </div>
    </fieldset>

    <div class="form-actions">
        <shiro:hasPermission name="applet:information:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/></shiro:hasPermission>
    </div>
</form:form>


</body>
</html>
