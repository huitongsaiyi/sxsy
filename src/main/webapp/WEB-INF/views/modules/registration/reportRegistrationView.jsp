<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>报案登记详情</title>
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
<form:form class="form-horizontal">
    <sys:message content="${message}"/>
    <fieldset>
        <legend>报案登记详情</legend>
        <table class="table-form">
            <tr>
                <td>案件编号：</td>
                <td>${reportRegistration.complaintMainId.caseNumber}</td>

                <td class="tit">报案人姓名：</td>
                <td>${reportRegistration.reportEmp}</td>

                <td class="tit">患者姓名：</td>
                <td>${reportRegistration.complaintMainId.patientName}</td>
            </tr>
            <tr>
                <td class="tit">患者性别：</td>
                <c:choose>
                    <c:when test="${reportRegistration.complaintMainId.patientSex== '1'}">
                        <td>男</td>
                    </c:when>
                    <c:otherwise>
                        <td>女</td>
                    </c:otherwise>
                </c:choose>

                <td class="tit">患者年龄：</td>
                <td>${reportRegistration.complaintMainId.patientAge}</td>

                <td class="tit">患者身份证：</td>
                <td>${reportRegistration.complaintMainId.patientCard}</td>
            </tr>
            <tr>
                <td class="tit">医院级别：</td>
                <td>${reportRegistration.complaintMainId.hospitalLevel}</td>

                <td class="tit">医院等级：</td>
                <td>${reportRegistration.complaintMainId.hospitalGrade}</td>

                <td class="tit">涉及医院：</td>
                <td>${reportRegistration.complaintMainId.involveHospital}</td>
            </tr>
            <tr>
                <td class="tit">涉及科室：</td>
                <td>${reportRegistration.complaintMainId.involveDepartment}</td>

                <td class="tit">设计人员</td>
                <td>${reportRegistration.complaintMainId.involveEmployee}</td>

                <td class="tit">患方联系方式：</td>
                <td>${reportRegistration.patientMobile}</td>
            </tr>
            <tr>
                <td class="tit">报案日期：</td>
                <td>${reportRegistration.reportTime}</td>

                <td class="tit">登记人员</td>
                <td>${reportRegistration.registrationEmp}</td>

                <td class="tit">登记日期</td>
                <td>${reportRegistration.registrationTime}</td>
            </tr>
            <tr>
                <td class="tit">与患者关系：</td>
                <c:choose>
                    <c:when test="${reportRegistration.patientRelation=='1'}">
                        <td>本人</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='2'}">
                        <td>夫妻</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='3'}">
                        <td>子女</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='4'}">
                        <td>父母</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='5'}">
                        <td>兄妹</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='6'}">
                        <td>亲属</td>
                    </c:when>
                    <c:when test="${reportRegistration.patientRelation=='7'}">
                        <td>其他</td>
                    </c:when>
                </c:choose>

                <td class="tit">纠纷发生时间：</td>
                <td>${reportRegistration.disputeTime}</td>

                <td class="tit">是否重大：</td>
                <c:choose>
                    <c:when test="${reportRegistration.isMajor=='1'}">
                        <td>是</td>
                    </c:when>
                    <c:otherwise>
                        <td>否</td>
                    </c:otherwise>
                </c:choose>
            </tr>
            <tr>
                <td class="tit">投诉纠纷概要：</td>
                <td colspan="3">${reportRegistration.summaryOfDisputes}</td>
            </tr>
            <tr>
                <td class="tit">投诉纠纷焦点</td>
                <td colspan="3">${reportRegistration.focus}</td>
            </tr>
            <tr>
                <td class="tit">患方要求</td>
                <td colspan="3">${reportRegistration.patientAsk}</td>
            </tr>
        </table>
    </fieldset>

    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
