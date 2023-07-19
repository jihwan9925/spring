<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메인화면"/>
	
</jsp:include>

<section id="content">
	<h2>Hello Spring</h2>
	<h3>ajax통신하기</h3>
	<h4><button class="btn btn-outline-primary" onclick="basicAjax();">기본ajax처리</button></h4>
	<h4><button class="btn btn-outline-success" onclick="convertAjax();">json받기</button></h4>
	<h4><button class="btn btn-outline-warning" onclick="basic2();">jsp받아오기</button></h4>
	<h4><button class="btn btn-outline-danger" onclick="selectMemberAll();">전체회원 가져오기</button></h4>
	<h4><button class="btn btn-outline-danger" onclick="insertData();">데이터 가져오기</button></h4>
	<div id="ajaxContainer"></div>
	
	<script>
		const basicAjax=()=>{
			$.get('${pageContext.request.contextPath}/ajax/basicTest.do',(data)=>{
				console.log(data);
				$("#ajaxContainer").html("<h2>"+data+"</h2>");
			});
		}
		const convertAjax=()=>{
			$.get('${pageContext.request.contextPath}/ajax/converter',data=>{
				console.log(data);
			});
		}
		const basic2=()=>{
			$.get('${pageContext.request.contextPath}/ajax/basic2',data=>{
				console.log(data);
			});
		}
		const selectMemberAll=()=>{
			$.get('${pageContext.request.contextPath}/ajax/selectMemberAll',data=>{
				console.log(data);
				const table = $("<table>");
				const key = ["아이디","이름","나이","성별","이메일","전화번호","주소","취미","가입일"];
				const tr = $("<tr>");
				key.forEach(e=>{
					const th = $("<th>").text(e);
					tr.append(th);
				});
				table.append(tr);
				
				data.forEach(e=>{
					const bodyTr = $("<tr>");
					const userId = $("<td>").text(e.userId);
					const name = $("<td>").text(e.userName);
					const age = $("<td>").text(e.age);
					const gender = $("<td>").text(e.gender);
					const email = $("<td>").text(e.email);
					const phone = $("<td>").text(e.phone);
					const address = $("<td>").text(e.address);
					const hobby = $("<td>").html(e.hobby.toString());
					const enrollDate = $("<td>").text(new Date(e.enrollDate));
					bodyTr.append(userId).append(name).append(age).append(gender)
					.append(email).append(phone).append(address).append(hobby).append(enrollDate);
					table.append(bodyTr);
				});
				
				$("#ajaxContainer").html(table);
			});
		}
		const insertData=()=>{
			const data = {userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"};
			/* $.post("${pageContext.request.contextPath}/ajax/insertData.do}",
					{userId:"test1",password:"test",userName:"테스트",age:19,gender:"M"},
					data=>{
						console.log(data);
					}); */
			$.ajax({
				url:"${pageContext.request.contextPath}/ajax/insertData.do",
				type:"post",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				success:data=>{
					console.log(data);
				}
			});
					
			//fetch함수를 제공함
			
		}
	</script>


</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>