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
<form:form id="searchForm" action="${ctx}/complaintmain/complaintMain/head?type=tj&newType=amout" method="post" class="breadcrumb form-search">
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
      <%--  <li>
            <label>类型：</label>
            <select name="newType" class="input-small ">
                <option value=""></option>
                <option value="duty">责任度</option>
                <option value="amountRatio">赔偿金额比例</option>
            </select>
        </li>--%>

        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="统计"/></li>
    </ul>
</form:form>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="aa" style="float : left; width: 49%;height:500px;margin: 0 auto;background : #ffffff ;background-size:cover;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
</div>
<div id="bb" style="float : right;width: 49%;height:500px;"><%--background-image: url('${ctxStatic}/images/background.jpg');--%>
    <table class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th style="text-align:center;">赔付额</th>
            <th style="text-align:center;">数量</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td style="text-align:center;">
                    2万及以下
                </td>
                <td style="text-align:center;">
                    <c:forEach items="${amountTableInfo}" var="info" varStatus="status">
                        ${info['2万及以下']}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td style="text-align:center;">
                    2万到10万及以下
                </td>
                <td style="text-align:center;">
                    <c:forEach items="${amountTableInfo}" var="info" varStatus="status">
                        ${info['2万到10万及以下']}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td style="text-align:center;">
                    10万到50万及以下
                </td>
                <td style="text-align:center;">
                    <c:forEach items="${amountTableInfo}" var="info" varStatus="status">
                        ${info['10万到50万及以下']}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td style="text-align:center;">
                    50万以上
                </td>
                <td style="text-align:center;">
                    <c:forEach items="${amountTableInfo}" var="info" >
                        ${info['50万以上']}
                    </c:forEach>
                </td>
            </tr>
        </tbody>
    </table>
</div>
<div align="center">
    <input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
</div>
<script type="text/javascript">
    var myChart = echarts.init(document.getElementById('aa'));

    //柱状图
    option = {
        backgroundColor:'rgba(0, 0, 0, 0.1)',
        xAxis: {
            type: 'category',
            data: ${keyList}
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: ${valueList},
            type: 'bar'
        }]
    };

    myChart.setOption(option);
    myChart.off('click');//防止重复绑定 事件
    myChart.on('click',function(params){
    });
</script>

</body>
</html>
