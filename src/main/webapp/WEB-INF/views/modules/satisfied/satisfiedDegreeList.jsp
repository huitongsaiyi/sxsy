<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>满意度管理</title>
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
		<li class="active"><a href="${ctx}/satisfied/satisfiedDegree/">满意度列表</a></li>
		<shiro:hasPermission name="satisfied:satisfiedDegree:edit"><li><a href="${ctx}/satisfied/satisfiedDegree/form">满意度添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="satisfiedDegree" action="${ctx}/satisfied/satisfiedDegree/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>申请人姓名：</label>
				<form:input path="satisfiedName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li><label>评价：</label>
				<form:input path="assess" htmlEscape="false" class="input-medium"/>
			</li>
			<li><label>建议：</label>
				<form:input path="proposal" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">申请人姓名</th>
				<th style="text-align: center">案件编号</th>
				<th style="text-align: center">调解能力</th>
				<th style="text-align: center">服务态度</th>
				<th style="text-align: center">仪容仪表</th>
				<th style="text-align: center">评价</th>
				<th style="text-align: center">建议</th>
				<th style="text-align: center">update_date</th>
				<shiro:hasPermission name="satisfied:satisfiedDegree:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="satisfiedDegree">
			<tr>
				<td style="text-align: center"><a href="${ctx}/satisfied/satisfiedDegree/form?type=view&id=${satisfiedDegree.satisfiedId}">
					${satisfiedDegree.satisfiedName}
				</a></td>
				<td style="text-align: center"><a href="${ctx}/sign/signAgreement/form?type=view&id=${satisfiedDegree.signAgreementId}">
						${satisfiedDegree.complaintMain.caseNumber}
				</a></td>
				<td style="text-align: center">
					${satisfiedDegree.ability}
				</td>
				<td style="text-align: center">
					${satisfiedDegree.attitude}
				</td>
				<td style="text-align: center">
					${satisfiedDegree.meter}
				</td>
				<td style="text-align: center">
					${satisfiedDegree.assess}
				</td>
				<td style="text-align: center">
					${satisfiedDegree.proposal}
				</td>
				<td style="text-align: center">
					<fmt:formatDate value="${satisfiedDegree.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="satisfied:satisfiedDegree:edit"><td>
					<a href="${ctx}/satisfied/satisfiedDegree/form?type=view&id=${satisfiedDegree.satisfiedId}">详情</a>
    				<%--<a href="${ctx}/satisfied/satisfiedDegree/form?id=${satisfiedDegree.satisfiedId}">修改</a>
					<a href="${ctx}/satisfied/satisfiedDegree/delete?id=${satisfiedDegree.satisfiedId}" onclick="return confirmx('确认要删除该满意度吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>