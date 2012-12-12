<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/nav" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My Page</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery.fileupload-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/raptoz-mypage.css"/>"/>
</head>
<body>
	<nav:gnb/>
	<nav:snb/>
	
	<div class="container" id="resultContainer">
		<div class="row" id="infos">
			<h1 id="${user.id}"></h1>
			<table>
				<tr>
					<td class="profile">
						<div>
							<img alt="profile image" src="data:image/gif;base64,${user.encodeProfileImage}"/>

							<form id="imageForm" action="<c:url value="/mypage/${user.id}/profileImage/update"/>" enctype="multipart/form-data" method="post">
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
								<form action="<c:url value="/mypage/${user.id}/update"/>" enctype="multipart/form-data" method="post">
  		 							<label class="control-label">Email</label>
	  								<input name="email" type="text" class="input-large" value="${user.email}"> <br/>
  					
  									<label class="control-label">Nickname</label>
	  								<input name="nickname" type="text" class="input-large" value="${user.nickname}">
									<button class="btn btn-small btn-primary">update setting</button> <br/>
								</form>
									
								<label class="control-label">Password</label>
								<a data-toggle="modal" href="#pwdForm"><button class="btn btn-small btn-primary" style="margin-top:4px;" >change password</button></a> <br/>
								
								<div style="margin-top:5px;">
									<div class="select2-container select2-container-multi">
<%-- 										<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post"> --%>
											<label class="control-label">Tags</label>
											<input type="hidden" id="tags" style="width: 219px; display: none; " value="">
<!-- 										</form> -->
									</div>
								</div>
    						</div>
  						</fieldset>
					</td>
				</tr>
  			</table>
		</div>
		
		<div class="row" id="posts">
<!-- 			<h1>Post</h1> -->
			<hr/>

<%-- 			<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post" style="margin-left:10px"> --%>
<!-- 		    	<input name="tag" type="text" class="input-small search-query" placeholder="Tag"> -->
<!-- 				<button class="btn btn-small btn-primary" type="submit"><i class="icon-plus icon-white"></i>&nbsp;Add Tag</button> -->
<!-- 			</form> -->
		</div>
	</div>
	
	<form action="<c:url value="/mypage/${user.id}/update"/>" enctype="multipart/form-data" method="post" class="form-horizontal modal hide fade in" id="pwdForm">
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
	
	<script type="text/javascript" >
        ENV = {
            CP_DEFAULT_CACHEABLE: true,
            VIEW_PRESERVES_CONTEXT: true
        };
        
        var PREFIX = '<c:url value="/"/>';
    </script>
	<script type="text/javascript" data-main="<c:url value="/js/raptoz-mypage"/>" src="<c:url value="/js/require-jquery.js"/>"></script>
</body>
</html>