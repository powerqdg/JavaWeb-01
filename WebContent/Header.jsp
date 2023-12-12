<%@ page import="lesson02.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% Member member = (Member)session.getAttribute("loginInfo"); %>
<div style="background-color: orange;color:#ffffff;padding:10px">
[HEADER] 페이지입니다.
<span style="float:right;">
<% if (member == null) { %>
<a href="../auth/login">로그인</a>
<%} else {%>
<%=member.getMname() %>
<a href="../auth/logout">로그아웃</a>
<% } %>
</span>
</div>