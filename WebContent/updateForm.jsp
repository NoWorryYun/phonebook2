<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@	page import = "java.util.List"%>
<%@	page import = "com.javaex.vo.PersonVo"%>

<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>전화번호부</h1>
	<h2>수정폼</h2>
	<p>전화번호를 수정하려면<br>
		아래 항목을 기입하고 수정 버튼을 눌러주세요.</p>
	<form action="./pbc" method="get">
	이름(name) <input type="text" name="name" value=""><br>
	핸드폰(hp) <input type="text" name="hp" value=""><br>
	회사(company) <input type="text" name="company" value=""><br><br>
	코드(id) <input type="text" name="id" value="<%=Integer.parseInt(request.getParameter("personId"))%>"><br><br>
	<input type="hidden" name="action" value="update">
	<button type="submit">수정</button>
	</form>
</body>
</html>