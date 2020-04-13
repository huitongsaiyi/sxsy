<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>审核受理管理详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            var show='${show2}';
            if(show=='' || show== null){
                //投诉接待详情
                if(${map.tsjd==null || map.tsjd==""}){
                    $("#tsjdDetail").attr("src","${ctx}/complaint/complaintInfo/form?id=${complaintId}&num=1&type=view");
                }else{
                    $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view");
                }
                var tsjd= document.getElementById("tsjdDetail");
                tsjd.height=document.documentElement.clientHeight-130;
                tsjd.width=document.documentElement.clientWidth;
                //报案登记
                $("#badjDetail").attr("src","${ctx}/registration/reportRegistration/form?id=${map.badj}&type=view&show2=y");
                var badj= document.getElementById("badjDetail");
                badj.height=document.documentElement.clientHeight-130;
                badj.width=document.documentElement.clientWidth;
            }
        });





    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="auditAcceptance" action="${ctx}/auditacceptance/auditAcceptance/save"
           method="post" class="form-horizontal">
    <sys:message content="${message}"/>
<fieldset>
    <ul id="myTab2" class="nav nav-tabs">
        <li class="active">
            <a href="#shsl" data-toggle="tab">审核受理</a>
        </li>
        <c:if test="${empty show2}">
            <li>
                <a href="#details" data-toggle="tab">详情</a>
            </li>
        </c:if>
    </ul>
    <legend>审核受理详情</legend>
    <div id="myTabContent2" class="tab-content">
        <div id="shsl" class="tab-pane fade in active">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#people" data-toggle="tab">人民调解受理登记信息</a>
        </li>
        <li>
            <a href="#hospitalS" data-toggle="tab">医方受理通知书</a>
        </li>
        <li>
            <a href="#patientS" data-toggle="tab">患方受理通知书</a>
        </li>
        <li>
            <a href="#patientT" data-toggle="tab">患方调解申请信息</a>
        </li>
        <li>
            <a href="#hospitalT" data-toggle="tab">医方调解申请信息</a>
        </li>
        <li>
            <a href="#annex" data-toggle="tab">附件</a>
        </li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade" id="patientS">
            <table class="table-form">
                <tr>
                    <td colspan="2">
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">患者</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
                            <span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">${auditAcceptance.complaintMain.patientName}</span>
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
                            <span style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">${auditAcceptance.complaintMain.hospital.name}</span>
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
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
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="2" class="tit" width="240px">一、材料名单:</td>
                    <td>
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
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="1" class="tit">二、要求:</td>
                    <td>
                        <p style="margin:0pt">
                            <span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span>
                            <span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交复印件及资料均需用</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">A4</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">纸</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、如实陈述纠纷事实</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">不得提供虚假证明材料</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">;</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、所有提交的资料需签字、按手印进行确认</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">并注明日期</span></p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospitalS">
            <table class="table-form">
                <tr>
                    <td colspan="2">
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                                style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">${auditAcceptance.complaintMain.hospital.name}</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">医院</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">贵院与患者</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_____</span><span
                                style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">${auditAcceptance.complaintMain.patientName}</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">发生的医疗纠纷案件</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">应悦者及</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">质院双方</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">调解申请，我单位经审查符合受理条件</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">为保证案件调解工作的及时进行</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">请贵院在三个工作日内提供如下材料</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span></p>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="2" class="tit" width="240px">一、材料名单</td>
                    <td>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交法定代表人身份证明书一份</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交授权委托书</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交委托人、被委托人的身份证复印件</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交执业许可证副本复印件</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">5</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、诊疗分析意见</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">6</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、出险医务人员身份证、执业证书复印件</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">7</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、与纠纷相关的证据材料</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、病历资料复印件</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">(3</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">份</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">)</span></p>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td rowspan="1" class="tit">二、要求</td>
                    <td>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交材料均需用</span><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">A4</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">纸复印</span></p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、所提交的材料均需加盖医院公章</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">3</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、出险医务人员需在身份证、执业证书复印件上签名</span>
                        </p>
                        <p style="margin:0pt"><span
                                style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">4</span><span
                                style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、如三个工作日未提交所需资料的，视为放弃调解。</span>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="patientT">
            <table class="table-form">
                <tr>
                    <td class="tit" width="180px"><font color="red">*</font>申请人</td>
                    <td width="460px">
                        <%--<form:input path="mediateApplyInfo.applyer" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
                        ${auditAcceptance.mediateApplyInfo.applyer}
                    </td>
                    <td class="tit" width="180px"><font color="red">*</font>与患者关系</td>
                    <td width="460px">
                        <%--<form:select path="mediateApplyInfo.patientRelation" class="input-medium required">--%>
                            <%--<form:options items="${fns:getDictList('patient_relation')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                            <c:choose>
                            <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation == '1'}">
                    本人
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='2'}">
                        夫妻
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='3'}">
                        子女
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='4'}">
                        父母
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='5'}">
                        兄妹
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='6'}">
                        亲属
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='7'}">
                        其他
                    </c:when>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者姓名</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.patientName" htmlEscape="false" maxlength="32" class="input-xlarge required"/>--%>
                         ${auditAcceptance.mediateApplyInfo.patientName}
                    </td>
                    <td class="tit"><font color="red">*</font>患者性别</td>
                        <%--<form:select path="mediateApplyInfo.patientSex" class="input-medium required">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                    <c:choose>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientSex == '1'}">
                    <td>男</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientSex == '2'}">
                        <td>女</td>
                    </c:when>
                    </c:choose>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.patientAge" htmlEscape="false" maxlength="32" cssClass="input-xlarge required digits"/>--%>
                                ${auditAcceptance.mediateApplyInfo.patientAge}
                    </td>
                    <td class="tit"><font color="red">*</font>患方联系电话</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.patientMobile" htmlEscape="false" maxlength="32" class="input-xlarge required phone"/>--%>
                                ${auditAcceptance.mediateApplyInfo.patientMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>涉及医院</td>
                    <td>
                        <%--<sys:treeselect id="involveHospital" name="mediateApplyInfo.involveHospital" value="${empty auditAcceptance.mediateApplyInfo.involveHospital ? auditAcceptance.complaintMain.involveHospital : auditAcceptance.mediateApplyInfo.involveHospital}" labelName="${empty auditAcceptance.mediateApplyInfo.involveHospital ? auditAcceptance.complaintMain.hospital.name : auditAcceptance.mediateApplyInfo.sjOffice.name}" labelValue="${empty auditAcceptance.mediateApplyInfo.involveHospital ?  auditAcceptance.complaintMain.hospital.name:auditAcceptance.mediateApplyInfo.sjOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty auditAcceptance.mediateApplyInfo.involveHospital ?  auditAcceptance.complaintMain.hospital.name:auditAcceptance.mediateApplyInfo.sjOffice.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>投诉纠纷概要</td>
                    <td colspan="3" style="word-break:break-all;white-space: normal;">
                        <%--<form:textarea path="mediateApplyInfo.summaryOfDisputes" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
                        ${auditAcceptance.mediateApplyInfo.summaryOfDisputes}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>当事人申请事项</td>
                    <td colspan="3" style="word-break:break-all;white-space: normal;">
                        <%--<form:textarea path="mediateApplyInfo.applyMatter" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
                        <%--<span style="width: 1450px;word-break: break-all;white-space: normal;">--%>
                                ${auditAcceptance.mediateApplyInfo.applyMatter}
                    </td>
                </tr>
                <tr >
                    <td colspan="4">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="hospitalT">
            <table class="table-form">
                <tr>
                    <td class="tit" width="175px"><font color="red">*</font>申请医院</td>
                    <td width="460px">
                        <%--<sys:treeselect id="applyHospital" name="mediateApplyInfo.docMediateApplyInfo.applyHospital" value="${empty auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital ? auditAcceptance.complaintMain.involveHospital:auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital}" labelName="${empty auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital ? auditAcceptance.complaintMain.hospital.name:auditAcceptance.mediateApplyInfo.docMediateApplyInfo.sqOffice.name}" labelValue="${empty auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital ? auditAcceptance.complaintMain.hospital.name:auditAcceptance.mediateApplyInfo.docMediateApplyInfo.sqOffice.name}"--%>
                                        <%--title="机构" url="/sys/office/treeData?type=1&officeType=2" isAll="true" cssClass="required" dataMsgRequired="请选择医院" allowClear="true" notAllowSelectParent="false"/>--%>
                                ${empty auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital ? auditAcceptance.complaintMain.hospital.name:auditAcceptance.mediateApplyInfo.docMediateApplyInfo.sqOffice.name}
                    </td>
                    <td class="tit" width="175px"><font color="red">*</font>代理人</td>
                    <td width="460px">
                        <%--<form:input path="mediateApplyInfo.docMediateApplyInfo.agent" htmlEscape="false" maxlength="32" class="input-xlarge required "/>--%>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.agent}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者姓名</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.docMediateApplyInfo.patientName" htmlEscape="false" maxlength="32" class="input-xlarge required "/>--%>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.patientName}
                    </td>
                    <td class="tit"><font color="red">*</font>患者性别</td>
                    <td>
                        <%--<form:select path="mediateApplyInfo.docMediateApplyInfo.patientSex" class="input-medium required">--%>
                            <%--<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
                        <%--</form:select>--%>
                     <c:choose>
                     <c:when test="${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.patientSex == '1'}">
                        男
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.patientSex == '2'}">
                        女
                    </c:when>
                    </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>患者年龄</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.docMediateApplyInfo.patientAge" htmlEscape="false" maxlength="32" class="input-xlarge required digits"/>--%>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.patientAge}
                    </td>
                    <td class="tit"><font color="red">*</font>医方联系电话</td>
                    <td>
                        <%--<form:input path="mediateApplyInfo.docMediateApplyInfo.hospitalMobile" htmlEscape="false" maxlength="32" class="input-xlarge required phone"/>--%>
                                ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.hospitalMobile}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>投诉纠纷概要</td>
                    <td colspan="3" style="word-break:break-all;white-space: normal;">
                        <%--<form:textarea path="mediateApplyInfo.docMediateApplyInfo.summaryOfDisputes" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
                                ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.summaryOfDisputes}
                    </td>
                </tr>
                <tr>
                    <td class="tit"><font color="red">*</font>当事人申请事项</td>
                    <td colspan="3" style="word-break:break-all;white-space: normal;">
                        <%--<form:textarea path="mediateApplyInfo.docMediateApplyInfo.applyMatter" htmlEscape="false" class="input-xlarge required" style="margin: 0px; width: 938px; height: 125px;"/>--%>
                                ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyMatter}
                    </td>
                </tr>
                <tr >
                    <td colspan="4">
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>

            </table>
        </div>
        <div class="tab-pane fade in active" id="people">
            <table class="table-form">
                <tr>
                    <td class="tit"><font color="red">*</font>
                        案件来源：
                    </td>
                    <td>
                        ${fns:getDictLabel(auditAcceptance.caseSource,'case_source' ,'未知' )}
                        <%--<form:select path="caseSource" class="input-medium required" onchange="show_input(this.value,'anjian')">--%>
                            <%--<form:options items="${fns:getDictList('case_source')}" itemLabel="label" itemValue="value" htmlEscape="false" />--%>
                        <%--</form:select>--%>
                    </td>
                    <td class="tit"><font color="red">*</font>
                        起保日期：
                    </td>
                    <td>
                        ${auditAcceptance.guaranteeTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        保险公司：
                    </td>
                    <td>
                        <form:select path="insuranceCompany" class="input-medium" disabled="true">
                            <form:options items="${fns:getDictList('sys_office_form')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                        </form:select>
                    </td>
                    <td class="tit">
                        保单号：
                    </td>
                    <td>
                            ${auditAcceptance.policyNumber}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        诊疗方式：
                    </td>
                    <td>
                            ${auditAcceptance.diagnosisMode}
                    </td>
                    <td class="tit"><font color="red">*</font>
                        治疗结果：
                    </td>
                    <td>
                            ${auditAcceptance.treatmentOutcome}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        时间
                    </td>
                    <td>
                        ${empty auditAcceptance.handleTime?fns:getDate('yyyy-MM-dd HH:mm'):auditAcceptance.handleTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">纠纷概要</td>
                    <td colspan="3">
                        ${auditAcceptance.summaryOfDisputes}
                    </td>
                </tr>

            </table>
        </div>
        <div class="tab-pane fade" id="annex">
            <ul id="myTab1" class="nav nav-tabs">
                <li class="active">
                    <a href="#patient_related" data-toggle="tab">患方相关附件</a>
                </li>
                <li>
                    <a href="#medical_related" data-toggle="tab">医方相关附件</a>
                </li>
            </ul>
            <div id="myTabContent1" class="tab-content">
                <div class="tab-pane fade in active" id="patient_related">
                    <table style="height: 150px;" class="table-form">
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方调解申请：</td>
                            <input type="hidden" name="fjtype1" value="1">
                            <td style="width: 450px;">
                                <input type="hidden" id="files1" name="files1" htmlEscape="false" class="input-xlarge"
                                       value="${files1}"/>
                                <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/MediationApplication" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗纠纷材料：</td>
                            <input type="hidden" name="fjtype2" value="2">

                            <td style="width: 450px;">
                                <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"
                                       value="${files2}"/>
                                <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/DisputeMaterials" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">当事人身份相关证件：
                            </td>
                            <input type="hidden" name="fjtype3" value="3">
                            <td style="width: 450px;">

                                <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge"
                                       value="${files3}"/>
                                <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/RelatedDocuments" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
                            <input type="hidden" name="fjtype4" value="4">
                            <td style="width: 450px;">

                                <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"
                                       value="${files4}"/>
                                <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/Proxy" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                委托人、被委托人相关证件：
                            </td>
                            <input type="hidden" name="fjtype5" value="5">
                            <td style="width: 450px;">

                                <input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge"
                                       value="${files5}"/>
                                <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                                    <%--<form:hidden id="files" path="files" htmlEscape="false" maxlength="255" class="input-xlarge" name="filess" />--%>
                                <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/WeituorenRelevantDocuments" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病例及相关检查资料：
                            </td>
                            <input type="hidden" name="fjtype6" value="6">
                            <td style="width: 450px;">

                                <input type="hidden" id="files6" name="files6" htmlEscape="false" class="input-xlarge"
                                       value="${files6}"/>
                                <input type="hidden" id="acceId6" name="acceId6" value="${acceId6}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/CheckTheData" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                医疗费用及其他相关费用票据：
                            </td>
                            <input type="hidden" name="fjtype7" value="7">
                            <td style="width: 450px;">

                                <input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge"
                                       value="${files7}"/>
                                <input type="hidden" id="acceId7" name="acceId7" value="${acceId7}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/FeeNotes" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">误工证明：</td>
                            <input type="hidden" name="fjtype8" value="8">
                            <td style="width: 450px;">

                                <input type="hidden" id="files8" name="files8" htmlEscape="false" class="input-xlarge"
                                       value="${files8}"/>
                                <input type="hidden" id="acceId8" name="acceId8" value="${acceId8}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files8" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/ProofOfMissingWork" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患者死亡相关证明：
                            </td>
                            <input type="hidden" name="fjtype9" value="9">
                            <td style="width: 450px;">

                                <input type="hidden" id="files9" name="files9" htmlEscape="false" class="input-xlarge"
                                       value="${files9}"/>
                                <input type="hidden" id="acceId9" name="acceId9" value="${acceId9}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files9" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/ProofOfDeath" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">新生儿出生证明：
                            </td>
                            <input type="hidden" name="fjtype10" value="10">
                            <td style="width: 450px;">

                                <input type="hidden" id="files10" name="files10" htmlEscape="false" class="input-xlarge"
                                       value="${files10}"/>
                                <input type="hidden" id="acceId10" name="acceId10" value="${acceId10}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files10" type="files"
                                                                              uploadPath="/AuditAcceptance/Patient/BirthCertificate" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="medical_related">
                    <table class=" table-form">
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方调解申请：</td>
                            <input type="hidden" name="fjtype11" value="11">
                            <td style="width: 450px;">

                                <input type="hidden" id="files11" name="files11" htmlEscape="false" class="input-xlarge"
                                       value="${files11}"/>
                                <input type="hidden" id="acceId11" name="acceId11" value="${acceId11}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files11" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/MediationApplication" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">法定代表人身份证明：
                            </td>
                            <input type="hidden" name="fjtype12" value="12">
                            <td style="width: 450px;">

                                <input type="hidden" id="files12" name="files12" htmlEscape="false" class="input-xlarge"
                                       value="${files12}"/>
                                <input type="hidden" id="acceId12" name="acceId12" value="${acceId12}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files12" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/Identification" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">授权委托书：</td>
                            <input type="hidden" name="fjtype13" value="13">
                            <td style="width: 450px;">

                                <input type="hidden" id="files13" name="files13" htmlEscape="false" class="input-xlarge"
                                       value="${files13}"/>
                                <input type="hidden" id="acceId13" name="acceId13" value="${acceId13}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files13" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/Proxy" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                委托人、被委托人相关证件：
                            </td>
                            <input type="hidden" name="fjtype14" value="14">
                            <td style="width: 450px;">

                                <input type="hidden" id="files14" name="files14" htmlEscape="false" class="input-xlarge"
                                       value="${files14}"/>
                                <input type="hidden" id="acceId14" name="acceId14" value="${acceId14}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files14" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/PrincipalRelatedDocuments" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">执业许可证副件：
                            </td>
                            <input type="hidden" name="fjtype15" value="15">
                            <td style="width: 450px;">

                                <input type="hidden" id="files15" name="files15" htmlEscape="false" class="input-xlarge"
                                       value="${files15}"/>
                                <input type="hidden" id="acceId15" name="acceId15" value="${acceId15}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files15" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/LicenseToPractise" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">诊疗分析意见：</td>
                            <input type="hidden" name="fjtype16" value="16">
                            <td style="width: 450px;">

                                <input type="hidden" id="files16" name="files16" htmlEscape="false" class="input-xlarge"
                                       value="${files16}"/>
                                <input type="hidden" id="acceId16" name="acceId16" value="${acceId16}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files16" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/Analysis" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">
                                出险医务人员相关证件（身份证、执业证书）：
                            </td>
                            <input type="hidden" name="fjtype17" value="17">
                            <td style="width: 450px;">

                                <input type="hidden" id="files17" name="files17" htmlEscape="false" class="input-xlarge"
                                       value="${files17}"/>
                                <input type="hidden" id="acceId17" name="acceId17" value="${acceId17}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files17" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/MedicalPersonnelDocuments" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">相关证据材料：</td>
                            <input type="hidden" name="fjtype18" value="18">
                            <td style="width: 450px;">

                                <input type="hidden" id="files18" name="files18" htmlEscape="false" class="input-xlarge"
                                       value="${files18}"/>
                                <input type="hidden" id="acceId18" name="acceId18" value="${acceId18}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files18" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/Evidence" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                        </tr>
                        <tr style=" ">
                            <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">病例资料：</td>
                            <input type="hidden" name="fjtype19" value="19">
                            <td style="width: 450px;">

                                <input type="hidden" id="files19" name="files19" htmlEscape="false" class="input-xlarge"
                                       value="${files19}"/>
                                <input type="hidden" id="acceId19" name="acceId19" value="${acceId19}">
                                <div style="margin-top: -45px;"><sys:ckfinder input="files19" type="files"
                                                                              uploadPath="/AuditAcceptance/Doctor/CaseInformation" selectMultiple="true" readonly="true"/></div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <table class="table-form">
                <tr style=" ">
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">人民调解受理登记表：</td>
                    <input type="hidden" name="fjtype20" value="20">
                    <td style="width: 450px;">

                        <input type="hidden" id="files20" name="files20" htmlEscape="false" class="input-xlarge"
                               value="${files20}"/>
                        <input type="hidden" id="acceId20" name="acceId20" value="${acceId20}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files20" type="files"
                                                                      uploadPath="/AuditAcceptance/People/AcceptanceOfRegistrationForms" selectMultiple="true" readonly="true"/></div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <table class="table-form">
        <%--<tr>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>--%>
                <%--案件来源：--%>
            <%--</td>--%>
            <%--<td width="460px">--%>
                <%--&lt;%&ndash;<form:select path="caseSource" class="input-medium required">&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<form:options items="${fns:getDictList('case_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</form:select>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                <%--<c:choose>--%>
                    <%--<c:when test="${auditAcceptance.caseSource=='1'}">--%>
                        <%--当事人申请--%>
                    <%--</c:when>--%>
                    <%--<c:when test="${auditAcceptance.caseSource=='2'}">--%>
                        <%--人民调解委员会主动调解--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                        <%--无--%>
                    <%--</c:otherwise>--%>
                <%--</c:choose>--%>
            <%--</td>--%>
            <%--<td class="tit" width="180px"><font color="red">*</font>--%>
                <%--起保日期：--%>
            <%--</td>--%>
            <%--<td width="460px">--%>
                <%--&lt;%&ndash;<input name="guaranteeTime" type="text" readonly="readonly" maxlength="20"&ndash;%&gt;--%>
                       <%--&lt;%&ndash;class="input-medium Wdate required"&ndash;%&gt;--%>
                       <%--&lt;%&ndash;value="${auditAcceptance.guaranteeTime}"&ndash;%&gt;--%>
                       <%--&lt;%&ndash;onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',isShowClear:false});"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                        <%--${auditAcceptance.guaranteeTime}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="tit"><font color="red">*</font>--%>
                <%--保险公司：--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--&lt;%&ndash;<form:input path="insuranceCompany" htmlEscape="false" maxlength="50"&ndash;%&gt;--%>
                            <%--&lt;%&ndash;class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                <%--${auditAcceptance.insuranceCompany}--%>
            <%--</td>--%>
            <%--<td class="tit"><font color="red">*</font>--%>
                <%--保单号：--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--&lt;%&ndash;<form:input path="policyNumber" htmlEscape="false" maxlength="15" class="input-xlarge required"/><span&ndash;%&gt;--%>
                    <%--&lt;%&ndash;class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                <%--${auditAcceptance.policyNumber}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td class="tit"><font color="red">*</font>--%>
                <%--诊疗方式：--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--&lt;%&ndash;<form:input path="diagnosisMode" htmlEscape="false" class="input-xlarge required"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                <%--${auditAcceptance.diagnosisMode}--%>
            <%--</td>--%>
            <%--<td class="tit"><font color="red">*</font>--%>
                <%--治疗结果：--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--&lt;%&ndash;<form:input path="treatmentOutcome" htmlEscape="false" class="input-xlarge required"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<span class="help-inline"><font color="red">*</font> </span>&ndash;%&gt;--%>
                <%--${auditAcceptance.treatmentOutcome}--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
                <%--<td class="tit"><font color="red">*</font>--%>
                <%--下一处理环节：--%>
                <%--</td>--%>
                <%--<td>--%>
                <%--<form:input path="nextLink" htmlEscape="false" maxlength="32" class="input-xlarge"/>--%>
                <%--</td>--%>
            <td class="tit" width="240px"><font color="red">*</font>
                下一环节处理人：
            </td>
            <td>
                <%--<sys:treeselect id="nextLinkMan" name="nextLinkMan" value="${empty auditAcceptance.nextLinkMan?fns:getUser().id:auditAcceptance.nextLinkMan}" labelName=""--%>
                                <%--labelValue="${empty auditAcceptance.linkEmployee.name?fns:getUser().name:auditAcceptance.linkEmployee.name}"--%>
                                <%--title="用户" url="/sys/office/treeData?type=3&officeType=1" cssClass="required" allowClear="true"--%>
                                <%--notAllowSelectParent="true" />--%>
                        ${empty auditAcceptance.linkEmployee.name?fns:getUser().name:auditAcceptance.linkEmployee.name}
            </td>
        </tr>
    </table>
        </div>
        <div class="tab-pane fade" id="details">
            <ul id="iframe" class="nav nav-tabs">
                <li class="active">
                    <a href="#tsjd" data-toggle="tab">投诉接待</a>
                </li>
                <li>
                    <a href="#badj" data-toggle="tab">报案登记</a>
                </li>
            </ul>
            <div id="iframeTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="tsjd">
                    <iframe id="tsjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="badj">
                    <iframe id="badjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
        <act:histoicFlow procInsId="${auditAcceptance.complaintMain.procInsId}"/>
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