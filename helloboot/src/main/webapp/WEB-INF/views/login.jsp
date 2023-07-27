<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <c:set var="path" value="${pageContext.request.contextPath}"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본 로그인화면</title>
</head>

<body>
	<h2>로그인</h2>
	<form action="${path }/login.do" method="post">
		<input type="text" name="userId">
		<input type="password" name="pw">
		<input type="submit" value="로그인">
	</form>


</body>
</html>