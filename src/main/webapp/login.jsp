<%--
  Created by IntelliJ IDEA.
  User: yjc
  Date: 2018/10/19
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form name="login" action="${pageContext.request.contextPath}/yjc/login" method="post">
    姓名：<input type="text" name="username"/>  <br/>
    密码：<input type="password" name="password"/><br/>
    <input type="submit" value="登录">
</form>

<br/><br/>

初始密码都为000

<div>${sessionScope.msg}</div>
</body>
</html>
