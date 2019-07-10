<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>评估鉴定管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function() {




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
            //投诉接待详情
            $("#tsjdDetail").attr("src","${ctx}/complaintdetail/complaintMainDetail/form?id=${map.tsjd}&type=view");
            var tsjd= document.getElementById("tsjdDetail");
            tsjd.height=document.documentElement.clientHeight-130;
            tsjd.width=document.documentElement.clientWidth;
            var show='${show2}';
            if(show=='' || show== null){
            //报案登记
            $("#badjDetail").attr("src","${ctx}/registration/reportRegistration/form?id=${map.badj}&type=view&show2=y");
            var badj= document.getElementById("badjDetail");
            badj.height=document.documentElement.clientHeight-130;
            badj.width=document.documentElement.clientWidth;
            //审核受理
            $("#shslDetail").attr("src","${ctx}/auditacceptance/auditAcceptance/form?id=${map.shsl}&type=view&show2=y");
            var shsl= document.getElementById("shslDetail");
            shsl.height=document.documentElement.clientHeight-130;
            shsl.width=document.documentElement.clientWidth;
            //调查取证
            $("#dcqzDetail").attr("src","${ctx}/nestigateeividence/investigateEvidence/form?id=${map.dcqz}&type=view&show2=y");
            var dcqz= document.getElementById("dcqzDetail");
            dcqz.height=document.documentElement.clientHeight-130;
            dcqz.width=document.documentElement.clientWidth;
            //质证调解
            $("#zztjDetail").attr("src","${ctx}/mediate/mediateEvidence/form?id=${map.zztj}&type=view&show2=y");
            var zztj= document.getElementById("zztjDetail");
            zztj.height=document.documentElement.clientHeight-130;
            zztj.width=document.documentElement.clientWidth;
            <%--//评估坚定申请--%>
            <%--$("#pgjdsqDetail").attr("src","${ctx}/assessapply/assessApply/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsq= document.getElementById("pgjdsqDetail");--%>
            <%--pgjdsq.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsq.width=document.documentElement.clientWidth;--%>
            <%--//评估鉴定审批--%>
            <%--$("#pgjdspDetail").attr("src","${ctx}/assessaudit/assessAudit/form?id=${map.pgjdsq}&type=view&show2=y");--%>
            <%--var pgjdsp= document.getElementById("pgjdspDetail");--%>
            <%--pgjdsp.height=document.documentElement.clientHeight-130;--%>
            <%--pgjdsp.width=document.documentElement.clientWidth;--%>
            }
        });
    </script>
