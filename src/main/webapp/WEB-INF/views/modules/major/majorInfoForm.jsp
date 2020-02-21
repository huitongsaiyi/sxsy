<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事件管理</title>
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
<%--
		<li><a href="${ctx}/major/majorInfo/">重大事件列表</a></li>
--%>
		<li class="active"><a href="${ctx}/major/majorInfo/form?id=${majorInfo.id}">重大事件<shiro:hasPermission name="major:majorInfo:edit">${not empty majorInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="major:majorInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="majorInfo" action="${ctx}/major/majorInfo/save" method="post" class="form-horizontal">
		<form:hidden path="majorId"/>
		<form:hidden path="complaintMainId"/>
		<sys:message content="${message}"/>
	<table class="table-form">
		<tr >
			<td class="tit" width="15%" colspan="2">发生时间：</td>
			<td colspan="3">
				<input name="occurrenceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="${majorInfo.occurrenceTime}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</td>
		</tr>
		<tr >
			<td class="tit" width="15%" colspan="2">重大事件参与人数：</td>
			<td width="35%">
				<form:input path="eventNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</td>
			<td class="tit" width="15%">持续时间：</td>
			<td width="35%">
				<form:input path="duration" htmlEscape="false" maxlength="20" class="input-xlarge"/>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">是否医院保安人员现场处理：</td>
			<td >
				<form:select path="hospitalSecurityHandle" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">是否卫生、司法等部门挂牌督办：</td>
			<td >
				<form:select path="supervise" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">是否公安机关出警：</td>
			<td >
				<form:select path="alarm" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">出警次数：</td>
			<td >
				<form:input path="numberPolice" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">是否有公安机关现场依法制止&ldquo;医闹&rdquo;事件：</td>
			<td >
				<form:select path="checkEvent" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">职业医闹人员数量：</td>
			<td >
				<form:input path="medicalTroubleNum" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td rowspan="5" width="1%">具体行为</td>
			<td class="tit" width="14%">辱骂、殴打、故意伤害医务人员：</td>
			<td >
				<form:select path="hurt" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">如有请填写受到伤害医务人员数量：</td>
			<td >
				<form:input path="hurtNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit">非法限制医务人员人身自由行动：</td>
			<td >
				<form:select path="limitFree" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">如有请填写限制医务人员数量：</td>
			<td >
				<form:input path="limitNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</td>
		</tr>
		<tr >
			<td >采取暴力或者其他方法公然辱骂、恐吓医务人员行为：</td>
			<td >
				<form:select path="abusePeople" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">故意损毁公私财物行为：</td>
			<td >
				<form:select path="damageProperty" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>

		</tr>
		<tr >
			<td >私设灵堂、摆放花圈、焚烧纸钱、悬挂横幅、堵塞大门等行为：</td>
			<td >
				<form:select path="behavior" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td class="tit">违规停尸：</td>
			<td >
				<form:select path="illegalMortuary" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
		</tr>
		<tr >
			<td >非法携带枪支、弹药、管制器具或爆炸性、放射性、毒害性、腐蚀性物品进入医疗机构，危及公共安全行为：</td>
			<td >
				<form:select path="ammunition" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
			<td >故意扩大事态、教唆他人实施针对医疗机构或医务人员的违法犯罪行为，或以受他人委托处理医疗纠纷为名实施敲诈勒索、寻衅滋事等行为：</td>
			<td >
				<form:select path="provoke" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">现场处理情况：</td>
			<td colspan="3">
				<form:textarea path="siteTreatment" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">下一步调解计划：</td>
			<td colspan="3">
				<form:textarea path="nextPlan" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">现场图片：</td>
			<td colspan="3">
				<form:hidden id="scenePictures" path="scenePictures" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="scenePictures" type="images" uploadPath="/major/majorInfo/pic" selectMultiple="true" />
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">视频：</td>
			<td colspan="3">
				<form:hidden id="video" path="video" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="video" type="files" uploadPath="/major/majorInfo/video" selectMultiple="true"/>
			</td>
		</tr>
		<tr >
			<td class="tit" colspan="2">音频：</td>
			<td colspan="3">
				<form:hidden id="audio" path="audio" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="audio" type="files" uploadPath="/major/majorInfo/audio" selectMultiple="true"/>
			</td>
		</tr>
		<tr>
			<td class="tit" colspan="2">参与处理调解员：</td>
			<td colspan="3">
				<sys:treeselect id="participant" name="participant" value="${majorInfo.participant}" labelName="par" labelValue="${majorInfo.parName}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</td>
		</tr>
	</table>
		<div class="form-actions">
			<%--<shiro:hasPermission name="major:majorInfo:edit">--%>
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<%--</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>