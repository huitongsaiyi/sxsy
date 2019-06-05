<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投诉接待管理</title>
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
		<li class="active"><a href="${ctx}/complaintdetail/complaintMainDetail/">投诉接待列表</a></li>
		<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><li><a href="${ctx}/complaintdetail/complaintMainDetail/form">投诉接待添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="complaintMainDetail" action="${ctx}/complaintdetail/complaintMainDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>访客姓名：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客电话：</label>
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>来访日期：</label>
				<input name="beginVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${complaintMainDetail.beginVisitorDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${complaintMainDetail.endVisitorDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>投诉方式</th>
				<th>访客姓名</th>
				<th>访客电话</th>
				<th>来访日期</th>
				<th>来访 人数</th>
				<th>与患者关系 字典维护</th>
				<th>是否重大</th>
				<th>投诉纠纷概要</th>
				<th>诉求</th>
				<th>update_date</th>
				<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintMainDetail">
			<tr>
				<td><a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">
					${complaintMainDetail.complaintMode}
				</a></td>
				<td>
					${complaintMainDetail.visitorName}
				</td>
				<td>
					${complaintMainDetail.visitorMobile}
				</td>
				<td>
					${complaintMainDetail.visitorDate}
				</td>
				<td>
					${complaintMainDetail.visitorNumber}
				</td>
				<td>
					${complaintMainDetail.patientRelation}
				</td>
				<td>
					${complaintMainDetail.isMajor}
				</td>
				<td>
					${complaintMainDetail.summaryOfDisputes}
				</td>
				<td>
					${complaintMainDetail.appeal}
				</td>
				<td>
					<fmt:formatDate value="${complaintMainDetail.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><td>
    				<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">修改</a>
					<a href="${ctx}/complaintdetail/complaintMainDetail/delete?id=${complaintMainDetail.complaintMainDetailId}" onclick="return confirmx('确认要删除该投诉接待吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>