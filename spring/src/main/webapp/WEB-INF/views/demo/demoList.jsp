<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo조회"/>
</jsp:include>
<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
			<th scope="col">수정</th>
		</tr>
		<c:if test="${!demo.isEmpty() }">
			<c:forEach var="d" items="${demo}">
			<tr>
				<td>${d.devNo }</td>
				<td>${d.devName }</td>
				<td>${d.devAge }</td>
				<td>${d.devEmail }</td>
				<td>${d.devGender }</td>
				<td>
				<c:forEach var="dl" items="${d.devLang }">
					${dl} 
				</c:forEach>
				</td>
				<td><button>수정하기</button></td>
			</tr>
			</c:forEach>
		</c:if>
	</table>	
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>