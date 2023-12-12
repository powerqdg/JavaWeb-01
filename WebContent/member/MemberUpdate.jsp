<%@ page import="lesson02.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원수정</title>
</head>
<body>
<jsp:include page="/Header.jsp"/>
<h1>회원수정</h1>
<% Member memberDetail = (Member)request.getAttribute("memberDetail"); %>
<form action="update.do" method="post">
번호: <input type="text" name="mno" value="<%=memberDetail.getMno() %>" readonly><br>
이름: <input type="text" name="mname" value="<%=memberDetail.getMname() %>"><br>
이메일: <input type="text" name="email"value="<%=memberDetail.getEmail() %>"><br>
가입일: <span><%=memberDetail.getCreDate() %></span><br>
수정일: <span><%=memberDetail.getModDate() %></span><br>
<input type="submit" value="수정"><input type="reset" value="취소">
</form>
<jsp:include page="/Tail.jsp"/>
</body>
</html>