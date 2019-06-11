<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估申请管理</title>
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
		<li class="active"><a href="${ctx}/assessapply/assessApply/">评估申请列表</a></li>
		<shiro:hasPermission name="assessapply:assessApply:edit"><li><a href="${ctx}/assessapply/assessApply/form">评估申请添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="assessApply" action="${ctx}/assessapply/assessApply/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>患方申请人：</label>
				<form:input path="patientApplyer" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>患方电话：</label>
				<form:input path="patientMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>患者姓名：</label>
				<form:input path="patientName" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>患方申请人</th>
				<th>与患者关系</th>
				<th>患方电话</th>
				<th>患者姓名</th>
				<th>申请医院</th>
				<th>create_date</th>
				<th>update_date</th>
				<shiro:hasPermission name="assessapply:assessApply:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessApply">
			<tr>
				<td><a href="${ctx}/assessapply/assessApply/form?id=${assessApply.assessApplyId}">
					${assessApply.patientApplyer}
				</a></td>
				<td>
					${assessApply.patientRelation}
				</td>
				<td>
					${assessApply.patientMobile}
				</td>
				<td>
					${assessApply.patientName}
				</td>
				<td>
					${assessApply.involveHospital}
				</td>
				<td>
					<fmt:formatDate value="${assessApply.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${assessApply.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="assessapply:assessApply:edit"><td>
    				<a href="${ctx}/assessapply/assessApply/form?id=${assessApply.assessApplyId}">修改</a>
    				<a href="${ctx}/assessapply/assessApply/form?id=${assessApply.assessApplyId}&type=view">详情</a>
<%--
					<a href="${ctx}/assessapply/assessApply/delete?id=${assessApply.assessApplyId}" onclick="return confirmx('确认要删除该评估申请吗？', this.href)">删除</a>
--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>