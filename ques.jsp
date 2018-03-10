<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="servlet.Question" %>
<%--
  Created by IntelliJ IDEA.
  User: Erik
  Date: 2017/12/12
  Time: 21:54
  To change this template use File | Settings | File Templates.
--%>
<%--
  这里多选题的方面好像还需要用到js，先摸了，一会儿写（
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%=request.getAttribute("title")%></title>
</head>
<body>
    <form action="submit" method="get" id="answer">
    <%List list = (list<Question>)request.getAttribute("list");%>
    <%for(int i=0;i<list.size();i++){%>
        <%Question ques = list.get(i);%>
        <%%>
    <div class="questions">
        <p><%=ques.number%>.<%=ques.problem%></p>
        <%if(ques.fillin){%>
            <input type="text" name="answer"/>
        <%}else{%>
               <%for(int k=0;k<ques.optionamount;k++){%>
               <input type="radio" name="answer" value=<%="option"+k%>/><%=ques.options[k]%>
               <%}%>
        <%}%>
    </div>
    <%}%>
    <input type="submit" value="提交" />
    </form>
</body>
</html>
