<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>insert</title>
</head>
<body>

<form:form modelAttribute="employee" action="${pageContext.request.contextPath}/yjc/insert" method="post">
    <form:errors path="*"></form:errors>  <br/>
    工号:<form:input path="id"></form:input> <form:errors path="id"/>  <br/>
    姓名:<form:input path="name"></form:input><form:errors path="name"/>    <br/>
    邮箱:<form:input path="email"></form:input> <form:errors path="email"/>   <br/>
    入职时间:<form:input path="birth"></form:input><form:errors path="birth"/>   <br/>
    领导工号:<form:input path="leaderId" ></form:input><form:errors path="leaderId"/> <br/>
    所属部门:<form:select path="department" items="${departmentList}"></form:select><br/>
    工资:<form:input path="salary"></form:input><form:errors path="salary"/><br/>
    <input type="submit" value="增加"/>
</form:form>


</body>
</html>
