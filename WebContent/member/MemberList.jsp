<%@ page import="lesson02.vo.Member" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
<h1>회원목록</h1>
<p><a href="add">신규추가</a></p>
<%
ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
%>
<% for (Member member : members) { %>
<%=member.getMno() %>, 
<a href="update?mno=<%=member.getMno() %>"><%=member.getMname() %></a>, 
<%=member.getEmail() %>, 
<%=member.getCreDate() %>, 
<%=member.getModDate() %>
<a href="delete?mno=<%=member.getMno() %>">[삭제]</a><br>
<% } %>
</body>
</html>