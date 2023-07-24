<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>전체회원 조회</h3>
	<c:if test="${not empty member }">
		<table>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>나이</td>
				<td>성별</td>
				<td>이메일</td>
			</tr>
			<c:forEach var="m" items="${member }">
				<tr>
					<td>${m.userId }</td>
					<td>${m.userName}</td>
					<td>${m.age}</td>
					<td>${m.gender}</td>
					<td>${m.email}</td>				
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>