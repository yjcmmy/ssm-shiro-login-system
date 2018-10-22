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
<form:form modelAttribute="employee" action="${pageContext.request.contextPath}/yjc/update" method="post">
    <form:hidden path="id"></form:hidden>
    <input type="hidden" name="_method" value="PUT"/>
    姓名:<form:input path="name"></form:input>   <br/>
    邮箱:<form:input path="email"></form:input>   <br/>
    入职时间:<form:input path="birth"></form:input>   <br/>
    领导工号:<form:input path="leaderId" ></form:input><br/>
    所属部门:<form:select path="department" items="${departmentList}"></form:select><br/>
    工资:<form:input path="salary"></form:input><br/>
    <input type="submit" value="修改" />
</form:form>

</body>
</html>
