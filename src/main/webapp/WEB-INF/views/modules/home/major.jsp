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
       background-color: black;
   }

</style>


<body>
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=${type}&commonType=sjzy" method="post" class="breadcrumb form-search">
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
<div id="main4" style="width: 49%; border: 0px;border-style:solid;height: 500px;float: left;margin-top: 6px;margin-left: 0.5%;"></div>
<div id="bb" style="float : right;width: 49%;height:500px;">
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th style="text-align:center;">专业名称</th>
            <th style="text-align:center;">数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${sjzyTableInfo}" var="info">
            <tr>
                <td style="text-align:center;">
                        ${info.name}
                </td>
                <td style="text-align:center;">
                        ${info.department}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var myChart4 = echarts.init(document.getElementById('main4'), 'dark');
    optionZY = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title:{
            text:'各专业案件统计',
            x:0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },
        tooltip : {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                label: {
                    backgroundColor: '#6a7985'
                }
            }
        },
        grid: {
            top:'12%',
            left: '6%',
            right: '6%',
            bottom: '6%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : ${nameList}
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [

            {
                name:'各专业案件数量',
                type:'line',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                areaStyle: {normal: {}},
                data:${departmentList}
            }
        ]
    };
    myChart4.off('click');//防止重复绑定 事件
    myChart4.setOption(optionZY);
</script>

</body>
</html>
