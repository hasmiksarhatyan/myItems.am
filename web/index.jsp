<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.Item" %>
<%@ page import="manager.ItemManager" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12.09.2022
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>myItems.am</title>
</head>

<%
    User user = (User) session.getAttribute("user");
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    List<Item> itemsByCategory = (List<Item>) request.getAttribute("itembycatId");
    List<Item> last20items = (List<Item>) request.getAttribute("item");
    List<Item> myitems = (List<Item>) request.getAttribute("myitems");

%>
<body>
<div style="width: 1024px">
    <div style="width: 100%;height: 5%; margin: 0 auto">
        <div style="padding-left: 80%">
            <%
                if (user != null) {
            %>
        </div>
        <div style="padding-left: 70%">
            <h2>Բարև<%=user.getName()%>
            </h2>
            <a href="/items/add">Ավելացնել</a>
            <a href="/myitems?userId=<%=user.getId()%>">Իմ հայտարարությունները</a>
            <a href="/logout">Ելք</a>
            <%
            } else {
            %>
            <a href="/login">Լոգին</a>
            <a href="/users/add">Գրանցում</a>
        </div>

        <%
            }
        %>
    </div>
    <div style="width: 100%; margin: 0 auto">
        <div style="padding-left: 20%; height: 50px">
            <a href="/">Գլխավոր</a>
            <%
                for (Category category : categories) {
            %>
            <a href="/category?categoryId=<%=category.getId()%>"><%=category.getName()%>
            </a>

            <%
                }
            %>
        </div>
        <table style="padding-left: 10%">
            <%
                if (last20items != null) {
                    for (Item last20item : last20items) {
            %>
            <th style="width: 100px;height:100px;border:black solid">
                <% if (last20item.getPicURL() == null || last20item.getPicURL().length() == 0) { %>
                <img src="/image/item.png" width="50"/>
                <% } else { %>
                <img src="/getImage?image=<%=last20item.getPicURL()%>" width="50" />
                <% } %>
                <br><%=last20item.getTitle()%><br><%=last20item.getPrice()%>
            </th>

            <%
                    }
                }
                if (itemsByCategory != null) {%>

            <% for (Item item : itemsByCategory) { %>
            <th style="width: 100px;height:100px;border:black solid">
                <%=item.getPicURL()%><br><%=item.getTitle()%><br><%=item.getPrice()%>
            </th>

            <%
                    }
                }
                if (myitems != null) {
            %>
            <%
                for (Item myitem : myitems) {%>
            <th style="width: 100px;height:100px;border:black solid">
                <%=myitem.getPicURL()%><br><%=myitem.getTitle()%><br><%=myitem.getPrice()%>
                <br> <a href="/items/remove?itemId=<%=myitem.getId()%>">Remove</a>
            </th>
            <%
                    }
                }
            %>

        </table
    </div>
</body>
</html>
