<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>投诉接待管理展示</title>
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
    <legend>投诉接待详情</legend>
    <table class="table-form">
        <tr>
            <td class="tit">案件编号：</td>
            <td>
                    ${complaintMainDetail.complaintMain.caseNumber}
            </td>
            <td class="tit">来访日期：</td>
            <td>
                    ${complaintMainDetail.visitorDate}
            </td>
        </tr>
        <tr>
            <td class="tit">投诉方式：</td>
            <c:choose>
                <c:when test="${complaintMainDetail.complaintMode == '0'}">
                    <td>来电</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '1'}">
                    <td>来访</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '2'}">
                    <td>来信</td>
                </c:when>
                <c:when test="${complaintMainDetail.complaintMode == '3'}">
                    <td>其他</td>
                </c:when>
            </c:choose>
            <td class="tit">是否重大：</td>
            <c:choose>
                <c:when test="${complaintMainDetail.isMajor == '1'}">
                    <td>是</td>
                </c:when>
                <c:otherwise>
                    <td>否</td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td class="tit">投诉纠纷概要：</td>
            <td colspan="3">
                    ${complaintMainDetail.summaryOfDisputes}
            </td>
        </tr>
        <tr>
            <td class="tit">诉求：</td>
            <td colspan="3">
                    ${complaintMainDetail.appeal}
            </td>
        </tr>
        <tr>
            <td class="tit">接待人员：</td>
            <td>
                    ${complaintMainDetail.jdEmployee.name}
            </td>

            <td class="tit">接待时间：</td>
            <td>
                    ${complaintMainDetail.receptionDate}
            </td>
        </tr>
        <tr>
            <td class="tit">下一环节处理人：</td>
            <td>
                    ${complaintMainDetail.linkEmployee.name}
            </td>
        </tr>
    </table>
</fieldset>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
