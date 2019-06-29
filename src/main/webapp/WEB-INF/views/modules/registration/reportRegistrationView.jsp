<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>报案信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //投诉接待详情
            var show='${show2}';
            if(show=='' || show== null){
                $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view&show2=y");
                var tsjd= document.getElementById("tsjdDetail");
                tsjd.height=document.documentElement.clientHeight-130;
                tsjd.width=document.documentElement.clientWidth;
            }
        });
    </script>
</head>
<body>
<%--<ul class="nav nav-tabs">--%>
    <%--<li><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>--%>
    <%--<li class="active"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">报案信息<shiro:hasPermission name="registration:reportRegistration:edit">${not empty reportRegistration.reportRegistrationId?'修改':'修改'}</shiro:hasPermission><shiro:lacksPermission name="registration:reportRegistration:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<form:form id="inputForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/save" method="post" class="form-horizontal">
    <form:hidden path="reportRegistrationId"/>
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
<fieldset>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#badj" data-toggle="tab">报案登记</a>
        </li>
        <c:if test="${empty show2}">
        <li id="li1">
            <a href="#details" data-toggle="tab">详情</a>
        </li>
        </c:if>
    </ul>
    <div id="myTabContent" class="tab-content">
    <div id="badj" class="tab-pane fade in active">
    <legend>报案登记详情</legend>
    <ul id="myTab1" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">报案人信息</a>
        </li>
        <li>
            <a href="#patient" data-toggle="tab">患者信息</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">涉及医院信息</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent1" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>报案人姓名：</td>
                    <td width="429px">
                        ${reportRegistration.reportEmp}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患方联系方式：</td>
                    <td width="429px">
                       ${reportRegistration.patientMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
                    <td width="476px">
                        <%--<form:select path="patientRelation" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                        <%--${reportRegistration.patientRelation}--%>
                                ${fns:getDictLabel(reportRegistration.patientRelation,'patient_relation', '无')}
                    </td>
                    <td class="tit" width="160px">报案日期：</td>
                    <td width="476px">
                            ${reportRegistration.reportTime}

                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="199px">患者姓名：</td>
                    <td width="522px">
                      ${reportRegistration.complaintMain.patientName}
                    </td>
                    <td class="tit" width="180px">患者性别：</td>
                    <td >
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <%--<form:select path="complaintMain.patientSex" class="input-medium">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                                    ${fns:getDictLabel(reportRegistration.complaintMain.patientSex,'sex', '无')}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患者年龄：</td>
                    <td >
                        ${reportRegistration.complaintMain.patientAge}
                    </td>
                    <td class="tit">患者身份证号：</td>
                    <td >
                        ${reportRegistration.complaintMain.patientCard}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr >
                    <td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
                    <td width="522px">
                        <%--<sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="" labelValue="${reportRegistration.complaintMain.hospital.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2"  dataMsgRequired="必填信息" cssClass="required"   allowClear="true" notAllowSelectParent="false"/>--%>
                                ${reportRegistration.complaintMain.hospital.name}
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>医院等级：</td>
                    <td>
                        ${reportRegistration.complaintMain.hospitalGrade}
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td >
                            ${reportRegistration.complaintMain.department.name}
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>医院级别：</td>
                    <td>
                        ${reportRegistration.complaintMain.hospitalLevel}
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td class="controls">
                            ${reportRegistration.complaintMain.employee.name}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">

                <td style="width: 450px;">

                        <input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
                        <%--<input type="hidden" id="images" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>--%>

                    <%--<sys:ckfinder input="images" type="images"  uploadPath="/reportReigsation/annex"  readonly="true"  maxWidth="1000" maxHeight="1000"/>--%>
                    <div style="margin-left: -100px;"><sys:ckfinder input="files" type="file"  uploadPath="/reportReigsation/annex"  readonly="true" selectMultiple="true" maxWidth="100" maxHeight="100"/></div>
                  </td>
            </table>
        </div>




    <%--<div class="control-group">--%>
    <%--<label class="control-label">与患者关系：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="patientRelation" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <table class="table-form">
        <tr>
            <td class="tit" width="160px">案件编号：</td>
            <td width="476px">
                ${reportRegistration.complaintMain.caseNumber}
            </td>
            <td class="tit" width="160px">登记人员：</td>
            <td width="476px">
                    ${reportRegistration.djEmployee.name}

            </td>
        </tr>
        <tr>
            <td class="tit" width="180px">登记日期：</td>
            <td >
                    ${reportRegistration.registrationTime}
            </td>
            <td class="tit" width="180px">纠纷发生时间：</td>
            <td >
                    ${reportRegistration.disputeTime}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>是否重大：</td>
            <c:if test="${'0'eq reportRegistration.isMajor}">
            <td >
                否
            </td>
            </c:if>
            <c:if test="${'1'eq reportRegistration.isMajor}">
                <td >
                    是
                </td>
            </c:if>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                ${reportRegistration.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷焦点：</td>
            <td colspan="3">
                ${reportRegistration.focus}
            </td>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>患方要求：</td>
            <td colspan="3">
                ${reportRegistration.patientAsk}
            </td>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>下一处理环节：</td>
            <td >
                ${reportRegistration.nextLink}
            </td>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td >
                    ${reportRegistration.linkEmployee.name}
            </td>
        </tr>
    </table>
    </div>
    </div>
        <div class="tab-pane fade" id="details">
            <ul id="iframe" class="nav nav-tabs">
                <li class="active">
                    <a href="#tsjd" data-toggle="tab">投诉接待</a>
                </li>
            </ul>
            <div id="iframeTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="tsjd">
                    <iframe id="tsjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <%--<shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>--%>
        <%--<shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>--%>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 500px;"/>
    </div>
    <act:histoicFlow procInsId="${reportRegistration.complaintMain.procInsId}" />
    </c:if>
</form:form>
</body>
</html>