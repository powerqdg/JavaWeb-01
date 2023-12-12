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
<jsp:include page="/Header.jsp"/>
<h1>회원목록</h1>
<p><a href="add.do">신규추가</a></p>
<%
ArrayList<Member> members = (ArrayList<Member>)request.getAttribute("members");
%>
<% for (Member member : members) { %>
<%=member.getMno() %>, 
<a href="update.do?mno=<%=member.getMno() %>"><%=member.getMname() %></a>, 
<%=member.getEmail() %>, 
<%=member.getCreDate() %>, 
<%=member.getModDate() %>
<a href="delete.do?mno=<%=member.getMno() %>">[삭제]</a><br>
<% } %>
<jsp:include page="/Tail.jsp"/>
</body>
</html>