</head>
<body>
<br/>
<form:form id="inputForm" modelAttribute="assessAppraisal" action="${ctx}/assessappraisal/assessAppraisal/save" method="post" class="form-horizontal">
    <form:hidden path="assessAppraisalId"/>
    <form:hidden path="createDate"/>
    <form:hidden path="createBy"/>
    <form:hidden path="recordInfo1.recordId"/>
    <form:hidden path="recordInfo1.yrecordInfo.recordId"/>
    <form:hidden path="proposal.proposalId"/>
    <form:hidden path="proposal.assessAppraisalId"/>
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
<fieldset>
    <ul id="myTab" class="nav nav-tabs">
        <li class="active">
            <a href="#pgjd" data-toggle="tab">评估鉴定</a>
        </li>
        <c:if test="${empty show2}">
        <li>
            <a href="#details" data-toggle="tab">详情</a>
        </li>
        </c:if>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div id="pgjd" class="tab-pane fade in active">
    <legend>评估鉴定详情</legend>
    <ul id="myTab2" class="nav nav-tabs">
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

    <div id="myTabContent1" class="tab-content">
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
                            ${assessAppraisal.recordInfo1.startTime}
                    </td>
                    <td class="tit">
                        结束时间：
                    </td>
                    <td>
                            ${assessAppraisal.recordInfo1.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        地点：
                    </td>
                    <td>
                        ${assessAppraisal.recordInfo1.recordAddress}
                    </td>
                    <%--<td class="tit">--%>
                        <%--事由：--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--${assessAppraisal.recordInfo1.cause}--%>
                    <%--</td>--%>
                    <td class="tit">
                        主持人：
                    </td>
                    <td>
                            ${assessAppraisal.hosts.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        记录人：
                    </td>
                    <td>
                        ${assessAppraisal.clerks.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        笔录内容：
                    </td>
                    <td colspan="3">
                        ${assessAppraisal.recordInfo1.recordContent}
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
                            ${assessAppraisal.recordInfo1.yrecordInfo.startTime}
                    </td>
                    <td class="tit">
                        结束时间：
                    </td>
                    <td>
                            ${assessAppraisal.recordInfo1.yrecordInfo.endTime}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        地点：
                    </td>
                    <td>
                       ${assessAppraisal.recordInfo1.yrecordInfo.recordAddress}
                    </td>
                    <%--<td class="tit">--%>
                        <%--事由：--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--${assessAppraisal.recordInfo1.yrecordInfo.cause}--%>
                    <%--</td>--%>
                    <td class="tit">
                        主持人：
                    </td>
                    <td>
                            ${assessAppraisal.hosts.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        记录人：
                    </td>
                    <td>
                        ${assessAppraisal.clerks.name}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        笔录内容：
                    </td>
                    <td colspan="3">
                        ${assessAppraisal.recordInfo1.yrecordInfo.recordContent}
                    </td>

                </tr>
            </table>
        </div>
        <div class="tab-pane fade " id="zjopinion">
            <table class="table-form">
                <tr>
                    <td class="tit" width="225px">
                        患者姓名：
                    </td>
                    <td>
                        ${assessAppraisal.patientName}
                    </td>
                    <td class="tit" width="225px">
                        患者性别：
                    </td>
                    <td>
                        ${fns:getDictLabel(assessAppraisal.patientSex,'sex' ,'无' )}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        患者年龄：
                    </td>
                    <td>
                        ${assessAppraisal.patientAge}
                    </td>
                    <td class="tit">
                        住院号：
                    </td>
                    <td>
                        ${assessAppraisal.hospitalNumber}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        涉及医院：
                    </td>
                    <td colspan="3">
                            ${assessAppraisal.complaintMain.hospital.name}
                    </td>

                </tr>
                <tr>
                    <td class="tit">诊断分析：</td>
                    <td colspan="3">
                        ${assessAppraisal.diagnosticAnalysis}
                    </td>
                </tr>
                <tr>
                    <td class="tit">治疗分析：</td>
                    <td colspan="3">
                        ${assessAppraisal.treatmentAnalysis}
                    </td>
                </tr>
                <tr>
                    <td class="tit">其他医疗分析：</td>
                    <td colspan="3">
                       ${assessAppraisal.otherMedicalAnalysis}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        医学专家：
                    </td>
                    <td colspan="3">
                        ${assessAppraisal.medicalExpertName}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        法律顾问：
                    </td>
                    <td colspan="3">
                        ${assessAppraisal.legalExpert}
                    </td>
                </tr>
                <tr>
                    <td class="tit">
                        其他：
                    </td>
                    <td colspan="3">
                        ${assessAppraisal.other}
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
                    <table id="patientTable" class="table table-striped table-bordered table-condensed">
                        <legend>患方</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >性别</th>
                            <th >身份证号</th>
                            <th >住址</th>
                            <th >操作</th>
                            <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="patientLinkEmpList"></tbody>
                        <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
                            <tfoot>
                            <tr><td colspan="7"></td></tr>
                            </tfoot></shiro:hasPermission>
                    </table>
                    <script type="text/template" id="patientLinkEmpTp">
						<tr id="patientLinkEmpList{{idx}}">
							<td class="hide">
								<td class="hide">
								<input id="patientLinkEmpList{{idx}}_id" name="patientLinkEmpList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="patientLinkEmpList{{idx}}_patientLinkEmpId" name="patientLinkEmpList[{{idx}}].patientLinkEmpId" type="hidden" value="{{row.patientLinkEmpId}}"/>
								<input id="patientLinkEmpList{{idx}}_linkType" name="patientLinkEmpList[{{idx}}].linkType" type="hidden" value="{{row.linkType}}"/>
								<input id="patientLinkEmpList{{idx}}_relationId" name="patientLinkEmpList[{{idx}}].relationId" type="hidden" value="{{row.relationId}}"/>
								<input id="patientLinkEmpList{{idx}}_delFlag" name="patientLinkEmpList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							</td>

							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkName" name="patientLinkEmpList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required" disabled="disabled"/>
							</td>
							<td>
								<select id="patientLinkEmpList{{idx}}_patientLinkSex" name="patientLinkEmpList[{{idx}}].patientLinkSex" data-value="{{row.patientLinkSex}}" class="input-mini" disabled="disabled">
									<option value=""></option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_idNumber" name="patientLinkEmpList[{{idx}}].idNumber" type="text" value="{{row.idNumber}}" maxlength="20" class="required" readonly="readonly" />
							</td>
							<td>
								<input id="patientLinkEmpList{{idx}}_patientLinkAddress" name="patientLinkEmpList[{{idx}}].patientLinkAddress" type="text" value="{{row.patientLinkAddress}}" maxlength="20" class="required" readonly="readonly" />
							</td>

							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkEmpList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>
                    <table id="patientDTable" class="table table-striped table-bordered table-condensed">
                        <legend>患方联系人</legend>
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >姓名</th>
                            <th >与患者关系</th>
                            <th >电话</th>
                            <th >操作</th>
                            <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="patientLinkDList"></tbody>
                        <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
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
								<input id="patientLinkDList{{idx}}_patientLinkName" name="patientLinkDList[{{idx}}].patientLinkName" type="text" value="{{row.patientLinkName}}" maxlength="100" class="required"disabled="disabled" />
							</td>
							<td>
                        		<select id="patientLinkDList{{idx}}_patientRelation" name="patientLinkDList[{{idx}}].patientRelation" value="{{row.patientRelation}}" data-value="{{row.patientRelation}}" class="input-mini" disabled="disabled">
									<option value=""></option>
									<option value="1"  >亲人</option>
									<option value="2"  >朋友</option>
									<option value="3"  >代理人</option>
								</select>
							</td>
							<td>
								<input id="patientLinkDList{{idx}}_patientLinkMobile" name="patientLinkDList[{{idx}}].patientLinkMobile" type="text" value="{{row.patientLinkMobile}}" maxlength="20" class="required" readonly="readonly"/>
							</td>


							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#patientLinkDList{{idx}}','_patientLinkEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>
                </div>
                <div class="tab-pane fade" id="medical">
                    <table id="hospitalTable" class="table table-striped table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th class="hide"></th>
                            <th >医疗机构名称</th>
                            <th >委托代理人</th>
                            <th >联系方式</th>
                            <th >职务</th>
                            <th >操作</th>
                            <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
                                <th >&nbsp;</th>
                            </shiro:hasPermission>
                        </tr>
                        </thead>
                        <tbody id="medicalOfficeEmpList"></tbody>
                        <shiro:hasPermission name="assessappraisal:assessAppraisal:edit">
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
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeName" name="medicalOfficeEmpList[{{idx}}].medicalOfficeName" type="text" value="{{row.medicalOfficeName}}" maxlength="100" class="required" readonly="readonly" />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeAgent" name="medicalOfficeEmpList[{{idx}}].medicalOfficeAgent" type="text" value="{{row.medicalOfficeAgent}}" maxlength="32" class="required" readonly="readonly" />
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficeMobile" name="medicalOfficeEmpList[{{idx}}].medicalOfficeMobile" type="text" value="{{row.medicalOfficeMobile}}" maxlength="200" class="required" readonly="readonly"/>
							</td>
							<td>
								<input id="medicalOfficeEmpList{{idx}}_medicalOfficePost" name="medicalOfficeEmpList[{{idx}}].medicalOfficePost" type="text" value="{{row.medicalOfficePost}}" maxlength="20" class="required" readonly="readonly"/>
							</td>
							<shiro:hasPermission name="assessappraisal:assessAppraisal:edit"><td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#medicalOfficeEmpList{{idx}}','_medicalOfficeEmpId')" title="删除">&times;</span>{{/delBtn}}
							</td></shiro:hasPermission>
						</tr>
                    </script>



                </div>
                <div class="tab-pane fade" id="profile">
                    <table class="table-form">
                        <tr>
                            <td class="tit" width="225px">诊疗概要:</td>
                            <td colspan="3">
                                ${assessAppraisal.proposal.treatmentSummary}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" id="points">
                    <table class="table-form">
                        <tr>
                            <td class="tit" width="225px">患方认为:</td>
                            <td colspan="3">
                                ${assessAppraisal.proposal.patientThink}
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">医方认为:</td>
                            <td colspan="3">
                                ${assessAppraisal.proposal.doctorThink}
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
                        <c:forEach items="${fxyj}" var="column" varStatus="vs">
                            <tr>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="typeInfosList[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="typeInfosList[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="typeInfosList[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
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
                                ${assessAppraisal.proposal.diagnosis}
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">
                                治疗:
                            </td>
                            <td>
                                ${assessAppraisal.proposal.treatment}
                            </td>
                        </tr>
                        <tr>
                            <td class="tit">
                                其他:
                            </td>
                            <td>
                                ${assessAppraisal.proposal.other}
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
                        <c:forEach items="${jl}" var="column" varStatus="vs">
                            <tr>
                                <td nowrap style="text-align:center;vertical-align:middle;">
                                    <input type="hidden" name="typeInfosList2[${vs.index}].typeId" value="${column.typeId}" disabled="disabled"/>
                                    <input type="hidden" name="typeInfosList2[${vs.index}].delFlag" value="${column.delFlag}" disabled="disabled"/>
                                    <input type="checkbox" name="typeInfosList2[${vs.index}].label" value="1" ${column.label eq '1' ? 'checked' : ''} disabled="disabled"/>
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
                        <input type="hidden" id="acceId1" name="acceId1" value="${acceId1}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files1" type="files"  uploadPath="/assessappraisal/assessAppraisal/qiandao" selectMultiple="true" readonly="true" /></div>
                    </td>

                </tr>
                <tr style=" " >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方笔录：</td>
                    <input type="hidden"  name="fjtype2" value="17">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files2" name="files2" htmlEscape="false" class="input-xlarge"  value="${files2}"/>
                        <input type="hidden" id="acceId2" name="acceId2" value="${acceId2}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files2" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbl" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">患方补充材料：</td>
                    <input type="hidden" name="fjtype3" value="18">
                    <td style="width: 450px; ">
                        <input type="hidden" id="files3" name="files3" htmlEscape="false" class="input-xlarge" value="${files3}" />
                        <input type="hidden" id="acceId3" name="acceId3" value="${acceId3}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files3" type="files"  uploadPath="/assessappraisal/assessAppraisal/huanbc" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方笔录：</td>
                    <input type="hidden"  name="fjtype4" value="19">
                    <td style="width: 450px; ">

                        <input type="hidden" id="files4" name="files4" htmlEscape="false" class="input-xlarge"  value="${files4}"/>
                        <input type="hidden" id="acceId4" name="acceId4" value="${acceId4}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files4" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibl" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医方补充材料：</td>
                    <input type="hidden" name="fjtype5" value="20">
                    <td style="width: 450px;">
                        <input type="hidden" id="files5" name="files5" htmlEscape="false" class="input-xlarge" value="${files5}" />
                        <input type="hidden" id="acceId5" name="acceId5" value="${acceId5}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files5" type="files"  uploadPath="/assessappraisal/assessAppraisal/yibc" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">专家评估意见表：</td>
                    <input type="hidden" name="fjtype6" value="21">
                    <td style="width: 450px;">
                        <input type="hidden" id="files6" name="files6" htmlEscape="false" class="input-xlarge" value="${files6}" />
                        <input type="hidden" id="acceId6" name="acceId6" value="${acceId6}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files6" type="files"  uploadPath="/assessappraisal/assessAppraisal/zhuanjiayj" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
                <tr style="" >
                    <td style="text-align: center; width: 80px; font-weight: bolder;height: 120px;">医疗损害核算单：</td>
                    <input type="hidden" name="fjtype7" value="22">
                    <td style="width: 450px;">
                        <input type="hidden" id="files7" name="files7" htmlEscape="false" class="input-xlarge" value="${files7}" />
                        <input type="hidden" id="acceId7" name="acceId7" value="${acceId7}">
                        <div style="margin-top: -45px;"><sys:ckfinder input="files7" type="files"  uploadPath="/assessappraisal/assessAppraisal/yiliaosh" selectMultiple="true" readonly="true"/></div>
                    </td>

                </tr>
            </table>
        </div>
    </div>



    <table class="table-form">
        <tr>
            <td class="tit" width="225px">
                申请类型：
            </td>
            <td width="520px">
                ${fns:getDictLabel(assessAppraisal.applyType,'assessmentAppraisal' ,'无' )}
            </td>
            <td class="tit" width="225px">
                责任比例：
            </td>
            <td>
                ${assessAppraisal.responsibilityRatio}
            </td>
        </tr>
        <tr>
            <td class="tit">
                主持人：
            </td>
            <td>
               ${assessAppraisal.hosts.name}
            </td>
            <td class="tit">
                书记员：
            </td>
            <td>
                ${assessAppraisal.clerks.name}
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

            <td class="tit" width="225px">
                下一环节处理人：
            </td>
            <td>
                    ${assessAppraisal.linkEmployee.name}
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
                <li>
                    <a href="#shsl" data-toggle="tab">审核受理</a>
                </li>
                <li>
                    <a href="#dcqz" data-toggle="tab">调查取证</a>
                </li>
                <li>
                    <a href="#zztj" data-toggle="tab">质证调解</a>
                </li>
                <%--<li>--%>
                    <%--<a href="#pgjdsq" data-toggle="tab">评估鉴定申请</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#pgjdsp" data-toggle="tab">评估鉴定审批</a>--%>
                <%--</li>--%>
            </ul>
            <div id="iframeTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="tsjd">
                    <iframe id="tsjdDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="badj">
                    <iframe id="badjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="shsl">
                    <iframe id="shslDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="dcqz">
                    <iframe id="dcqzDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <div class="tab-pane fade" id="zztj">
                    <iframe id="zztjDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>
                </div>
                <%--<div class="tab-pane fade" id="pgjdsq">--%>
                    <%--<iframe id="pgjdsqDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>--%>
                <%--</div>--%>
                <%--<div class="tab-pane fade" id="pgjdsp">--%>
                    <%--<iframe id="pgjdspDetail" src="" style="box-shadow:0 0 10px rgba(0,0,0,0.3)" frameborder="0" scrolling="auto" ></iframe>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</fieldset>
    <c:if test="${empty show2}">
    <div class="form-actions">
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" style="margin-left: 550px;"/>
    </div>
    <act:histoicFlow procInsId="${assessAppraisal.complaintMain.procInsId}" />
    </c:if>
</form:form>
<script type="text/javascript">
    var medicalOfficeEmpRowIdx = 0, medicalOfficeEmpTp = $("#medicalOfficeEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkEmpRowIdx = 0, patientLinkEmpTp = $("#patientLinkEmpTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    var patientLinkDRowIdx = 0, patientLinkDTp = $("#patientLinkDTp").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
    $(document).ready(function() {
        var data = ${fns:toJson(assessAppraisal.medicalOfficeEmpList)};
        console.log(data)
        for (var i=0; i<data.length; i++){
            addRow('#medicalOfficeEmpList', medicalOfficeEmpRowIdx, medicalOfficeEmpTp, data[i]);
            medicalOfficeEmpRowIdx = medicalOfficeEmpRowIdx + 1;
        }

        var data = ${fns:toJson(assessAppraisal.patientLinkDList)};
        for (var i=0; i<data.length; i++){
            addRow('#patientLinkDList', patientLinkDRowIdx, patientLinkDTp, data[i]);
            patientLinkDRowIdx = patientLinkDRowIdx + 1;
        }

        var PatientData = ${fns:toJson(assessAppraisal.patientLinkEmpList)};
        for (var i=0; i<PatientData.length; i++){
            addRow('#patientLinkEmpList', patientLinkEmpRowIdx, patientLinkEmpTp, PatientData[i]);
            patientLinkEmpRowIdx = patientLinkEmpRowIdx + 1;
        }

    });
</script>
</body>
</html>