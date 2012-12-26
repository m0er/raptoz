<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/nav" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
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
	
	<div class="container" id="resultContainer">
		<div class="row" id="mypageInfo" data-user-id="${dto.user.id}">
			<form class="well form-horizontal not visible" id="mypageForm" action="<c:url value="/mypage/${dto.user.id}/update"/>" method="post">
			    <fieldset>
				    <legend>Edit Account Settings</legend>
				    <div class="control-group">
						<label class="control-label" for="inputNickname">Nickname</label>
						<div class="controls">
							<input type="text" name="nickname" id="inputNickname" placeholder="input nickname" value="${dto.user.nickname}">
						</div>
					</div>
				    <div class="control-group">
						<label class="control-label" for="changePasswordButton">Password</label>
						<div class="controls">
							<input type="button" data-toggle="modal" data-target="#passwordFormTemplate" id="changePasswordButton" class="button" value="Change Password">
						</div>
					</div>
				    <div class="control-group">
						<label class="control-label" for="changeProfileImageButton">Profile Image</label>
						<div class="controls">
							<ul class="thumbnails">
								<li>
									<a href="#" class="thumbnail">
										<c:choose>
											<c:when test="${not empty dto.user.encodeProfileImage}">
												<img alt="${dto.user.nickname}`s profile image" src="data:image/gif;base64,${dto.user.encodeProfileImage}" width="160" height="160"/>
											</c:when>
											<c:otherwise>
												<img alt="${dto.user.nickname}`s profile image" src="" data-src="holder.js/160x160/social" width="160" height="160"/>
											</c:otherwise>
										</c:choose>
									</a>
								</li>
							</ul>
							<input type="file" id="inputProfileImage" class="btn" name="profileImage" value="Change Image">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="inputTags">Tags</label>
						<div class="input-tags controls">
							<input id="inputTags" name="tags" type="text" value="${dto.user.tagPrint}"/>
						</div>
					</div>
				    <div class="control-group">
						<div class="controls">
						    <input type="submit" class="btn btn-primary" value="Submit">
						</div>
					</div>
			    </fieldset>
		    </form>
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
	
	<template:password-modal/>
	
	<script type="text/javascript" >
        var PREFIX = '<c:url value="/"/>';
    </script>
	<script type="text/javascript" data-main="<c:url value="/js/raptoz-mypage-form"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>