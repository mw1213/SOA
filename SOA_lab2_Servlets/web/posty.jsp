<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 07.03.2020
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posty</title>
</head>
<body>
<form action="/procesposts" method="post">
    Please enter your username
    <input type="text" name="name"/><br>
    Please enter your email
    <input type="text" name="email"/><br>
    Please enter your message
    <input type="text" name="message"/>
    <input type="submit" value="submit">
</form>
<% try {%>
<% List<List<String>> posty = (List<List<String>>)session.getAttribute("posty"); %>
<table border="1" style="width: 50%" height="50%">
    <tbody>
    <% for(List post : posty){ %>
    <tr>
        <td><%=post.get(0).toString()%></td>
        <td><%=post.get(1).toString()%></td>
        <td><%=post.get(2).toString()%></td>
    </tr>
    <%} %>
    </tbody>
<%} catch (Exception e){ }%>
</table>
</body>
</html>
