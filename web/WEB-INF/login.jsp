<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<%
 String msg=(String) request.getAttribute("msg");
%>
<% if (msg!=null){ %>
<p style="color: red"><%=msg%></p>
<% } %>
<form action="/login" method="post">
<input type="email" name="email" placeholder="Please input email"><br>
<input type="password" name="password" placeholder="Please input password">
<input type="submit" value="login">
</form>
</body>
</html>
