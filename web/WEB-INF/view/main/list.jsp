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
		
		#userlist {min-width: 430px;}
		#userlist article {border: 1px solid black; margin-left: 0px; margin-bottom: 10px; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; width: 418px;}
		#userlist .usertag {padding-top: 10px; margin-left: 2px;}
		#userlist .usertag ul li ul li {display: inline;}
		#userlist .usertag .taglist {margin-bottom: 5px;}
		#userlist .userface {height: 85px; float: left; padding: 15px 5px; width: 90px; text-align: center; background-color: #FFF;}
		#userlist .userface img {width: 66px; height: 66px;}
		#userlist .usernamex {text-align: center;}
		#userlist .usertag .participate-toz-list li {display: block;}
		
		#tozlist {margin-left: 0; width: 480px;}
		#tozlist .toz {border: 1px solid black; box-shadow: 0 1px 2px rgba(34, 25, 25, 0.4); background-color: #FFF; padding: 5px; margin-bottom: 16px;}
		#tozlist .toz > *:not(.taglist) {cursor: pointer;}
		#tozlist .toz .toz-title {text-align: center;}
		#tozlist .toz .description {padding: 0 5px;}
		#tozlist .toz .taglist {margin-top: 5px;}
		#tozlist .toz footer {padding: 0; margin: 0; text-align: inherit; background-color: inherit; padding: 0 5px;}
		
		#tozlist .toz-modal .modal-header {height: 50px; padding: 15px;}
		#tozlist .toz-modal .profile-image {float: left; margin-right: 10px; width: 50px; height: 50px;}
		#tozlist .toz-modal .modal-body {clear: both;}
		#tozlist .toz-modal .modal-footer {text-align: left; background-color: #FFF;}
		#tozlist .toz-modal .modal-footer .comment {height: 50px; margin-bottom: 10px;}
		#tozlist .toz-modal .modal-footer .comment textarea {border: 1px solid; font-size: 1em; height: 32px; padding: 8px; resize: none; width: 452px; border-color: #DDDDDD #E1DFDF #D1CDCD;}
		#tozlist .toz-modal .modal-footer .comment:last-child {margin-bottom: 0;}
		#tozlist .toz-modal .modal-footer .commenter-nickname {margin-bottom: 0;} 
		
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
				Toz
			</li>
			<li><a id="createToz" href="#createTozForm">Create Toz</a></li>
			<li class="nav-header">
				Poster
			</li>
			<li><a>Attach Poster</a></li>
		</ul>
		
	    <form action="<c:url value="/toz/create"/>" method="post" class="form-horizontal modal hide fade in" id="createTozForm">
			<div class="modal-header">
				<h3>Create Toz</h3>
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
			<section id="userlist" class="span4">
				<c:forEach var="user" items="${result.userList}">
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
										<c:forEach var="tag" items="${user.userTagList}">
											<li>${tag.value}</li>
										</c:forEach>
									</ul>
								</li>
								<li>
									<b>Recent participate toz</b>
									<ul class="unstyled participate-toz-list">
										<c:forEach var="recent" items="${user.recentParticipantTozList}" begin="0" end="3">
											<li>${recent.title}</li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</article>
				</c:forEach>
			</section>
			<section id="tozlist" class="span6">
				<div class="row">
					<c:forEach var="toz" items="${result.tozList}">
						<article class="span3 toz" data-toz-id="${toz.id}" data-toggle="modal" data-target="tozModalTemplate">
							<header class="toz-title">
								<b>${toz.title}</b>
							</header>
							<p class="description">
								<str:truncateNicely lower="400" appendToEnd="...">${toz.description}</str:truncateNicely>
							</p>
							<ul class="unstyled taglist">
								<c:forEach var="tag" items="${toz.tagList}">
									<li>${tag.value}</li>
								</c:forEach>
							</ul>
							<footer>
								<p>
									<small>${fn:length(toz.tozParticipantList)} participants</small>&nbsp;
									<small>1h ago</small>
								</p>
							</footer>
						</article>
					</c:forEach>
					<section class="modal hide toz-modal" id="tozModalTemplate">
					    <header class="modal-header">
						    <img src="<c:url value="/image/50x50.gif"/>" alt="questioner image" class="profile-image"/>
						    <h3>title</h3>
					    </header>
					    <article class="modal-body">
					    	<p>One fine body…</p>
					    </article>
					    <footer class="modal-footer">
					    	<c:if test="${sessionScope.loginUser ne null}">
					    	<form action="<c:url value="/participant/add"/>" method="post">
					    		<article class="comment comment-input">
						    		<img src="data:image/gif;base64,${sessionScope.loginUser.encodeProfileImage}" alt="your image" class="profile-image"/>
						    		<textarea name="comment" placeholder="Add your opinion..."></textarea>
						    	</article>
						    </form>
					    	</c:if>
					    </footer>
				    </section>
				</div>
				<article class="comment hide" id="tozModalCommentTemplate">
					<c:if test="${sessionScope.loginUser ne null}">
					<button class="close comment-delete">x</button>
					</c:if>
		    		<img src="<c:url value="/image/50x50.gif"/>" alt="commenter image" class="profile-image"/>
		    		<p class="commenter-nickname"><b>nickname</b></p>
		    		<p>comment test</p>
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
			
			$(document).on("keypress", ".comment-input", function(e) {
				if (e.keyCode == enter) {
					e.preventDefault();
					var $target = $(e.target);
					var $form = $target.parents("form");
					var params = $form.serialize();
					params += "&tozId=" + $target.parents("section").attr("id").replace(/\D/g, "");
					console.log(params);
					
					$.ajax({
						url: $form.attr("action"),
						data: params,
						type: $form.attr("method").toUpperCase()
					}).done(function(comment) {
						console.log(comment);
						$tozModal = $("#tozModal" + comment.tozId);
						var $commentTemplate = $("#tozModalCommentTemplate").clone().removeClass("hide");
						$commentTemplate.attr("id", "participant" + comment.id).find("b").text(comment.participant.nickname).end()
							.children("img").attr("src", getProfileImageIfExists(comment.participant)).end()
							.children(":last").text(comment.comment);
						$form.before($commentTemplate).find("textarea").val("").blur();
					});
				}
			}).on("click", ".comment-delete", function(e) {
				console.log(e);
				var wholeId = $(e.target).parent().attr("id");
				var commentId = wholeId.replace(/\D/g, "");
				var url = "<c:url value="/participant/delete/"/>" + commentId;
				$.get(url, function(data) {
					console.log(data);
					$("#" + wholeId).remove();
				});
			});
			
			$("#signupForm, #createTozForm").modal({
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
			
			$("#createToz").click(function (e) {
				e.preventDefault();
				var target = $(e.target).attr("href");
				var url = "<c:url value="/user/islogin" />";
				$.get(url, function(data) {
					if (data) $(target).modal("show");
				});
			});
			
			$(".toz > *:not(.taglist)").click(function(e) {
				var $target = $(e.target);
				var tozId = $target.hasClass(".toz") ? $target.attr("data-toz-id") : $target.parents(".toz").attr("data-toz-id");
				var $toz = $("#tozModal" + tozId);
				if (isExists($toz)) {
					$toz.modal("show");
					return;
				}
				
				var url = "<c:url value='/toz/' />" + tozId;
				$.get(url, function(toz) {
					var template = getTozModalTemplates(tozId);
					createTozModal(toz, template);
					appendParticipant(tozId, template);
				});
			});
			
			$("#search").submit(function() {
				var term = $(this).children("input").val();
				$(this).attr("action", $(this).attr("action") + "/" + term);
				return true;
			});
			
			function getTozModalTemplates(tozId) {
				var template = new Object();
				template.outer = $("#tozModalTemplate").clone().attr("id", "tozModal" + tozId);
				template.header = template.outer.children(".modal-header");
				template.body = template.outer.children(".modal-body");
				template.footer = template.outer.children(".modal-footer");
				return template;
			}
			
			function isExists($target) {
				return $target.length != 0;
			}
			
			function createTozModal(toz, template) {
				template.header.children("h3").text(toz.title).end()
					.children("img").attr("src", getProfileImageIfExists(toz.questioner));
				template.body.children("p").text(toz.description);
			}
			
			function appendParticipant(tozId, template) {
				var url = "<c:url value='/participant/' />" + tozId;
				$.get(url, function(data) {
					$.each(data, function(index, comment) {
						template.comment = $("#tozModalCommentTemplate").clone().removeClass("hide");
						template.comment.attr("id", "participant" + comment.id).find("b").text(comment.participant.nickname).end()
							.children("img").attr("src", getProfileImageIfExists(comment.participant)).end()
							.children(":last").text(comment.comment);
						
						if (comment.participant.nickname != "${sessionScope.loginUser.nickname}")
							template.comment.children(".close").remove();
						
						if (template.footer.children("form").size() > 0)
							template.footer.children("form").before(template.comment);
						else
							template.footer.append(template.comment);
					});
					template.outer.appendTo("#tozlist").modal("show");
				});
			}
			
			function getProfileImageIfExists(target) {
				return target.encodeProfileImage == "" ? "<c:url value="/image/50x50.gif"/>" : "data:image/gif;base64," + target.encodeProfileImage;
			}
			
			function positioning() {
				$tozs = $("article.toz");
				$row = $("#tozlist .row");
				$tozs.eq(0).position({of: $row, at: "left top", collision: "none", my: "left top", offset: "20 0"});
				$tozs.eq(1).position({of: $row, at: "right top", collision: "none", my: "right top", offset: "-5 0"});
				
				for (var i = 0, j = 2; i < $tozs.size() - 2; i++, j++) {
					$tozs.eq(j).position({of: $("article.toz").eq(i), at: "bottom", collision: "none", my: "top", offset: "0 10"});
				}
			}
				
			positioning();
		});
	</script>
</body>
</html>