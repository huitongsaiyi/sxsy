<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>通知管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var send = '${oaNotify.send}';
			var arr=send.split(",");
			$("#send").val(arr).trigger('change');

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
			sendTo();
		});

		function sendTo() {
			var send=$("#send").val();
			if(send !=null && send !=undefined && send!=''){
				if(send.indexOf("0") != -1){//包含 0  显示接受人 ，且为必填
					$("#jsr").attr("style","display:block;");//显示div

					$('#oaNotifyRecord').addClass('required');
				}else{
					$("#jsr").attr("style","display:none;");//隐藏div

					$('#oaNotifyRecord').removeClass('required');

				}
			}

		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaNotify/">通知列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaNotify/form?id=${oaNotify.id}">通知<shiro:hasPermission name="oa:oaNotify:edit">${oaNotify.status eq '1' ? '查看' : not empty oaNotify.id ? '修改' : '添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaNotify:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaNotify" action="${ctx}/oa/oaNotify/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<c:if test="${oaNotify.status ne '1'}">
		<div class="control-group">
			<label class="control-label">类型：</label>
			<div class="controls">
				<form:select path="type" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
			<div class="control-group">
				<label class="control-label">发往：</label>
				<div class="controls">
					<form:select path="send" class="input-xlarge required" multiple="true" onchange="sendTo()" >
						<form:option value="0">平台</form:option>
						<form:option value="1">小程序</form:option>
						<form:option value="2">网站</form:option>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
			<div class="control-group">
			<label class="control-label">标题：</label>
			<div class="controls">
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">副标题：</label>
			<div class="controls">
				<form:input path="remarks" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		</c:if>
		<div class="control-group">
			<label class="control-label">内容：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="input-xxlarge required"/>
				<sys:ckeditor replace="content" uploadPath="/oaNotify/content" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<c:if test="${oaNotify.status ne '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">状态：</label>
				<div class="controls">
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
					<span class="help-inline"><font color="red">*</font> 发布后不能进行操作。</span>
				</div>
			</div>
			<div id="jsr" class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
	                <sys:treeselect id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="input-xxlarge required" dataMsgRequired="请选择接受人" notAllowSelectParent="true" checked="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</c:if>
		<c:if test="${oaNotify.status eq '1'}">
			<div class="control-group">
				<label class="control-label">附件：</label>
				<div class="controls">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge"/>
					<sys:ckfinder input="files" type="files" uploadPath="/oa/notify" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<%--<div class="control-group">
				<label class="control-label">接受人：</label>
				<div class="controls">
					<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>接受人</th>
								<th>接受部门</th>
								<th>阅读状态</th>
								<th>阅读时间</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${oaNotify.oaNotifyRecordList}" var="oaNotifyRecord">
							<tr>
								<td>
									${oaNotifyRecord.user.name}
								</td>
								<td>
									${oaNotifyRecord.user.office.name}
								</td>
								<td>
									${fns:getDictLabel(oaNotifyRecord.readFlag, 'oa_notify_read', '')}
								</td>
								<td>
									<fmt:formatDate value="${oaNotifyRecord.readDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					已查阅：${oaNotify.readNum} &nbsp; 未查阅：${oaNotify.unReadNum} &nbsp; 总共：${oaNotify.readNum + oaNotify.unReadNum}
				</div>
			</div>--%>
		</c:if>
		<div class="form-actions">
			<c:if test="${oaNotify.status ne '1'}">
				<shiro:hasPermission name="oa:oaNotify:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>