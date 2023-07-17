<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <c:set var="path" value="${pageContext.request.contextPath}"/> 
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param name="title" value="게시판 리스트"/>
</jsp:include>
<section>
   <section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty board }">
            	<c:forEach var="b" items="${board }">
            		<tr>
		                <td>${b.boardNo }</td>
		                <td><a href="${path }/board/boardView.do?no=${b.boardNo }">${b.boardTitle }</a></td>
		                <td>${b.boardWriter }</td>
		                <td>${b.boardDate }</td>
		                <td>x</td>
		                <td>${b.boardReadCount }</td>
		            </tr>
            	</c:forEach>
            </c:if>
        </table>
        <div class="justify-content">${pageBar}</div>
        <!-- <c:out value="${pageBar}" escacpeXml="false"/>escacpeXml="false"사용하면 코드로 잘나온다. -->
</section>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>