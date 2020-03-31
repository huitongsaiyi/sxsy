<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投保单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/insuranceslip/insuranceSlip/">投保单列表</a></li>
		<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><li><a href="${ctx}/insuranceslip/insuranceSlip/form">投保单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="insuranceSlip" action="${ctx}/insuranceslip/insuranceSlip/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>医院机构ID：</label>
				<sys:treeselect id="office" name="office.id" value="${insuranceSlip.office.id}" labelName="office.name" labelValue="${insuranceSlip.office.name}"
					title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>投保人：</label>
				<form:input path="policyHolder" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>投保人：联系电话：</label>
				<form:input path="policyPhone" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>被保险人：创建时间：</label>
				<input name="creationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${insuranceSlip.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>被保险人：医疗机构等级：</label>
				<form:select path="grade" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>承保性质：</label>
				<form:input path="assumeNature" htmlEscape="false" maxlength="1" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>医院机构ID</th>
				<th>投保人</th>
				<th>投保人：联系电话</th>
				<th>投保人：通信地址和邮编</th>
				<th>投保人：电子邮箱</th>
				<th>被保险人：创建时间</th>
				<th>被保险人：执业许可号</th>
				<th>被保险人：医疗机构等级</th>
				<th>被保险人：类别</th>
				<th>实收保险费</th>
				<th>保险期开始时间</th>
				<th>保险截止时间</th>
				<th>投保时间</th>
				<th>承保性质</th>
				<th>修改时间</th>
				<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="insuranceSlip">
			<tr>
				<td><a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.id}">
					${insuranceSlip.office.name}
				</a></td>
				<td>
					${insuranceSlip.policyHolder}
				</td>
				<td>
					${insuranceSlip.policyPhone}
				</td>
				<td>
					${insuranceSlip.sitePostcode}
				</td>
				<td>
					${insuranceSlip.emailAddress}
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${insuranceSlip.practiceNumber}
				</td>
				<td>
					${fns:getDictLabel(insuranceSlip.grade, 'hospital_grade', '')}
				</td>
				<td>
					${fns:getDictLabel(insuranceSlip.mold, 'category', '')}
				</td>
				<td>
					${insuranceSlip.oddicialReceiptsPermium}
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.insuranceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.insuranceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.insureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${insuranceSlip.assumeNature}
				</td>
				<td>
					<fmt:formatDate value="${insuranceSlip.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="insuranceslip:insuranceSlip:edit"><td>
    				<a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.id}">修改</a>
					<a href="${ctx}/insuranceslip/insuranceSlip/delete?id=${insuranceSlip.id}" onclick="return confirmx('确认要删除该投保单吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>