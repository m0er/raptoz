<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Raptoz List Page</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.tagit.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>"/>
	<style type="text/css">
		body {padding-top: 70px;}
		
		ul.tagit li.tagit-choice {padding-right: 7px;}
		
		#topNav .navbar-inner {border-radius: 0;}
		
		#leftNav {margin-left: 50px;}
		#leftNav, #leftNav .nav {background-color: #2C2C2C;}
		#leftNav .nav li {color: #FFF;}
		#leftNav .nav li a {color: #999; text-shadow: none;}
		#leftNav .nav li a:HOVER {color: #FFF; background-color: #2C2C2C;}
		
		#writePostForm textarea {resize: none;}
		
		#users {min-width: 430px;}
		#users article {border: 1px solid black; margin-left: 0px; margin-bottom: 10px; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; width: 418px;}
		#users .usertag {padding-top: 10px; margin-left: 2px;}
		#users .usertag ul li ul li {display: inline;}
		#users .usertag .taglist {margin-bottom: 5px;}
		#users .userface {height: 85px; float: left; padding: 15px 5px; width: 90px; text-align: center; background-color: #FFF;}
		#users .userface img {width: 66px; height: 66px;}
		#users .usernamex {text-align: center;}
		#users .usertag .reply-list li {display: block;}
		
		#posts {margin-left: 0; width: 480px;}
		#posts .post {border: 1px solid black; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; padding: 5px 5px 0 5px; margin-bottom: 16px;}
		#posts .post > header, #posts .post > .content {cursor: pointer;}
		#posts .post .post-title {text-align: center;}
		#posts .post .content {padding: 0 5px;}
		#posts .post .taglist {margin-top: 5px;}
		#posts .post .icon-space {margin-right: 10px;}
		#posts .post footer {padding: 0; margin: 0; text-align: inherit; background-color: inherit; padding: 0 5px;}
		#posts .post footer .post-status {float: right;}
		#posts .post footer .post-writer {float: left; background-color: #F2F0F0; width: 100%; padding: 7px 10px; margin: 0 -10px;}
		#posts .post footer .post-writer a:first-child:HOVER {text-decoration: none;}
		
		#posts .modal-post {top: 40%;}
		#posts .modal-post .modal-header {height: 50px; padding: 15px; border: none;}
		#posts .modal-post .modal-header .post-content {float: left; margin: 10px 0; width: 100%;}
		#posts .modal-post .profile-image {float: left; margin-right: 10px; width: 50px; height: 50px;}
		#posts .modal-post .modal-body {clear: both; border-top: 1px solid #EEEEEE;}
		#posts .modal-post footer {margin-top: 0;}
		#posts .modal-post .modal-reply {text-align: left; background-color: #FFF;}
		#posts .modal-post .modal-reply .reply {height: 50px; margin-bottom: 10px;}
		#posts .modal-post .modal-reply .reply textarea {border: 1px solid; font-size: 1em; height: 32px; padding: 8px; resize: none; width: 437px; border-color: #DDDDDD #E1DFDF #D1CDCD;}
		#posts .modal-post .modal-reply .reply:last-child {margin-bottom: 0;}
		#posts .modal-post .modal-reply .replyer-nickname {margin-bottom: 0;} 
		
		footer {margin-top: 30px; padding: 20px; text-align: center;}
	</style>
