<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审核受理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/auditacceptance/auditAcceptance/">审核受理列表</a></li>
		<li class="active"><a href="${ctx}/auditacceptance/auditAcceptance/form?id=${auditAcceptance.id}">审核受理<shiro:hasPermission name="auditacceptance:auditAcceptance:edit">${not empty auditAcceptance.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="auditacceptance:auditAcceptance:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="auditAcceptance" action="${ctx}/auditacceptance/auditAcceptance/save" method="post" class="form-horizontal">
		<form:hidden path="auditAcceptanceId"/>
		<form:hidden path="createDate"/>
		<form:hidden path="createBy"/>
		<form:hidden path="complaintMainId"/>
		<form:hidden path="complaintMain.complaintMainId"/>
		<form:hidden path="complaintMain.act.taskId"/>
		<form:hidden path="complaintMain.act.taskName"/>
		<form:hidden path="complaintMain.act.taskDefKey"/>
		<form:hidden path="complaintMain.act.procInsId"/>
		<form:hidden path="complaintMain.act.procDefId"/>
		<form:hidden path="complaintMain.procInsId"/>
		<form:hidden id="flag" path="complaintMain.act.flag"/>
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#patientS" data-toggle="tab">患方受理通知书</a>
			</li>
			<li>
				<a href="#hospitalS" data-toggle="tab">医方受理通知书</a>
			</li>
			<li>
				<a href="#patientT" data-toggle="tab">患方调解申请信息</a>
			</li>
			<li>
				<a href="#hospitalT" data-toggle="tab">医方调解申请信息</a>
			</li>
			<li>
				<a href="#people" data-toggle="tab">人民调解受理登记信息</a>
			</li>
			<li>
				<a href="#annex" data-toggle="tab">附件</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="patientS">
				<table class="table-form">
					<p style="margin:0pt; text-align:center">
						<span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bold">医疗纠纷调解受理通知书</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">患者</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
						<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
						<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患者姓名</span>
						<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">及其家属</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">你方与</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">_</span>
						<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
						<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">涉及医院名称</span>
						<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">发生的医疗纠纷</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">应你方与医院双方的调解申请</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">我单位经审查符合</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">受理条作</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">为保证案件调解工作能及时进行</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">请你方在五个工作日内提供如下</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">材</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">料</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医疗纠纷材料</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">(</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">治疗经过、诊疗疑问、诉求</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提供当事人的身份证、户口簿首页及本人页原件及复印件</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、授权委托书</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交委托人、被委托人的身份证原件及复印件</span>
					</p>
					<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">5</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、户籍中不能体现夫妻关系的需提交结婚证明</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">6</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交病历（门诊病历或住院病历）及相关检查资料</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">7</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医疗费用及具他相关我用票据原体</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、误工证明</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">(</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">有固定工作者</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">需提供发生医疗损害之前连</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">续三个月工资单及发生医疗损</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">害</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">之后实际损失证明</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">自由职业者</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">需提供上一年度完税证明</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">)</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">注</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">加盖财务公章。</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">9</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、患者死亡</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">需提交医学死亡证明和户口注销证明</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">10</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、新生儿提供</span>
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">出生证明。</span>
					</p>
					<p style="margin:0pt">
						<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">二、要求</span>
						<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span>
					</p><p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交复印件及资料均需用</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">A4</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">纸</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、如实陈述纠纷事实</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">不得提供虚假证明材料</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、所有提交的资料需签字、按手印进行确认</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">并注明日期</span></p>
					<p style="margin:0pt; orphans:0; text-align:justify; widows:0">
						<span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
					</p>
					<div class="cnzz" style="display: none;"></div>
		</table>
			</div>
			<div class="tab-pane fade" id="hospitalS">
				<table class="table-form">
					<div>
						<p style="margin:0pt; text-align:center"><span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bold">医疗纠纷调解受理通知书</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">涉及医院名称</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">医院</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">贵院与患者</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_____</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患者姓名</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">发生的医疗纠纷案件</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">应悦者及</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">质院双方</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">调解申请，我单位经审查符合受理条件</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">为保证案件调解工作的及时进行</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span></p><p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">请贵院在三个工作日内提供如下材料</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span></p><p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">一、材料名单</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交法定代表人身份证明书一份</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交授权委托书</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交委托人、被委托人的身份证复印件</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交执业许可证副本复印件</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">5</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、诊疗分析意见</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">6</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、出险医务人员身份证、执业证书复印件</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">7</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、与纠纷相关的证据材料</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、病历资料复印件</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">(3</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">份</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">)</span></p><p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">二、要求</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交材料均需用</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">A4</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">纸复印</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、所提交的材料均需加盖医院公章</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、出险医务人员需在身份证、执业证书复印件上签名</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、如三个工作日未提交所需资料的，视为放弃调解。</span></p><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
					</div>
					<div class="cnzz" style="display: none;"></div>
				</table>
			</div>
			<div class="tab-pane fade" id="patientT">
				<table class="table-form">

				</table>
			</div>
			<div class="tab-pane fade" id="hospitalT">
				<table class="table-form">

				</table>
			</div>
			<div class="tab-pane fade" id="people">
				<table class="table-form">
					<div><p style="margin:0pt; text-align:center"><span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bold">人民调解受理登记表</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">时间</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">人民调解委员会依当事人申请（人民调解委员会主动调解），经当事人同意，调解</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患者姓名</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">与</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">_</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">涉及医院名称</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">之前的纠纷。</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">案件来源：</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">案件来源</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:宋体; font-size:12pt">纠纷简要情况：</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; text-decoration:underline">{</span><span style="color:#d9001b; font-family:宋体; font-size:12pt; text-decoration:underline">纠纷概要</span><span style="color:#d9001b; font-family:Arial; font-size:12pt; text-decoration:underline">}</span><span style="color:#333333; font-family:Arial; font-size:12pt">__________________________________________________________________</span><span style="color:#333333; font-family:Arial; font-size:12pt">_______________________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">___________________________________________________________________________________</span><span style="color:#333333; font-family:Arial; font-size:12pt">_____________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt"> </span><span style="color:#333333; font-family:Arial; font-size:12pt">__________________________________________________________________________</span><span style="color:#333333; font-family:Arial; font-size:12pt">______________________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">______________________________________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">当事人（患方）</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">当事人（医方）</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:bold">&#xa0;</span></p><p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">登记人（签名）</span><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span></p><p style="margin:0pt; text-align:right"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">山西省医疗纠纷人民调解委员会</span></p><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p></div>
				</table>
			</div>
			<div class="tab-pane fade" id="annex">

			</div>
		</div>
	<table class="table-form">
		<tr>
			<td class="tit">
				案件来源：
			</td>
			<td>
				<form:input path="caseSource" htmlEscape="false" maxlength="1" class="input-xlarge "/>
			</td>
			<td class="tit">
				起保日期：
			</td>
			<td>
				<input name="guaranteeTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="${auditAcceptance.guaranteeTime}"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</td>
		</tr>
		<tr>
			<td class="tit">
				保险公司：
			</td>
			<td>
				<form:input path="insuranceCompany" htmlEscape="false" maxlength="50" class="input-xlarge "/>
			</td>
			<td class="tit">
				保单号：
			</td>
			<td>
				<form:input path="policyNumber" htmlEscape="false" maxlength="15" class="input-xlarge "/>
			</td>
		</tr>
		<tr>
			<td class="tit">
				诊疗方式：
			</td>
			<td>
				<form:input path="diagnosisMode" htmlEscape="false" class="input-xlarge "/>
			</td>
			<td class="tit">
				治疗结果：
			</td>
			<td>
				<form:input path="treatmentOutcome" htmlEscape="false" class="input-xlarge "/>
			</td>
		</tr>
		<tr>
			<td class="tit">
				下一处理环节：
			</td>
			<td>
				<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</td>
			<td class="tit">
				下一环节处理人：
			</td>
			<td>
				<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${auditAcceptance.nextLinkMan}" labelName="" labelValue="${auditAcceptance.linkEmployee.name}"
								title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="" allowClear="true" notAllowSelectParent="true" checked="true"/>
			</td>
		</tr>
	</table>
		<div class="form-actions">
			<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="auditacceptance:auditAcceptance:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="下一步" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		<c:if test="${not empty auditAcceptance.auditAcceptanceId}">
			<act:histoicFlow procInsId="${auditAcceptance.complaintMain.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>





<%--<div class="control-group">
			<label class="control-label">处理人：</label>
			<div class="controls">
				<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">处理日期：</label>
			<div class="controls">
				<form:input path="handleTime" htmlEscape="false" maxlength="20" class="input-xlarge "/>
			</div>
		</div>--%>