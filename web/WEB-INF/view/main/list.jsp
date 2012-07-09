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
	<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/bootstrap.css"/>"/>
	<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/tag-it.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ez-bg-resize.js"/>"></script>
	<style type="text/css">
		ul.tagit li.tagit-choice {padding-right: 7px;}
		
		#topNav .navbar-inner {border-radius: 0;}
		
		#leftNav {margin-left: 50px;}
		#leftNav, #leftNav .nav {background-color: #2C2C2C;}
		#leftNav .nav li {color: #FFF;}
		#leftNav .nav li a {color: #999; text-shadow: none;}
		#leftNav .nav li a:HOVER {color: #FFF; background-color: #2C2C2C;}
		
		#users {min-width: 430px;}
		#users article {border: 1px solid black; margin-left: 0px; margin-bottom: 10px; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; width: 418px;}
		#users .usertag {padding-top: 10px; margin-left: 2px;}
		#users .usertag ul li ul li {display: inline;}
		#users .usertag .taglist {margin-bottom: 5px;}
		#users .userface {height: 85px; float: left; padding: 15px 5px; width: 90px; text-align: center; background-color: #FFF;}
		#users .userface img {width: 66px; height: 66px;}
		#users .usernamex {text-align: center;}
		#users .usertag .participate-post-list li {display: block;}
		
		#posts {margin-left: 0; width: 480px;}
		#posts .post {border: 1px solid black; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; padding: 5px; margin-bottom: 16px;}
		#posts .post > *:not(.taglist) {cursor: pointer;}
		#posts .post .post-title {text-align: center;}
		#posts .post .description {padding: 0 5px;}
		#posts .post .taglist {margin-top: 5px;}
		#posts .post footer {padding: 0; margin: 0; text-align: inherit; background-color: inherit; padding: 0 5px;}
		
		#posts .post-modal .modal-header {height: 50px; padding: 15px;}
		#posts .post-modal .profile-image {float: left; margin-right: 10px; width: 50px; height: 50px;}
		#posts .post-modal .modal-body {clear: both;}
		#posts .post-modal .modal-footer {text-align: left; background-color: #FFF;}
		#posts .post-modal .modal-footer .reply {height: 50px; margin-bottom: 10px;}
		#posts .post-modal .modal-footer .reply textarea {border: 1px solid; font-size: 1em; height: 32px; padding: 8px; resize: none; width: 452px; border-color: #DDDDDD #E1DFDF #D1CDCD;}
		#posts .post-modal .modal-footer .reply:last-child {margin-bottom: 0;}
		#posts .post-modal .modal-footer .replyer-nickname {margin-bottom: 0;} 
		
		footer {margin-top: 30px; padding: 20px; text-align: center;}
	</style>
