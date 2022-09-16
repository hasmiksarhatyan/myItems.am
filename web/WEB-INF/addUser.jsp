
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add user</title>
</head>
<body>
<%
    String msg=(String) request.getAttribute("msg");
%>
<% if (msg!=null){ %>
<p style="color: red"><%=msg%></p>
<% } %>
Please input user's data
<form action="/users/add" method="post" >
    <input type="text" name="name" placeholder="please input name"> <br>
    <input type="text" name="surname" placeholder="please input surname"> <br>
    <input type="email" name="email" placeholder="please input email"> <br>
    <input type="password" name="password" placeholder="please input password"> <br>
    <input type="submit" value="Register">
</form>
</body>
</html>
