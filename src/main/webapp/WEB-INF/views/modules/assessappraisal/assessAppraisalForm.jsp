<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>评估鉴定管理</title>
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
			// //全选(分析意见)
			// function doallcheck(){
			// 	var allche = document.getElementById("allid");
			// 	var che = document.getElementsByName("mycheckbox");
			// 	if(allche.checked == true){
			// 		for(var i=0;i<che.length;i++){
			// 			che[i].checked="checked";
			// 		}
			// 	}else{
			// 		for(var i=0;i<che.length;i++){
			// 			che[i].checked=false;
			// 		}
			// 	}
			// }
			// //全选(结论)
			// function doallcheck1(){
			// 	var allche = document.getElementById("allid1");
			// 	var che = document.getElementsByName("mycheckbox");
			// 	if(allche.checked == true){
			// 		for(var i=0;i<che.length;i++){
			// 			che[i].checked="checked";
			// 		}
			// 	}else{
			// 		for(var i=0;i<che.length;i++){
			// 			che[i].checked=false;
			// 		}
			// 	}
			// }

			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					$("input[type=checkbox]").each(function(){
						$(this).after("<input type=\"hidden\" name=\""+$(this).attr("name")+"\" value=\""
								+($(this).attr("checked")?"1":"0")+"\"/>");
						$(this).attr("name", "_"+$(this).attr("name"));
					});
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
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function addRow(list, idx, tpl, row){
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/assessappraisal/assessAppraisal/">评估鉴定列表</a></li>
		<li class="active"><a href="${ctx}/assessappraisal/assessAppraisal/form?id=${assessAppraisal.id}">评估鉴定<shiro:hasPermission name="assessappraisal:assessAppraisal:edit">${not empty assessAppraisal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="assessappraisal:assessAppraisal:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="assessAppraisal" action="${ctx}/assessappraisal/assessAppraisal/save" method="post" class="form-horizontal">
		<form:hidden path="assessAppraisalId"/>
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
		<sys:message content="${message}"/>
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#meetings" data-toggle="tab">评估鉴定会议表</a>
			</li>
			<li>
				<a href="#recordhf" data-toggle="tab">评估鉴定会笔录(患方)</a>
			</li>
			<li>
				<a href="#recordyf" data-toggle="tab">评估鉴定会笔录(医方)</a>
			</li>
			<li>
				<a href="#zjopinion" data-toggle="tab">专家评估意见</a>
			</li>
			<li>
				<a href="#opinion" data-toggle="tab">意见书</a>
			</li>
			<li>
				<a href="#fj" data-toggle="tab">附件</a>
			</li>
		</ul>

	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="meetings">
			<table class="table-form">
				<p style="margin:0pt; text-align:center">
					<span style="color:#333333; font-family:宋体; font-size:15pt; font-weight: bolder;">山西省医疗纠纷人民调解委员会</span>
				<p style="margin:0 auto ;width: 190px;">

					<span style="color:#d9001b; font-family:Arial; font-size:15pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:15pt; font-weight:normal; text-decoration:underline">申请类型</span>
					<span style="color:#d9001b; font-family:Arial; font-size:15pt; font-weight:normal; text-decoration:underline">}</span>
					<span style="color:#333333; font-family:Arial; font-size:15pt; font-weight:normal"></span>
					<span style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bolder;">工作程序</span>

				</p>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">时间</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">地点：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">地点</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">案件：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">案件</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">一、主持人向评估委员会成员介绍纠纷概要、纠纷焦点</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">二、医患双方代表进入会场</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">三、介绍评估委员会成员、确认医患双方及参会人员身份</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">专家：</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">医学专家</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">,</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">法学专家</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">患方：</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患方</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">医方：</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">医方</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">主持人：</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">主持人</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>

					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">书记员：</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span>
					<span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">书记员</span>
					<span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
				</p>

				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">请问医患双方确认以上参会人员身份有无要求回避</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">患方：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">医方：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">四、宣读有关纪律及注意事项：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、双方当事人及工作人员酒后不的参会，会议中不得吸烟、不得中途退场、不得当众喧哗。保持会场安静，遵守会场秩序。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、参会人应当将通讯工具关闭或调至静音状态（请大家配合一下）。会议期间不得录音、录像。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、医患双方要如实陈述纠纷事实，提供的证据应当真实、合法、有效，不得伪造、毁灭证据，妨碍评估人员正确作出调解。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、双方当事人不得因纠纷矛盾相互指责、谩骂使用人身攻击性语言，激化矛盾；不得对评估委员会成员进行威胁、恐吓，以非法手段 干扰评估会的正常工作秩序。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">5</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、当事人或代理人陈述时，其他参加人员不得发言，需要补充时，须在当事人或代理人结束发言后，经主持人同意方可进行补充。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">6</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、对于有不良行为的参加人，山西省医疗纠纷人民调解委员会将责令其退出会议室。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">7</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、要求各位专家客观公正进行分析并得出结论。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、本次会议的主要目的是要通过医学专家评估及法律顾问责任认定，完成本次医疗纠纷的定责、定损、计赔事宜，评估结论将会在会后3到5个工作日内由主管调解员负责口头告知医患双方。若医患双方对评估结论有异议，有权申请医学会鉴定或司法诉讼。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> 以上宣读内容医患双方是否听清楚？有无异议？</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">患方：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">医方：</span>
				</p>
				<p style="margin:0pt">
				<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">五、患方陈述、提出诉求，提交证明材料。</span>

			    </p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">六、医方陈述，提供证明材料。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">七、医方退场。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">八、专家提问、质证</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">九、患方确认笔录，签名，按手印，退场。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">十、医方入场，专家提问，质证。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">十一、医方入场，专家提问，质证。</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">患方署名：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">医方署名：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">十二、评估组成员进行讨论分析，并在规定时间内得出结论。</span>

				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">主持人署名：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
				</p>
				<p style="margin:0pt">
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">书记人署名：</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span>
					<span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span>
					<span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal;">时间：</span>
				</p>
				<div class="cnzz" style="display: none;"></div>
			</table>
		</div>
		<div class="tab-pane fade " id="recordhf">
			<table class="table-form">
				<tr>
					<td class="tit">
						开始时间：
					</td>
					<td>
						<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="${assessAppraisal.recordInfo1.startTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					<td class="tit">
						结束时间：
					</td>
					<td>
						<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="${assessAppraisal.recordInfo1.endTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						地点：
					</td>
					<td>
						<form:input path="recordInfo1.recordAddress" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit">
						事由：
					</td>
					<td>
						<form:input path="recordInfo1.cause" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						主持人：
					</td>
					<td>
						<form:input path="recordInfo1.host" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit">
						记录人：
					</td>
					<td>
						<form:input path="recordInfo1.noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						笔录内容：
					</td>
					<td colspan="3">
						<form:textarea path="recordInfo1.recordContent" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge " cssStyle="width: 560px;"/>
					</td>

				</tr>
			</table>
		</div>
		<div class="tab-pane fade " id="recordyf">
			<table class="table-form">
				<tr>
					<td class="tit">
						开始时间：
					</td>
					<td>
						<input name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="${assessAppraisal.recordInfo2.startTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
					<td class="tit">
						结束时间：
					</td>
					<td>
						<input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="${assessAppraisal.recordInfo2.endTime}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						地点：
					</td>
					<td>
						<form:input path="recordInfo2.recordAddress" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit">
						事由：
					</td>
					<td>
						<form:input path="recordInfo2.cause" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						主持人：
					</td>
					<td>
						<form:input path="recordInfo2.host" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit">
						记录人：
					</td>
					<td>
						<form:input path="recordInfo2.noteTaker" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						笔录内容：
					</td>
					<td colspan="3">
						<form:textarea path="recordInfo2.recordContent" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge " cssStyle="width: 560px;"/>
					</td>

				</tr>
			</table>
		</div>
		<div class="tab-pane fade " id="zjopinion">
			<table class="table-form">
				<tr>
					<td class="tit">
						患者姓名：
					</td>
					<td>
						<form:input path="patientName" htmlEscape="false" maxlength="10" class="input-xlarge "/>
					</td>
					<td class="tit">
						患者性别：
					</td>
					<td>
						<form:select path="patientSex" class="input-medium" style="text-align:center">
							<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="tit">
						患者年龄：
					</td>
					<td>
						<form:input path="patientAge" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit">
						住院号：
					</td>
					<td>
						<form:input path="hospitalNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						涉及医院：
					</td>
					<td colspan="3">
						<form:input path="involveHospital" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>

				</tr>
				<tr>
					<td class="tit">诊断分析：</td>
					<td colspan="3">
						<form:textarea path="diagnosticAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">治疗分析：</td>
					<td colspan="3">
						<form:textarea path="treatmentAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">其他医疗分析：</td>
					<td colspan="3">
						<form:textarea path="otherMedicalAnalysis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						医学专家：
					</td>
					<td colspan="3">
						<form:input path="medicalExpert" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						法律顾问：
					</td>
					<td colspan="3">
						<form:input path="legalExpert" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>
				<tr>
					<td class="tit">
						其他：
					</td>
					<td colspan="3">
						<form:input path="other" htmlEscape="false" maxlength="32" class="input-xlarge " cssStyle="width: 560px;"/>
					</td>
				</tr>

			</table>
		</div>
		<div class="tab-pane fade " id="opinion">
			<ul id="myTab1" class="nav nav-tabs">
				<li class="active">
					<a href="#affected" data-toggle="tab">患方</a>
				</li>
				<li>
					<a href="#medical" data-toggle="tab">医方</a>
				</li>
				<li>
					<a href="#profile" data-toggle="tab">诊疗概要</a>
				</li>
				<li>
					<a href="#points" data-toggle="tab">争议要点</a>
				</li>
				<li>
					<a href="#analysisOpinion" data-toggle="tab">分析意见</a>
				</li>
				<li>
					<a href="#conclusion" data-toggle="tab">结论</a>
				</li>
			</ul>
			<div id="myTabContent1" class="tab-content">
				<div class="tab-pane fade in active" id="affected">
					<h4 style="color: black; " >患者</h4>

					<div class="tab-pane fade in active" id="mediation">
						<input type="hidden" name="linkType1" value="1">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
							<tr>
								<th class="hide"></th>
								<th width="10">姓名</th>
								<th width="100">性别</th>
								<th width="100">身份证号</th>
								<th width="100">住址</th>
								<shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
									<th width="100">操作</th>
								</shiro:hasPermission>
							</tr>
							</thead>
							<tbody id="patientLinkEmps1"></tbody>
							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
								<tfoot>
								<tr><td colspan="7"><a href="javascript:" onclick="addRow('#patientLinkEmps1', mediateEvidenceRowIdx, mediateEvidenceTpl);mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;" class="btn">新增</a></td></tr>
								</tfoot></shiro:hasPermission>
						</table>
						<script type="text/template" id="mediateEvidenceTpl">//<!--
						<tr id="patientLinkEmps1{{idx}}">
							<td class="hide">
								<input id="patientLinkEmps1{{idx}}_id" name="patientLinkEmps1[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkEmps1{{idx}}_medicalOfficeEmpId" name="patientLinkEmps1[{{idx}}].mediateRecord" type="hidden" value="{{row.medicalOfficeEmpId}}"/>
								<input id="patientLinkEmps1{{idx}}_relationId" name="patientLinkEmps1[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkEmps1{{idx}}_delFlag" name="patientLinkEmps1[{{idx}}].delFlag" type="hidden" value="{{row.delFlag}}"/>
							</td>
							<td >
								<input id="patientLinkEmps1{{idx}}_patientLinkName" name="patientLinkEmps1[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="32" class="input-small "/>

							</td>
							<td>
								<input id="patientLinkEmps1{{idx}}_patientLinkSex" name="patientLinkEmps1[{{idx}}].patientLinkSex" type="text" value="{{row.patientLinkSex}}" maxlength="100" class="required" />
							</td>
							<td>
								<input id="patientLinkEmps1{{idx}}_idNumber" name="patientLinkEmps1[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="32" class="required" />
							</td>
							<td>
								<input id="patientLinkEmps1{{idx}}_patientLinkAddress" name="patientLinkEmps1[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="32" class="required" />
							</td>
							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmps1{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
						</script>
						<script type="text/javascript">
							var mediateEvidenceRowIdx = 0, mediateEvidenceTpl = $("#mediateEvidenceTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
							$(document).ready(function() {
								var data = ${fns:toJson(mediateEvidence.patientLinkEmps1)};
								for (var i=0; i<data.length; i++){
									addRow('#medicalofficeempList1', mediateEvidenceRowIdx, mediateEvidenceTpl, data[i]);
									mediateEvidenceRowIdx = mediateEvidenceRowIdx + 1;
								}
							});
						</script>
					</div>
					<h4 style="color: black; " >联系人</h4>
					<div class="controls" style="margin-left: 0px;">
						<input type="hidden" name="linkType2" value="2">
						<table id="contentTable1" class="table table-striped table-bordered table-condensed">
							<thead>
							<tr>
								<th class="hide" style="text-align: center"></th>
								<th style="text-align: center">姓名</th>
								<th style="text-align: center">与患者关系</th>
								<th style="text-align: center">联系电话</th>


								<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><th width="10">操作</th></shiro:hasPermission>
							</tr>
							</thead>
							<tbody id="patientLinkEmps">
							</tbody>
							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><tfoot>
							<tr><td colspan="8"><a href="javascript:" onclick="addRow('#patientLinkEmps', advanceAccountDetail1RowIdx, advanceAccountDetailTp2);advanceAccountDetail1RowIdx = advanceAccountDetail1RowIdx + 1;" class="btn">新增</a></td></tr>
							</tfoot></shiro:hasPermission>
						</table>
						<script type="text/template" id="advanceAccountDetailTp2">//<!--
						<tr id="patientLinkEmps{{idx}}">
							<td class="hide">
								<input id="patientLinkEmps{{idx}}_id" name="patientLinkEmps[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkEmps{{idx}}_patientLinkEmpId" name="patientLinkEmps[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkEmps{{idx}}_relationId" name="patientLinkEmps[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkEmps{{idx}}_delFlag" name="patientLinkEmps[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="patientLinkEmps{{idx}}_patientLinkName" name="patientLinkEmps[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="32" class="input-small required"/>
							</td>
							<td>
								<input id="patientLinkEmps{{idx}}_patientRelation" name="patientLinkEmps[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="patientLinkEmps{{idx}}_patientLinkMobile" name="patientLinkEmps[{{idx}}].patientLinkMobile" type="text" value="{{row.patientLinkMobile}}" maxlength="1" class="input-small required"/>
							</td>


							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmps{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
						</script>
						<script type="text/javascript">
							var advanceAccountDetail1RowIdx = 0, advanceAccountDetailTp2 = $("#advanceAccountDetailTp2").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
							$(document).ready(function() {
								var data = ${fns:toJson(assessAppraisal.patientLinkEmps)};
								for (var i=0; i<data.length; i++){
									addRow('#advanceAccounpatientLinkEmpstDetailList', advanceAccountDetail1RowIdx, advanceAccountDetailTp2, data[i]);
									advanceAccountDetail1RowIdx = advanceAccountDetail1RowIdx + 1;
								}
							});
						</script>
					</div>
				</div>
				<div class="tab-pane fade" id="medical">
					<div class="controls" style="margin-left: 0px;">
					<table id="contentTable2" class="table table-striped table-bordered table-condensed">
						<thead>
						<tr>
							<th class="hide" style="text-align: center"></th>
							<th style="text-align: center">医疗机构名称</th>
							<th style="text-align: center">联系人</th>
							<th style="text-align: center">联系电话</th>
							<th style="text-align: center">职务</th>

							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><th width="10">操作</th></shiro:hasPermission>
						</tr>
						</thead>
						<tbody id="medicalofficeempList2">
						</tbody>
						<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><tfoot>
						<tr><td colspan="8"><a href="javascript:" onclick="addRow('#medicalofficeempList2', advanceAccountDetailRowIdx, advanceAccountDetailTp3);advanceAccountDetailRowIdx = advanceAccountDetailRowIdx + 1;" class="btn">新增</a></td></tr>
						</tfoot></shiro:hasPermission>
					</table>
					<script type="text/template" id="advanceAccountDetailTp3">//<!--
						<tr id="medicalofficeempList2{{idx}}">
							<td class="hide">
								<input id="medicalofficeempList2{{idx}}_id" name="medicalofficeempList2[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="medicalofficeempList2{{idx}}_medicalOfficeEmpId" name="medicalofficeempList2[{{idx}}].medicalOfficeEmpId" type="hidden" value="{{row.medicalOfficeEmpId}}"/>
								<input id="medicalofficeempList2{{idx}}_relationId" name="medicalofficeempList2[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="medicalofficeempList2{{idx}}_delFlag" name="medicalofficeempList2[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>
								<input id="medicalofficeempList2{{idx}}_medicalOfficeName" name="medicalofficeempList2[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="32" class="input-small required"/>
							</td>
							<td>
								<input id="medicalofficeempList2{{idx}}_medicalOfficeAgent" name="medicalofficeempList2[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="input-small "/>
							</td>
							<td>
								<input id="medicalofficeempList2{{idx}}_medicalOfficeMobile" name="medicalofficeempList2[{{idx}}].medicalOfficeMobile" type="text" value="{{row.medicalOfficeMobile}}" maxlength="1" class="input-small required"/>
							</td>
							<td>
								<input id="medicalofficeempList2{{idx}}_medicalOfficePost" name="medicalofficeempList2[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="32" class="input-small required"/>
							</td>

							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#medicalofficeempList2{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var advanceAccountDetailRowIdx = 0, advanceAccountDetailTp3= $("#advanceAccountDetailTp3").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						$(document).ready(function() {
							var data = ${fns:toJson(assessAppraisal.medicalofficeempList2)};
							for (var i=0; i<data.length; i++){
								addRow('#medicalofficeempList2', advanceAccountDetailRowIdx, advanceAccountDetailTp3, data[i]);
								advanceAccountDetailRowIdx = advanceAccountDetailRowIdx + 1;
							}
						});
					</script>

				</div>



			</div>
				<div class="tab-pane fade" id="profile">
					<table class="table-form">
						<tr>
							<td class="tit">诊疗概要:</td>
							<td colspan="3">
								<form:textarea path="proposal.treatmentSummary" htmlEscape="false" rows="10" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tab-pane fade" id="points">
					<table class="table-form">
						<tr>
							<td class="tit">患方认为:</td>
							<td colspan="3">
								<form:textarea path="proposal.patientThink" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
						<tr>
							<td class="tit">医方认为:</td>
							<td colspan="3">
								<form:textarea path="proposal.doctorThink" htmlEscape="false" rows="5" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tab-pane fade" id="analysisOpinion">
					<table class="table-form">

						<tr>
							<th></th>
							<th>类型</th>
							<th>内容</th>
						</tr>
						<c:forEach items="${relationModel1}" var="column" varStatus="vs">
						<tr>
							<td nowrap style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="typeInfosList[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="typeInfosList[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="checkbox" name="typeInfosList[${vs.index}].label" value="1" ${column.typeId eq '1' ? 'checked' : ''}/>
							</td>
							<td style="text-align:center;vertical-align:middle;">${column.typeName}</td>
							<td style="text-align:center;vertical-align:middle;">${column.content}</td>
						</tr>
						</c:forEach>
					</table>
					<table class="table-form">
						<tr>
							<td class="tit">
								诊断:
							</td>
							<td>
								<form:textarea path="proposal.diagnosis" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
						<tr>
							<td class="tit">
								治疗:
							</td>
							<td>
								<form:textarea path="proposal.treatment" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
						<tr>
							<td class="tit">
								其他:
							</td>
							<td>
								<form:textarea path="proposal.other" htmlEscape="false" rows="2" maxlength="500" class="input-xxlarge " cssStyle="width: 1374px;"/>
							</td>
						</tr>
					</table>
				</div>
				<div class="tab-pane fade" id="conclusion">
					<table class="table-form">
						<tr>

							<td></td>
							<th>类型</th>
							<th>内容</th>
						</tr>
						<c:forEach items="${relationModel2}" var="column" varStatus="vs">
						<tr>
							<td nowrap style="text-align:center;vertical-align:middle;">
								<input type="hidden" name="typeInfosList2[${vs.index}].typeId" value="${column.typeId}"/>
								<input type="hidden" name="typeInfosList2[${vs.index}].delFlag" value="${column.delFlag}"/>
								<input type="checkbox" name="typeInfosList2[${vs.index}].label" value="1" ${column.typeId eq '1' ? 'checked' : ''}/>
							</td>

							<td style="text-align:center;vertical-align:middle;">
									${column.typeName}
							</td>
							<td style="text-align:center;vertical-align:middle;">
									${column.content}
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>

			</div>
		</div>
		<div class="tab-pane fade " id="fj">
			<table class="table-form">
				<tr style=" " >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">签到表：</td>
					<input type="hidden"  name="fjtype1" value="16">
					<td style="width: 450px; ">

						<input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"  value="${files1}"/>
						<div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessappraisal/assessAppraisal/qiandao" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style=" " >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
					<input type="hidden"  name="fjtype2" value="17">
					<td style="width: 450px; ">

						<input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"  value="${files2}"/>
						<div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbl" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style="" >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
					<input type="hidden" name="fjtype3" value="18">
					<td style="width: 450px; ">
						<input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge" value="${files3}" />
						<div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbc" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style="" >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
					<input type="hidden"  name="fjtype4" value="19">
					<td style="width: 450px; ">

						<input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"  value="${files4}"/>
						<div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibl" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style="" >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
					<input type="hidden" name="fjtype5" value="20">
					<td style="width: 450px;">
						<input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge" value="${files5}" />
						<div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibc" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style="" >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">专家评估意见表：</td>
					<input type="hidden" name="fjtype6" value="21">
					<td style="width: 450px;">
						<input type="hidden" id="files6" name="files5" htmlEscape="false" class="input-xlarge" value="${files6}" />
						<div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"  uploadPath="/assessappraisal/assessAppraisal/zhuanjiayj" selectMultiple="true" /></div>
					</td>

				</tr>
				<tr style="" >
					<td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗损害核算单：</td>
					<input type="hidden" name="fjtype7" value="22">
					<td style="width: 450px;">
						<input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge" value="${files7}" />
						<div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"  uploadPath="/assessappraisal/assessAppraisal/yiliaosh" selectMultiple="true" /></div>
					</td>

				</tr>
			</table>
		</div>
	</div>



		<table class="table-form">
			<tr>
				<td class="tit">
					申请类型：
				</td>
				<td>
					<form:select path="applyType" class="input-medium" style="text-align:center">
						<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
				</td>
				<td class="tit">
					责任比例：
				</td>
				<td>
					<form:input path="responsibilityRatio" htmlEscape="false" maxlength="10" class="input-xlarge "/>
				</td>
			</tr>
			<tr>
				<td class="tit">
					主持人：
				</td>
				<td>
					<form:input path="host" htmlEscape="false" maxlength="10" class="input-xlarge "/>
					<%--<form:checkboxes path="host" items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>--%>
				</td>
				<td class="tit">
					书记员：
				</td>
				<td>
					<form:input path="clerk" htmlEscape="false" maxlength="32" class="input-xlarge "/>
				</td>
			</tr>
			<%--<tr>--%>
				<%--<td class="tit">--%>
					<%--处理人：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<form:input path="handlePeople" htmlEscape="false" maxlength="32" class="input-xlarge "/>--%>
				<%--</td>--%>
				<%--<td class="tit">--%>
					<%--处理日期：--%>
				<%--</td>--%>
				<%--<td>--%>
					<%--<input name="handleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "--%>
						   <%--value="${assessAppraisal.handleTime}"--%>
						   <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>--%>
				<%--</td>--%>
			<%--</tr>--%>
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
			<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>