<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>审核受理详情</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            //$("#name").focus();
            $("#inputForm").validate({
                submitHandler: function (form) {
                    loading('正在提交，请稍等...');
                    form.submit();
                },
                errorContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    $("#messageBox").text("输入有误，请先更正。");
                    if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
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
        <legend>审核受理详情</legend>
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
                <span style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
                    style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span>
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
            </p>
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
            <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
                <span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span>
            </p>
            <div class="cnzz" style="display: none;"></div>
        </table>
        <hr/>
        <table class="table-form">
            <div>
                <p style="margin:0pt; text-align:center"><span
                        style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bold">医疗纠纷调解受理通知书</span></p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">涉及医院名称</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
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
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患者姓名</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">发生的医疗纠纷案件</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">应悦者及</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">质院双方</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">调解申请，我单位经审查符合受理条件</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">为保证案件调解工作的及时进行</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">,</span></p>
                <p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">请贵院在三个工作日内提供如下材料</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">:</span></p>
                <p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">一、材料名单</span>
                </p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">1</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交法定代表人身份证明书一份</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_</span></p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">2</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、提交授权委托书</span></p>
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
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、与纠纷相关的证据材料</span></p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">8</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">、病历资料复印件</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">(3</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">份</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">)</span></p>
                <p style="margin:0pt"><span style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">二、要求</span>
                </p>
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
                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                        style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
            </div>
            <div class="cnzz" style="display: none;"></div>
        </table>
        <div class="tab-pane fade" id="patientT">
            <table class="table-form">

            </table>
        </div>
        <div class="tab-pane fade" id="hospitalT">
            <table class="table-form">

            </table>
        </div>
        <hr/>
        <table class="table-form">
            <div><p style="margin:0pt; text-align:center"><span
                    style="color:#333333; font-family:宋体; font-size:15pt; font-weight:bold">人民调解受理登记表</span></p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">时间</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">人民调解委员会依当事人申请（人民调解委员会主动调解），经当事人同意，调解</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">患者姓名</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">___</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">与</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">_</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">涉及医院名称</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">__</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">之前的纠纷。</span></p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">案件来源：</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; font-weight:normal; text-decoration:underline">案件来源</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; font-weight:normal; text-decoration:underline">}</span>
                </p>
                <p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:宋体; font-size:12pt">纠纷简要情况：</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; text-decoration:underline">{</span><span
                        style="color:#d9001b; font-family:宋体; font-size:12pt; text-decoration:underline">纠纷概要</span><span
                        style="color:#d9001b; font-family:Arial; font-size:12pt; text-decoration:underline">}</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">__________________________________________________________________</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">_______________________________</span>
                </p>
                <p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">___________________________________________________________________________________</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">_____________________</span></p>
                <p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">__________________________________________________________________________</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt">______________________________</span>
                </p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">______________________________________________</span>
                </p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">当事人（患方）</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span>
                </p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">当事人（医方）</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span>
                </p>
                <p style="margin:0pt"><span style="color:#333333; font-family:Arial; font-size:12pt; font-weight:bold">&#xa0;</span>
                </p>
                <p style="margin:0pt"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal"> </span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">登记人（签名）</span><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">_________________________________</span>
                </p>
                <p style="margin:0pt; text-align:right"><span
                        style="color:#333333; font-family:Arial; font-size:12pt; font-weight:normal">&#xa0;</span><span
                        style="color:#333333; font-family:宋体; font-size:12pt; font-weight:normal">山西省医疗纠纷人民调解委员会</span>
                </p>
                <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                        style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p></div>
        </table>
        <table class="table-form">
            <hr/>
            <tr>
                <td class="tit">
                    案件来源：
                </td>
                <td>
                        ${auditAcceptance.caseSource}
                </td>
                <td class="tit">
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
                        ${auditAcceptance.insuranceCompany}
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
                <td class="tit">
                    治疗结果：
                </td>
                <td>
                        ${auditAcceptance.treatmentOutcome}
                </td>
            </tr>
            <tr>
                <td class="tit">申请人</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.applyer}
                </td>
                <td class="tit">与患者关系</td>

                <c:choose>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation == '1'}">
                        <td>本人</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='2'}">
                        <td>夫妻</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='3'}">
                        <td>子女</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='4'}">
                        <td>父母</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='5'}">
                        <td>兄妹</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='6'}">
                        <td>亲属</td>
                    </c:when>
                    <c:when test="${auditAcceptance.mediateApplyInfo.patientRelation =='7'}">
                        <td>其他</td>
                    </c:when>
                </c:choose>

            </tr>
            <tr>
                <td class="tit">患者姓名</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.patientName}
                </td>
                <td class="tit">患者性别</td>
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
                <td class="tit">患者年龄</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.patientAge}
                </td>
                <td class="tit">患方联系电话</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.patientMobile}
                </td>
            </tr>
            <tr>
                <td class="tit">涉及医院</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.involveHospital}
                </td>
                <td class="tit">申请医院</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyHospital}
                </td>
            </tr>
            <tr>
                <td class="tit">医方代理人</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyer}
                </td>
                <td class="tit">医方联系电话</td>
                <td>
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.hospitalMobile}
                </td>
            </tr>
            <tr>
                <td class="tit">患方投诉纠纷概要</td>
                <td colspan="3">
                        ${auditAcceptance.mediateApplyInfo.summaryOfDisputes}
                </td>
                <td class="tit">患方当事人申请事项</td>
                <td colspan="3">
                        ${auditAcceptance.mediateApplyInfo.applyMatter}
                </td>
            </tr>
            <tr>
                <td class="tit">医方投诉纠纷概要</td>
                <td colspan="3">
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.summaryOfDisputes}
                </td>
                <td class="tit">医方当事人申请事项</td>
                <td colspan="3">
                        ${auditAcceptance.mediateApplyInfo.docMediateApplyInfo.applyMatter}
                </td>
            </tr>
        </table>
    </fieldset>
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>
