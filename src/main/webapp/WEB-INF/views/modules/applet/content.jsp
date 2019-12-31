<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>内容设置</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#useTips").focus();
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
<form:form id="inputForm" modelAttribute="content"  action="${ctx}/applet/content/save" method="post" class="form-horizontal">
    <sys:message content="${message}"/>
    <ul class="nav nav-tabs">
        <li class="active"><a>内容管理</a></li>
    </ul>
    <br/>

    <div class="control-group">
        <label class="control-label">首页焦点图:</label>
        <div class="controls">
            <form:hidden path="focusPicture" htmlEscape="false" maxlength="255" class="input-xlarge"/>
            <sys:ckfinder input="focusPicture" type="images" uploadPath="/applet/content/" selectMultiple="false"/>
            <span class="help-inline" style="margin-top: 40px;">图片大小：680 × 270（像素）</span>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">是否开启使用说明:</label>
        <div class="controls">
            <form:radiobuttons path="isOpenPop" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
            <span class="help-inline">“是”代表在启动微信小程序时，弹出用户使用说明，“否”则表示关闭</span>
        </div>
        <label style=" display:block; margin-top:10px;"></label>
        <label class="control-label">启动使用说明:</label>
        <div class="controls">
            <form:textarea id="useTips" htmlEscape="true" path="useTips" rows="4" maxlength="200"
                      class="input-xxlarge"/>
            <sys:ckeditor replace="useTips" uploadPath="/applet/content"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">医疗调解纠纷咨询:</label>
        <div class="controls">
            <form:textarea id="consultTips" htmlEscape="true" path="consultTips" rows="4" maxlength="200"
                      class="input-xxlarge"/>
            <sys:ckeditor replace="consultTips" uploadPath="/applet/content"/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">经典案例提示:</label>
        <div class="controls">
            <form:textarea id="caseTips" htmlEscape="true" path="caseTips" rows="4" maxlength="200"
                      class="input-xxlarge"/>
            <sys:ckeditor replace="caseTips" uploadPath="/applet/content"/>
        </div>
    </div>
    <div class="form-actions">
        <shiro:hasPermission name="applet:content:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/></shiro:hasPermission>
    </div>
</form:form>
</body>
</html>
