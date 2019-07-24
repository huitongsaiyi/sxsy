<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var value='${complaintInfo.handleWay}';
            if(value==''){
                value = 0;
            }
            next(value);
        });
        function next(value) {

            if (value == 1) {
                $("<td id='shiftBody' class='tit'>转办科室:</td>").insertAfter("#handleWay");
                $("#shiftHandle").show();
                // document.getElementById("shiftHead").style.display="inline";
                // document.getElementById("shiftHandle").style.display="inline";
            } else {
                $("#shiftBody").remove();
                $("#shiftHandle").hide();
                // document.getElementById("shiftHead").style.display="none";
                // document.getElementById("shiftHandle").style.display="none";
            }
        }
    </script>
</head>
<body>
<form:form class="form-horizontal">
    <input type="hidden" id="flag" name="flag"/>
    <sys:message content="${message}"/>
<fieldset>
    <legend>医院投诉接待详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">来访者信息</a>
        </li>
        <li>
            <a href="#patient" data-toggle="tab">患者信息</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">涉及医院信息</a>
        </li>
    </ul>

    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>访客姓名：</td>
                    <td width="476px">
                            ${complaintInfo.visitorName}
                    </td>
                    <td class="tit" width="180px">访客电话：</td>
                    <td>
                            ${complaintInfo.visitorMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>与患者关系：</td>
                    <td>
                        ${fns:getDictLabel(complaintInfo.patientRelation,'patient_relation','未知')}
                    </td>
                    <td class="tit"><font color="red">*</font>来访人数：</td>
                    <td>
                            ${complaintInfo.visitorNumber}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patient">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>患者姓名：</td>
                    <td width="476px">
                            ${complaintInfo.patientName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>患者性别：</td>
                    <td>
                            <%--<form:input path="patientSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                        ${fns:getDictLabel(complaintInfo.patientSex,'sex','未知')}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄：</td>
                    <td>
                            ${complaintInfo.patientAge}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">
                <tr>
                    <td class="tit" width="160px"><font color="red">*</font>涉及医院：</td>
                    <td width="476px">
                        ${complaintInfo.hospitalName}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>涉及科室：</td>
                    <td>
                            <%--<sys:treeselect id='involveDepartment' name='involveDepartment' value='${complaintInfo.involveDepartment}' labelName='departmentName' labelValue='${complaintInfo.departmentName}' title='部门' url='/sys/office/treeData?type=2&officeType=2'--%>
                            <%--pid='involveHospital' isAll='true' cssClass='required' dataMsgRequired='请选择科室' allowClear='true' notAllowSelectParent='true' disabled='true'/>--%>
                                    ${fns:getDictLabel(complaintInfo.involveDepartment, 'department', '未知')}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>涉及人员：</td>
                    <td>
                        ${empty complaintInfo.employeeName?complaintInfo.involveEmployee:complaintInfo.employeeName}
                            <%--<sys:treeselect id="involveEmployee" name="involveEmployee" value="${complaintInfo.involveEmployee}" labelName="employeeName" labelValue="${complaintInfo.employeeName}"--%>
                            <%--title="用户" url="/sys/office/treeData?type=3&officeType=2" pid="involveDepartment" isAll="true" cssClass="required" dataMsgRequired="请选择人员" allowClear="true" notAllowSelectParent="true"/>--%>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="160px"><font color="red">*</font>案件编号：</td>
            <td width="476px">
                    ${complaintInfo.caseNumber}
            </td>
            <td class="tit" width="180px"><font color="red">*</font>来访日期：</td>
            <td>
                    <%--<form:input path="visitorDate" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
                    ${complaintInfo.visitorDate}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉方式：</td>
            <td>
                    <%--<form:input path="complaintMode" htmlEscape="false" maxlength="1" class="input-xlarge "/>--%>
                <c:choose>
                    <c:when test="${complaintInfo.complaintMode == '0'}">
                        来电
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '1'}">
                        来访
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '2'}">
                        来信
                    </c:when>
                    <c:when test="${complaintInfo.complaintMode == '3'}">
                        其他
                    </c:when>
                </c:choose>
            </td>
            <td class="tit"><font color="red">*</font>是否重大：</td>
            <td>
                <c:choose>
                    <c:when test="${complaintInfo.isMajor == '0'}">
                        否
                    </c:when>
                    <c:when test="${complaintInfo.isMajor == '1'}">
                        是
                    </c:when>
                </c:choose>
            </td>

        </tr>
        <tr>
            <td class="tit">投诉类别：</td>
            <td>
                ${complaintInfo.typeName}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>投诉纠纷概要：</td>
            <td colspan="3">
                    ${complaintInfo.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>诉求：</td>
            <td colspan="3">
                    ${complaintInfo.appeal}
            </td>
        </tr>
        <tr>
            <td class="tit">处理方式：</td>
            <td id="handleWay">
                <c:choose>
                    <c:when test="${complaintInfo.handleWay == '0'}">
                        当面处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '1'}">
                        转办处理
                    </c:when>
                    <c:when test="${complaintInfo.handleWay == '2'}">
                        转调解处理
                    </c:when>
                </c:choose>
            </td>
            <td id="shiftHandle">
                ${complaintInfo.shiftHandleName}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>处理经过</td>
            <td colspan="3">
                    ${complaintInfo.handlePass}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>处理结果</td>
            <td colspan="3">
                    ${complaintInfo.handleResult}
            </td>
        </tr>
        <tr>
            <td class="tit"><font color="red">*</font>接待人员：</td>
            <td>
                    <%--<form:input path="receptionEmployee" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                ${complaintInfo.employee.name}
            </td>

            <td class="tit"><font color="red">*</font>接待时间：</td>
            <td>
                    <%--<form:input path="receptionDate" htmlEscape="false" maxlength="20" class="input-xlarge "/>--%>
                ${complaintInfo.receptionDate}
            </td>
        </tr>

        <tr>
                <%--<td class="tit"><font color="red">*</font>是否进入医调委调解：</td>--%>
                <%--<td>--%>
                <%--<form:select id="isMediate" path="isMediate" style='width:110px;text-align: center;' >--%>
                <%--<form:option value="0">否</form:option>--%>
                <%--<form:option value="1">是</form:option>--%>
                <%--</form:select>--%>
                <%--</td>--%>
                <%--<td class="hidden"><font color="red">*</font>下一处理环节：</td>--%>
                <%--<td class="hidden">--%>
                <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <%--</td>--%>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                ${complaintInfo.link.name}
            </td>
        </tr>
    </table>
</fieldset>
    <c:if test="${empty show2}">
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </c:if>
</form:form>
</body>
</html>
