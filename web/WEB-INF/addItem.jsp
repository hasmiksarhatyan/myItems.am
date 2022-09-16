<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User:
   User
  Date: 02.09.2022
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add item</title>
</head>
<body>
<%
    List<Category> categories = (List<Category>) request.getAttribute("categories");
%>

Please input item's data
<form action="/items/add" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="please input title"> <br>
    <input type="number" name="price" placeholder="please input price"> <br>
    <input type="file" name="image"> <br>
    Category
    <select name="categoryId">
        <% for (Category category : categories) { %>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% } %>
    </select> <br>
    <input type="submit" value="Register">

</form>
</body>
</html>
