<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/9/4
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
<%--<h1 align="center">用户列表</h1>--%>
<center>
<table border="1" width="800px" style="text-align: center">
    <tr>
        <td>用户名</td>
        <td>age</td>
        <td>address</td>
        <td>remark</td>
        <td>操作</td>
    </tr>

    <c:forEach items="${uList}" var="item">
        <tr>
            <td>${item.username}</td>
            <td>${item.age}</td>
            <td>${item.address}</td>
            <td>${item.remark}</td>
            <td>删除</td>
        </tr>
    </c:forEach>
</table>

    <a href="${pageContext.request.contextPath}/user/userList?pageNum=${pageNum-1}">上一页</a>
    <a href="${pageContext.request.contextPath}/user/userList?pageNum=${pageNum+1}">下一页</a>
</center>

</body>
</html>
