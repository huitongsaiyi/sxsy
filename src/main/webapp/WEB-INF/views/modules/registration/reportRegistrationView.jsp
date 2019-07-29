<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>报案信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //投诉接待详情
            var show = '${show2}';
            if (show == '' || show == null) {
                $("#tsjdDetail").attr("src", "${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view&show2=y");
                var tsjd = document.getElementById("tsjdDetail");
                tsjd.height = document.documentElement.clientHeight - 130;
                tsjd.width = document.documentElement.clientWidth;
            }
        });
    </script>
</head>
<body>
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/registration/reportRegistration/">报案信息列表</a></li>--%>
<%--<li class="active"><a href="${ctx}/registration/reportRegistration/form?id=${reportRegistration.reportRegistrationId}">报案信息<shiro:hasPermission name="registration:reportRegistration:edit">${not empty reportRegistration.reportRegistrationId?'修改':'修改'}</shiro:hasPermission><shiro:lacksPermission name="registration:reportRegistration:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<form:form id="inputForm" modelAttribute="reportRegistration" action="${ctx}/registration/reportRegistration/save"
           method="post" class="form-horizontal">
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
    <form:hidden path="complaintMain.hospital.name"/>
    <form:hidden path="area.name"/>
    <form:hidden path="complaintMain.involveDepartment"/>
    <form:hidden path="complaintMain.involveEmployee"/>
    <form:hidden path="complaintMain.involveHospital"/>
    <form:hidden path="complaintMain.hospital.area.name"/>
    <form:hidden path="complaintMain.patientAge"/>
<sys:message content="${message}"/>
<fieldset>
<ul id="myTab2" class="nav nav-tabs">
    <li class="active">
        <a href="#shsl" data-toggle="tab">报案登记</a>
    </li>
    <c:if test="${empty show2}">
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
    </c:if>
