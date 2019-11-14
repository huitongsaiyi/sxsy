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
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=${type}&commonType=jfjd" method="post" class="breadcrumb form-search">
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
        <%--<li>
            <label>类型：</label>
            <select name="newType" class="input-small ">
                <option value=""></option>
                <option value="duty">责任度</option>
                <option value="amountRatio">赔偿金额比例</option>
            </select>
        </li>--%>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
        <%--<li class="btns"><input id="expSubmit" class="btn btn-primary" type="submit" value="导出"/></li>--%>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<%--<div id="aa" style="width: 100%;margin: 0 auto;background : #ffffff ;background-size:cover;">&lt;%&ndash;background-image: url('${ctxStatic}/images/background.jpg');&ndash;%&gt;


    <div style="clear: both;"></div>
</div>--%>
<div id="main"  style="width: 49%;height: 500px; border: 0px;border-style:solid;float: left;" ></div>
<div id="bb" style="float : right;width: 49%;height:500px;">
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th style="text-align:center;">焦点名称</th>
            <th style="text-align:center;">数量</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${jfjdTableInfo}" var="info">
            <tr>
                <td style="text-align:center;">
                        ${info.typeName}
                </td>
                <td style="text-align:center;">
                        ${info.num}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('main'), 'dark');
    //案件类型统计option
    option = {
        backgroundColor:'rgba(128, 128, 128, 0.5)', //rgba设置透明度0.1
        title: {
            text: '纠纷焦点统计',
            left: 0,
            textStyle:{
                color:'rgb(255,255,255)',
                fontSize:14
            }
        },

        // color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ${list},
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'数量',
                type:'bar',
                barWidth: '60%',
                data:${list2},
                rawdate:${ids},
                itemStyle:{
                    normal:{
                        color:'#4ad2ff'
                    }
                }
            }
        ]
    };



    myChart.setOption(option);
    myChart.off('click');//防止重复绑定 事件
    myChart.on('click',function(params){
    });

</script>

</body>
</html>
