<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script>
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a>用户列表</a></li>
</ul>
<form:form id="searchForm" modelAttribute="wxUser" action="${ctx}/applet/user/" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div>
        <label>手机号：</label><form:input path="tel" htmlEscape="false" maxlength="50" class="input-small"/>&nbsp;
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
    </div>
</form:form>
<sys:message content=""/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>头像</th>
        <th>昵称</th>
        <th>绑定手机号</th>
        <th>身份</th>
        <th>创建时间</th>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="user" varStatus="i">
        <tr>
            <td>${i.count}</td>
            <td><img src="${user.avatarUrl}" width="40" height="40" width="40"/></td>
            <td>${user.nickName}</td>
            <td>${user.tel}</td>
            <td>${user.userType eq 0?"普通会员":(user.userType eq 1? "医院":"调解员")}</td>

            <td><fmt:formatDate value="${user.createDate}" type="both"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
