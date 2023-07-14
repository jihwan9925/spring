<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <c:set var="path" value="${pageContext.request.contextPath}"/> 
<jsp:include page="/WEB-INF/views/common/header.jsp">
   <jsp:param name="title" value="메모 리스트"/>
</jsp:include>
<section>

<style>
    div#memo-container{width:60%; margin:0 auto;}
    </style>
    <div id="memo-container">
        <form action="${path }/memo/memoInsert.do" class="form-inline" method="post">
            <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
            <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
            <button class="btn btn-outline-success" type="submit" >저장</button>
        </form>
    </div>
    
    <br />
       <!-- 메모목록 -->
       <table class="table">
           <tr>
               <th scope="col">번호</th>
               <th scope="col">메모</th>
               <th scope="col">날짜</th>
               <th scope="col">삭제</th>
           </tr>
           <c:if test="${empty memo }">
           		<tr><td colspan="4">저장한 메모가 없습니다.</td></tr>
           </c:if>
           <c:if test="${not empty memo }">
           		<c:forEach var="m" items="${memo }">
           			<tr>
           				<td>${m.memoNo}</td>
           				<td>${m.memo}</td>
           				<td>${m.memoDate}</td>
           				<td>
           					<button class="btn btn-outline-danger" 
           					onclick="location.replace('${path}/memo/memoDelete.do?no='+${m.memoNo})">
           					삭제하기</button>
           				</td>
           			</tr>
           		</c:forEach>
           </c:if>
           
내용출력
       </table>
   
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>