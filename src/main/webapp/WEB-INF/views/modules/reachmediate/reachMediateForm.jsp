<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>达成调解管理</title>
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
        function delRow(obj, prefix){
            var id = $(prefix+"_mediateRecord");
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
		<li><a href="${ctx}/reachmediate/reachMediate/">达成调解列表</a></li>
		<li class="active"><a href="${ctx}/reachmediate/reachMediate/form?id=${reachMediate.reachMediateId}">达成调解<shiro:hasPermission name="reachmediate:reachMediate:edit">${not empty reachMediate.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="reachmediate:reachMediate:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="reachMediate" action="${ctx}/reachmediate/reachMediate/save" method="post" class="form-horizontal">
		<form:hidden path="reachMediateId"/>
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
						<shiro:hasPermission name="reachmediate:reachMediate:edit">
							<th width="100">&nbsp;</th>
						</shiro:hasPermission>
					</tr>
					</thead>
					<tbody id="mediateEvidenceList"></tbody>
					<shiro:hasPermission name="reachmediate:reachMediate:edit">
						<tfoot>
						<tr><td colspan="7"><a href="javascript:" onclick="addRow('#mediateEvidenceList', reachMediateRowIdx, reachMediateTpl);reachMediateRowIdx = reachMediateRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
				</table>
				<script type="text/template" id="reachMediateTpl">//<!--
						<tr id="mediateEvidenceList{{idx}}">
							<td class="hide">
								<input id="mediateEvidenceList{{idx}}_id" name="mediateEvidenceList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="mediateEvidenceList{{idx}}_mediateRecord" name="mediateEvidenceList[{{idx}}].mediateRecord" type="hidden" value="{{row.mediateRecord}}"/>
								<input id="mediateEvidenceList{{idx}}_relationId" name="mediateEvidenceList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="mediateEvidenceList{{idx}}_delFlag" name="mediateEvidenceList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td >
								<%--<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" value="{{row.time}}" maxlength="32" class="input-small "/>--%>
								<input id="mediateEvidenceList{{idx}}_time" name="mediateEvidenceList[{{idx}}].time" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" "
                                    value="{{row.time}}"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_content" name="mediateEvidenceList[{{idx}}].content" type="text" value="{{row.content}}" maxlength="100" class="required" />
							</td>
							<td>
								<input id="mediateEvidenceList{{idx}}_result" name="mediateEvidenceList[{{idx}}].result" type="text" value="{{row.result}}" maxlength="32" class="required" />
							</td>
							<shiro:hasPermission name="reachmediate:reachMediate:edit"><td class="text-center" width="10">
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
							<input name="reaMeetingTime" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate "
								   value="${reachMediate.reaMeetingTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						</td>
						<td class="tit">地点:</td>
						<td>
							<form:input path="reaAddress" htmlEscape="false" maxlength="20" class="input-xlarge " />
						</td>
					</tr>
					<tr>
						<td class="tit">案件:</td>
						<td>
							<form:input path="reaCaseInfo" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
						<td class="tit">医方:</td>
						<td class="controls">
							<sys:treeselect id="reaDoctor" name="reaDoctor"
											value="${reachMediate.reaDoctor}" labelName=""
											labelValue="${reachMediate.doctorUser.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=2" cssClass=""
											allowClear="true" isAll="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr>
						<td class="tit">医调委人员:</td>
						<td>
							<sys:treeselect id="reaUserId" name="reaUserId"
											value="${reachMediate.reaUserId}" labelName=""
											labelValue="${reachMediate.ytwUser.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
											allowClear="true" notAllowSelectParent="true" checked="true"/>
						</td>
						<td class="tit">患方</td>
						<td>
							<form:input path="reaPatient" htmlEscape="false" maxlength="20" class="input-xlarge "
										value="${reachMediate.reaPatient}"/>
						</td>
					</tr>
					<td style="text-align: center;"><input id="btnGenerate" class="btn" type="button" value="生成会议表"></td>
				</table>
			</div>
			<div class="tab-pane fade" id="recorded_patient">
				<table class="table-form">
					<tr>
						<td class="tit">开始时间</td>
						<td>
							<input name="recordInfo.startTime" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate "
								   value="${reachMediate.recordInfo.startTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						</td>
						<td class="tit">结束时间</td>
						<td>
							<input name="recordInfo.endTime" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate "
								   value="${reachMediate.recordInfo.endTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:true});"/>
						</td>
					</tr>
					<tr>
						<td class="tit">地点</td>
						<td>
							<form:input path="recordInfo.recordAddress" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
						<td class="tit">事由</td>
						<td>
							<form:input path="recordInfo.cause" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit">主持人</td>
						<td>
							<sys:treeselect id="h_host" name="recordInfo.host"
											value="${reachMediate.recordInfo.host}" labelName=""
											labelValue="${reachMediate.recordInfo.ytwHost.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
											allowClear="true" notAllowSelectParent="true"/>
						</td>
						<td class="tit">记录人</td>
						<td>
							<sys:treeselect id="h_noteTaker" name="recordInfo.noteTaker"
											value="${reachMediate.recordInfo.noteTaker}" labelName=""
											labelValue="${reachMediate.recordInfo.ytwNoteTaker.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
											allowClear="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr>
						<td class="tit">患方</td>
						<td>
							<form:input path="recordInfo.patient" htmlEscape="false" maxlength="20" class="input-xlarge "/>
						</td>
						<td class="tit">医方</td>
						<td>
							<sys:treeselect id="h_doctor" name="recordInfo.doctor"
											value="${reachMediate.recordInfo.doctor}" labelName=""
											labelValue="${reachMediate.recordInfo.yfDoctor.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=2" cssClass=""
											allowClear="true" isAll="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr>
						<td class="tit">其他参加人员</td>
						<td>
							<form:input path="recordInfo.otherParticipants" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit">笔录内容</td>
						<td colspan="3">
							<form:textarea path="recordInfo.recordContent" htmlEscape="false" class="input-xlarge "
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
								   class="input-medium Wdate "
								   value="${reachMediate.recordInfo.yrecordInfo.startTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
						</td>
						<td class="tit">结束时间</td>
						<td>
							<input name="recordInfo.yrecordInfo.endTime" type="text" readonly="readonly" maxlength="20"
								   class="input-medium Wdate "
								   value="${reachMediate.recordInfo.yrecordInfo.endTime}"
								   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
						</td>
					</tr>
					<tr>
						<td class="tit">地点</td>
						<td>
							<form:input path="recordInfo.yrecordInfo.recordAddress" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
						<td class="tit">事由</td>
						<td>
							<form:input path="recordInfo.yrecordInfo.cause" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit">主持人</td>
						<td>
							<sys:treeselect id="y_host" name="recordInfo.yrecordInfo.host"
											value="${reachMediate.recordInfo.yrecordInfo.host}" labelName=""
											labelValue="${reachMediate.recordInfo.yrecordInfo.ytwHost.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
											allowClear="true" notAllowSelectParent="true"/>
						</td>
						<td class="tit">记录人</td>
						<td>
							<sys:treeselect id="y_noteTaker" name="recordInfo.yrecordInfo.noteTaker"
											value="${reachMediate.recordInfo.yrecordInfo.noteTaker}" labelName=""
											labelValue="${reachMediate.recordInfo.yrecordInfo.ytwNoteTaker.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass=""
											allowClear="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr>
						<td class="tit">患方</td>
						<td>
							<form:input path="recordInfo.yrecordInfo.patient" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
						<td class="tit">医方</td>
						<td>
							<sys:treeselect id="y_doctor" name="recordInfo.yrecordInfo.doctor"
											value="${reachMediate.recordInfo.yrecordInfo.doctor}" labelName=""
											labelValue="${reachMediate.recordInfo.yrecordInfo.yfDoctor.name}"
											title="用户" url="/sys/office/treeData?type=3&officeType=2" cssClass=""
											allowClear="true" isAll="true" notAllowSelectParent="true"/>
						</td>
					</tr>
					<tr>
						<td class="tit">其他参加人员</td>
						<td>
							<form:input path="recordInfo.yrecordInfo.otherParticipants" htmlEscape="false" maxlength="20"
										class="input-xlarge "/>
						</td>
					</tr>
					<tr>
						<td class="tit">笔录内容</td>
						<td colspan="3">
							<form:textarea path="recordInfo.yrecordInfo.recordContent" htmlEscape="false"
										   class="input-xlarge"
										   style="margin: 0px; width: 938px; height: 125px;"/>
						</td>
					</tr>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">
				<tr style="border:solid">
					<input type="hidden" name="fjtype" value="7">
					<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">
						签到表：
						<input type="hidden" id="files" name="files" htmlEscape="false" class="input-xlarge"
							   value="${files}"/>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<sys:ckfinder input="files" type="files" uploadPath="/mediateEvidence/annex" selectMultiple="false"
									  maxWidth="100" maxHeight="100"/>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype1" value="8">
					<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">
						患方笔录：
						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
							   value="${files1}"/>
							<%--<form:hidden id="files1" path="files1" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<sys:ckfinder input="files1" type="files" uploadPath="/mediateEvidence/annex"
									  selectMultiple="false"
									  maxWidth="100" maxHeight="100"/>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype2" value="9">
					<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">
						患方补充材料：
						<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
							   value="${files2}"/>
							<%--<form:hidden id="files2" path="files2" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<sys:ckfinder input="files2" type="files" uploadPath="/mediateEvidence/annex"
									  selectMultiple="false"
									  maxWidth="100" maxHeight="100"/>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype3" value="10">
					<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">
						医方笔录：
						<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
							   value="${files3}"/>
							<%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<sys:ckfinder input="files3" type="files" uploadPath="/mediateEvidence/annex"
									  selectMultiple="false"
									  maxWidth="100" maxHeight="100"/>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="fjtype4" value="11">
					<td style="width: 450px; margin-left:20px;  display:inline-block; height: 50px; margin-top: -40px;">
						医方补充材料：
						<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
							   value="${files4}"/>
							<%--<form:hidden id="nameImage" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>--%>
							<%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
						<sys:ckfinder input="files4" type="files" uploadPath="/mediateEvidence/annex"
									  selectMultiple="false"
									  maxWidth="100" maxHeight="100"/>
					</td>
				</tr>
			</div>
		</div>
		<table class="table-form">
			<tr>
				<td class="tit">调解结果</td>
				<td>
					<form:select path="reaMediateResult" cssStyle="width: 180px">
						<form:option value="1">成功</form:option>
						<form:option value="2">失败</form:option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td class="tit">会议总结</td>
				<td colspan="3">
					<form:textarea path="reaSummary" htmlEscape="false" class="input-xlarge "
								   style="margin: 0px; width: 938px; height: 125px;"/>
				</td>
			</tr>
			<tr>
				<td class="tit"><font color="red">*</font>下一处理环节：</td>
				<td>
					<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				</td>
				<td class="tit"><font color="red">*</font>下一环节处理人：</td>
				<td>
					<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${reachMediate.nextLinkMan}" labelName=""
									labelValue="${reachMediate.linkEmployee.name}"
									title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true"
									notAllowSelectParent="true" checked="true"/>
				</td>
			</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="reachmediate:reachMediate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="reachmediate:reachMediate:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
			<c:if test="${not empty reachMediate.reachMediateId}">
				<act:histoicFlow procInsId="${reachMediate.complaintMain.procInsId}" />
			</c:if>
		</div>
	</form:form>
	<script type="text/javascript">
        var reachMediateRowIdx = 0, reachMediateTpl = $("#reachMediateTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
        $(document).ready(function() {
            var data = ${fns:toJson(reachMediate.mediateEvidenceList)};
            for (var i=0; i<data.length; i++){
                addRow('#mediateEvidenceList', reachMediateRowIdx, reachMediateTpl, data[i]);
                reachMediateRowIdx = reachMediateRowIdx + 1;
            }
        });
	</script>
</body>
</html>