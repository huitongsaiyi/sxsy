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
		<li class="active"><a href="${ctx}/epidemic/epidemicDaily/statistics">疫情统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="epidemicDaily" action="${ctx}/epidemic/epidemicDaily/statistics" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>填表日期：</label>
				<input name="beginFillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${epidemicDaily.beginFillingDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/> <%---
				<input name="endFillingDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="${epidemicDaily.endFillingDate}"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>--%>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>部门</th>
				<th>应报</th>
				<th>实报</th>
				<th>体温大于37.3以上</th>
				<th>有外出情况</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sta">
			<tr>
				<td>
						${sta.deptName}
				</td>
				<td>
						${sta.yingbao}
				</td>
				<td>
						${sta.shibao}
				</td>
				<td>
						${sta.dayu}
				</td>
				<td>
						${sta.waichu}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>