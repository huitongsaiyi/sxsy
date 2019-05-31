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
		<th>手术同意书编号</th>
		<th>手术类型</th>
		<th>见证时间</th>
		<th>见证地点</th>
		<th>患方人员</th>
		<th>医方人员</th>
		<th>是否投保</th>
		<th>见证人</th>
		<th>记录人</th>

		<th>备注信息</th>
		<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="preOperativeConsent">
		<tr>
			<td><a href="${ctx}/surgicalconsentbook/preOperativeConsent/form?id=${preOperativeConsent.id}">
					${preOperativeConsent.surgicalConsentId}
			</a></td>
			<td>
					${preOperativeConsent.operationType}
			</td>
			<td>
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${preOperativeConsent.witnessTime}"/>

			</td>
			<td>
					${preOperativeConsent.witnessLocations}
			</td>
			<td>
					${preOperativeConsent.affectedParty}
			</td>
			<td>
					${preOperativeConsent.medicalSide}
			</td>
			<td>
					${preOperativeConsent.insured}
			</td>
			<td>
					${preOperativeConsent.witness}
			</td>
			<td>
					${preOperativeConsent.recordMan}
			</td>

			<td>
					${preOperativeConsent.remarks}
			</td>
			<shiro:hasPermission name="surgicalconsentbook:preOperativeConsent:edit"><td>
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