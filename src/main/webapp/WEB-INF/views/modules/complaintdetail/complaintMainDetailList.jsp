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
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>访客姓名：</label>
				<form:input path="visitorName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>访客电话：</label>
				<form:input path="visitorMobile" htmlEscape="false" maxlength="15" class="input-medium"/>
			</li>
			<li><label>来访日期：</label>
				<input name="beginVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${complaintMainDetail.beginVisitorDate}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/> -
				<input name="endVisitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${complaintMainDetail.endVisitorDate}" pattern="yyyy-MM-dd HH:mm"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
            <li class="btns"><input id="btnReset" class="btn btn-primary" type="reset" value="重置"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column case_number">案件编号</th>
				<th class="sort-column complaint_mode">投诉方式</th>
				<th class="sort-column visitor_name">访客姓名</th>
				<th class="sort-column visitor_mobile">访客电话</th>
				<th class="sort-column visitor_date">来访日期</th>
				<th class="sort-column visitor_Number">来访 人数</th>
				<th class="sort-column patient_relation">与患者关系</th>
				<th class="sort-column is_major">是否重大</th>
				<th class="sort-column summary_of_disputes">投诉纠纷概要</th>
				<th class="sort-column appeal">诉求</th>
				<th class="sort-column a.update_date">更新日期</th>
				<shiro:hasPermission name="complaintdetail:complaintMainDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="complaintMainDetail">
			<tr>
				<td>
					<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">
							${complaintMainDetail.complaintMain.caseNumber}
					</a>
				</td>
				<td>
					<c:choose>
                        <c:when test="${complaintMainDetail.complaintMode=='0'}">
                            来电
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='1'}">
                            来访
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='2'}">
                            来信
                        </c:when>
                        <c:when test="${complaintMainDetail.complaintMode=='3'}">
                            其他
                        </c:when>
                    </c:choose>
				</td>
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
					${complaintMainDetail.patientRelationlabel}
				</td>
				<td>
					${complaintMainDetail.isMajorlabel}
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
					<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}">处理</a>
    				<a href="${ctx}/complaintdetail/complaintMainDetail/form?id=${complaintMainDetail.complaintMainDetailId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>