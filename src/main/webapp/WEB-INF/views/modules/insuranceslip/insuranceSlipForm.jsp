<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>投保单管理</title>
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
	<script>
        function officeTreeselectCallBack(v, h, f,id){
            // alert(111)
            // var hospital = $("#office").val
            var path = "${ctx}/insuranceslip/insuranceSlip/officeInfo";
            $.post(path,{hospital:id},function (res) {
                // var res1 = JSON.parse(res);
                console.log(typeof res);
                console.log(res.data)
                if (res.data.policyPhone != '' && res.data.policyPhone != undefined){
                    $("#policyPhone").val(res.data.policyPhone)
                }
                if (res.data.emailAddress != '' && res.data.emailAddress != undefined){
                    $("#emailAddress").val(res.data.emailAddress)
                }
                if (res.data.sitePostcode != '' && res.data.sitePostcode != undefined){
                    $("#sitePostcode").val(res.data.sitePostcode)
                }
                if (res.data.hospitalGrade != '' && res.data.hospitalGrade != undefined){
                    $("#grade").text(res.data.hospitalGrade)
                }
                if (res.data.sickbedNumber != '' && res.data.sickbedNumber != undefined){
                    $("#sickbedNumber").val(res.data.sickbedNumber)
                }
                if (res.data.postalCode != '' && res.data.postalCode != undefined){
                    $("#postalCode").val(res.data.postalCode)
                }
                //年累计赔偿限额
                if (res.data.accumulatedQuota != '' && res.data.accumulatedQuota != undefined){
                    $("#accumulatedQuota").text(res.data.accumulatedQuota)
                    // var  tj = res.data.accumulatedQuota + 500000
                    // $("#accumulatedQuota option[value=' " + res.data.accumulatedQuota + "' label=' " + res.data.accumulatedQuota + "']").attr("selected", true);
					<%--$("#accumulatedQuota").append(<form:option value="" label=""/>)--%>
                    // $("#accumulatedQuota").find("option:contains('res.data.accumulatedQuota')").attr("selected",ture);
                }
                //每次赔偿限额
                if (res.data.ecerytimeQuota != '' && res.data.ecerytimeQuota != undefined){
                    $("#everytimeQuota").text(res.data.ecerytimeQuota)
                }
                //每人累计赔偿限额
                if (res.data.everyoneQuota != '' && res.data.everyoneQuota != undefined){
                    $("#everyoneQuota").text(res.data.everyoneQuota)
                }
                //法律费用 累计限额
                if (res.data.lawAccumulatedQuota != '' && res.data.lawAccumulatedQuota != undefined){
                    $("#lawAccumulatedQuota").text(res.data.lawAccumulatedQuota)
                }
                //法律费用 每次限额
                if (res.data.lawEverytimeQuota != '' && res.data.lawEverytimeQuota != undefined){
                    $("#lawEverytimeQuota").text(res.data.lawEverytimeQuota)
                }
            },"json")
		}
		function allmingdan(){
            $("#selEveryonePremium").prop('disabled','disabled');
            $("#selClinicOperationNumber").prop('disabled','disabled');
            $("#selClinicNotoperationNumber").prop('disabled','disabled');
            $("#selMedicalLaboratoryNumber").prop('disabled','disabled');
            $("#selPremiumTotal").prop('disabled','disabled');

            $('#allMedicalNumber').removeAttr("disabled");
            $('#allEveryonePremium').removeAttr("disabled");
            $('#allPremiumTotal').removeAttr("disabled");

			var number = $('#allMedicalNumber').val();
			var money = $("#allEveryonePremium").val();
			var sum = number * money;
			$("allPremiumTotal").text(sum);
		}
        //
        function selmingdan(){
            $("#selEveryonePremium").removeAttr("disabled");
            $("#selClinicOperationNumber").removeAttr("disabled");
            $("#selClinicNotoperationNumber").removeAttr("disabled");
            $("#selMedicalLaboratoryNumber").removeAttr("disabled");
            $("#selPremiumTotal").removeAttr("disabled");

            $('#allMedicalNumber').prop('disabled','disabled');
            $('#allEveryonePremium').prop('disabled','disabled');
            $('#allPremiumTotal').prop('disabled','disabled');

        }
        //提交
		function tijiao() {
			$("#arbitrator").removeAttr("disabled");
        }
        //诉讼
        function shusong() {
            $("#arbitrator").prop('disabled','disabled');
        }

        //附加险
        function addRisk() {
            // alert(11);
            var options = $("#addittionRisk option:selected");
            var neirong = options.val();
            if (neirong == "changsuo"){
                // alert(22);
                //获取list集合
                var sitePremium = '${fns:getDictList('site_premium')}';
                sitePremium = sitePremium.substr(0,sitePremium.length-1); //删除开头结尾
                sitePremium = sitePremium.substr(1,sitePremium.length-1);
                sitePremium = sitePremium.replace(/\s*/g,""); //去除字符串中所有的而空格
				var list = new Array();
				list = sitePremium.split(",");
                console.log(list);
                //病床数
                var sickbedNumber = $("#sickbedNumber").val();
				for (i = 0; i <list.length;i++){
                    var list2 = new Array();
					var str = list[i];
					list2 = str.split("-");
					minNum = list2[0];
					maxNum = list2[1];
                    if (Number(sickbedNumber) >= Number(minNum) && Number(sickbedNumber) <= Number(maxNum)){
                        //附加险 场所 保险费
                        var money = '${fns:getDictValue("0-99" , "site_premium","费无" )}';
                        alert(money);
						$("#addittionPremium").val(money);
						//每人赔偿限额
                        var siteAddup = '${fns:getDictValue("0-99" , "site_addup","人无" )}';
						$("#appendEverytimeQuota").val(siteAddup);
						//累计赔偿限额
                        var siteYearAddup = '${fns:getDictValue("0-99" , "site_year_addup","无" )}';
                        $("#appendAccumulatedQuota").val(siteYearAddup)
						break;
					}
				}
            }
            if (neirong == "shanghai"){

			}
            if (neirong == "chuanran"){

            }
            if (neirong == "waiping"){

            }
        }

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/insuranceslip/insuranceSlip/">投保单列表</a></li>
		<li class="active"><a href="${ctx}/insuranceslip/insuranceSlip/form?id=${insuranceSlip.id}">投保单<shiro:hasPermission name="insuranceslip:insuranceSlip:edit">${not empty insuranceSlip.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="insuranceslip:insuranceSlip:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="insuranceSlip" action="${ctx}/insuranceslip/insuranceSlip/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<h2 style="text-align: center;font-weight: bold">
			中国人寿财产保险股份有限公司
		</h2>
		<h3 style="text-align: center;font-weight: bold">
			医疗责任保险投保单
		</h3>
		<p>
			欢迎您到中国人寿财产保险股份有限公司投保！请您在投保前务必详细阅读相关保险条款，特别注意<b>责任免除、投保人及被保险人义务、赔偿处理</b>等内容，据实回答保险人就投保事项提出的相关询问，并用蓝色或黑色墨水笔如实填写保单，投保后相关内容发生变动，请及时通知保险人。
		</p>
		<div class="tab-pane fade in active" id="visitor">
			<table class="table-form">
				<tr>
					<td class="tit" width="7%"><font color="red">*</font>投保人名称</td>
					<td colspan="2" style="font-size: 16px;">
						<sys:treeselect id="office" name="office.id" value="${insuranceSlip.office.id}" labelName="office.name" labelValue="${insuranceSlip.office.name}"
										title="部门" url="/sys/office/treeData?type=2"  cssClass="" allowClear="true" notAllowSelectParent="true"/>
					</td>
					<td class="tit" width="3%">联系电话</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="policyPhone" htmlEscape="false" maxlength="15" class="input-xlarge "/>
					</td>
				</tr>
				<tr class="border-top-none">
					<td class="tit" width="10%">通信地址和邮编</td>
					<td colspan="2" style="font-size: 16px; ">
						<form:input path="sitePostcode" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">电子邮箱</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="emailAddress" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">被保险人名称</td>
					<td colspan="2" style="font-size: 16px;">
						<form:input path="theInsured" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">联系电话</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="theInsuredPhone" htmlEscape="false" maxlength="15" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">被保险人地址</td>
					<td colspan="2" style="font-size: 16px;">
						<form:input path="theInsuredSite" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">邮政编码</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="postalCode" htmlEscape="false" maxlength="15" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">投保区域范围</td>
					<td colspan="6" style="font-size: 16px;">
						<form:input path="insureArea" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width: 500px"/>
					</td>
				</tr>
				<tr>
					<td rowspan="5" style="text-align: center">被保险人<br>基本情况</td>
					<td class="tit" width="10%">创建时间：</td>
					<td colspan="1" style="font-size: 16px;">
						<input name="creationTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</td>
					<td class="tit" width="10%">执业许可证号：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="practiceNumber" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">医疗机构等级：</td>
					<td colspan="1" style="font-size: 16px;" id="grade">
						<%--<form:select path="grade" class="input-xlarge ">--%>
							<%--<form:option value="" label=""/>--%>
							<%--<form:options items="${fns:getDictList('hospital_grade')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
						<%--</form:select>--%>
					</td>
					<td class="tit" width="10%">类型：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:select path="mold" class="input-xlarge ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">所有制性质：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="nature" htmlEscape="false" maxlength="32" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">主管部门：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="department" htmlEscape="false" maxlength="64" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">病床数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="sickbedNumber" htmlEscape="false" maxlength="5" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">年诊疗人数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="peopleNumber" htmlEscape="false" maxlength="5" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">医务人员人数：</td>
					<td colspan="1" style="font-size: 16px;">
							<form:input path="medicalStaffNumber" htmlEscape="false" class="input-xlarge "/>
					</td>
				</tr>

				<tr>
					<td rowspan="3" style="text-align: center">医疗责任<br>赔偿限额:</td>
					<td class="tit" width="10%">每人赔偿限额:</td>
					<td colspan="2" style="font-size: 16px;" id="everyoneQuota">
						<%--<form:input path="everyoneQuota" htmlEscape="false" class="input-xlarge "/>--%>
						<%--<form:select path="everyoneQuota" class="input-xlarge ">--%>
							<%--<form:option value="" label=""/>--%>
							<%--<form:options items="${fns:getDictList('eceryone_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
						<%--</form:select>--%>
					</td>
					<td class="tit" rowspan="1" width="10%" style="font-size: 16px; align-content: center">
						每次索赔免赔额
					</td>

				</tr>
				<tr>
					<td class="tit" width="10%">其中：精神损害赔偿额:</td>
					<td colspan="2" style="font-size: 16px;">
						<form:input path="spiritQuota" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td rowspan="2" width="10%" id="everytimeQuota" style="font-size: 16px; align-content: center">
							<%--<form:input path="everytimeQuota" htmlEscape="false" class="input-xlarge "/>--%>
							<%--<form:select path="everytimeQuota" class="input-xlarge ">--%>
							<%--<form:option value="" label=""/>--%>
							<%--<form:options items="${fns:getDictList('ecerytime_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
							<%--</form:select>--%>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">累计赔偿限额:</td>
					<td  style="font-size: 16px;" id="accumulatedQuota">
						<%--<form:input path="accumulatedQuota" htmlEscape="false" class="input-xlarge "/>--%>
						<%--<form:select path="accumulatedQuota" class="input-xlarge ">--%>
							<%--<form:option value="" label=""/>--%>
							<%--&lt;%&ndash;<form:options items="${fns:getDictList('accumulated_quota1')}" itemLabel="label" itemValue="value" htmlEscape="false"/>&ndash;%&gt;--%>
						<%--</form:select>--%>
					</td>
				</tr>

				<tr>
					<td rowspan="1" style="text-align: center">法律费用<br>赔偿限额:</td>
					<td class="tit" width="10%">每次赔偿限额:</td>
					<td colspan="1" style="font-size: 16px;" id="lawEverytimeQuota">
						<%--<form:input path="lawEverytimeQuota" htmlEscape="false" class="input-xlarge "/>--%>
							<%--<form:select path="lawEverytimeQuota" class="input-xlarge ">--%>
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('law_everytime_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
							<%--</form:select>--%>
					</td>
					<td class="tit" width="10%">累计赔偿限额</td>
					<td style="font-size: 16px;" id="lawAccumulatedQuota">
						<%--<form:input path="lawAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>--%>
							<%--<form:select path="lawAccumulatedQuota" class="input-xlarge ">--%>
								<%--<form:option value="" label=""/>--%>
								<%--<form:options items="${fns:getDictList('law_accumulated_quota')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
							<%--</form:select>--%>
					</td>
				</tr>
				<tr>
					<td rowspan="1" style="text-align: center">附加险<br>赔偿限额</td>
					<td class="tit" width="10%">每人赔偿限额</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="appendEverytimeQuota" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">累计赔偿限额</td>
					<td  style="font-size: 16px;">
						<form:input path="appendAccumulatedQuota" htmlEscape="false" class="input-xlarge "/>
					</td>
				</tr>
				<tr style="text-align: center;font-weight: bold">
					<td colspan="5" style="text-align: center;height: 50px;font-size: 20px">
						基本保险费计算
					</td>
				</tr>
				<tr>
					<td rowspan="1" style="text-align: center">医疗机构<br>保险费</td>
					<td class="tit" width="10%">每床位保险费</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="bedPremium"  htmlEscape="false" class="input-xlarge"/>
					</td>
					<td class="tit" width="10%">医疗机构保险费</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="medicalPremium" htmlEscape="false" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td rowspan="6" style="text-align: center">医务人员<br>保险费</td>
					<td class="tit" colspan="2" width="10%">
						全部医务人员投保入口<br>(请提供全部人员名单)
						<input type="radio" name="mingdan" style="height: 20px;width: 20px;" onchange="allmingdan()">
					</td>
					<td class="tit" colspan="2" width="10%">
						选择科室投保入口<br>(请提供全部人员名单)
						<input type="radio" name="mingdan" style="height: 20px;width: 20px;" onchange="selmingdan()">
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%"></td>
					<td colspan="1" style="font-size: 16px;text-align: center">

					</td>
					<td class="tit" width="10%">每人基本保险费：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="selEveryonePremium" htmlEscape="false" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">医务人员人数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="allMedicalNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">临床手术科室人数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="selClinicOperationNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">每人基本保险费：</td>
					<td colspan="1" style="font-size: 16px;">
						<%--<form:input path="allEceryonePremium" htmlEscape="false" class="input-xlarge "/>--%>
						<form:input path="allEveryonePremium" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">临床非手术科室人数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="selClinicNotoperationNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">保险费合计：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="allPremiumTotal" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td class="tit" width="10%">医技科室人数：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="selMedicalLaboratoryNumber" htmlEscape="false" maxlength="4" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%"></td>
					<td colspan="1" style="font-size: 16px;text-align: center">
					</td>
					<td class="tit" width="10%">保险费合计：</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="selPremiumTotal" htmlEscape="false" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">基本保险费总计</td>
					<td colspan="2" align="right" style="font-size: 16px;">
						<form:input path="basicPremiumTotal" htmlEscape="false" class="input-xlarge "/>
						元
					</td>
				</tr>
				<tr>
					<td colspan="5" style="text-align: center;height: 50px;font-size: 20px;font-weight: bold">
						保险费调整
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">增加/减少赔偿限额后的<br>保险费调整系数</td>
					<td class="tit" width="10%">提高免赔额后的<br>保险费调整系数</td>
					<td class="tit" width="10%">风险浮动系数</td>
					<td class="tit" width="10%">调整后的保险费</td>
				</tr>
				<tr>
					<td colspan="1" style="font-size: 16px;">
						<%--<form:input path="addReduce" htmlEscape="false" class="input-xlarge "/>--%>
						<form:select path="addReduce" class="input-xlarge ">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('quota_adjust')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="enchanceDeduction" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="riskFloat" htmlEscape="false" class="input-xlarge "/>
					</td>
					<td colspan="1" style="font-size: 16px;">
						<form:input path="asleftPremium" htmlEscape="false" class="input-xlarge "/>
							<%--<b>元</b>--%>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">附加保险费用</td>
					<td colspan="3" style="font-size: 16px;">
						<form:select path="addittionRisk" class="input-xlarge " onchange="addRisk()">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('additional_risk')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						（小写）
						<form:input path="addittionPremium" htmlEscape="false" class="input-xlarge " cssStyle="width: 100px"/>
						<%--<form:select path="addittionPremium" class="input-xlarge ">--%>
							<%--<form:option value="" label=""/>--%>
							<%--<form:options items="${fns:getDictList('site_premium')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
						<%--</form:select>--%>
						元
					</td>
                    <%--<td>--%>
                        <%--<input type="button" >--%>
                    <%--</td>--%>
				</tr>
				<th>实收保险费计算</th>
				<tr>
					<td class="tit" width="10%">计算公式</td>
					<td colspan="3" style="font-size: 16px;">
						<form:input path="computationalFormula" htmlEscape="false" maxlength="64" class="input-xlarge "/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">实收保险费</td>
					<td colspan="3" style="font-size: 16px;">
						（小写）<form:input path="oddicialReceiptsPermium" htmlEscape="false" class="input-xlarge " cssStyle="width: 100px"/>
						元
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">保险期间</td>
					<td colspan="3" style="font-size: 16px;">
						<form:input path="insuranceDate" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 30px"/>
						个月,自
						<input name="insuranceStartTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.insuranceStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						零时起，至
						<input name="insuranceEndTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.insuranceEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						二十四时止。
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">追溯期</td>
					<td colspan="3" style="font-size: 16px;">
						<form:input path="retroactiveDate" htmlEscape="false" maxlength="4" class="input-xlarge " cssStyle="width: 30px"/>
						个月,自
						<input name="retroactiveStratDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.retroactiveStratDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						零时起，追溯至
						<input name="retroactiveEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.retroactiveEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
						二十四时止。
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">保险费交付日期</td>
					<td colspan="3" style="font-size: 16px;">
						<input name="premiumDeliceryTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.premiumDeliceryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">司法管辖</td>
					<td colspan="3" style="font-size: 16px;">
						中华人民共和国
					</td>
				</tr>
				<tr>
					<td rowspan="1" class="tit" width="10%">保险合同争议<br>处理方式选择</td>
					<td>
						<div class="radiobox ">
							<input type="radio" id="dispute" checked="checked" name="chuliType" style="width: 20px;height: 20px;" onchange="tijiao()"/>
							<span>提交</span>
							<form:input path="arbitrator" htmlEscape="false" class="input-xlarge " style="width: 50px" disabled="true"/>仲裁委员会仲裁；<br>
							<input type="radio" id="dispute" checked="checked" name="chuliType" style="width:20px;height: 20px;" onchange="shusong()"/>
							<span>诉讼</span>
						</div>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">以往投保情况<br>(如填写不下，<br>请另附纸说明)</td>
					<td colspan="3" style="font-size: 16px;">
						<form:textarea path="insureConditions" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="5"/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="10%">以往事故情况<br>(如填写不下，<br>请另附纸说明)</td>
					<td colspan="3" style="font-size: 16px;">
						<form:textarea path="accidentConditions" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="5"/>
					</td>
				</tr>
				<tr>
					<td class="tit" width="5%">特别约定</td>
					<td colspan="3" style="font-size: 16px;">
						<form:textarea path="specialAgreement" htmlEscape="false" class="input-xlarge" style="margin: 0px;width: 99%;font-size: 16px;" rows="3"/>
					</td>
				</tr>
				<tr style="border-bottom: 0px">
					<td  colspan="4" style="font-size: 16px; height: 200px">
						<p>
							<b>投保人声明:</b> 上述所填内容属实；保险人已将《医疗责任保险条款》（包括责任免除内容向投保人作了明确说明；投保人对《医疗责任保险条款》（包括责任免除内容）和保险人的说明已经了解。
						</p>
					</td>

				</tr>
				<tr style="border-top: 0px">
					<td colspan="4" align="right" style=" height: 50px">
						投保人(签盖)
						<form:input path="signature" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:80px"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="right" style=" height: 50px">
						年 月 日
						<input name="insureDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
							   value="<fmt:formatDate value="${insuranceSlip.insureDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					</td>
				</tr>
				<tr>
					<td class="tit">承包性质：</td>
					<td>
						<input type="radio" id="xinbao" checked="checked" name="cbType" style="width: 20px;height: 20px;" />
						<span>新保</span>
						<input type="radio" id="xubao" checked="checked" name="cbType" style="width:20px;height: 20px;"/>
						<span>续保</span>
					</td>
					<td class="tit">
						业务员/代理人代码：
						<form:input path="agencyCode" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:150px"/>
					</td>
					<td class="tit">
						业务员/代理人姓名：
						<form:input path="agencyName" htmlEscape="false" maxlength="32" class="input-xlarge" cssStyle="width:80px"/>
					</td>
				</tr>
			</table>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#flag').val('no')"/>&nbsp;</shiro:hasPermission>
			<shiro:hasPermission name="complaint:complaintInfo:edit"><input id="btnSubmit1" class="btn btn-primary" type="submit" value="提交审核" onclick="$('#flag').val('yes')"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>


</body>
</html>