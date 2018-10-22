<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: yjc
  Date: 2018/9/11
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改您的个人信息</title>
</head>
<body>
您的工号为：${requestScope.employee.id}<br/>
您的姓名为：${requestScope.employee.name}<br/>
<form:form modelAttribute="employee" action="${pageContext.request.contextPath}/yjc/update" method="post">
    <form:hidden path="id"></form:hidden>
    <input type="hidden" name="_method" value="PUT"/>
    <form:hidden path="name"></form:hidden>
    邮箱:<form:input path="email"></form:input>   <br/>
    入职时间:<form:input path="birth"></form:input>   <br/>
    <form:hidden path="leaderId" ></form:hidden><br/>
    <form:hidden path="department"></form:hidden><br/>
    <form:hidden path="salary"></form:hidden><br/>
    <input type="submit" value="修改" />
</form:form>


</body>
</html>