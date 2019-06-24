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
                    var aa=$("#export").val();
                    if(aa!='meeting'){
                        loading('正在提交，请稍等...');
                    }
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
        function delRow(obj, prefix,key){
            var id = $(prefix+key);
            var delFlag = $(prefix+"_delFlag");
            if (id.val() == ""){
                $(obj).parent().parent().remove();
            }else if(delFlag.val() == "0"){
                delFlag.val("1");
                $(obj).html("&divide;").attr("title", "撤销删除");
                $(obj).parent().parent().addClass("error");
            }else if(delFlag.val() == "1"){
                delFlag.val("0");
                $(obj).html("&times;").attr("title", "删除");
                $(obj).parent().parent().removeClass("error");
            }
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
    <input type="hidden"  id="export" name="export"/>
    <sys:message content="${message}"/>
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
                <tr><td colspan="7"><a href="javascript:" onclick="addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl);mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;" class="btn">新增</a></td></tr>
                </tfoot></shiro:hasPermission>
            </table>
            <script type="text/template" id="mediateEvidenceTpl">//<!--
						<tr id="mediateEvidenceList{{idx}}">
							<td class="hide">
								<input id="mediateEvidenceList{{idx}}_id" name="mediateEvidenceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mediateEvidenceList{{idx}}_mediateRecord" name="mediateEvidenceList[{{idx}}].mediateRecord" type="hidden" value="{{row.mediateRecord}}"/>
								<input id="mediateEvidenceList{{idx}}_relationId" name="mediateEvidenceList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="mediateEvidenceList{{idx}}_delFlag" name="mediateEvidenceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td >
								<%--<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" value="{{row.time}}" maxlength="32" class="input-small "/>--%>
								<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text"  maxlength="20" class="input-medium Wdate required" "
                                    value="{{row.time}}"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>

							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_content" name="mediateEvidenceList[{{idx}}].content" type="text" value="{{row.content}}" maxlength="100" class="required" />
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_result" name="mediateEvidenceList[{{idx}}].result" type="text" value="{{row.result}}" maxlength="32" class="required" />
							</td>
							<shiro:hasPermission name="mediate:mediateEvidence:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#mediateEvidenceList{{idx}}','_mediateRecord')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
            </script>
        </div>
        <div class="tab-pane fade" id="meeting">
            <table class="table-form">
                <tr>
                    <td class="tit">时间:</td>
                    <td>
                        <input name="meetingTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${mediateEvidence.meetingTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                    </td>
                    <td class="tit">地点:</td>
                    <td>
                        <form:input path="meetingAddress" htmlEscape="false" maxlength="20" class="input-xlarge required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">案件:</td>
                    <td>
                        <form:input path="caseInfoName" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                    </td>
                    <td class="tit">医方:</td>
                    <td class="controls">
                        <sys:treeselect id="doctor" name="doctor"
                                        value="${mediateEvidence.doctor}" labelName=""
                                        labelValue="${mediateEvidence.doctorUser.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=2"
                                        allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">医调委人员:</td>
                    <td>
                        <sys:treeselect id="userId" name="userId"
                                        value="${mediateEvidence.userId}" labelName=""
                                        labelValue="${mediateEvidence.ytwUser.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                        allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                    <td class="tit">患方</td>
                    <td>
                        <form:input path="patient" htmlEscape="false" maxlength="20" class="input-xlarge required"
                                    value="${mediateEvidence.patient}"/>
                    </td>
                </tr>
                <td colspan="4" style="text-align: center;">
                    <input id="btnGenerate" class="btn btn-primary" type="submit" value="生成会议表" value="导 出" onclick="$('#export').val('meeting')">
                </td>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_patient">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                        <input name="recordInfo.startTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${mediateEvidence.recordInfo.startTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                        <input name="recordInfo.endTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${mediateEvidence.recordInfo.endTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                        <form:input path="recordInfo.recordAddress" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                    <td class="tit">事由</td>
                    <td>
                        <form:input path="recordInfo.cause" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                        <sys:treeselect id="h_host" name="recordInfo.host"
                                        value="${mediateEvidence.recordInfo.host}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.ytwHost.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                        allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                        <sys:treeselect id="h_noteTaker" name="recordInfo.noteTaker"
                                        value="${mediateEvidence.recordInfo.noteTaker}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.ytwNoteTaker.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                        allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                        <form:input path="recordInfo.patient" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
                    </td>
                    <td class="tit">医方</td>
                    <td>
                        <sys:treeselect id="h_doctor" name="recordInfo.doctor"
                                        value="${mediateEvidence.recordInfo.doctor}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.yfDoctor.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=2"
                                        allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                        <form:input path="recordInfo.otherParticipants" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                        <form:textarea path="recordInfo.recordContent" htmlEscape="false" class="input-xlarge required"
                                       style="margin: 0px; width: 938px; height: 125px;"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="recorded_doctor">
            <table class="table-form">
                <tr>
                    <td class="tit">开始时间</td>
                    <td>
                        <input name="recordInfo.yrecordInfo.startTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${mediateEvidence.recordInfo.yrecordInfo.startTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                    </td>
                    <td class="tit">结束时间</td>
                    <td>
                        <input name="recordInfo.yrecordInfo.endTime" type="text" readonly="readonly" maxlength="20"
                               class="input-medium Wdate required"
                               value="${mediateEvidence.recordInfo.yrecordInfo.endTime}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">地点</td>
                    <td>
                        <form:input path="recordInfo.yrecordInfo.recordAddress" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                    <td class="tit">事由</td>
                    <td>
                        <form:input path="recordInfo.yrecordInfo.cause" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">主持人</td>
                    <td>
                        <sys:treeselect id="y_host" name="recordInfo.yrecordInfo.host"
                                        value="${mediateEvidence.recordInfo.yrecordInfo.host}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.yrecordInfo.ytwHost.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                        allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                    <td class="tit">记录人</td>
                    <td>
                        <sys:treeselect id="y_noteTaker" name="recordInfo.yrecordInfo.noteTaker"
                                        value="${mediateEvidence.recordInfo.yrecordInfo.noteTaker}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.yrecordInfo.ytwNoteTaker.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=1"
                                        allowClear="true" notAllowSelectParent="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">患方</td>
                    <td>
                        <form:input path="recordInfo.yrecordInfo.patient" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                    <td class="tit">医方</td>
                    <td>
                        <sys:treeselect id="y_doctor" name="recordInfo.yrecordInfo.doctor"
                                        value="${mediateEvidence.recordInfo.yrecordInfo.doctor}" labelName=""
                                        labelValue="${mediateEvidence.recordInfo.yrecordInfo.yfDoctor.name}"
                                        title="用户" url="/sys/office/treeData?type=3&officeType=2"
                                        allowClear="true" notAllowSelectParent="true" isAll="true" dataMsgRequired="必填信息" cssClass="required" />
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他参加人员</td>
                    <td>
                        <form:input path="recordInfo.yrecordInfo.otherParticipants" htmlEscape="false" maxlength="20"
                                    class="input-xlarge required"/>
                    </td>
                </tr>
                <tr>
                    <td class="tit">笔录内容</td>
                    <td colspan="3">
                        <form:textarea path="recordInfo.yrecordInfo.recordContent" htmlEscape="false"
                                       class="input-xlarge required"
                                       style="margin: 0px; width: 938px; height: 125px;"/>
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
                                                                  maxWidth="100" maxHeight="100"/></div>
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
                                                                  maxWidth="100" maxHeight="100"/></div>
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
                                                                  maxWidth="100" maxHeight="100"/></div>
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
                                                                  maxWidth="100" maxHeight="100"/></div>
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
                                                                  maxWidth="100" maxHeight="100"/></div>
                </td>
            </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <tr>
            <td class="tit">调解结果</td>
            <td>
                <form:select path="mediateResult" cssStyle="width: 180px">
                    <form:option value="1">成功</form:option>
                    <form:option value="2">失败</form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td class="tit">会议总结</td>
            <td colspan="3">
                <form:textarea path="summary" htmlEscape="false" class="input-xlarge required"
                               style="margin: 0px; width: 938px; height: 125px;"/>
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
                    <%--<form:input path="nextLinkMan" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
                <sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${mediateEvidence.nextLinkMan}" labelName=""
                                labelValue="${mediateEvidence.linkEmployee.name}"
                                title="用户" url="/sys/office/treeData?type=3&officeType=1" allowClear="true"
                                notAllowSelectParent="true"  dataMsgRequired="必填信息" cssClass="required" />
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
        <shiro:hasPermission name="mediate:mediateEvidence:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                                type="submit" value="保 存"
                                                                                onclick="$('#flag').val('no'),$('#export').val('no')"/>&nbsp;</shiro:hasPermission>
        <shiro:hasPermission name="mediate:mediateEvidence:edit"><input id="btnSubmit" class="btn btn-primary"
                                                                                type="submit" value="下一步"
                                                                                onclick="$('#flag').val('yes'),$('#export').val('no')"/>&nbsp;</shiro:hasPermission>

        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
    <act:histoicFlow procInsId="${mediateEvidence.complaintMain.procInsId}" />
</form:form>
<script type="text/javascript">
    var mediateEvidenceRowIdx = 0, mediateEvidenceTpl = $("#mediateEvidenceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(mediateEvidence.mediateEvidenceList)};
        console.log(data)
        for (var i=0; i<data.length; i++){
            addRow('#mediateEvidenceList', mediateEvidenceRowIdx, mediateEvidenceTpl, data[i]);
            mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;
        }
    });
</script>
</body>
</html>