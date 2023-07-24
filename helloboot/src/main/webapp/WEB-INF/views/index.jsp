<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boot메인화면</title>
</head>
<body>
	<h2>나의 첫 boot화면</h2>
	<h3><a href="${pageContext.request.contextPath}/member/memberAll">전체회원 조회</a></h3>
	<form action="${pageContext.request.contextPath}/fileUpload" method="post" enctype="multipart/form-data">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="file" name="upFile">
		<input type="submit" value="파일저장">
	</form>
	<form action="${pageContext.request.contextPath}/datatest" method="post">
		<input type="text" name="data">
		<input type="submit" value="전송">
	</form>
	<!-- 아이디로 조회 -->
	<form action="${pageContext.request.contextPath}/memberId" method="post">
		<input type="text" name="userId">
		<input type="submit" value="전송">
	</form>
	<form action="${pageContext.request.contextPath}/membername">
		<input type="text" name="userName" placeholder="이름으로 조회">
		<input type="submit" value="전송">
	</form>
	<table>
		<c:if test="${not empty param }">
			<c:forEach var="p" items="${param }">
				<tr>
					<td>${p.userName }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	
	<button onclick="openchatting();">채팅하기</button>
	
	<script>
		function openchatting(){
			open("/chattingpage","_blank","width=400, height=500");
		}
	</script>
</body>
</html>