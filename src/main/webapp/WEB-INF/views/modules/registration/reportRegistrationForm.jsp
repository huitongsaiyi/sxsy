<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>报案信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function(form){
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function(error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
    <li><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>
    <li class="active"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">报案信息<shiro:hasPermission name="registration:reportRegistration:edit">${not empty reportRegistration.reportRegistrationId?'修改':'修改'}</shiro:hasPermission><shiro:lacksPermission name="registration:reportRegistration:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
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
    <ul id="myTab" class="nav nav-tabs">
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
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>报案人姓名：</td>
                    <td width="429px">
                        <form:input path="reportEmp" htmlEscape="false" maxlength="32" class="input-xlarge "/>
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患方联系方式：</td>
                    <td width="429px">
                        <form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-xlarge "/>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>与患者关系：</td>
                    <td width="476px">
                        <form:select path="patientRelation" class="input-medium">
                            <form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit" width="160px">报案日期：</td>
                    <td width="476px">
                        <input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                               value="${reportRegistration.reportTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="199px">患者姓名：</td>
                    <td width="522px">
                        <form:input path="complaintMain.patientName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
                    </td>
                    <td class="tit" width="180px">患者性别：</td>
                    <td >
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        <form:select path="complaintMain.patientSex" class="input-medium">
                            <form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit">患者年龄：</td>
                    <td >
                        <form:input path="complaintMain.patientAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
                    </td>
                    <td class="tit">患者身份证号：</td>
                    <td >
                        <form:input path="complaintMain.patientCard" htmlEscape="false" maxlength="20" class="input-xlarge "/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr >
                    <td class="tit" width="199px"><font color="red">*</font>涉及医院：</td>
                    <td width="522px">
                        <sys:treeselect id="involveHospital" name="complaintMain.involveHospital" value="${reportRegistration.complaintMain.involveHospital}" labelName="" labelValue="${reportRegistration.complaintMain.hospital.name}"
                                        title="机构" url="/sys/office/treeData?type=1&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="false"/>
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>医院等级：</td>
                    <td>
                        <form:select path="complaintMain.hospitalGrade" cssStyle="width: 180px">
                            <form:option value="1">特等</form:option>
                            <form:option value="2">甲等</form:option>
                            <form:option value="3">乙等</form:option>
                            <form:option value="4">丙等</form:option>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td >
                        <sys:treeselect id="involveDepartment" name="complaintMain.involveDepartment" value="${reportRegistration.complaintMain.involveDepartment}" labelName="" labelValue="${reportRegistration.complaintMain.department.name}"
                                        title="部门" url="/sys/office/treeData?type=2&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
                    </td>
                    <td class="tit" width="199px"><font color="red">*</font>医院级别：</td>
                    <td>
                        <form:select path="complaintMain.hospitalLevel" cssStyle="width: 180px">
                            <form:option value="1">一级</form:option>
                            <form:option value="2">二级</form:option>
                            <form:option value="3">三级</form:option>
                        </form:select>
                    </td>
                </tr>
                <tr >
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td class="controls">
                        <sys:treeselect id="involveEmployee" name="complaintMain.involveEmployee" value="${reportRegistration.complaintMain.involveEmployee}" labelName="" labelValue="${reportRegistration.complaintMain.employee.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=2" cssClass="" allowClear="true" notAllowSelectParent="true"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <input type="hidden"  name="fjtype" value="0">
            <td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">

                <input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"  value="${reportRegistration.files}"/>
                <form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                <sys:ckfinder input="files" type="images"  uploadPath="/reportReigsation/annex" selectMultiple="false" maxWidth="100" maxHeight="100"/>
            </td>
        </div>
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
                <form:input path="complaintMain.caseNumber" htmlEscape="false" maxlength="20" class="input-xlarge "/>
            </td>
            <td class="tit" width="160px">登记人员：</td>
            <td width="476px">
                <sys:treeselect id="registrationEmp" name="registrationEmp" value="${reportRegistration.registrationEmp}" labelName="" labelValue="${reportRegistration.djEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>

            </td>
        </tr>
        <tr>
            <td class="tit" width="180px">登记日期：</td>
            <td >
                    <%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
                <input name="registrationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                       value="${reportRegistration.registrationTime}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </td>
            <td class="tit" width="180px">纠纷发生时间：</td>
            <td >
                <input name="disputeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                       value="${reportRegistration.disputeTime}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>是否重大：</td>
            <td >
                <form:select path="isMajor" style='width:110px;text-align: center;'>
                    <form:option value="1">是</form:option>
                    <form:option value="0">否</form:option>
                </form:select>
            </td>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                <form:textarea path="summaryOfDisputes" htmlEscape="false" class="input-xlarge " style="margin: 0px; width: 938px; height: 125px;"/>
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷焦点：</td>
            <td colspan="3">
                <form:textarea path="focus" htmlEscape="false" class="input-xlarge " style="margin: 0px; width: 938px; height: 125px;"/>
            </td>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>患方要求：</td>
            <td colspan="3">
                <form:textarea path="patientAsk" htmlEscape="false" class="input-xlarge " style="margin: 0px; width: 938px; height: 125px;"/>
            </td>
        </tr>
        <tr >
            <td class="tit"><font color="red">*</font>下一处理环节：</td>
            <td >
                <form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
            </td>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td >
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${reportRegistration.nextLinkMan}" labelName="" labelValue="${reportRegistration.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true"/>
            </td>
        </tr>
    </table>
    <div class="form-actions">
        <shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="registration:reportRegistration:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <c:if test="${not empty reportRegistration.reportRegistrationId}">
        <act:histoicFlow procInsId="${reportRegistration.complaintMain.procInsId}" />
    </c:if>
</form:form>
</body>
</html>