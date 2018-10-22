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
    <title>修改</title>
</head>
<body>
您的工号为：${requestScope.employee.id}<br/>
您的姓名为：${requestScope.employee.name}<br/>

<form action="/yjc/changePassword" method="post">
    <input type="hidden" name="_method" value="PUT"/>
    <input type="hidden" value="${requestScope.employee.id}" name="userid"/>
    请输入旧密码<input type="text" name="oldpassword"/>  &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.msg2}
    请输入新密码<input type="text" name="newpassword"/>
    <input type="submit" value="提交修改">
</form>
</body>
</html>
