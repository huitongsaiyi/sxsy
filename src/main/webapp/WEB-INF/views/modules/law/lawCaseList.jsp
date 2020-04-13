<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>信息管理</title>
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
		<li class="active"><a href="${ctx}/law/lawCase/list?law=${law}">信息列表</a></li>
		<shiro:hasPermission name="law:lawCase:edit"><li><a href="${ctx}/law/lawCase/form?law=${law}">信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="lawCase" action="${ctx}/law/lawCase/list?law=${law}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
		<c:choose>
			<c:when test="${not empty law and law eq '1'}">
					<li><label>标题：</label>
						<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
					</li>
			</c:when>
		</c:choose>

			<li><label>内容：</label>
				<form:input path="content" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:choose>
					<c:when test="${not empty law and law eq '1'}">
						<th style="text-align: center">类型</th>
						<th style="text-align: center">公布时间</th>
						<th style="text-align: center">标题</th>
					</c:when>
				<c:otherwise>
				</c:otherwise>
				</c:choose>
				<th style="text-align: center">内容</th>
				<th style="text-align: center">修改时间</th>
				<shiro:hasPermission name="law:lawCase:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="lawCase">
			<tr>
				<c:choose>
					<c:when test="${not empty law and law eq '1'}">
						<td style="text-align: center">
							<c:choose>
								<c:when test="${lawCase.type eq  1}">法律法规</c:when>
								<c:when test="${lawCase.type eq  2}">经典案例</c:when>
								<c:when test="${lawCase.type eq  3}">行政调解</c:when>
								<c:otherwise>法律诉讼</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: center">
								${lawCase.publishTime}
						</td>
						<td style="text-align: center">
								${lawCase.title}
						</td>
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
				<td style="text-align: center">
					<a href="javascript:" onclick="$('#e_${lawCase.lawCaseId}').toggle()">
							${fns:abbr(lawCase.content,100)}
					</a>
				</td>
				<td style="text-align: center">
					<fmt:formatDate value="${lawCase.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="law:lawCase:edit"><td style="text-align: center">
    				<a href="${ctx}/law/lawCase/form?id=${lawCase.lawCaseId}&law=${law}">修改</a>
					<a href="${ctx}/law/lawCase/delete?id=${lawCase.lawCaseId}&law=${lawCase.type}" onclick="return confirmx('确认要删除该信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
			<tr id="e_${lawCase.lawCaseId}" style="background:#fdfdfd;display:none;"><td colspan="6">${fns:replaceHtml2(lawCase.content)}</td></tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>