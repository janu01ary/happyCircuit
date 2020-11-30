<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- onclick에 주소 추가해야 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>
<style>
	* {
		text-align: center;
	}
	.align-center {
		margin: 0px auto;
	}
	.profile-image {
		margin-top: 50px;
		width: 200px;
		height: 200px;
		border-radius: 100px;
		background-color: #C3C3C3;
	}
	.profile-img {
		width: 100%;
		height: auto;
		border-radius: 100px;
	}
	.profile-nickname {
		padding: 10px;
		margin-top: 20px;
	}
	.profile-introduction {
		padding: 10px;
		width: 60%;
		min-width: 500px;
		margin-top: 20px;
		background-color: #EEEEEE;
	}
	.like-list {
		padding: 10px;
		width: 80%;
	}
	.DM {
		padding-right: 10px;
		margin: auto 30px;
	}
	.music-container {
		display: flex;
		justify-content: space-around;
		margin: 20px 0px;
		min-width: 800px;
	}
	.music {
		flex-grow: 1;
		margin: 12px;
	}
	.music-img {
		max-width: 100%;
		height: auto;
	}
	.music-title {
		
	}
	.music-artist {
		font-size: 0.8em;
		color: #BBBBBB !important;
	}
	.update, .delete {
    	margin-top: 20px;
    	margin-bottom: 50px; 
	}
	.btn-update, .btn-delete, .DM, .btn-DM {
      	background-color: #BBBBBB;
      	color: white;
      	padding: 10px;
      	border-radius: 15px;
      	border: 0px;
      	width: 50px;
      	border: 1px solid #BBBBBB;
    	text-decoration: none;
    	font-size: 0.9em;
    }
    .btn-update:hover, .btn-delete:hover, .DM:hover, .btn-DM:hover {
        text-decoration: none;
        background-color: white;
        color: #BBBBBB;
        transition-property: background-color, color;
        transition-duration: 0.2s;
        transition-timing-function: ease-in-out;
    }
	.hover-cursor {
		cursor: pointer;
	}
	@media (orientation: portrait) {
      	
    }
</style>
<script>
function checkRemove(targetUri) {
	if (confirm("탈퇴하시겠습니까??") == true){    //확인
	     document.removefrm.submit();
	     location.href=targetUri;
	 }else{   //취소
	     return false;
	 }
}
</script>
</head>
<body>
	<c:if test="${isSameArtist}">
		<div style="margin: 30px;">
			<a href="<c:url value='/DM/list' />" class="DM">DM</a> <!-- 내 DM 목록 -->
		</div>
	</c:if>
	
	<div class="align-center profile-image">
		<img src="${pageContext.request.contextPath}/sample/${artist.image}" class="profile-img">
	</div>

	<div class="aling-cneter profile-nickname">
		<span>${artist.nickname}</span>
	</div>
	
	<div class="align-center profile-introduction">
		<span>${artist.profile}</span>
	</div>
	<div class="update">
		<c:if test="${isSameArtist}">
			<a href="<c:url value='/mypage/update'>
				 	 	<c:param name='artistId' value='${artist.artistId}'/>
				 	 </c:url>" class="btn-update">수정</a>
		</c:if>
		<c:if test="${!isSameArtist}">
			<a href="<c:url value='/DM/create'>
						<c:param name='artistId' value='${artist.artistId}'/>
					</c:url>" class="btn-DM">DM보내기</a>
		</c:if>
        <c:if test="${updateFailed || deleteFailed}">
	      <font color="red"><c:out value="${exception.getMessage()}" /></font>
	    </c:if>   
	     
		<c:if test="${isSameArtist}">
			<a href="<c:url value='/mypage/recommendMusic'>
				 	 	<c:param name='artistId' value='${artist.artistId}'/>
				 	 </c:url>" class="btn-update">음악 추천</a>
		</c:if>
	</div>
	
	<div class="align-center like-list">
		<c:forEach var="music" items="${musicList}" varStatus="status">
			<c:if test="${status.index % 5 == 0}">
				<div class="music-container">
			</c:if>
			<div class="music">
				<img src="../sample/holding_onto_gravity.jpg" class="music-img hover-effect" onclick="location.href=''">
				<div class="music-title">
					<span onclick="location.href=''" class="hover-cursor">${music.musicName}</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href=''" class="hover-cursor">${music.artistId}</span>
				</div>
			</div>
			<c:if test="${status.index + 1 % 5 == 0}">
				</div>
			</c:if>
		</c:forEach>  
		<!-- 
		<% 
		for (int i = 0; i < 15; i++) {
			if (i % 5 == 0) {
		%>
				<div class="music-container">
			<%
			}
			%>
			<div class="music">
				<img src="../sample/holding_onto_gravity.jpg" class="music-img hover-effect" onclick="location.href=''">
				<div class="music-title">
					<span onclick="location.href=''" class="hover-cursor">title</span>
				</div>
				<div class="music-artist">
					<span onclick="location.href=''" class="hover-cursor">artist</span>
				</div>
			</div>
		<%
			if ((i + 1) % 5 == 0) {
		%>
				</div>
		<%
			}
		}
		%>-->
	</div>
	<c:if test="${isSameArtist}">
		<div class="delete">
			<a href="<c:url value='/artist/delete' />" class="btn-delete hover-cursor" onClick="checkRemove('<c:url value='/artist/delete' />')">탈퇴</a>
		</div>
	</c:if>
</body>
</html>