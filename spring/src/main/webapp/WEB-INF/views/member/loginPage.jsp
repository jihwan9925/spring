<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <c:set var="path" value="${pageContext.request.contextPath}"/> 
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param name="title" value=""/>
</jsp:include>
<section>
	<form action="${path }/loginEnd" method="post">
		<input type="text" name="userId">
		<input type="password" name="password">
		<input type="submit" value="로그인">
	</form>
   
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>