<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/nav" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Raptoz List Page</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/selector/select2.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/raptoz-list.css"/>"/>
</head>
<body>
	<nav:gnb/>
	<nav:snb/>
	
	<div class="container" id="resultContainer" style="visibility: hidden;">
		<div class="row">
			<section id="users" class="span4">
				<c:forEach var="user" items="${search.users}">
					<article class="row userinfo" data-user-id="${user.id}">
						<div class="userface">
							<a href="mypage/${user.id}">
								<c:choose>
									<c:when test="${not empty user.encodeProfileImage}">
										<img alt="${user.nickname}`s profile image" src="data:image/gif;base64,${user.encodeProfileImage}"/>
									</c:when>
									<c:otherwise>
										<img alt="${user.nickname}`s profile image" src="<c:url value="/img/66x66.gif"/>"/>
									</c:otherwise>
								</c:choose>
							</a>
							<p class="username"><small>${user.nickname}</small></p>
						</div>
						<div class="span4 usertag">
							<ul class="unstyled">
								<li>
									<b>Interests</b>
								</li>
								<li>
									<input class="taglist" type="text" name="tags" value="${user.tagPrint}"/>
								</li>
							</ul>
						</div>
						<footer>
							<c:if test="${sessionScope.loginUser ne null and user.id ne sessionScope.loginUser.id}">
								<button class="btn btn-small send-message">
									<i class="icon-comment"></i>
									메시지
								</button>
							</c:if>
						</footer>
					</article>
				</c:forEach>
				<section style="display: none;" id="sendMessageTemplate">
					<form action="<c:url value="/message/send/"/>" method="post">
						<textarea rows="" cols="" placeholder="메시지를 입력하세요." name="content"></textarea>
						<input class="btn btn-primary" type="submit" value="send"/>
					</form>
				</section>
			</section>
			<section id="posts" class="span6">
				<div class="row">
					<c:forEach var="post" items="${search.posts}">
						<article class="span3 post" data-post-id="${post.id}" data-toggle="modal" data-target="postModalTemplate">
							<header class="post-title">
								<b>${post.title}</b>
							</header>
							<p class="content">
								<str:truncateNicely lower="400" appendToEnd="...">${post.content}</str:truncateNicely>
							</p>
							<input class="taglist" type="text" value="${post.tagPrint}"/>
							<footer>
								<p class="post-status">
									<small class="post-view-count" style="margin-right: 7px;"><i class="icon-eye-open icon-gray"></i>&nbsp;<span class="number">${post.viewCount == null ? 0 : post.viewCount}</span></small>
									<small class="post-reply-count" style="margin-right: 7px;"><i class="icon-comment icon-gray"></i>&nbsp;<span class="number">${fn:length(post.replyIds)}</span></small>
									<small class="post-created-time"><i class="icon-time icon-gray"></i>&nbsp;<span class="string">1h ago</span></small>
								</p>
								<p class="post-writer">
									<a href="<c:url value="/mypage/${post.writer.id}"/>">
										<img alt="profile image" src="<c:url value="/img/66x66.gif"/>" alt="" width="30" height="30"/>
									</a>
									<a href="<c:url value="/mypage/${post.writer.id}"/>"><b>${post.writer.nickname}</b></a> attach to <a href="#"><b>toz</b></a>
								</p>
							</footer>
						</article>
					</c:forEach>
					<section class="modal hide modal-post" id="postModalTemplate">
					    <header class="modal-header">
						    <img class="profile-image" src="<c:url value="/img/50x50.gif"/>" alt="questioner image" />
						    <h3 class="post-title">title</h3>
						    <article class="post-content">
					    	</article>
					    	<input class="post-taglist" type="text" name="tags"/>
					    </header>
					    <article class="modal-body">
					    	<div class="modal-reply">
						    	<c:if test="${sessionScope.loginUser ne null}">
						    	<form action="<c:url value="/reply/add"/>" method="post">
						    		<article class="reply reply-input">
							    		<c:choose>
											<c:when test="${not empty sessionScope.loginUser.encodeProfileImage}">
												<img class="profile-image" alt="your profile image" src="data:image/gif;base64,${sessionScope.loginUser.encodeProfileImage}"/>
											</c:when>
											<c:otherwise>
												<img class="profile-image" alt="your profile image" src="<c:url value="/img/66x66.gif"/>"/>
											</c:otherwise>
										</c:choose>
							    		<textarea name="content" placeholder="Add your opinion..."></textarea>
							    	</article>
							    </form>
						    	</c:if>
					    	</div>
				    	</article>
					    <footer class="modal-footer">
					    	<button class="btn btn-primary post-modify" type="submit">Modify</button>
					    	<button class="btn btn-danger post-delete" type="submit">Delete</button>
							<button class="btn" data-dismiss="modal">Cancel</button>
					    </footer>
				    </section>
				</div>
				<article class="reply hide" id="postModalReplyTemplate">
					<c:if test="${sessionScope.loginUser ne null}">
						<button class="close reply-delete">x</button>
					</c:if>
					<c:choose>
						<c:when test="${not empty sessionScope.loginUser.encodeProfileImage}">
							<img class="profile-image" alt="${sessionScope.loginUser.nickname}`s profile image" src="data:image/gif;base64,${user.encodeProfileImage}"/>
						</c:when>
						<c:otherwise>
							<img class="profile-image" alt="anonymous profile image" src="<c:url value="/img/66x66.gif"/>"/>
						</c:otherwise>
					</c:choose>
		    		<p class="replyer-nickname"><b>nickname</b></p>
		    		<p class="reply-content">reply test</p>
		    	</article>
			</section>
		</div>
	</div>
	<!-- 
	<footer>
		Raptoz@2012
	</footer>
	 -->
    <script id="entry-template" type="text/x-handlebars-template">
		handlebars template content
	</script>
	<script type="text/javascript" >
        var PREFIX = '<c:url value="/"/>';
    </script>
    <script type="text/javascript" data-main="<c:url value="/js/raptoz-list"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>