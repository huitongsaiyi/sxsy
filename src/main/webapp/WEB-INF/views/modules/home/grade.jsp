<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>统计</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/dist/echarts.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/echarts/dist/echarts-gl.min.js"></script>
    <script src="${ctxStatic}/echarts/map/js/province/shanxi.js"></script>
    <script src="${ctxStatic}/echarts/dark.js"></script>
</head>
<style type="text/css">

   body{
       background-color: white;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=${type}&commonType=yydj" method="post" class="breadcrumb form-search">
    <ul class="ul-form" style="height: 35px;">
        <li id="year" style="">
            <label>日期(年)：</label>
            <input id="yearDate" name="yearDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${yearDate}"
                   onclick="WdatePicker({dateFmt:'yyyy',isShowClear:true}); $('#beginMonthDate').val('');$('#endMonthDate').val('');"/>
        </li>
        <li id="month" style="">
            <label>日期(月)：</label>
            <input id="beginMonthDate" name="beginMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${beginMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>
            -
            <input id="endMonthDate" name="endMonthDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
                   value="${endMonthDate}"
                   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});$('#yearDate').val('');"/>

        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--<div id="aa" style="width: 100%;margin: 0 auto;background : #ffffff ;background-size:cover;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;
    <div style="clear: both;"></div>
</div>--%>
<div id="main3" style="width: 49%; border: 0px;border-style:solid;height:500px;float: left;margin-top: 6px;"></div>
<div id="bb" style="float : right;width: 49%;height:500px;">
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th style="text-align:center;">专业名称</th>
            <th style="text-align:center;">数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${yydjTableInfo}" var="info">
            <tr>
                <td style="text-align:center;">
                        ${info.name}
                </td>
                <td style="text-align:center;">
                        ${info.value}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var myChart3 = echarts.init(document.getElementById('main3'), 'dark');
    //饼状图
    optionBZ = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title : {
            text: '各等级医院纠纷数量统计',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'right',
            top : '50px',
            data: ['三级甲等','三级乙等','二级甲等','二级乙等','乡镇卫生院','社区服务站','民营医院','门诊']
        },
        color:['#B24971','#E9D082','#1CDEAA','#1CB7DE','#711CDE','#DE1C67','#D8DE1C','#1CDE29'],
        series : [
            {
                name: '投诉数量',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data: ${asdf},
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(255, 255, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart3.setOption(optionBZ);
</script>

</body>
</html>
