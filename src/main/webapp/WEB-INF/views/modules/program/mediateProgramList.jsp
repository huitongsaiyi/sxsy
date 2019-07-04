<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>调解程序管理</title>
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
		<li class="active"><a href="${ctx}/program/mediateProgram/">调解程序列表</a></li>
		<shiro:hasPermission name="program:mediateProgram:edit"><li><a href="${ctx}/program/mediateProgram/form">调解程序添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="mediateProgram" action="${ctx}/program/mediateProgram/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>调解程序主键</th>
				<th>关联表主键</th>
				<th>调解员</th>
				<th>书记员</th>
				<th>医调委人员  多人用逗号隔开</th>
				<th>患方</th>
				<th>医方</th>
				<th>其他</th>
				<th>案件</th>
				<th>地点</th>
				<th>会议时间</th>
				<th>会议次数</th>
				<th>create_by</th>
				<th>create_date</th>
				<th>update_by</th>
				<th>update_date</th>
				<shiro:hasPermission name="program:mediateProgram:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="mediateProgram">
			<tr>
				<td><a href="${ctx}/program/mediateProgram/form?id=${mediateProgram.id}">
					${mediateProgram.mediateProgramId}
				</a></td>
				<td>
					${mediateProgram.relationId}
				</td>
				<td>
					${mediateProgram.}
				</td>
				<td>
					${mediateProgram.}
				</td>
				<td>
					${mediateProgram.user.id}
				</td>
				<td>
					${mediateProgram.patient}
				</td>
				<td>
					${mediateProgram.}
				</td>
				<td>
					${mediateProgram.other}
				</td>
				<td>
					${mediateProgram.caseInfo}
				</td>
				<td>
					${mediateProgram.address}
				</td>
				<td>
					${mediateProgram.meetingTime}
				</td>
				<td>
					${mediateProgram.meetingFrequency}
				</td>
				<td>
					${mediateProgram.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${mediateProgram.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${mediateProgram.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${mediateProgram.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="program:mediateProgram:edit"><td>
    				<a href="${ctx}/program/mediateProgram/form?id=${mediateProgram.id}">修改</a>
					<a href="${ctx}/program/mediateProgram/delete?id=${mediateProgram.id}" onclick="return confirmx('确认要删除该调解程序吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>