</ul>
<legend>报案登记详情</legend>
<div id="myTabContent2" class="tab-content">
    <div id="shsl" class="tab-pane fade in active">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#visitor" data-toggle="tab">报案信息登记表</a>
            </li>
                <%--<li>--%>
                <%--<a href="#patient" data-toggle="tab">患者信息</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="#hospital" data-toggle="tab">涉及医院信息</a>--%>
                <%--</li>--%>
            <li>
                <a href="#annex" data-toggle="tab">附件</a>
            </li>
        </ul>
        <div id="myTabContent" class="tab-content">

            <div class="tab-pane fade in active" id="visitor">
                <p style="margin:0pt; text-align:center">
                    <span style="color:#333333; font-family:宋体; font-size:15pt; font-weight: bolder; font-weight:bolder; ">山西省医疗纠纷人民调解委员会</span>
                <p style="margin:0 auto ;width: 270px;">
                    <span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bolder;margin-left: 25px;">医疗纠纷报案信息登记表</span>
                </p>
                </p>
                <table cellspacing="0" cellpadding="0"
                       style="border-collapse:collapse; margin:0pt 9pt; width:80%; margin:auto;">
                    <tr style="height:39.6pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:4.5pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">医疗机构</span><br/><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">名称</span></p></td>
                        <td colspan="4"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:4.5pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:175.75pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">
                                    ${reportRegistration.complaintMain.hospital.name}
                            </span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:4.5pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:53.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">保单号</span></p></td>
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:4.5pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:255.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.policyNumber}
                            </span>
                            </p></td>
                    </tr>
                    <tr style="height:40.8pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">纠纷发生</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">时间</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:73.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:14pt; font-weight:bold">
                                    ${reportRegistration.disputeTime}
                            </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:37.05pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">机构</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">等级</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:43.9pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bolder">
                                <c:choose>
                                    <c:when test="${reportRegistration.complaintMain.hospitalGrade=='1'}">
                                        特等
                                    </c:when>
                                    <c:when test="${reportRegistration.complaintMain.hospitalGrade=='2'}">
                                        甲等
                                    </c:when>
                                    <c:when test="${reportRegistration.complaintMain.hospitalGrade=='3'}">
                                        乙等
                                    </c:when>
                                    <c:when test="${reportRegistration.complaintMain.hospitalGrade=='4'}">
                                        丙等
                                    </c:when>
                                </c:choose>

                             </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:53.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">所属</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">地市</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:55pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.hospital.area.name}
                            </span>
                            </p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:62.3pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">报案人姓名</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:116.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.reportEmp}
                            </span>
                            </p></td>
                    </tr>
                    <tr style="height:31pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">患者姓名</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:73.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.patientName}
                            </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:37.05pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">性别</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:43.9pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                ${fns:getDictLabel(reportRegistration.complaintMain.patientSex, 'sex', '未知')}
                                 <%--<form:select path="complaintMain.patientSex" class="input-medium" cssStyle="width: 80%;">--%>
                                     <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
                                 <%--</form:select>--%>
                            </span>
                            </p></td>
                        <td colspan="2"
                        style=" border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt;
                            border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt;
                            border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt;
                            border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt;
                            padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:53.15pt">
                        <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                style="font-family:宋体; font-size:12pt; font-weight:bold">年龄</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:55pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.patientAge}
                            </span>
                            </p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:62.3pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">身份</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">证号</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:116.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.patientCard}
                            </span>
                            </p></td>
                    </tr>
                    <tr style="height:29.9pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">出险时间</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:73.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.reportTime}
                            </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:37.05pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">科室</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:43.9pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.testTree}
                            </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:53.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">出险</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">医生</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:55pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${empty reportRegistration.complaintMain.employee.name?reportRegistration.complaintMain.involveEmployee:reportRegistration.complaintMain.employee.name}
                            </span>
                            </p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:62.3pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">是否重大纠纷</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:116.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                <c:choose>
                                    <c:when test="${reportRegistration.isMajor=='1'}">
                                        是
                                    </c:when>
                                    <c:otherwise>
                                        否
                                    </c:otherwise>
                                </c:choose>
                                <%--<form:select path="isMajor" style='width:110px;text-align: center;'>--%>
                                    <%--<form:option value="1">是</form:option>--%>
                                    <%--<form:option value="0">否</form:option>--%>
                                <%--</form:select>--%>
                            </span>
                            </p></td>
                    </tr>
                    <tr style="height:30.85pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">联系电话</span></p></td>
                        <td colspan="6"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:239.7pt">
                            <p style="margin:0pt; orphans:0; widows:0">
                                <span style="font-family:宋体; font-size:12pt; font-weight:bold">患方：</span>
                                <span style="font-family:宋体; font-size:12pt; font-weight:bold">
                                        ${reportRegistration.patientMobile}
                                </span>
                            </p>
                        </td>
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:255.1pt">
                            <p style="margin:0pt; orphans:0; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">医方：</span>
                                <span style="font-family:宋体; font-size:12pt; font-weight:bold">
                                        ${reportRegistration.doctorMobile}
                                </span>
                            </p></td>

                    </tr>
                    <tr style="height:269.25pt">
                        <td colspan="10"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:3.15pt; vertical-align:top; width:580.6pt">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:宋体; font-size:16pt; font-weight:bold">纠纷概要：</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0;width: 1035px">
                            <span style="font-family:'Times New Roman'; font-size:10.5pt; font-weight:bold ;width: 1053px;word-break: break-all;white-space: normal;">
                                    ${reportRegistration.summaryOfDisputes}
                                <hr/>
                            </span>
                            </p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:宋体; font-size:16pt; font-weight:bold">纠纷焦点：</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:10.5pt; font-weight:bold;;width: 1053px;word-break: break-all;white-space: normal;">
                                    ${reportRegistration.focus}
                                <hr/>
                            </span>
                            </p>
                            <p style="line-height:29pt; margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:宋体; font-size:16pt; font-weight:bold">患方要求：</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:10.5pt; font-weight:bold;width: 1053px;word-break: break-all;white-space: normal;">
                                    ${reportRegistration.patientAsk}
                            </span>
                            </p>


                        </td>
                    </tr>
                    <tr style="height:37.15pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">填表人</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">签名</span></p></td>
                        <td colspan="5"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:202.15pt;">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.djEmployee.name}
                            </span>
                            </p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:92.6pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">填表日期</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:189.3pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt;font-weight:bold;">
                                    ${reportRegistration.registrationTime}
                            </span></p></td>
                    </tr>
                    <tr style="height:25.5pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">报案号</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:100.85pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold">
                                    ${reportRegistration.complaintMain.caseNumber}
                            </span>
                            </p></td>
                        <td colspan="3"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:90.45pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">审核人</span></p></td>
                        <td colspan="2"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:92.6pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt; font-weight:bold;">
                                    ${reportRegistration.nextLink}
                            </span>
                            </p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:middle; width:62.3pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">审核日期</span></p></td>
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:middle; width:116.15pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0">
                            <span style="font-family:'Times New Roman'; font-size:12pt;font-weight:bold;">
                                    ${reportRegistration.patientRelation}
                            </span></p></td>
                    </tr>
                    <tr style="height:127pt">
                        <td style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:4.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:4.5pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:3.15pt; padding-right:5.03pt; vertical-align:middle; width:64.1pt">
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">备</span></p>
                            <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                                    style="font-family:宋体; font-size:12pt; font-weight:bold">注</span></p></td>
                        <td colspan="9"
                            style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:4.5pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:4.5pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:3.15pt; vertical-align:top; width:505.65pt">
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">1.</span><span
                                    style="font-family:宋体; font-size:12pt">表格中的案件编号由负责接案管理员填写；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">2.</span><span
                                    style="font-family:宋体; font-size:12pt">报案号、审核日期由负责向经纪</span><span
                                    style="font-family:'Times New Roman'; font-size:12pt">/</span><span
                                    style="font-family:宋体; font-size:12pt">保险公司报案的工作人员填写；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">3.</span><span
                                    style="font-family:宋体; font-size:12pt">纠纷发生时间是指患者首次向医疗机构提出索赔的时间；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">4.</span><span
                                    style="font-family:宋体; font-size:12pt">出险时间系患者发生人身损害的时间；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">5.</span><span
                                    style="font-family:宋体; font-size:12pt">报案人系填表人；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">6.</span><span
                                    style="font-family:宋体; font-size:12pt">保内、保外案件均使用此表填写，未参保的在保单号内填写“未参保”；</span></p>
                            <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                                    style="font-family:'Times New Roman'; font-size:12pt">7.</span><span
                                    style="font-family:宋体; font-size:12pt">审核人系负责向经纪</span><span
                                    style="font-family:'Times New Roman'; font-size:12pt">/</span><span
                                    style="font-family:宋体; font-size:12pt">保险公司报案的工作人员。</span></p></td>
                    </tr>
                    <tr style="height:0pt">
                        <td style="width:74.9pt; border:none"></td>
                        <td style="width:83.9pt; border:none"></td>
                        <td style="width:27.75pt; border:none"></td>
                        <td style="width:20.1pt; border:none"></td>
                        <td style="width:54.8pt; border:none"></td>
                        <td style="width:26.4pt; border:none"></td>
                        <td style="width:37.55pt; border:none"></td>
                        <td style="width:65.85pt; border:none"></td>
                        <td style="width:73.1pt; border:none"></td>
                        <td style="width:127.05pt; border:none"></td>
                    </tr>
                </table>
                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                        style="font-family:宋体; font-size:22pt; font-weight:bold">&#xa0;</span></p>
                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                        style="font-family:仿宋; font-size:16pt">&#xa0;</span>
                </p>
            </div>

            <div class="tab-pane fade" id="annex">
                <input type="hidden" name="fjtype" value="0">
                <td style="width: 450px;">
                    <input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"
                           value="${files}"/>
                    <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                    <sys:ckfinder input="files" type="files" uploadPath="/reportReigsation/annex" selectMultiple="true"
                                  maxWidth="100" maxHeight="100" readonly="true"/>
                </td>
            </div>
        </div>
        <table class="table-form">
            <tr>
                <td class="tit" width="220px;">下一环节处理人：</td>
                <td>
                        <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                        ${empty reportRegistration.linkEmployee.name?fns:getUser().name:reportRegistration.linkEmployee.name}
                </td>
            </tr>
        </table>

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
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 45%;"/>
        </div>
        <act:histoicFlow procInsId="${reportRegistration.complaintMain.procInsId}"/>
    </c:if>
</form:form>
</body>
</html>