</head>
<body>
	<nav class="navbar" id="topNav">
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
							<li><a href="<c:url value="/mypage/${sessionScope.loginUser.id}"/>">My Page</a></li>
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
			<li><a id="writePost" href="#writePostForm">Write post</a></li>
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
					<label class="control-label" for="description">Description</label>
					<div class="controls">
						<input type="text" class="input-xlarge" id="description" name="description" placeholder="description"/>
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
								<img alt="profile image" src="<c:url value="/image/66x66.gif"/>" alt=""/>
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
									<b>Recent participate toz</b>
									<ul class="unstyled participate-post-list">
										<%-- <c:forEach var="recent" items="${user.recentwriterTozList}" begin="0" end="3">
											<li>${recent.title}</li>
										</c:forEach> --%>
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
							<p class="description">
								<str:truncateNicely lower="400" appendToEnd="...">${post.content}</str:truncateNicely>
							</p>
							<ul class="unstyled taglist">
								<c:forEach var="tag" items="${post.tags}">
									<li>${tag.value}</li>
								</c:forEach>
							</ul>
							<footer>
								<p>
									<small>${fn:length(post.replyIds)} replies</small>&nbsp;
									<small>1h ago</small>
								</p>
							</footer>
						</article>
					</c:forEach>
					<section class="modal hide post-modal" id="postModalTemplate">
					    <header class="modal-header">
						    <img src="<c:url value="/image/50x50.gif"/>" alt="questioner image" class="profile-image"/>
						    <h3>title</h3>
					    </header>
					    <article class="modal-body">
					    	<p>One fine body…</p>
					    </article>
					    <footer class="modal-footer">
					    	<c:if test="${sessionScope.loginUser ne null}">
					    	<form action="<c:url value="/reply/add"/>" method="post">
					    		<article class="reply reply-input">
						    		<img src="data:image/gif;base64,${sessionScope.loginUser.encodeProfileImage}" alt="your image" class="profile-image"/>
						    		<textarea name="reply" placeholder="Add your opinion..."></textarea>
						    	</article>
						    </form>
					    	</c:if>
					    </footer>
				    </section>
				</div>
				<article class="reply hide" id="postModalReplyTemplate">
					<c:if test="${sessionScope.loginUser ne null}">
					<button class="close reply-delete">x</button>
					</c:if>
		    		<img src="<c:url value="/image/50x50.gif"/>" alt="replyer image" class="profile-image"/>
		    		<p class="replyer-nickname"><b>nickname</b></p>
		    		<p>reply test</p>
		    	</article>
			</section>
		</div>
	</div>
	<!-- 
	<footer>
		Raptoz@2012
	</footer>
	 -->
	<script type="text/javascript">
		$(document).ready(function() {
			var enter = 13;
			
			$("body").ezBgResize({
				img: "<c:url value='/image/living.social.street1.jpg'/>"
			});
			
			$(document).on("keypress", ".reply-input", function(e) {
				if (e.keyCode == enter) {
					e.preventDefault();
					var $target = $(e.target);
					var $form = $target.parents("form");
					var params = $form.serialize();
					params += "&postId=" + $target.parents("section").attr("id").replace(/\D/g, "");
					console.log(params);
					
					$.ajax({
						url: $form.attr("action"),
						data: params,
						type: $form.attr("method").toUpperCase()
					}).done(function(reply) {
						console.log(reply);
						$postModal = $("#postModal" + reply.postId);
						var $replyTemplate = $("#postModalReplyTemplate").clone().removeClass("hide");
						$replyTemplate.attr("id", "writer" + reply.id).find("b").text(reply.writer.nickname).end()
							.children("img").attr("src", getProfileImageIfExists(reply.writer)).end()
							.children(":last").text(reply.content);
						$form.before($replyTemplate).find("textarea").val("").blur();
					});
				}
			}).on("click", ".reply-delete", function(e) {
				console.log(e);
				var wholeId = $(e.target).parent().attr("id");
				var replyId = wholeId.replace(/\D/g, "");
				var url = "<c:url value="/reply/delete/"/>" + replyId;
				$.get(url, function(data) {
					console.log(data);
					$("#" + wholeId).remove();
				});
			});
			
			$("#signupForm, #writePostForm").modal({
				show: false
			});
			
			$(".taglist").tagit({
				allowAddTag: false,
				showCloseButton: false
			});
			
			$("#bgChange").click(function(e) {
				e.preventDefault();
				$("#jq_ez_bg img").attr("src", "<c:url value='/image/paper.jpg'/>");
			});
			
			$("#writePost").click(function (e) {
				e.preventDefault();
				var target = $(e.target).attr("href");
				var url = "<c:url value="/user/islogin" />";
				$.get(url, function(data) {
					if (data) $(target).modal("show");
				});
			});
			
			$(".post > *:not(.taglist)").click(function(e) {
				var $target = $(e.target);
				var postId = $target.hasClass(".post") ? $target.attr("data-post-id") : $target.parents(".post").attr("data-post-id");
				var $post = $("#postModal" + postId);
				if (isExists($post)) {
					$post.modal("show");
					return;
				}
				
				var url = "<c:url value='/post/' />" + postId;
				$.get(url, function(post) {
					var template = getPostModalTemplates(postId);
					writePostModal(post, template);
					appendWriter(postId, template);
				});
			});
			
			$("#search").submit(function() {
				var term = $(this).children("input").val();
				$(this).attr("action", $(this).attr("action") + "/" + term);
				return true;
			});
			
			function getPostModalTemplates(postId) {
				var template = new Object();
				template.outer = $("#postModalTemplate").clone().attr("id", "postModal" + postId);
				template.header = template.outer.children(".modal-header");
				template.body = template.outer.children(".modal-body");
				template.footer = template.outer.children(".modal-footer");
				return template;
			}
			
			function isExists($target) {
				return $target.length != 0;
			}
			
			function writePostModal(post, template) {
				template.header.children("h3").text(post.title).end()
					.children("img").attr("src", getProfileImageIfExists(post.writer));
				template.body.children("p").text(post.description);
			}
			
			function appendWriter(postId, template) {
				var url = "<c:url value='/reply/' />" + postId;
				$.get(url, function(data) {
					$.each(data, function(index, reply) {
						template.reply = $("#postModalReplyTemplate").clone().removeClass("hide");
						template.reply.attr("id", "writer" + reply.id).find("b").text(reply.writer.nickname).end()
							.children("img").attr("src", getProfileImageIfExists(reply.writer)).end()
							.children(":last").text(reply.content);
						
						if (reply.writer.nickname != "${sessionScope.loginUser.nickname}")
							template.reply.children(".close").remove();
						
						if (template.footer.children("form").size() > 0)
							template.footer.children("form").before(template.reply);
						else
							template.footer.append(template.reply);
					});
					template.outer.appendTo("#posts").modal("show");
				});
			}
			
			function getProfileImageIfExists(target) {
				return target.encodeProfileImage == "" ? "<c:url value="/image/50x50.gif"/>" : "data:image/gif;base64," + target.encodeProfileImage;
			}
			
			function positioning() {
				$posts = $("article.post");
				$row = $("#posts .row");
				$posts.eq(0).position({of: $row, at: "left top", collision: "none", my: "left top", offset: "20 0"});
				$posts.eq(1).position({of: $row, at: "right top", collision: "none", my: "right top", offset: "-5 0"});
				
				for (var i = 0, j = 2; i < $posts.size() - 2; i++, j++) {
					$posts.eq(j).position({of: $("article.post").eq(i), at: "bottom", collision: "none", my: "top", offset: "0 10"});
				}
			}
				
			positioning();
		});
	</script>
</body>
</html>