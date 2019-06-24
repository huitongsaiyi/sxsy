<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>履行协议详情</title>
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
    <form:form class="form-horizontal">
        <sys:message content="${message}"/>
        <fieldset>
            <legend>履行协议详情</legend>
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#patient" data-toggle="tab">履行协议情况</a>
                </li>
                <li>
                    <a href="#annex" data-toggle="tab">附件</a>
                </li>
            </ul>
            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="patient">
                    <table class="table-form">
                        <tr>
                            <td class="tit">协议赔付金额</td>
                            <td>
                                ${performAgreement.agreementPayAmount}
                            </td>
                            <td class="tit">医院赔付金额</td>
                            <td>
                                ${performAgreement.hospitalPayAmount}
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">医院赔付时间</td>
                            <td>
                                ${performAgreement.hospitalPayTime}
                            </td>
                            <td class="tit">保险公司赔付金额</td>
                            <td>
                                ${performAgreement.insurancePayAmount}
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">保险公司赔付时间</td>
                            <td>
                                ${performAgreement.insurancePayTime}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="annex">
                    <table class="table-form">
                        <tr style=" " >
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">转款凭证：</td>
                            <input type="hidden"  name="fjtype1" value="14">
                            <td style="width: 450px; ">
                                <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                                <input type="hidden" id="files1" name="files" htmlEscape="false" class="input-xlarge"  value="${files}"/>
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/performAgreement/Transfer" selectMultiple="true" readonly="true"/></div>
                            </td>

                        </tr>
                        <tr style="" >
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">收款收据：</td>
                            <input type="hidden" name="fjtype2" value="15">
                            <td style="width: 450px; ">
                                <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                                <input type="hidden" id="files2" name="files1" htmlEscape="false" class="input-xlarge" value="${files1}" />
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/performAgreement/Receipt" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </fieldset>
        <div class="form-actions">
            <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
        </div>
    </form:form>
    </body>
</html>

