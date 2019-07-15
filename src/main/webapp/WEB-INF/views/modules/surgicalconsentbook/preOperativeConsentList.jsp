<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
	<title>术前同意书管理</title>
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
	<li class="active"><a href="${ctx}/surgicalconsentbook/preOperativeConsent/">术前同意书列表</a></li>
	<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><li><a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?surgicalConsentId=${preOperativeConsent.id}">术前同意书添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="preOperativeConsent" action="${ctx}/surgicalconsentbook/preOperativeConsent/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
	<ul class="ul-form">
		<li><label style="width: 110px;">手术同意书编号：</label>
			<form:input path="surgicalConsentId" htmlEscape="false" maxlength="64" class="input-medium"/>
		</li>
		<li><label>手术类型：</label>
			<form:input path="operationType" htmlEscape="false" maxlength="1" class="input-medium"/>
		</li>

		<li><label>见证地点：</label>
			<form:input path="witnessLocations" htmlEscape="false" maxlength="64" class="input-medium"/>
		</li>
		<li><label >见证人：</label>
			<form:input path="witness" htmlEscape="false" maxlength="10" class="input-medium"/>
		</li>
		<li><label style="margin-left: 40px;">记录人：</label>
			<form:input path="recordMan" htmlEscape="false" maxlength="10" class="input-medium"/>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th class="sort-column surgical_consent_id" style="text-align: center;">手术同意书编号</th>
		<th class="sort-column operation_type" style="text-align: center;">手术类型</th>
		<th class="sort-column witness_time" style="text-align: center;">见证时间</th>
		<th class="sort-column witness_locations" style="text-align: center;">见证地点</th>
		<th class="sort-column affected_party" style="text-align: center;">患方人员</th>
		<th class="sort-column medical_side" style="text-align: center;">医方人员</th>
		<th class="sort-column insured" style="text-align: center;">是否投保</th>
		<th class="sort-column witness" style="text-align: center;">见证人</th>
		<th class="sort-column record_man" style="text-align: center;">记录人</th>

		<th class="sort-column remarks" style="text-align: center;">备注信息</th>
		<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><th class="sort-column" style="text-align: center;">操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="preOperativeConsent">
		<tr>
			<td style="text-align: center;">
				<a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">
					${preOperativeConsent.surgicalConsentId}</a>
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.operationType}
			</td>
			<td style="text-align: center;">
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${preOperativeConsent.witnessTime}"/>

			</td>
			<td style="text-align: center;">
					${preOperativeConsent.witnessLocations}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.affectedParty}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.medicalSide}
			</td>
			<td style="text-align: center;">
			<c:choose>
				<c:when test="${preOperativeConsent.insured=='1'}">
					否
				</c:when>
				<c:when test="${preOperativeConsent.insured=='2'}">
					是
				</c:when>
			</c:choose>
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.witness}
			</td>
			<td style="text-align: center;">
					${preOperativeConsent.recordMan}
			</td>

			<td style="text-align: center;">
					${preOperativeConsent.remarks}
			</td>

			<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><td style="text-align: center;">
				<a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">修改</a>
				<a href="${ctx}/surgicalconsentbook/preOperativeConsent/delete?id=${preOperativeConsent.id}" onclick="return confirmx('确认要删除该术前同意书吗？', this.href)">删除</a>
			</td></shiro:hasPermission>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>