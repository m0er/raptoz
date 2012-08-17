<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>My Page</title>
	<!-- twitter bootstrap : default style -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery-ui.css"/>">
	<!-- jquery fileupload style -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/overcast/jquery.fileupload-ui.css"/>">
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/jquery.tagit.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/bootstrap.css"/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/selector/select2.css"/>"/>
	<style type="text/css">
		#topNav .navbar-inner {border-radius: 0;}
		
		#leftNav {margin-left: 50px;}
		#leftNav, #leftNav .nav {background-color: #2C2C2C;}
		#leftNav .nav li {color: #FFF;}
		#leftNav .nav li a {color: #999;}
		#leftNav .nav li a:HOVER {color: #FFF; background-color: #2C2C2C;}
		
		div.row h1 {margin:5px 0 0 10px;}
		td.profile {padding:20px 20px 20px 20px; border:1px solid #000000;}
  		td.profile img {height:130px;width:130px;}
 		td.profile span.btn {float:left; margin:6px 0 0 22px; width:75px;}
 		
 		.select2-container {margin-top:4px;}
 		
 		fieldset {margin-left:20px;}
 		h4 {text-align:center; margin-bottom:15px; padding-bottom:15px; border-bottom:1px solid #eeeeee;}

		#general form {margin:8px 0 5px 0;}
		#general form button {margin:-5px 0 0 20px;}

		#infos {margin-bottom:30px;}
		#posts {margin-top:30px; width:958px; height:200px; border:1px solid #000000;}
		
		div.control-group {margin:5px; padding-left:50px;}
		div.control-group input {margin-top:5px; text-indent:10px;}
		label.control-label {display:inline; margin:10px 37px 0px 0px; text-align:right; width:60px; float:left;}
		td.user-group {vertical-align:top; border:1px solid #000000; width:80%;}
		td.user-group label {width:120px;}
		
		.tagit-label {position:relative;top:10px;}
		.icon-remove-sign {position:relative;top:-5px;right:-15px;}
	</style>
</head>
<body>
	<nav class="navbar" id="topNav">
		<div class="navbar-inner">
			<div class="container">
				<a class="brand" href="<c:url value="/list"/>">Raptoz</a>
				<form action="<c:url value="/search"/>" class="navbar-search pull-left">
					<input type="text" class="search-query" placeholder="Search"/>
				</form>
				<ul class="nav pull-right">
					<li id="bgChange"><a href="#">Background</a></li>
				</ul>
			</div>
		</div>
	</nav>
	
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
								<button class="btn btn-small btn-primary" style="margin-top:4px;">change password</button> <br/>
								
								<div style="margin-top:5px;">
									<div class="select2-container select2-container-multi">
										<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post">
											<label class="control-label">Tags</label>
											<input type="hidden" id="tags" style="width: 219px; display: none; " value="">
										</form>
									</div>
								</div>
    						</div>
    						
    						
<!--     							<div id="chPass" class="control-group"> -->
<!-- 	  		 						<h4>Change Password</h4> -->
<!--   		 							<label class="control-label">Current Password</label> -->
<!-- 									<input name="curPwd" type="password" class="input-large"> <br/> -->
  					
<!--   									<label class="control-label">New Password</label> -->
<!--   									<input name="newPwd" type="password" class="input-large"> <br/> -->
  									
<!--   									<label class="control-label">Confirm</label> -->
<!--   									<input name="confirmPwd" type="password" class="input-large"> <br/> -->
<!--     							</div> -->

  						</fieldset>
					</td>
				</tr>
  			</table>
  			
<!--   			<h1>Tag</h1> -->
<!-- 			<hr/> -->

<%-- 			<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post" style="margin-left:10px"> --%>
<!-- 		    	<input name="tag" type="text" class="input-small search-query" placeholder="Tag"> -->
<!-- 				<button class="btn btn-small btn-primary" type="submit"><i class="icon-plus icon-white"></i>&nbsp;Add Tag</button> -->
<!-- 			</form> -->
			
<!-- 			<div id="tags"> -->
<!-- 				<ul class="tagit ui-widget ui-widget-content ui-corner-all"> -->
<%-- 					<c:forEach var="tag" items="${user.tags}"> --%>
<!-- 					<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all"> -->
<%-- 						<span class="tagit-label" id="${tag.id}">${tag.value}</span> --%>
<!-- 						<a class="close"> -->
<!-- 							<span class="icon-remove-sign"></span> -->
<!-- 						</a> -->
<!-- 					</li> -->
<%-- 					</c:forEach> --%>
					
<!-- 					<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all" id="tagTemplate" style="display:none;"> -->
<!-- 						<span class="tagit-label" id=""></span> -->
<!-- 						<a class="close"> -->
<!-- 							<span class="icon-remove-sign"></span> -->
<!-- 						</a> -->
<!-- 					</li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
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
	<script type="text/javascript" >
        ENV = {
            CP_DEFAULT_CACHEABLE: true,
            VIEW_PRESERVES_CONTEXT: true
        };
    </script>
	<script type="text/javascript" data-main="/js/raptoz-mypage" src="/js/require-jquery.js"></script>
</body>
</html>