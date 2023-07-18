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
        <button class="btn btn-outline-primary"
        onclick="location.assign('${path}/board/boardForm.do')">글쓰기</button>
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
            	<c:forEach var="b" items="${board }" varStatus="vs">
            		<tr>
		                <td>${b.boardNo }</td>
		                <td><a href="${path }/board/boardView.do?no=${b.boardNo }">${b.boardTitle }</a></td>
		                <td>${b.boardWriter.userId }</td>
		                <td>${b.boardDate }</td>
		                <td>
		                	<c:if test="${not empty b.file }">
            					<img src="${path }/resources/images/file.png" alt="첨부파일사진">
            					<span>x${b.file.size()}</span>
            				</c:if>
		                </td>
		                <td>${b.boardReadCount }</td>
		            </tr>
            	</c:forEach>
            </c:if>
        </table>
        <div class="justify-content">${pageBar}</div>
</section>
        

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>