<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: shiko
  Date: 2017/12/15
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>问卷情况</title>
</head>
<body>
<h1>
    <%
        String name = request.getParameter(title);

    %>
</h1>
<%List list = (list<Question>) request.getAttribute("list");%>
<div class="questions">
    <script>
        for (var i = 0; i < (list.size - 1); i++) {
            document.write(list[i]);
        }

    </script>
</div>
</body>
</html>
