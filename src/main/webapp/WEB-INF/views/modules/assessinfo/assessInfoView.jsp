<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>案件评价详情</title>
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
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#evaluate" data-toggle="tab">案件评价</a>
        </li>
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
    </ul>
    <fieldset>
        <legend>案件评价详情</legend>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="evaluate">
                <table class="table-form">
                    <tr>
                        <td class="tit">评价人</td>
                        <td>
                            ${assessInfo.user.name}
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">评价时间</td>
                        <td>
                            ${assessInfo.assessTime}
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">评价分数</td>
                        <td>
                            ${assessInfo.assessGrade}
                        </td>
                    </tr>
                    <tr>
                        <td class="tit">评价内容</td>
                        <td>
                            ${assessInfo.assessContent}
                        </td>
                    </tr>
                </table>
            </div>
            <div class="tab-pane fade" id="details">
                <ul id="myTab1" class="nav nav-tabs">
                    <li>
                        <a href="#complaintInFo" data-toggle="tab">投诉接待</a>
                    </li>
                    <li>
                        <a href="#reportRegistration" data-toggle="tab">报案登记</a>
                    </li>
                    <li>
                        <a href="#auditAcceptance" data-toggle="tab">审核受理</a>
                    </li>
                    <li>
                        <a href="#investigateEvidence" data-toggle="tab">调查取证</a>
                    </li>
                    <li>
                        <a href="#mediateEvidence" data-toggle="tab">质证调解</a>
                    </li>
                    <li>
                        <a href="#signAgreement" data-toggle="tab">签署协议</a>
                    </li>
                    <li>
                        <a href="#summaryInFo" data-toggle="tab">案件总结</a>
                    </li>
                </ul>
                <div class="tab-pane fade" id="complaintInFo">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="reportRegistration">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="auditAcceptance">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="investigateEvidence">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="mediateEvidence">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="signAgreement">
                    <table class="table-form">

                    </table>
                </div>
                <div class="tab-pane fade" id="summaryInFo">
                    <table class="table-form">

                    </table>
                </div>
            </div>
        </div>
        <act:histoicFlow procInsId="${assessInfo.complaintMain.procInsId}" />
</form:form>
</body>
</html>
