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
		<li><a href="${ctx}/major/majorInfo/">重大事件列表</a></li>
		<li class="active"><a href="${ctx}/major/majorInfo/form?id=${majorInfo.id}">重大事件<shiro:hasPermission name="major:majorInfo:edit">${not empty majorInfo.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="major:majorInfo:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="majorInfo" action="${ctx}/major/majorInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">主键：</label>
			<div class="controls">
				<form:input path="majorId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">主表主键：</label>
			<div class="controls">
				<form:input path="complaintMainId" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发生时间：</label>
			<div class="controls">
				<input name="occurrenceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${majorInfo.occurrenceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">重大事件参与人数：</label>
			<div class="controls">
				<form:input path="eventNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">持续时间：</label>
			<div class="controls">
				<form:input path="duration" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否医院保安人员现场处理：</label>
			<div class="controls">
				<form:select path="hospitalSecurityHandle" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否卫生、司法等部门挂牌督办：</label>
			<div class="controls">
				<form:select path="supervise" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否公安机关出警：</label>
			<div class="controls">
				<form:select path="alarm" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出警次数：</label>
			<div class="controls">
				<form:input path="numberPolice" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否有公安机关现场依法制止&ldquo;医闹&rdquo;事件：</label>
			<div class="controls">
				<form:select path="checkEvent" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职业医闹人员数量：</label>
			<div class="controls">
				<form:input path="medicalTroubleNum" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">辱骂、殴打、故意伤害医务人员：</label>
			<div class="controls">
				<form:select path="hurt" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">如有请填写受到伤害医务人员数量：</label>
			<div class="controls">
				<form:input path="hurtNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">非法限制医务人员人身自由行动：</label>
			<div class="controls">
				<form:select path="limitFree" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">如有请填写限制医务人员数量：</label>
			<div class="controls">
				<form:input path="limitNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采取暴力或者其他方法公然辱骂、恐吓医务人员行为：</label>
			<div class="controls">
				<form:select path="abusePeople" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故意损毁公私财物行为：</label>
			<div class="controls">
				<form:select path="damageProperty" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">私设灵堂、摆放花圈、焚烧纸钱、悬挂横幅、堵塞大门等行为：</label>
			<div class="controls">
				<form:select path="behavior" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">违规停尸：</label>
			<div class="controls">
				<form:select path="illegalMortuary" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">非法携带枪支、弹药、管制器具或爆炸性、放射性、毒害性、腐蚀性物品进入医疗机构，危及公共安全行为：</label>
			<div class="controls">
				<form:select path="ammunition" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">故意扩大事态、教唆他人实施针对医疗机构或医务人员的违法犯罪行为，或以受他人委托处理医疗纠纷为名实施敲诈勒索、寻衅滋事等行为：</label>
			<div class="controls">
				<form:select path="provoke" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现场处理情况：</label>
			<div class="controls">
				<form:textarea path="siteTreatment" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下一步调解计划：</label>
			<div class="controls">
				<form:textarea path="nextPlan" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现场图片：</label>
			<div class="controls">
				<form:hidden id="scenePictures" path="scenePictures" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="scenePictures" type="files" uploadPath="/major/majorInfo" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">视频：</label>
			<div class="controls">
				<form:hidden id="video" path="video" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="video" type="files" uploadPath="/major/majorInfo" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">音频：</label>
			<div class="controls">
				<form:hidden id="audio" path="audio" htmlEscape="false" maxlength="200" class="input-xlarge"/>
				<sys:ckfinder input="audio" type="files" uploadPath="/major/majorInfo" selectMultiple="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参与处理调解员：</label>
			<div class="controls">
				<sys:treeselect id="participant" name="participant" value="${majorInfo.participant}" labelName="" labelValue="${majorInfo.participant}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="major:majorInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>