<%@ page import="java.util.List" %>
<%@ page import="java.util.Vector" %><%--
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
    <input type="email" name="email"/><br>
    Please enter your message
    <input type="text" name="message"/>
    <input type="submit" value="submit">
</form>
<% try {%>
<% Vector<List<String>> posty = (Vector<List<String>>)session.getAttribute("posty"); %>

    <% for(List post : posty){ %>
        <%=post.get(0).toString()%>,
        <b><%=post.get(1).toString()%></b> says <br>
        <%=post.get(2).toString()%><br>

    <%} %>
<%} catch (Exception e){ }%>

</body>
</html>
