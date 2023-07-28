<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet" />
<script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
<script type="module"
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
<script nomodule
	src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
<script src="${path }/js/jquery-3.7.0.min.js"></script>
<link rel="stylesheet" href="${path }/css/default.css" />
<link rel="stylesheet" href="${path }/css/login/login.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat:500,800"/>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<!DOCTYPE html>
<html>
<head>
<title>여행은 살아보는거야</title>
</head>
<body>
	<header>
		<div class="offcanvas offcanvas-start" id="demo">
			<div class="offcanvas-header">
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
			</div>
			<div id="userProfile">
				<img src="${path }/images/common/user.jpg" alt="" />
				<p>UserId</p>
				<button>MyPage</button>
				<b> | </b>
				<button>Logout</button>
			</div>
			<div class="offcanvas-body">
				<p>예약내역</p>
			</div>
		</div>

		<nav class="navbar navbar-expand-sm navbar-dark fixed-top drop">
			<img id="headerLogo" src="${path }/images/common/logo.png" alt="" />
			<div class="container-fluid">
				<ion-icon name="menu" class="btn btn-primary" type="button"
					data-bs-toggle="offcanvas" data-bs-target="#demo"></ion-icon>

				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link active" href="#">Home</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="javascript:void(0)" id="btn-modal">Login</a></li>
					<li class="nav-item"><a class="nav-link" href="">MyPage</a></li>
				</ul>
			</div>

			<div id="mainHeader">
				<ul class="main-menu">
					<li class="item">
						<div class="item__name">TRAVEL&FOOD</div>
					</li>
					<li class="item">
						<div class="item__name">ACCOMMODATION</div>
					</li>
					<li class="item">
						<div class="item__name" onclick="location.href='${path }/notice/noticeList.do'">NOTICE</div>
					</li>
					<li class="item">
						<div class="item__name" onclick="location.href='${path }/community/communityList.do'">COMMUNITY</div>
					</li>
				</ul>
			</div>
		</nav>
	</header>
		<div id="modal" class="modal-overlay">
	        <div class="modal-window">
	          <div class="close-area">X</div>
	            <div class="content">
	                <div class="container">
	                    <!-- Heading -->
	                    <h1>DEVELOPERS</h1>
	                    <div class="separator">
	                        <p>소셜 로그인</p>
	                    </div>
	                    	<div class="m-btn-container">
			                    <!-- 카카오 button -->
			                    <img class="socialbtn" src="${path }/images/login/kakaobtn.png" alt="어딧니?" onclick="kakaologin();">
			                    <!-- 네이버 button -->
			                    <img class="socialbtn" src="${path }/images/login/naverbtn.png" alt="어딧니?" onclick="naverlogin();">
			                    <!-- google button -->
			                    <img class="socialbtn" src="${path }/images/login/googlebtn.png" alt="어딧니?" onclick="googlelogin();">
			                    <button onclick="kakaologout();">카카오 연결끊기</button>
	                    	</div>
	                  </div>
	            </div>
	        </div>
	    </div>
<script src="${path }/js/login/login.js"></script>