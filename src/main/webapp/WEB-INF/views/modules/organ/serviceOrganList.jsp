<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务机构信息管理</title>
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
		<li class="active"><a href="${ctx}/organ/serviceOrgan/">服务机构信息列表</a></li>
		<c:if test="${empty page.list}">
			<shiro:hasPermission name="organ:serviceOrgan:edit"><li><a href="${ctx}/organ/serviceOrgan/form">服务机构信息添加</a></li></shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="serviceOrgan" action="${ctx}/organ/serviceOrgan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>邮箱：</label>
				<form:input path="email" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center">电话</th>
				<th style="text-align: center">地址</th>
				<th style="text-align: center">邮箱</th>
				<th style="text-align: center">工作时间</th>
				<th style="text-align: center">服务机构简介</th>
				<th style="text-align: center">主要责任</th>
				<th style="text-align: center">服务宗旨</th>
				<th style="text-align: center">三个援助</th>
				<th style="text-align: center">调解员</th>
				<th style="text-align: center">修改时间</th>
				<shiro:hasPermission name="organ:serviceOrgan:edit"><th style="text-align: center">操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="serviceOrgan">
			<tr>
				<td>
					${serviceOrgan.phone}
				</td>
				<td>
					${serviceOrgan.address}
				</td>
				<td>
					${serviceOrgan.email}
				</td>
				<td>
					${serviceOrgan.workTime}
				</td>
				<td>
					<a href="javascript:" onclick="$('#a_${serviceOrgan.serviceOrganId}').toggle()">${fns:abbr(fns:replaceHtml(serviceOrgan.introduce),30)}</a>
				</td>
				<td>
					<a href="javascript:" onclick="$('#b_${serviceOrgan.serviceOrganId}').toggle()">${fns:abbr(fns:replaceHtml(serviceOrgan.duty),30)}</a>
				</td>
				<td>
					<a href="javascript:" onclick="$('#c_${serviceOrgan.serviceOrganId}').toggle()">${fns:abbr(fns:replaceHtml(serviceOrgan.serviceTenet),30)}</a>
				</td>
				<td>
					<a href="javascript:" onclick="$('#d_${serviceOrgan.serviceOrganId}').toggle()">${fns:abbr(fns:replaceHtml(serviceOrgan.threeAid),30)}</a>
				</td>
				<td>
					<a href="javascript:" onclick="$('#e_${serviceOrgan.serviceOrganId}').toggle()">${fns:abbr(fns:replaceHtml(serviceOrgan.userName),10)}</a>
				</td>
				<td>
					<fmt:formatDate value="${serviceOrgan.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="organ:serviceOrgan:edit"><td>
    				<a href="${ctx}/organ/serviceOrgan/form?id=${serviceOrgan.serviceOrganId}">修改</a>
					<a href="${ctx}/organ/serviceOrgan/delete?id=${serviceOrgan.serviceOrganId}" onclick="return confirmx('确认要删除该服务机构信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
			<tr id="a_${serviceOrgan.serviceOrganId}" style="background:#fdfdfd;display:none;"><td colspan="11">${fns:replaceHtml2(serviceOrgan.introduce)}</td></tr>
			<tr id="b_${serviceOrgan.serviceOrganId}" style="background:#fdfdfd;display:none;"><td colspan="11">${fns:replaceHtml2(serviceOrgan.duty)}</td></tr>
			<tr id="c_${serviceOrgan.serviceOrganId}" style="background:#fdfdfd;display:none;"><td colspan="11">${fns:replaceHtml2(serviceOrgan.serviceTenet)}</td></tr>
			<tr id="d_${serviceOrgan.serviceOrganId}" style="background:#fdfdfd;display:none;"><td colspan="11">${fns:replaceHtml2(serviceOrgan.threeAid)}</td></tr>
			<tr id="e_${serviceOrgan.serviceOrganId}" style="background:#fdfdfd;display:none;"><td colspan="11">${fns:replaceHtml2(serviceOrgan.userName)}</td></tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>