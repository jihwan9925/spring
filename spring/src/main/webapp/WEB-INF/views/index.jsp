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
			/* $.ajax({
				url:"${pageContext.request.contextPath}/ajax/insertData.do",
				type:"post",
				data:JSON.stringify(data),
				contentType:"application/json;charset=utf-8",
				success:data=>{
					console.log(data);
				}
			}); */
					
			//fetch함수를 제공함, 다른 라이브러리가 필요없다.
			//사용법 : fetch("URL주소", {요청에 대한 옵션})
			//.then(response=>response.json()) [중간과정이 생김으로서 응답내용의 파싱(=에러처리)을 할수있다.]
			//.then(data=>{처리로직}) == success함수
			/* fetch("${pageContext.request.contextPath}/ajax/selectMemberAll.do",{
				method:"get",
				//headers:{} 헤더에 세부적인 옵션값을 줄 수 있다.
				//body:JSON.stringify(객체)
			})
			// fetch에서 리턴되는 값이 response가 된다.
			.then(response=>{
				console.log(response);
				if(!response.ok) throw new Error("요청실패!");
				return response.json()
			})
			// return response.json()가 data가 된다.
			.then(data=>{
				console.log(data)
			}).catch(e=>{
				//위에서 에러발생한 것에 대한처리를 이곳에서 처리할 수 있다.
				alert(e);
			}); */
			console.log(JSON.stringify(data));
			fetch("${pageContext.request.contextPath}/ajax/insertData.do",{
				method:"post",
				headers:{
					"Content-type":"application/json" //보내는방식이 json이라는 의미(=파일의 확장자와 유사한 개념)
				},body:JSON.stringify(data) //자바스크립트의 값을 JSON 문자열로 변환하는 함수
			}).then(response=>{
				if(!response.ok)new Error("입력실패");
				return response.json()	//서버가 json으로 응답하는 로직
				//일반문자를 받아올 땐 response.text()
			})
			.then(data=>{
				console.log(data);
				}).catch(e=>{
					alert(e);
				});
			
			
		}
		
		
	</script>
	<h1>JPA테스트</h1>
	<h3><a href="${pageContext.request.contextPath }/jpa/basicTest.do">
	기본 EntityManager이용하기</a></h3>


</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>