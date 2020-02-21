<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>疫情日报管理</title>
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
			var id = $(prefix+"_egressId");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li><a href="${ctx}/epidemic/epidemicDaily/">疫情日报列表</a></li>
	<li class="active"><a href="${ctx}/epidemic/epidemicDaily/form?id=${epidemicDaily.id}">疫情日报<shiro:hasPermission name="epidemic:epidemicDaily:edit">${not empty epidemicDaily.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="epidemic:epidemicDaily:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="epidemicDaily" action="${ctx}/epidemic/epidemicDaily/save" method="post" class="form-horizontal">
	<form:hidden path="epidemicId"/>
	<input type="hidden" id="workRemark" name="workRemark"/>
	<sys:message content="${message}"/>
	<fieldset>
		<table class="table-form">
			<legend>身体情况</legend>
			<div class="control-group">
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>
							<th class="hide"></th>
							<shiro:hasPermission name="epidemic:epidemicDaily:edit"><th width="10">&nbsp;</th></shiro:hasPermission>
						</tr>
						</thead>
						<tbody id="egressInfoList">
						</tbody>
					</table>
					<script type="text/template" id="egressInfoTpl">//<!--
					<table id="egressInfoList{{idx}}" class="table table-striped table-bordered table-condensed">
						<tr >
							<td class="hide">
								<input id="egressInfoList{{idx}}_egressId" name="egressInfoList[{{idx}}].egressId" type="hidden" value="{{row.egressId}}"/>
								<input id="egressInfoList{{idx}}_delFlag" name="egressInfoList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td class="tit" width="10%"><font color="red">*</font>姓名：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_egressName" name="egressInfoList[{{idx}}].egressName" type="text" value="{{row.egressName}}" maxlength="20" class="input-mini " readonly="readonly"/>
							</td>
							<td class="tit" width="10%"><font color="red">*</font>性别：</td>
							<td width="15%">
								<select id="egressInfoList{{idx}}_egressSex" name="egressInfoList[{{idx}}].egressSex" data-value="{{row.egressSex}}" class="input-mini " disabled="disabled" >
									<option value=""></option>
									<c:forEach items="${fns:getDictList('sex')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td class="tit" width="10%"><font color="red">*</font>年龄：</td>
							<td width="15%">
									<input id="egressInfoList{{idx}}_egressAge" name="egressInfoList[{{idx}}].egressAge" type="text" value="{{row.egressAge}}" maxlength="4" class="input-mini " readonly="readonly"/>岁
							</td>
							<td class="tit" width="10%"><font color="red">*</font>关系：</td>
							<td width="15%">
								<select id="egressInfoList{{idx}}_relation" name="egressInfoList[{{idx}}].relation" data-value="{{row.relation}}" class="input-mini " disabled="disabled" >
									<option value=""></option>
									<c:forEach items="${fns:getDictList('patient_relation')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<shiro:hasPermission name="epidemic:epidemicDaily:edit"><td class="text-center" width="10" rowspan="4">
											{{#delBtn}}<span class="close" onclick="delRow(this, '#egressInfoList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
										</td></shiro:hasPermission>
						</tr>
						<tr >
							<td class="tit" width="10%"><font color="red">*</font>体温：</td>
							<td width="15%">
									<input id="egressInfoList{{idx}}_temperature" name="egressInfoList[{{idx}}].temperature" type="text" value="{{row.temperature}}" maxlength="255" class="input-mini " readonly="readonly"/>℃
							</td>
							<td class="tit" width="10%"><font color="red">*</font>健康状况：</td>
							<td width="15%">
									<input id="egressInfoList{{idx}}_healthCondition" name="egressInfoList[{{idx}}].healthCondition" type="text" value="{{row.healthCondition}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
							<td class="tit" width="10%"><font color="red">*</font>家庭详细地址：</td>
							<td width="15%" colspan="3">
								<input id="egressInfoList{{idx}}_userAddress" name="egressInfoList[{{idx}}].userAddress" type="text" value="{{row.userAddress}}" maxlength="255" class="input-xxlarge " readonly="readonly"/>
							</td>
						</tr>
						<tr >
							<td class="tit" width="10%"><font color="red">*</font>是否外出：</td>
							<td width="15%">
								<select id="egressInfoList{{idx}}_isEgress" name="egressInfoList[{{idx}}].isEgress" data-value="{{row.isEgress}}" class="input-mini " disabled="disabled" >
									<option value=""></option>
									<c:forEach items="${fns:getDictList('yes_no')}" var="dict">
										<option value="${dict.value}">${dict.label}</option>
									</c:forEach>
								</select>
							</td>
							<td class="tit" width="10%">外出时间：</td>
							<td width="15%">
									<input id="egressInfoList{{idx}}_egressTime" name="egressInfoList[{{idx}}].egressTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate "
									value="{{row.egressTime}}"  disabled="disabled" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td class="tit" width="10%">外出方式：</td>
							<td width="15%">
									<input id="egressInfoList{{idx}}_egressMode" name="egressInfoList[{{idx}}].egressMode" type="text" value="{{row.egressMode}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
							<td class="tit" width="10%">车次/航班号：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_trainFlight" name="egressInfoList[{{idx}}].trainFlight" type="text" value="{{row.trainFlight}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
						</tr>
						<tr >
							<td class="tit" width="10%">外出地点：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_egressPlace" name="egressInfoList[{{idx}}].egressPlace" type="text" value="{{row.egressPlace}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
							<td class="tit" width="10%">返回时间：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_returnTime" name="egressInfoList[{{idx}}].returnTime" type="text" readonly="readonly" maxlength="20" class="input-mini Wdate "
									value="{{row.returnTime}}" disabled="disabled" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
							</td>
							<td class="tit" width="10%">返回方式：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_returnMode" name="egressInfoList[{{idx}}].returnMode" type="text" value="{{row.returnMode}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
							<td class="tit" width="10%">返回地点：</td>
							<td width="15%">
								<input id="egressInfoList{{idx}}_returnPlace" name="egressInfoList[{{idx}}].returnPlace" type="text" value="{{row.returnPlace}}" maxlength="255" class="input-mini " readonly="readonly"/>
							</td>
						</tr>
					</table>
						//-->
					</script>
					<script type="text/javascript">
						var egressInfoRowIdx = 0, egressInfoTpl = $("#egressInfoTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(epidemicDaily.egressInfoList)};
							for (var i=0; i<data.length; i++){
								addRow('#egressInfoList', egressInfoRowIdx, egressInfoTpl, data[i]);
								egressInfoRowIdx = egressInfoRowIdx + 1;
							}
						});
					</script>
				</div>
			</div>
		</table>
		<table class="table-form">
			<legend>工作情况</legend>
			<tr >
				<td >
					<form:textarea path="workSituation" rows="3" style="margin: 0px;  width: 80%;" readonly="true"/>
				</td>
			</tr>
		</table>
		<table class="table-form">
			<legend>填表日期</legend>
			<tr >
				<td>
					<input name="fillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
						   value="${epidemicDaily.fillingDate}" disabled="disabled"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
<%--<div class="control-group">
        <label class="control-label">主键：</label>
        <div class="controls">
            <form:input path="epidemicId" htmlEscape="false" maxlength="32" class="input-xlarge required"/>
            <span class="help-inline"><font color="red">*</font> </span>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位：</label>
        <div class="controls">
            <form:input path="workUnit" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">填表日期：</label>
        <div class="controls">
            <input name="fillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                value="<fmt:formatDate value="${epidemicDaily.fillingDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
            <form:input path="userName" htmlEscape="false" maxlength="20" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
        <div class="controls">
            <form:input path="userSex" htmlEscape="false" maxlength="1" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">年龄：</label>
        <div class="controls">
            <form:input path="userAge" htmlEscape="false" maxlength="4" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">住址：</label>
        <div class="controls">
            <form:input path="userAddress" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">体温：</label>
        <div class="controls">
            <form:input path="temperature" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">健康状况：</label>
        <div class="controls">
            <form:input path="healthCondition" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">家人健康状况：</label>
        <div class="controls">
            <form:input path="familyHealthCondition" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">备注：</label>
        <div class="controls">
            <form:input path="remark" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作情况：</label>
        <div class="controls">
            <form:input path="workSituation" htmlEscape="false" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作情况备注：</label>
        <div class="controls">
            <form:input path="workRemark" htmlEscape="false" maxlength="1000" class="input-xlarge "/>
        </div>
    </div>--%>