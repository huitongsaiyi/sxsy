<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>调查取证管理</title>
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
<%--<ul class="nav nav-tabs">--%>
<%--<li><a href="${ctx}/nestigateeividence/investigateEvidence/">列表</a></li>--%>
<%--<li class="active"><a href="${ctx}/nestigateeividence/investigateEvidence/form?id=${investigateEvidence.id}" id="c">调查取证<shiro:hasPermission name="nestigateeividence:investigateEvidence:edit">${not empty investigateEvidence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="nestigateeividence:investigateEvidence:edit">查看</shiro:lacksPermission></a></li>--%>
<%--</ul><br/>--%>
<form:form id="inputForm" modelAttribute="investigateEvidence" action="${ctx}/nestigateeividence/investigateEvidence/save" method="post" class="form-horizontal">
    <form:hidden path="investigateEvidenceId"/>
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
    <form:hidden path="investigateEvidence.investigateEvidenceId"/>
    <form:hidden path="investigateEvidence.complaintMainId"/>
    <form:hidden path="investigateEvidence.createDate"/>
    <form:hidden path="investigateEvidence.createBy"/>
    <form:hidden path="investigateEvidence.investigateType"/>
    <form:hidden path="investigateEvidence.nextLinkMan"/>
    <sys:message content="${message}"/>
    <legend>调查取证详情</legend>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#visitor" data-toggle="tab">患方调查笔录</a>
        </li>
        <li>
            <a href="#patient" data-toggle="tab">医方调查笔录</a>
        </li>
        <li>
            <a href="#hospital" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="visitor">
            <input type="hidden" name="investigateType" value="1">
            <table class="table-form">
                <tr >
                    <td class="tit" width="140px" style=" border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.startTime}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

                    <td width="195px;">
                            ${investigateEvidence.endTime}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.address}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

                    <td width="195px;">
                            ${investigateEvidence.cause}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.investigator}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

                    <td width="195px;">
                            ${investigateEvidence.noteTaker}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>反应焦点：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.focus}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid; " ><font color="red">*</font>笔录内容：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.content}
                    </td>
                </tr>
            </table>
            <ul  class="nav nav-tabs">
                <li class="active">
                    <a href="#investigation1" data-toggle="tab">被调查人1</a>
                </li>
                <li>
                    <a href="#investigation2" data-toggle="tab">被调查人2</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="investigation1">
                    <table class="table-form">

                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td style="display: inline-block; width: 115px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo.respondentName}
                            </td>
                            <td class="tit" style="display: inline-block; margin-left: -1px;width: 140px;"><font color="red">*</font>性别：</td>
                            <td style=" display: inline-block;margin-left: 0px; width: 60px; text-align: center;height: 20px;">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;"><font color="red">*</font>年龄：</td>
                            <td style="display: inline-block;margin-left: 0px; width: 65px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo.respondentAge}
                            </td>
                            <td class="tit"  style="display: inline-block; width: 140px; "><font color="red">*</font>联系方式：</td>
                            <td style="display: inline-block;width: 302px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo.respondentMobile}
                            </td>
                            <td class="tit" style="width: 140px;display: inline-block;text-align: center;"><font color="red">*</font>被调查人身份:</td>
                            <td style="display: inline-block;width: 312px;text-align:center;height: 20px;" >
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" style=""><font color="red">*</font>工作单位:</td>
                            <td style="display: inline-block; width: 1033px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo.respondentWorkUnit}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;margin-left: 0px; "><font color="red">*</font>职务:</td>
                            <td style="display: inline-block; width: 312px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane " id="investigation2">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td style="display: inline-block; width: 115px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo2.respondentName}
                            </td>
                            <td class="tit" style="display: inline-block; margin-left: -1px;width: 140px;"><font color="red">*</font>性别：</td>
                            <td style=" display: inline-block;margin-left: 0px; width: 60px; text-align: center;height: 20px;">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo2.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;"><font color="red">*</font>年龄：</td>
                            <td style="display: inline-block;margin-left: 0px; width: 65px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo2.respondentAge}
                            </td>
                            <td class="tit"  style="display: inline-block; width: 140px; "><font color="red">*</font>联系方式：</td>
                            <td style="display: inline-block;width: 302px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo2.respondentMobile}
                            </td>
                            <td class="tit" style="width: 140px;display: inline-block;text-align: center;"><font color="red">*</font>被调查人身份:</td>
                            <td style="display: inline-block;width: 312px;text-align:center;height: 20px;" >
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo2.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" style=""><font color="red">*</font>工作单位:</td>
                            <td style="display: inline-block; width: 1033px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo2.respondentWorkUnit}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;margin-left: 0px; "><font color="red">*</font>职务:</td>
                            <td style="display: inline-block; width: 312px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo2.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="patient">
            <input type="hidden" name="investigateType2" value="2">
            <table class="table-form">
                <tr >
                    <td class="tit" width="140px" style=" border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查时间：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.investigateEvidence.startTime}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>结束时间：</td>

                    <td width="195px;">
                        ${investigateEvidence.investigateEvidence.endTime}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查地点：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.investigateEvidence.address}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>调查事由：</td>

                    <td width="195px;">
                            ${investigateEvidence.investigateEvidence.cause}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;"><font color="red">*</font>调查人：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.investigateEvidence.investigator}
                    </td>
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid;border-Left:1px #e2e2e2 solid;"><font color="red">*</font>记录人：</td>

                    <td width="195px;">
                            ${investigateEvidence.investigateEvidence.noteTaker}
                    </td>
                </tr>
                <tr >
                    <td class="tit" width="140px" style="border-right:1px #e2e2e2 solid; "><font color="red">*</font>笔录内容：</td>
                    <td style="width: 105px;">
                            ${investigateEvidence.investigateEvidence.content}
                    </td>
                </tr>
            </table>
            <ul  class="nav nav-tabs">
                <li class="active">
                    <a href="#investigation3" data-toggle="tab">被调查人1</a>
                </li>
                <li>
                    <a href="#investigation4" data-toggle="tab">被调查人2</a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane fade in active" id="investigation3">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td style="display: inline-block; width: 115px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo3.respondentName}
                            </td>
                            <td class="tit" style="display: inline-block; margin-left: -1px;width: 140px;"><font color="red">*</font>性别：</td>
                            <td style=" display: inline-block;margin-left: 0px; width: 60px; text-align: center;height: 20px;">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo3.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;"><font color="red">*</font>年龄：</td>
                            <td style="display: inline-block;margin-left: 0px; width: 65px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo3.respondentAge}
                            </td>
                            <td class="tit"  style="display: inline-block; width: 140px; "><font color="red">*</font>联系方式：</td>
                            <td style="display: inline-block;width: 302px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo3.respondentMobile}
                            </td>
                            <td class="tit" style="width: 140px;display: inline-block;text-align: center;"><font color="red">*</font>被调查人身份:</td>
                            <td style="display: inline-block;width: 312px;text-align:center;height: 20px;" >
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo3.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" style=""><font color="red">*</font>工作单位:</td>
                            <td style="display: inline-block; width: 1033px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo3.respondentWorkUnit}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;margin-left: 0px; "><font color="red">*</font>职务:</td>
                            <td style="display: inline-block; width: 312px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo3.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="investigation4">
                    <table class="table-form">
                        <tr >
                            <td class="tit" width="140px" ><font color="red">*</font>姓名：</td>
                            <td style="display: inline-block; width: 115px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo4.respondentName}
                            </td>
                            <td class="tit" style="display: inline-block; margin-left: -1px;width: 140px;"><font color="red">*</font>性别：</td>
                            <td style=" display: inline-block;margin-left: 0px; width: 60px; text-align: center;height: 20px;">
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo4.respondentSex,'sex', '无')}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;"><font color="red">*</font>年龄：</td>
                            <td style="display: inline-block;margin-left: 0px; width: 65px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo4.respondentAge}
                            </td>
                            <td class="tit"  style="display: inline-block; width: 140px; "><font color="red">*</font>联系方式：</td>
                            <td style="display: inline-block;width: 302px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo4.respondentMobile}
                            </td>
                            <td class="tit" style="width: 140px;display: inline-block;text-align: center;"><font color="red">*</font>被调查人身份:</td>
                            <td style="display: inline-block;width: 312px;text-align:center;height: 20px;" >
                                    ${fns:getDictLabel(investigateEvidence.respondentInfo4.respondentIdentity,'investigation', '无')}
                            </td>
                        </tr>
                        <tr >
                            <td class="tit" style=""><font color="red">*</font>工作单位:</td>
                            <td style="display: inline-block; width: 1033px; text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo4.respondentWorkUnit}
                            </td>
                            <td class="tit"  style="display: inline-block;width: 140px;margin-left: 0px; "><font color="red">*</font>职务:</td>
                            <td style="display: inline-block; width: 312px;text-align: center;height: 20px;">
                                    ${investigateEvidence.respondentInfo4.respondentPost}
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="tab-pane fade" id="hospital">
            <table class="table-form">

                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
                    <input type="hidden"  name="fjtype1" value="3">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files1" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbilu" readonly="true" selectMultiple="true" /></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
                    <input type="hidden" name="fjtype2" value="4">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files2" name="files1" htmlEscape="false" class="input-xlarge" value="${files1}" />
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/nestigateeividence/investigateEvidence/huanbuchong" readonly="true" selectMultiple="true" /></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
                    <input type="hidden"  name="fjtype3" value="5">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"  value="${files3}"/>
                        <input type="hidden" id="acceId3" name="acceId1" value="${acceId3}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibilu" readonly="true" selectMultiple="true" /></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
                    <input type="hidden" name="fjtype4" value="6">
                    <td style="width: 450px;">
                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge" value="${files4}" />
                        <input type="hidden" id="acceId4" name="acceId1" value="${acceId4}">
                            <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/nestigateeividence/investigateEvidence/yibuchong" readonly="true" selectMultiple="true" /></div>
                    </td>

                </tr>
            </table>


        </div>
    </div>

    <table class="table-form" style="margin-top: 20px;">
        <tr  style="">
            <td class="tit"  style="display: inline-block; width: 140px;"><font color="red">*</font>下一环节处理人：</td>

            <td width="540px;" style="display:inline-block;text-align: center;width: 312px;height: 20px;">
                    ${investigateEvidence.linkEmployee.name}
            </td>
        </tr>
    </table>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${investigateEvidence.complaintMain.procInsId}" />
</form:form>
</body>
</html>