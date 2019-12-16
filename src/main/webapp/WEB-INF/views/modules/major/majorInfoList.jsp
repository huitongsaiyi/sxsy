<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>重大事件管理</title>
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
		<li class="active"><a href="${ctx}/major/majorInfo/">重大事件列表</a></li>
		<shiro:hasPermission name="major:majorInfo:edit"><li><a href="${ctx}/major/majorInfo/form">重大事件添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="majorInfo" action="${ctx}/major/majorInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>发生时间：</label>
				<input name="beginOccurrenceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${majorInfo.beginOccurrenceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> - 
				<input name="endOccurrenceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${majorInfo.endOccurrenceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>是否医院保安人员现场处理：</label>
				<form:select path="hospitalSecurityHandle" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>主表主键</th>
				<th>发生时间</th>
				<th>重大事件参与人数</th>
				<th>持续时间</th>
				<th>是否医院保安人员现场处理</th>
				<th>是否卫生、司法等部门挂牌督办</th>
				<th>是否公安机关出警</th>
				<th>出警次数</th>
				<th>是否有公安机关现场依法制止&ldquo;医闹&rdquo;事件</th>
				<th>职业医闹人员数量</th>
				<th>辱骂、殴打、故意伤害医务人员</th>
				<th>如有请填写受到伤害医务人员数量</th>
				<th>非法限制医务人员人身自由行动</th>
				<th>如有请填写限制医务人员数量</th>
				<th>采取暴力或者其他方法公然辱骂、恐吓医务人员行为</th>
				<th>故意损毁公私财物行为</th>
				<th>私设灵堂、摆放花圈、焚烧纸钱、悬挂横幅、堵塞大门等行为</th>
				<th>违规停尸</th>
				<th>非法携带枪支、弹药、管制器具或爆炸性、放射性、毒害性、腐蚀性物品进入医疗机构，危及公共安全行为</th>
				<th>故意扩大事态、教唆他人实施针对医疗机构或医务人员的违法犯罪行为，或以受他人委托处理医疗纠纷为名实施敲诈勒索、寻衅滋事等行为</th>
				<th>现场处理情况</th>
				<th>下一步调解计划</th>
				<th>update_date</th>
				<shiro:hasPermission name="major:majorInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="majorInfo">
			<tr>
				<td><a href="${ctx}/major/majorInfo/form?id=${majorInfo.id}">
					${majorInfo.complaintMainId}
				</a></td>
				<td>
					${majorInfo.occurrenceTime}
				</td>
				<td>
					${majorInfo.eventNumber}
				</td>
				<td>
					${majorInfo.duration}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.hospitalSecurityHandle, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.supervise, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.alarm, 'yes_no', '')}
				</td>
				<td>
					${majorInfo.numberPolice}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.checkEvent, 'yes_no', '')}
				</td>
				<td>
					${majorInfo.medicalTroubleNum}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.hurt, 'yes_no', '')}
				</td>
				<td>
					${majorInfo.hurtNumber}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.limitFree, 'yes_no', '')}
				</td>
				<td>
					${majorInfo.limitNumber}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.abusePeople, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.damageProperty, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.behavior, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.illegalMortuary, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.ammunition, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(majorInfo.provoke, 'yes_no', '')}
				</td>
				<td>
					${majorInfo.siteTreatment}
				</td>
				<td>
					${majorInfo.nextPlan}
				</td>
				<td>
					<fmt:formatDate value="${majorInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="major:majorInfo:edit"><td>
    				<a href="${ctx}/major/majorInfo/form?id=${majorInfo.id}">修改</a>
					<a href="${ctx}/major/majorInfo/delete?id=${majorInfo.id}" onclick="return confirmx('确认要删除该重大事件吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>