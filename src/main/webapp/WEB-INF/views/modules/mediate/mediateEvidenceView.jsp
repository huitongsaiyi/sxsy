<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>质证调解管理</title>
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

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function addRow(list, idx, tpl, row){
            $(list).append(Mustache.render(tpl, {
                idx: idx, delBtn: true, row: row
            }));
            $(list+idx).find("select").each(function(){
                $(this).val($(this).attr("data-value"));
            });
            $(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
                var ss = $(this).attr("data-value").split(',');
                for (var i=0; i<ss.length; i++){
                    if($(this).val() == ss[i]){
                        $(this).attr("checked","checked");
                    }
                }
            });
        }

    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/mediate/mediateEvidence/">质证调解列表</a></li>
    <li class="active"><a href="${ctx}/mediate/mediateEvidence/form?id=${mediateEvidence.id}">质证调解<shiro:hasPermission
            name="mediate:mediateEvidence:edit">${not empty mediateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission
            name="mediate:mediateEvidence:edit">查看</shiro:lacksPermission></a></li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="mediateEvidence" action="${ctx}/mediate/mediateEvidence/save" method="post"
           class="form-horizontal">
    <form:hidden path="mediateEvidenceId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="complaintMainId"/>
    <form:hidden path="recordInfo.recordId"/>
    <form:hidden path="recordInfo.yrecordInfo.recordId"/>
    <form:hidden path="complaintMain.complaintMainId"/>
    <form:hidden path="complaintMain.act.taskId"/>
    <form:hidden path="complaintMain.act.taskName"/>
    <form:hidden path="complaintMain.act.taskDefKey"/>
    <form:hidden path="complaintMain.act.procInsId"/>
    <form:hidden path="complaintMain.act.procDefId"/>
    <form:hidden path="complaintMain.procInsId"/>
    <form:hidden id="flag" path="complaintMain.act.flag"/>
    <sys:message content="${message}"/>
    <legend>质证调解详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#mediation" data-toggle="tab">调解志</a>
        </li>
        <li>
            <a href="#meeting" data-toggle="tab">调解会议表</a>
        </li>
        <li>
            <a href="#recorded_patient" data-toggle="tab">调解会笔录（患方）</a>
        </li>
        <li>
            <a href="#recorded_doctor" data-toggle="tab">调解会笔录（医方）</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="mediation">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead>
                <tr>
                    <th class="hide"></th>
                    <th width="10">时间</th>
                    <th width="100">内容</th>
                    <th width="100">结果</th>
                    <shiro:hasPermission name="mediate:mediateEvidence:edit">
                        <th width="100">&nbsp;</th>
                    </shiro:hasPermission>
                </tr>
                </thead>
                <tbody id="mediateEvidenceList"></tbody>
                <shiro:hasPermission name="mediate:mediateEvidence:edit">
                    <tfoot>
                        <%--<tr><td colspan="7"><a href="javascript:" onclick="addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl);mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;" class="btn">新增</a></td></tr>--%>
                    </tfoot></shiro:hasPermission>
            </table>
            <script type="text/template" id="mediateEvidenceTpl">//<!--
						<tr id="mediateEvidenceList{{idx}}">
							<td class="hide">
								<input id="mediateEvidenceList{{idx}}_id" name="mediateEvidenceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mediateEvidenceList{{idx}}_mediateRecord" name="mediateEvidenceList[{{idx}}].mediateRecord" type="hidden" value="{{row.mediateRecord}}"/>
								<input id="mediateEvidenceList{{idx}}_relationId" name="mediateEvidenceList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="mediateEvidenceList{{idx}}_delFlag" name="mediateEvidenceList[{{idx}}].delFlag" type="hidden" value="{{row.delFlag}}"/>
							</td>
							<td >
								<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" maxlength="20" class="input-medium Wdate required"
                                   value="{{row.time}}"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});" disabled="disabled"/>

							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_content" name="mediateEvidenceList[{{idx}}].content" type="text" value="{{row.content}}" maxlength="100" class="required" readonly="readonly"/>
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_result" name="mediateEvidenceList[{{idx}}].result" type="text" value="{{row.result}}" maxlength="32" class="required" readonly="readonly"/>
							</td>
							<shiro:hasPermission name="mediate:mediateEvidence:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#mediateEvidenceList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
            </script>
        </div>
        <div class="tab-pane fade" id="meeting">
            <table class="table-form">
                <tr>
                    <td class="tit">时间:</td>
                    <td>
                            ${mediateEvidence.meetingTime}
                    </td>
                    <td class="tit">地点:</td>
                    <td>
                            ${mediateEvidence.meetingAddress}
                    </td>
                </tr>
                <tr>
                    <td class="tit">案件:</td>
                    <td>
                            ${mediateEvidence.caseInfoName}
                    </td>
                    <td class="tit">医方:</td>
                    <td class="controls">
                            ${mediateEvidence.doctorUser.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">医调委人员:</td>
                    <td>
                            ${mediateEvidence.ytwUser.name}
                    </td>
                    <td class="tit">患方</td>
                    <td>
                            ${mediateEvidence.patient}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_patient">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                            ${mediateEvidence.recordInfo.startTime}
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                            ${mediateEvidence.recordInfo.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                            ${mediateEvidence.recordInfo.recordAddress}
                    </td>
                    <td class="tit">事由</td>
                    <td>
                            ${mediateEvidence.recordInfo.cause}
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                            ${mediateEvidence.recordInfo.ytwHost.name}
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                            ${mediateEvidence.recordInfo.ytwNoteTaker.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                            ${mediateEvidence.recordInfo.patient}
                    </td>
                    <td class="tit">医方</td>
                    <td>
                            ${mediateEvidence.recordInfo.yfDoctor.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                            ${mediateEvidence.recordInfo.otherParticipants}
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                            ${mediateEvidence.recordInfo.recordContent}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_doctor">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.startTime}
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.recordAddress}
                    </td>
                    <td class="tit">事由</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.cause}
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.ytwHost.name}
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.ytwNoteTaker.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.patient}
                    </td>
                    <td class="tit">医方</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.yfDoctor.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                            ${mediateEvidence.recordInfo.yrecordInfo.otherParticipants}
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                            ${mediateEvidence.recordInfo.yrecordInfo.recordContent}
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr >
                    <input type="hidden" name="fjtype1" value="7">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        签到表：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                               value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files" uploadPath="/mediate/mediateEvidence/sign" selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype2" value="8">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        患方笔录：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
                               value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                            <%--<form:hidden id="files1" path="files1" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files" uploadPath="/mediate/mediateEvidence/huanRecord"
                                                                      selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype3" value="9">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        患方补充材料：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
                               value="${files3}"/>
                        <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                            <%--<form:hidden id="files2" path="files2" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files" uploadPath="/mediate/mediateEvidence/huanAdd"
                                                                      selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype4" value="10">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        医方笔录：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
                               value="${files4}"/>
                        <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                            <%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files" uploadPath="/mediate/mediateEvidence/yiRecord"
                                                                      selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype5" value="11">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        医方补充材料：
                    </td>
                    <td style="width: 450px; ">
                        <input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge"
                               value="${files5}"/>
                        <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                            <%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files" uploadPath="/mediate/mediateEvidence/yiAdd"
                                                                      selectMultiple="false"
                                                                      maxWidth="100" maxHeight="100" readonly="true"/></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit" width="140px">调解结果</td>
            <td>
                <c:choose>
                    <c:when test="${mediateEvidence.mediateResult=='1'}">
                        成功
                    </c:when>
                    <c:when test="${mediateEvidence.mediateResult=='2'}">
                        失败
                    </c:when>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="tit">会议总结</td>
            <td colspan="3">
                    ${mediateEvidence.summary}
            </td>
        </tr>
            <%--<tr>
                <td class="tit">处理人</td>
                <td>
                    <form:input path="handlePeople" htmlEscape="false" maxlength="20" class="input-xlarge "/>
                </td>
                <td class="tit">处理日期</td>
                <td>
                    <input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                           value="${mediateEvidence.handleTime}"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
                </td>
            </tr>--%>
        <tr>
            <td class="tit"><font color="red">*</font>下一环节处理人：</td>
            <td>
                    ${mediateEvidence.linkEmployee.name}
            </td>
        </tr>
    </table>
    <%--<div class="control-group">--%>
    <%--<label class="control-label"><font color="red">*</font>调解结果：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:select path="mediateResult" cssStyle="width: 180px">--%>
    <%--<form:option value="1">成功</form:option>--%>
    <%--<form:option value="2">失败</form:option>--%>
    <%--</form:select>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">会议总结：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:textarea path="summary" htmlEscape="false" rows="4" class="input-xxlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理人：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="handlePeople" htmlEscape="false" maxlength="10" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">处理日期：</label>--%>
    <%--<div class="controls">--%>
    <%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
    <%--value="<fmt:formatDate value="${mediateEvidence.handleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
    <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">下一处理环节：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <%--<div class="control-group">--%>
    <%--<label class="control-label">下环节处理人：</label>--%>
    <%--<div class="controls">--%>
    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>

</form:form>
<script type="text/javascript">
    var mediateEvidenceRowIdx = 0, mediateEvidenceTpl = $("#mediateEvidenceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(mediateEvidence.mediateEvidenceList)};
        for (var i=0; i<data.length; i++){
            addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl, data[i]);
            mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;
        }
    });
</script>
</body>
</html>