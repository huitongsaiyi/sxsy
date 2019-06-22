<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>评估鉴定审批管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                        error.appendTo(element.parent().parent());
                    } else {
                        error.insertAfter(element);
                    }
                }
            });
        });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/assessaudit/assessAudit/">评估鉴定审批列表</a></li>
    <li class="active"><a href="${ctx}/assessaudit/assessAudit/form?id=${assessAudit.id}">评估鉴定审批<shiro:hasPermission
            name="assessaudit:assessAudit:edit">${not empty assessAudit.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="assessaudit:assessAudit:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="assessAudit" action="${ctx}/assessaudit/assessAudit/save" method="post"
           class="form-horizontal">
    <form:hidden path="assessAuditId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <sys:message content="${message}"/>
    <legend>评估鉴定审批详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#patient" data-toggle="tab">申请表（患方）</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">申请表（医方）</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>

    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请人：</td>
                    <td width="429px">
                        ${assessAudit.patientApplyer}
                    </td>
                    <td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
                    <td width="476px">
                            ${fns:getDictLabel(assessAudit.patientRelation,'patient_relation', '无')}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>电话：</td>
                    <td width="429px">
                        ${assessAudit.patientMobile}
                    </td>
                    <td class="tit" width="199px">患者姓名：</td>
                    <td width="522px">
                        ${assessAudit.patientName}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px">性别：</td>
                    <td >
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <%--<form:select path="patientSex" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                                    ${fns:getDictLabel(assessAudit.patientSex,'sex', '无')}
                    </td>
                    <td class="tit">年龄：</td>
                    <td >
                        ${assessAudit.patientAge}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
                    <td width="522px">
                        <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${empty assessAudit.involveHospital ? assessAudit.complaintMain.involveHospital : assessAudit.involveHospital}" labelName="" labelValue="${empty assessAudit.involveHospital ? assessAudit.complaintMain.hospital.name : assessAudit.sjOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" isAll="true" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty assessAudit.involveHospital ? assessAudit.complaintMain.hospital.name : assessAudit.sjOffice.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px">
                        ${assessAudit.patientApplyMatter}
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请医院：</td>
                    <td width="429px">
                        <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.involveHospital : assessAudit.hospitalApply}" labelName="" labelValue="${empty assessAudit.hospitalApply ? assessAudit.complaintMain.hospital.name : assessAudit.sqOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" isAll="true" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty assessAudit.hospitalApply ? assessAudit.complaintMain.hospital.name : assessAudit.sqOffice.name}
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>代理人：</td>
                    <td>
                        ${assessAudit.agent}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>电话：</td>
                    <td class="controls">
                        ${assessAudit.hospitalMobile}
                    </td>
                    <td class="tit"><font color="red">*</font>医方姓名：</td>
                    <td class="controls">
                        ${assessAudit.hospitalName}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>性别：</td>
                    <td class="controls">
                            <%--<form:input path="hospitalSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <%--<form:select path="hospitalSex" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                                    ${fns:getDictLabel(assessAudit.hospitalSex,'sex', '无')}
                    </td>
                    <td class="tit"><font color="red">*</font>年龄：</td>
                    <td class="controls">
                        ${assessAudit.hospitalAge}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="199px"><font color="red">*</font>申请事项：</td>
                    <td width="522px">
                        ${assessAudit.hospitalApplyMatter}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方申请表：</td>
                    <input type="hidden"  name="fjtype1" value="12">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessaudit/assessAudit/Huan" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方申请表：</td>
                    <input type="hidden" name="fjtype2" value="13">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge" value="${files2}" />
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessaudit/assessAudit/Hospital" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="225px;">申请类型：</td>
            <td>
                    ${fns:getDictLabel(assessAudit.applyType,'assessmentAppraisal', '无')}
            </td>
            <td class="tit">地点：</td>
            <td>
                ${assessAudit.auditAddress}
            </td>
        </tr>
        <tr>
            <td class="tit">医学专家：</td>
            <td>
                ${assessAudit.medicalExpert}
            </td>
            <td class="tit">日期：</td>
            <td>
                    ${assessAudit.date}
            </td>
        </tr>
        <tr>
            <td class="tit">法律专家：</td>
            <td>
                ${assessAudit.legalExpert}
            </td>
            <td class="tit">下一环节处理人：</td>
            <td >
                    ${assessAudit.linkEmployee.name}
            </td>
        </tr>

    </table>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
</form:form>
</body>
</html>