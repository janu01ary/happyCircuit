<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>협업 신청 내역</title>
<!-- 
	<link rel="stylesheet" href="../resources/css/bootstrap.css">
    <link rel="stylesheet" href="../resources/css/listPost.css">
 -->
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css' />">
    <link rel="stylesheet" href="<c:url value='/resources/css/listPost.css' />">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="../resources/js/bootstrap.js"></script>
    <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/article/boardStyles.css">
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>

	<div id='menu'>
		<ul>
			<li><a href='<c:url value='/home' />'>Home</a></li>
			<li><a href="<c:url value='/article/articleMain' />">Article</a></li>
			<li class='active'><a href="<c:url value='/post/list' />">Find Artist</a></li>
			<li><a href="<c:url value='/mypage'>
				<c:param name='artistId' value='${artistId}'/>
				</c:url>">My Page</a></li>
		</ul>
	</div>
	<br> <br>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive project-list">
                        <table class="table project-table table-centered table-nowrap">
                            <thead>
                            <tr>
								<td colspan="3">
									<b><font size="6">협업 신청 내역</font></b>
								</td>
							</tr>
                                <tr>
                                    <th scope="col">번호</th>
                                    <th scope="col">제목</th>
                                    <th scope="col">작성자 ID</th>
                                    <th scope="col">작성일</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach var="collaboration" items="${collaborationList}">
                            		<tr>
                            			<th scope="row">${collaboration.collaborationId}</th>
                                    	
                                    	<td>
	                                    	<a href="<c:url value='/collaboration/view'>
											   <c:param name='collaborationId' value='${collaboration.collaborationId}'/>
									 		 	</c:url>">
								  			${collaboration.collaborationTitle}</a>
							  			</td>
                                    	<td>${collaboration.collaborationArtistId}</td>	
										<td>${collaboration.collaborationDate}</td>
									</tr>
                            	</c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- end project-list -->
            
                     <div class="pt-3">
                        <ul class="pagination">
				    		<li>
						      <a href="#" aria-label="Previous">
						        <span aria-hidden="true">&laquo;</span>
						      </a>
						    </li>
						    <li><a href="#">1</a></li>
						    <li><a href="#">2</a></li>
						    <li><a href="#">3</a></li>
						    <li><a href="#">4</a></li>
						    <li><a href="#">5</a></li>
						    <li>
						      <a href="#" aria-label="Next">
						        <span aria-hidden="true">&raquo;</span>
						      </a>
							</li>
					  </ul>
                    </div>
                    <!-- end project-list -->
					
					<div class="write-find-artist">
				        
				        <form name="listPost" method="POST">
							<a href="<c:url value='/post/list' />">
								<input type="button" name="goToPostList" value="구인글 목록으로 돌아가기"  class="btn btn-light">			        	
					        </a>
						</form>
				        
					</div>
					<!-- end write button -->

					<div class="col-sm-3">
						<c:if test="${search eq false}">
	        				<form class="searchCollaboration" method="POST" action="<c:url value='/collaboration/search' />" role="search">
	       						 <div class="input-group">
	            					<input type="text" name="collaborationTitle" class="form-control" placeholder="제목 검색" >
					            	<div class="input-group-btn">
					                	<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
					                	 <br>
					            	</div>
					        	</div>
					        </form>
				        </c:if>
				    </div>
				    <!-- end search -->

            	</div>
            </div>
        </div>
    </div>
    <!-- end row -->
</div>
</body>
</html>

