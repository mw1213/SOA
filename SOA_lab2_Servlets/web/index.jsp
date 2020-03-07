<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: maciej
  Date: 02.03.2020
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Api webowe</title>
</head>
<body>
<h1>To jest 1 strona web w JavaEE</h1>
<p>Statyczna</p>
<%
  Date tmp = new Date();
  out.println("<h2> " +tmp.toString()+ "</h2>");
%>
</body>
</html>