</head>
<body>
	<nav class="navbar navbar-fixed-top" id="topNav">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="<c:url value="/list"/>">Raptoz</a>
				<form id="search" action="<c:url value="/list"/>" class="navbar-search pull-left" method="post">
					<input type="text" class="search-query" name="term" placeholder="Search"/>
				</form>
				<ul class="nav pull-right">
					<c:choose>
						<c:when test="${sessionScope.loginUser eq null}">
							<li><a data-toggle="modal" href="#loginForm">Login</a></li>
							<li><a data-toggle="modal" href="#signupForm">Sign up</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="<c:url value="/user/logout"/>">Logout</a></li>
							<li id="mypage" data-sessionuser-nickname="${sessionScope.loginUser.nickname}"><a href="<c:url value="/mypage/${sessionScope.loginUser.id}"/>">My Page</a></li>
						</c:otherwise>
					</c:choose>
					<li id="bgChange"><a href="#">Background</a></li>
					<li><a href="<c:url value="/test/dummy/create"/>">create dummy</a></li>
					<li><a href="<c:url value="/test/dummy/delete"/>">delete dummy</a></li>
				</ul>
				<form action="<c:url value="/user/login"/>" method="post" class="form-horizontal modal hide fade in" id="loginForm">
					<div class="modal-header">
						<h3>Login</h3>
					</div>
					<fieldset class="modal-body">
						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="email" name="email" placeholder="Email"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="password" name="password" placeholder="Password"/>
							</div>
						</div>
					</fieldset>
					<div class="modal-footer">
						<button class="btn btn-primary" type="submit">Login</button>
						<button class="btn" data-dismiss="modal">Cancel</button>
					</div>
				</form>
				<form action="<c:url value="/user/signup"/>" enctype="multipart/form-data" method="post" class="form-horizontal modal hide fade in" id="signupForm">
					<div class="modal-header">
						<h3>Sign up</h3>
					</div>
					<fieldset class="modal-body">
						<div class="control-group">
							<label class="control-label" for="email">Email</label>
							<div class="controls">
								<input type="text" class="input-xlarge" id="email" name="email" placeholder="Email"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password">Password</label>
							<div class="controls">
								<input type="password" class="input-xlarge" id="password" name="password" placeholder="Password"/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="profileImage">Profile Image</label>
							<div class="controls">
								<input type="file" class="input-file" name="profileImage" id="profileImage"/>
							</div>
						</div>
					</fieldset>
					<div class="modal-footer">
						<button class="btn btn-primary" type="submit">Sign up</button>
						<button class="btn" data-dismiss="modal">Cancel</button>
					</div>
				</form>
			</div>
		</div>
	</nav>
	
	<nav class="pull-left well" id="leftNav">
		<ul class="nav nav-list">
			<li class="nav-header">
				Post
			</li>
			<li><a id="writePost" data-toggle="modal" href="#writePostForm">Write post</a></li>
			<li class="nav-header">
				Poster
			</li>
			<li><a>Attach Poster</a></li>
		</ul>
		
	    <form action="<c:url value="/post/write"/>" method="post" class="form-horizontal modal hide fade in" id="writePostForm">
			<div class="modal-header">
				<h3>Write post</h3>
			</div>
			<fieldset class="modal-body">
				<div class="control-group">
					<label class="control-label" for="title">Title</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="title" name="title" placeholder="title"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="content">Content</label>
					<div class="controls">
						<textarea class="input-xlarge" id="content" name="content" rows="10" cols="3"></textarea>
					</div>
				</div>
			</fieldset>
			<div class="modal-footer">
				<button class="btn btn-primary" type="submit">Submit</button>
				<button class="btn" data-dismiss="modal">Cancel</button>
			</div>
		</form>
	</nav>
	
	<div class="container" id="resultContainer">
		<div class="row">
			<section id="users" class="span4">
				<c:forEach var="user" items="${search.users}">
					<article class="row userinfo">
						<div class="userface">
							<a href="#">
								<img alt="profile image" src="<c:url value="/img/66x66.gif"/>" alt=""/>
							</a>
							<p class="username"><small>${user.nickname}</small></p>
						</div>
						<div class="span4 usertag">
							<ul class="unstyled">
								<li>
									<b>Interests</b>
								</li>
								<li>
									<ul class="unstyled taglist">
										<c:forEach var="tag" items="${user.tags}">
											<li>${tag.value}</li>
										</c:forEach>
									</ul>
								</li>
								<li>
									<b>Recent activities</b>
									<ul class="unstyled reply-list">
										<c:forEach var="activity" items="${user.activities}">
											<li>
												<c:choose>
													<c:when test="${activity.type eq 'POST' }">
														<span class="label label-success">post</span>&nbsp;<str:truncateNicely lower="40" appendToEnd="...">${activity.contentString}</str:truncateNicely>
													</c:when>
													<c:when test="${activity.type eq 'REPLY' }">
														<span class="label label-info">reply</span>&nbsp;<str:truncateNicely lower="40" appendToEnd="...">${activity.contentString}</str:truncateNicely>
													</c:when>
												</c:choose>
											</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</article>
				</c:forEach>
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
							<ul class="unstyled taglist">
								<c:forEach var="tag" items="${post.tags}">
									<li>${tag.value}</li>
								</c:forEach>
							</ul>
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
						    <img src="<c:url value="/img/50x50.gif"/>" alt="questioner image" class="profile-image"/>
						    <h3 class="post-title">title</h3>
						    <article class="post-content">
					    	</article>
					    </header>
					    <article class="modal-body">
					    	<div class="modal-reply">
						    	<c:if test="${sessionScope.loginUser ne null}">
						    	<form action="<c:url value="/reply/add"/>" method="post">
						    		<article class="reply reply-input">
							    		<img src="/img/66x66.gif" alt="your image" class="profile-image"/>
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
		    		<img src="<c:url value="/img/50x50.gif"/>" alt="replyer image" class="profile-image"/>
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
	<script type="text/javascript" >
        ENV = {
            CP_DEFAULT_CACHEABLE: true,
            VIEW_PRESERVES_CONTEXT: true
        };
    </script>
    <script type="text/javascript" data-main="<c:url value="/js/raptoz-list"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>