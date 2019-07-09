<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作量统计</title>
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
		<li class="active"><a href="${ctx}/complaint/complaintInfo/">工作量统计</a></li>
	</ul>
	<form:form id="searchForm" action="${ctx}/complaint/complaintInfo/statement" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>类型：</label>
				<select id="type" name="type"  class="input-medium" onchange="changeDate(this.value)">
					<option value="day">日表</option>
					<option value="month">月表</option>
				</select>
			</li>
			<li id="day" style="">
				<label>日期：</label>
				<input id="visitorDate" name="visitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value=""
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>
			</li>

			<li id="month" style="display:none">
				<label>日期：</label>
				<input id="visitorMonthDate" name="visitorDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value=""
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
			</li>

			<li><label>部门：</label>
				<sys:treeselect id="involveDepartment" name="involveDepartment" value="" labelName="departmentName" labelValue=""
					title="部门" url="/sys/office/treeData?type=2&officeType=2" isAll="true"  cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li><label>人员</label>
				<sys:treeselect id="involveEmployee" name="involveEmployee" value="" labelName="departmentName" labelValue=""
								title="部门" url="/sys/office/treeData?type=3&officeType=2" isAll="true"  cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
			<li class="btns"><input id="btnExportSubmit" class="btn btn-primary" type="submit" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align: center" class="sort-column case_number">部门</th>
				<th style="text-align: center" class="sort-column visitor_name">人员</th>
				<th style="text-align: center" class="sort-column visitor_mobile">接待日期</th>
				<th style="text-align: center" class="sort-column patient_relation">同意书见证</th>
				<th style="text-align: center" class="sort-column patient_name">投诉接待数量(总)</th>
				<th style="text-align: center" class="sort-column patient_sex">医院转办数量</th>
				<th style="text-align: center" class="sort-column patient_age">转调解数量</th>
				<th style="text-align: center" class="sort-column visitor_number">当面处理数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="statement">
			<c:forEach items="${statement}" var="single">
				<tr>
					<td style="text-align: center" >
							${single.deptName}
					</td>
					<td style="text-align: center" >
							${single.name}
					</td>
					<td style="text-align: center" >
							${single.visitor_date}
					</td>
					<td style="text-align: center" >

					</td>
					<td style="text-align: center" >
							${single.amount}
					</td>
					<td style="text-align: center" >
							${single.zb}
					</td>
					<td style="text-align: center" >
							${single.ytw}
					</td>
					<td style="text-align: center" >
							${single.dm}
					</td>
				</tr>
			</c:forEach>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>