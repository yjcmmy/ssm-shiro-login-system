<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: yjc
  Date: 2018/10/18
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>员工信息页</title>
    <script type="text/javascript" src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">

        $(function () {
            $(".delete").click(function () {
                var url=$(this).attr("href");
                $("form").attr("action",url);
                $("form").submit();
                return false;
            })

        });
    </script>

</head>
<body>
<h1>Welcome，${requestScope.user.name},
    <shiro:hasRole name="boss">
        您的身份是：Boss
    </shiro:hasRole>
    <shiro:lacksRole name="boss">
        <shiro:hasRole name="manager">
            您的身份是：Manager
        </shiro:hasRole>
        <shiro:lacksRole name="manager">
            您的身份是：Employee
        </shiro:lacksRole>
    </shiro:lacksRole>
    <br/>
</h1>


<button onclick="window.location.href='${pageContext.request.contextPath}/yjc/updateMyself/${requestScope.user.name}'">修改个人信息</button> <br/>
<button onclick="window.location.href='${pageContext.request.contextPath}/yjc/changePassword/${requestScope.user.name}'">修改个人密码</button> <br/>
<button onclick="window.location.href='${pageContext.request.contextPath}/logout'">退出登录</button> <br/>



<table border="1" width="100%">
    <caption><h2>公司员工信息表</h2></caption>
    <tr>
        <td>工号</td><td>姓名</td><td>邮箱</td><td>入职时间</td><td>领导工号</td><td>所在部门</td><td>工资</td>
    </tr>
    <c:forEach items="${requestScope.employees}" var="employee" >
        <tr>
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td><fmt:formatDate value="${employee.birth}" pattern="yyyy-MM-dd"/></td>
            <td>${employee.leaderId}</td>
            <td>${employee.department}</td>
            <shiro:hasRole name="manager">
                <td>${employee.salary}</td>
            </shiro:hasRole>
            <shiro:hasRole name="boss">
                <td><a href="${pageContext.request.contextPath}/yjc/update/${employee.id}">编辑</a></td>
                <td><a class="delete" href="${pageContext.request.contextPath}/yjc/delete/${employee.id}">删除</a></td>
            </shiro:hasRole>

        </tr>
    </c:forEach>
</table>
<shiro:hasRole name="boss">
    <button onclick="window.location.href='${pageContext.request.contextPath}/yjc/insert'">增加</button>
</shiro:hasRole>


<form action=""  method="post">
    <input type="hidden" name="_method" value="DELETE"/>
</form>

<a href="${pageContext.request.contextPath}/yjc/homepage/1">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;
页码：
<c:forEach items="${requestScope.pages}" var="page">
    <c:if test="${requestScope.localpage==page}">
        ${page}
    </c:if>
    <c:if test="${requestScope.localpage!=page}">
        <a href="${pageContext.request.contextPath}/yjc/homepage/${page}">${page}</a>
    </c:if>
</c:forEach>&nbsp;&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/yjc/homepage/${requestScope.lastpage}">尾页</a>


</body>
</html>
