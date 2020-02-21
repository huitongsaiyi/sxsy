<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>疫情日报管理</title>
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
		<li class="active"><a href="${ctx}/epidemic/epidemicDaily/">疫情日报列表</a></li>
		<c:forEach items="${page.list}" var="epidemicDaily">
			<c:if test="${epidemicDaily.createBy eq fns:getUser().id and fn:contains(epidemicDaily.fillingDate,fns:getDate('yyyy-MM-dd'))}">
				<c:set var="is" value="1"></c:set>
			</c:if>
		</c:forEach>
		<c:if test="${empty is}"><%--当前人员 没有有日报--%>
			<shiro:hasPermission name="epidemic:epidemicDaily:edit"><li><a href="${ctx}/epidemic/epidemicDaily/form">疫情日报添加</a></li></shiro:hasPermission>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="epidemicDaily" action="${ctx}/epidemic/epidemicDaily/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>填表日期：</label>
				<input name="beginFillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${epidemicDaily.beginFillingDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> -
				<input name="endFillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${epidemicDaily.endFillingDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                <c:forEach items="${fns:getUser().roleList}" var="role">
                    <c:if test="${fns:getUser().admin or fn:contains(role,'commission') or fn:contains(role,'DirectorOfMediation') or fn:contains(role,'sz') }">
                        <c:set var="isRole" value="1"></c:set>
                    </c:if>
                </c:forEach>

                <c:if test="${not empty isRole}">
                    <label>部门：</label>
                    <sys:treeselect id="officeId" name="officeId" value="" labelName="office.name" labelValue=""
                                    title="部门" url="/sys/office/treeData?type=2&&officeType=1" cssClass="input-small" isAll="true" allowClear="true" notAllowSelectParent="true"/>

                </c:if>

            </li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th class="sort-column a.user_name">姓名</th>
				<th>部门</th>
				<th class="sort-column a.filling_date">填表日期</th>
				<th class="sort-column a.temperature">体温</th>
				<th class="sort-column a.health_condition">健康状况</th>
				<th class="sort-column a.is_egress">是否外出</th>
				<th >修改时间</th>
				<shiro:hasPermission name="epidemic:epidemicDaily:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="epidemicDaily">
			<tr>
				<td>
						${epidemicDaily.userName}
				</td>
                <td>
                        ${fns:getUserById(epidemicDaily.createBy.id).office.name}
                </td>
				<td>
						${epidemicDaily.fillingDate}
				</td>
				<td>
						${epidemicDaily.temperature}
				</td>
				<td>
						${epidemicDaily.healthCondition}
				</td>
				<td>
						${fns:getDictLabel(epidemicDaily.isEgress,"yes_no","")}
				</td>
				<td>
					<fmt:formatDate value="${epidemicDaily.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="epidemic:epidemicDaily:edit"><td>
					<c:if test="${empty epidemicDaily.workRemark}">
    					<a href="${ctx}/epidemic/epidemicDaily/form?id=${epidemicDaily.epidemicId}">修改</a>
						<a href="${ctx}/epidemic/epidemicDaily/delete?id=${epidemicDaily.epidemicId}" onclick="return confirmx('确认要删除该疫情日报吗？', this.href)">删除</a>
					</c:if>
					<a href="${ctx}/epidemic/epidemicDaily/form?id=${epidemicDaily.epidemicId}&type=view">详情</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>