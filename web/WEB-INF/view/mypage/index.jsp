<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/nav" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My Page</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery.fileupload-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/selector/select2.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/raptoz-base.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/raptoz-mypage.css"/>"/>
</head>
<body>
	<nav:gnb/>
	<nav:snb/>
	
	<div class="container" id="resultContainer" style="visibility: hidden;">
		<div class="row" id="mypageInfo" data-user-id="${dto.user.id}">
			<form action="" class="well span7">
				<a class="profile-image">
				<c:choose>
					<c:when test="${not empty dto.user.encodeProfileImage}">
						<img alt="${dto.user.nickname}`s profile image" src="data:image/gif;base64,${dto.user.encodeProfileImage}"/>
					</c:when>
					<c:otherwise>
						<img alt="${dto.user.nickname}`s profile image" src="<c:url value="/img/66x66.gif"/>"/>
					</c:otherwise>
				</c:choose>
				</a>
				<fieldset>
					<h1>${dto.user.nickname}</h1>
					<label>Interests</label>
					<input id="tags" class="taglist" type="text" name="tags" value="${dto.user.tagPrint}"/>
					<c:if test="${dto.user.id eq sessionScope.loginUser.id}">
						<button type="submit" class="btn">Edit Profile</button>
					</c:if>
				</fieldset>
			</form>
			<section class="well span4">
				TBD
			</section>
			<section class="well span11 post-listing" id="posts">
				<div class="navbar">
					<div class="navbar-inner">
						<a class="brand" href="#"><strong>Written posts</strong></a>
					</div>
				</div>
				<div class="row">
					<c:forEach var="post" items="${dto.posts}">
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
				</div>
			</section>
			<%-- <table>
				<tr>
					<td class="profile">
						<div>
							<form id="imageForm" action="<c:url value="/mypage/${dto.user.id}/profileImage/update"/>" enctype="multipart/form-data" method="post">
								<span class="btn btn-mini btn-primary fileinput-button">
									<i class="icon-picture icon-white"></i>
									<span>change</span>
									<input type="file" class="input-file" name="profileImage" id="profileImage"/>
								</span>
							</form>
						</div>
					</td>
					<td style="width:20px;"></td>
					<td class="user-group">
						<fieldset>
							<div id="general" class="control-group">
								<form action="<c:url value="/mypage/${dto.user.id}/update"/>" enctype="multipart/form-data" method="post">
  		 							<label class="control-label">Email</label>
	  								<input name="email" type="text" class="input-large" value="${dto.user.email}"> <br/>
  					
  									<label class="control-label">Nickname</label>
	  								<input name="nickname" type="text" class="input-large" value="${dto.user.nickname}">
									<button class="btn btn-small btn-primary">update setting</button> <br/>
								</form>
									
								<label class="control-label">Password</label>
								<a data-toggle="modal" href="#pwdForm"><button class="btn btn-small btn-primary" style="margin-top:4px;" >change password</button></a> <br/>
								
								<div style="margin-top:5px;">
									<label class="control-label">Interests</label>
									<input id="tags" class="taglist" type="text" name="tags" value="${dto.user.tagPrint}"/>
								</div>
    						</div>
  						</fieldset>
					</td>
				</tr>
  			</table> --%>
		</div>
	</div>
	
	<form action="<c:url value="/mypage/${dto.user.id}/update"/>" enctype="multipart/form-data" method="post" class="form-horizontal modal hide fade in" id="pwdForm">
		<div class="modal-header">
			<h3>Password</h3>
		</div>
		<fieldset class="modal-body">
			<div class="control-group">
				<label class="control-label" for="email">Current Password</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="curPwd" name="curPwd" placeholder="CurrentPassword"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">New Password</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="newPwd" name="newPwd" placeholder="NewPassword"/>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="password">Confirm</label>
				<div class="controls">
					<input type="password" class="input-xlarge" id="confirmPwd" name="confirmPwd" placeholder="Confirm"/>
				</div>
			</div>
		</fieldset>
		<div class="modal-footer">
			<button class="btn btn-primary" type="submit">Ok</button>
			<button class="btn" data-dismiss="modal">Cancel</button>
		</div>
	</form>
	
	<template:post/>
	<template:reply/>
	
	<script type="text/javascript" >
        var PREFIX = '<c:url value="/"/>';
    </script>
	<script type="text/javascript" data-main="<c:url value="/js/raptoz-mypage"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>