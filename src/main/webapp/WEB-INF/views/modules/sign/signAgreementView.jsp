<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>签署协议管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
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
        function delRow(obj, prefix,key){
            var id = $(prefix+key);
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
    <li><a href="${ctx}/sign/signAgreement/">签署协议列表</a></li>
    <li class="active"><a href="${ctx}/sign/signAgreement/form?id=${signAgreement.id}">签署协议<shiro:hasPermission name="sign:signAgreement:edit">${not empty signAgreement.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="sign:signAgreement:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
<form:form id="inputForm" modelAttribute="signAgreement" action="${ctx}/sign/signAgreement/save" method="post" class="form-horizontal">
    <form:hidden path="signAgreementId"/>
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
    <legend>签署协议详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#sign" data-toggle="tab">调解协议书</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="sign">
            <ul id="myTab1" class="nav nav-tabs">
                <li class="active">
                    <a href="#patient" data-toggle="tab">甲方(患方)</a>
                </li>
                <li>
                    <a href="#hospital" data-toggle="tab">乙方(医方)</a>
                </li>
                <li>
                    <a href="#jfgy" data-toggle="tab">纠纷概要</a>
                </li>
                <li>
                    <a href="#tjqk" data-toggle="tab">调解情况</a>
                </li>
                <li>
                    <a href="#xyydsx" data-toggle="tab">协议约定事项</a>
                </li>
                <li>
                    <a href="#lxxyfs" data-toggle="tab">履行协议方式</a>
                </li>
                <li>
                    <a href="#xysm" data-toggle="tab">协议说明</a>
                </li>
            </ul>
            <div id="myTab1Content" class="tab-content">
                <div class="tab-pane fade in active" id="patient">
                    <table id="patientTable" class="table table-striped table-bordered table-condensed">
                        <legend>患方</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >与患者关系</th>
                            <th >身份证号</th>
                            <th >住址</th>
                            <th >操作</th>
                            <shiro:hasPermission name="sign:signAgreement:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="patientLinkEmpList"></tbody>
                        <shiro:hasPermission name="sign:signAgreement:edit">
                            <tfoot>
                            <tr><td colspan="7"></td></tr>
                            </tfoot></shiro:hasPermission>
                    </table>
                    <script type="text/template" id="patientLinkEmpTp">
						<tr id="patientLinkEmpList{{idx}}">
							<td class="hide">
								<input id="patientLinkEmpList{{idx}}_id" name="patientLinkEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkEmpList{{idx}}_patientLinkEmpId" name="patientLinkEmpList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkEmpList{{idx}}_linkType" name="patientLinkEmpList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
								<input id="patientLinkEmpList{{idx}}_relationId" name="patientLinkEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkEmpList{{idx}}_delFlag" name="patientLinkEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkName" name="patientLinkEmpList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_patientRelation" name="patientLinkEmpList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_idNumber" name="patientLinkEmpList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkAddress" name="patientLinkEmpList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" disabled="disabled"/>
							</td>

							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmpList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>


                    <table id="patientDTable" class="table table-striped table-bordered table-condensed">
                        <legend>委托（法定）代理人</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >与患者关系</th>
                            <th >身份证号</th>
                            <th >住址</th>
                            <th >操作</th>
                            <shiro:hasPermission name="sign:signAgreement:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="patientLinkDList"></tbody>
                        <shiro:hasPermission name="sign:signAgreement:edit">
                            <tfoot>
                            <tr><td colspan="7"></td></tr>
                            </tfoot></shiro:hasPermission>
                    </table>
                    <script type="text/template" id="patientLinkDTp">
						<tr id="patientLinkDList{{idx}}">
							<td class="hide">
								<input id="patientLinkDList{{idx}}_id" name="patientLinkDList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkDList{{idx}}_patientLinkEmpId" name="patientLinkDList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkDList{{idx}}_linkType" name="patientLinkDList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
								<input id="patientLinkDList{{idx}}_relationId" name="patientLinkDList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkDList{{idx}}_delFlag" name="patientLinkDList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="patientLinkDList{{idx}}_patientLinkName" name="patientLinkDList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" type="text" value="{{row.patientRelation}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_idNumber" name="patientLinkDList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_patientLinkAddress" name="patientLinkDList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" disabled="disabled"/>
							</td>

							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkDList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>


                </div>
                <div class="tab-pane fade" id="hospital">
                    <table id="hospitalTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >医疗机构名称</th>
                            <th >地址</th>
                            <th >法定代表人</th>
                            <th >职务</th>
                            <th >委托代理人</th>
                            <th >性别</th>
                            <th >身份证号</th>
                            <th >单位及职务</th>
                            <th >操作</th>
                            <shiro:hasPermission name="sign:signAgreement:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="medicalOfficeEmpList"></tbody>
                        <shiro:hasPermission name="sign:signAgreement:edit">
                            <tfoot>
                            <tr><td colspan="7"></td></tr>
                            </tfoot></shiro:hasPermission>
                    </table>
                    <script type="text/template" id="medicalOfficeEmpTp">
						<tr id="medicalOfficeEmpList{{idx}}">
							<td class="hide">
								<input id="medicalOfficeEmpList{{idx}}_id" name="medicalOfficeEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeEmpId" name="medicalOfficeEmpList[{{idx}}].medicalOfficeEmpId" type="hidden" value="{{row.medicalOfficeEmpId}}"/>
								<input id="medicalOfficeEmpList{{idx}}_relationId" name="medicalOfficeEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="medicalOfficeEmpList{{idx}}_delFlag" name="medicalOfficeEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>

							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeName" name="medicalOfficeEmpList[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAddress" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAddress" type="text" value="{{row.medicalOfficeAddress}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_legalRepresentative" name="medicalOfficeEmpList[{{idx}}].legalRepresentative" type="text" value="{{row.legalRepresentative}}" maxlength="20" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficePost" name="medicalOfficeEmpList[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="20" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAgent" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="required" disabled="disabled"/>
							</td>
							<td>
								<select id="medicalOfficeEmpList{{idx}}_medicalOfficeSex" name="medicalOfficeEmpList[{{idx}}].medicalOfficeSex" value="{{row.medicalOfficeSex}}" data-value="{{row.medicalOfficeSex}}" class="input-mini" disabled="disabled">
									<option value=""></option>
									<option value="1"  >男</option>
									<option value="2"  >女</option>
								</select>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeIdcard" name="medicalOfficeEmpList[{{idx}}].medicalOfficeIdcard" type="text" value="{{row.medicalOfficeIdcard}}" maxlength="20" class="required" disabled="disabled"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeCompany" name="medicalOfficeEmpList[{{idx}}].medicalOfficeCompany" type="text" value="{{row.medicalOfficeCompany}}" maxlength="200" class="required" disabled="disabled"/>
							</td>
							<shiro:hasPermission name="sign:signAgreement:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#medicalOfficeEmpList{{idx}}','_medicalOfficeEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>
                </div>
                <div class="tab-pane fade" id="jfgy">
                    <table  class="table-form">
                        <tr>
                            <td class="tit" width="225px">
                                纠纷概要：
                            </td>
                            <td >
                               ${signAgreement.summaryOfDisputes}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="tjqk">
                    <table id="contentTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th></th>
                            <th style="text-align:center;">类型</th>
                            <th style="text-align:center;">内容</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tjqk}" var="column" varStatus="vs">
                            <tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="mediationList[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="mediationList[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="mediationList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.typeName}
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.content}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="xyydsx">
                    <table id="content1Table" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th></th>
                            <th style="text-align:center;">内容</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${xyydsx}" var="column" varStatus="vs">
                            <tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="meatterList[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="meatterList[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="meatterList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.content}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="lxxyfs">
                    <table id="content2Table" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th></th>
                            <th style="text-align:center;">类型</th>
                            <th style="text-align:center;">内容</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lxxyfs}" var="column" varStatus="vs">
                            <tr><%--${column.delFlag eq '1'?' class="error" title="已删除的列，保存之后消失！"':''}--%>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="performList[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="performList[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="performList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.typeName}
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.content}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class="tab-pane fade" id="xysm">
                    <table id="content3Table" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th></th>
                            <th style="text-align:center;">类型</th>
                            <th style="text-align:center;">内容</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${xysm}" var="column" varStatus="vs">
                            <tr>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="agreementList[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="agreementList[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="agreementList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.typeName}
                                </td>
                                <td style="text-align:center;vertical-align:middle;">
                                        ${column.content}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="annex">
            <table class="table-form">
                <tr>
                    <input type="hidden" name="fjtype1" value="7">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        签到表：
                    </td>
                    <td style="width: 450px;">
                        <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                               value="${files1}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <sys:ckfinder input="files1" type="files" uploadPath="/sign/signAgreement/sign" selectMultiple="false"
                                      maxWidth="100" maxHeight="100" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype2" value="8">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        会议记录：
                    </td>
                    <td style="width: 450px;">
                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
                               value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <sys:ckfinder input="files2" type="files" uploadPath="/sign/signAgreement/meet"
                                      selectMultiple="false"
                                      maxWidth="100" maxHeight="100" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype3" value="9">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        协议书：
                    </td>
                    <td style="width: 450px;">
                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
                               value="${files3}"/>
                        <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                        <sys:ckfinder input="files3" type="files" uploadPath="/sign/signAgreement/xieyi"
                                      selectMultiple="false"
                                      maxWidth="100" maxHeight="100" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <input type="hidden" name="fjtype4" value="10">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                        其他材料：
                    </td>
                    <td style="width: 450px;">
                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
                               value="${files4}"/>
                        <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                        <sys:ckfinder input="files4" type="files" uploadPath="/sign/signAgreement/other"
                                      selectMultiple="false"
                                      maxWidth="100" maxHeight="100" readonly="true"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <table class="table-form">
        <tr>
            <td class="tit" width="225px">协议号：</td>
            <td >
                ${signAgreement.agreementNumber}
            </td>
            <td class="tit" width="225px">签署协议/判决时间：</td>
            <td >
                    ${signAgreement.ratifyAccord}
            </td>
        </tr>
        <tr>
            <td class="tit" >协议金额：</td>
            <td >
               ${signAgreement.agreementAmount}
            </td>
            <td class="tit">保险金额：</td>
            <td >
                ${signAgreement.insuranceAmount}
            </td>
        </tr>
        <tr>
            <td class="tit" >交理赔时间：</td>
            <td >
                    ${signAgreement.claimSettlementTime}
            </td>
            <td class="tit">赔付时间：</td>
            <td >
                    ${signAgreement.compensateTime}
            </td>
        </tr>
        <tr>
            <td class="tit">下一环节处理人：</td>
            <td >
                    ${signAgreement.linkEmployee.name}
            </td>
        </tr>
    </table>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <c:if test="${not empty signAgreement.signAgreementId}">
        <act:histoicFlow procInsId="${signAgreement.complaintMain.procInsId}" />
    </c:if>
</form:form>
<script type="text/javascript">
    var medicalOfficeEmpRowIdx = 0, medicalOfficeEmpTp = $("#medicalOfficeEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkEmpRowIdx = 0, patientLinkEmpTp = $("#patientLinkEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkDRowIdx = 0, patientLinkDTp = $("#patientLinkDTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var officeData = ${fns:toJson(signAgreement.medicalOfficeEmpList)};
        for (var i=0; i<officeData.length; i++){
            addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp, officeData[i]);
            medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;
        }

        var data = ${fns:toJson(signAgreement.patientLinkDList)};
        for (var i=0; i<data.length; i++){
            addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp, data[i]);
            patientLinkDRowIdx = patientLinkDRowIdx + 1;
        }

        var PatientData = ${fns:toJson(signAgreement.patientLinkEmpList)};
        for (var i=0; i<PatientData.length; i++){
            addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp, PatientData[i]);
            patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;
        }
    });
</script>
</body>
</html>
<%--
<div class="control-group">
			<label class="control-label">调解情况 多个逗号：</label>
			<div class="controls">
				<form:input path="mediation" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议约定事项  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="agreedMatter" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">履行协议方式  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="performAgreementMode" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">协议说明  多个逗号隔开：</label>
			<div class="controls">
				<form:input path="agreementExplain" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
<div class="control-group">
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