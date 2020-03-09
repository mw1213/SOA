<%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 05.03.2020
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="doradcaPiwny.Piwo" %>
<%@page import="java.util.List"%>
<html>
<head>
    <title>Doradzam piwa</title>
</head>
<body>
<% List<Piwo> piwa = (List<Piwo>)request.getAttribute("piwa"); %>
<% if (piwa.isEmpty()){%>
    Nie znam takich piw :( <br>
<%} else {%>
<table border="1" style="width: 50%" height="50%">
    <thead>
    <tr>
        <th>Nazwa</th>
    </tr>
    </thead>
    <tbody>
    <% for(Piwo piwo : piwa){ %>
    <tr>
        <td><%=piwo.getNazwa()%></td>
    </tr>
    <%} %>
    </tbody>

</table>
<%}%>
</body>
</html>
