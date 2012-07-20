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
	<link rel="stylesheet" type="text/css" href="<c:url value="/bootstrap/css/bootstrap.css"/>"/>
	<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/tag-it.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/bootstrap/js/bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.ez-bg-resize.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/js/jquery.form.js"/>"></script>
	<style type="text/css">
		#topNav .navbar-inner {border-radius: 0;}
		
		#leftNav {margin-left: 50px;}
		#leftNav, #leftNav .nav {background-color: #2C2C2C;}
		#leftNav .nav li {color: #FFF;}
		#leftNav .nav li a {color: #999;}
		#leftNav .nav li a:HOVER {color: #FFF; background-color: #2C2C2C;}
		
		div.row {background-color:#ffffff; border:1px solid #000000;}
		div.row h1 {margin:5px 0 0 10px;}
/*  		td.profile {padding:0px 10px 10px 10px; float:left; width:130px;} */
		td.profile {position:relative; left:160px; top:-100px; width:130px; padding:0px 10px 10px 10px;}
  		td.profile img {height:130px;width:130px;}
 		td.profile span.btn {float:left; margin:6px 0 0 22px; width:75px;}
 		
 		fieldset {width:390px; margin-left:170px;}
 		h4 {text-align:center; margin-bottom:15px; padding-bottom:15px; border-bottom:1px solid #eeeeee;}

		#myInfos {margin-bottom:30px;}
		#tagInfos {margin-top:30px;}
		#tozInfos {margin-top:30px;}
		#chPass h4 {margin-top:50px;}
		
		div.control-group {margin:5px;}
		div.control-group input {margin-top:5px; text-indent:10px;}
		label.control-label {display:inline; margin:10px 37px 0px 0px; text-align:right; width:60px; float:left;}
		td.user-group {vertical-align:top;}
		td.user-group label {width:120px;}
		td.user-group button {position:relative;width:115px;left:170px;margin-bottom:30px;}
		
		.tagit-label {position:relative;top:10px;}
		.icon-remove-sign {position:relative;top:-5px;right:-15px;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("body").ezBgResize({
				img: "<c:url value='/image/living.social.street1.jpg'/>"
			});
			
			$("#bgChange").click(function(e) {
				e.preventDefault();
				$("#jq_ez_bg img").attr("src", "<c:url value='/image/paper.jpg'/>");
			});
			
			$("#profileImage").change(function() {
				$("#imageForm").ajaxSubmit({
					success : function(data) {
						$("td.profile img").attr("src", "data:image/gif;base64," + data);	
					}
				});
			});
			
			$("#tagInfos form").submit(function(e) {
				e.preventDefault();
				var url = $(this).attr("action") + "?" + $(this).serialize();
				$.ajax({
					url : url
				}).done(function(data) {
					console.log(data);
					var $tagTemplate = $("#tagTemplate").clone();
					$tagTemplate.removeAttr("style");
					$tagTemplate.children().first().attr("id", data.id).text(data.value).end().end().appendTo("#tags ul");
				});
			});
			
			$(document).on("click", "a.close", function() {
				var id = $(this).parent().find(".tagit-label").attr("id");
				$.ajax({
					url : "${user.id}/tag/" + id + "/delete"
				});
				$(this).parent().remove();
			});
		});
	</script>
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
		<div class="row" id="myInfos">
			<h1>User</h1>
			<hr/>
			
			<table>
				<tr>
					<td class="profile">
						<div>
							<img alt="profile image" src="data:image/gif;base64,${user.encodeProfileImage}"/>

							<form id="imageForm" action="<c:url value="/mypage/${user.id}/profileImage/update"/>" enctype="multipart/form-data" method="post">
								<span class="btn btn-mini btn-primary fileinput-button">
									<i class="icon-picture icon-white"></i>
									<span>&nbsp;&nbsp;change</span>
									<input type="file" class="input-file" name="profileImage" id="profileImage"/>
								</span>
							</form>
						</div>
					</td>
					<td class="user-group">
						<form action="<c:url value="/mypage/${user.id}/update"/>" enctype="multipart/form-data" method="post">
							<fieldset>
				 				<div id="general" class="control-group">
	  			 					<h4>General</h4>
  		 							<label class="control-label">Email</label>
	  								<input name="email" type="text" class="input-large" value="${user.email}"> <br/>
  					
  									<label class="control-label">Nickname</label>
	  								<input name="nickname" type="text" class="input-large" value="${user.nickname}"> <br/>
    							</div>
    						
    							<div id="chPass" class="control-group">
	  		 						<h4>Change Password</h4>
  		 							<label class="control-label">Current Password</label>
									<input name="curPwd" type="password" class="input-large"> <br/>
  					
  									<label class="control-label">New Password</label>
  									<input name="newPwd" type="password" class="input-large"> <br/>
  									
  									<label class="control-label">Confirm</label>
  									<input name="confirmPwd" type="password" class="input-large"> <br/>
    							</div>
   								<button class="btn btn-small btn-primary" type="submit"><i class="icon-cog icon-white"></i>&nbsp;&nbsp;&nbsp;Update Setting</button>		
	  						</fieldset>
  						</form>
					</td>
				</tr>
  			</table>
		</div>
		
		<div class="row" id="tagInfos">
			<h1>Tag</h1>
			<hr/>

			<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post" style="margin-left:10px">
		    	<input name="tag" type="text" class="input-small search-query" placeholder="Tag">
				<button class="btn btn-small btn-primary" type="submit"><i class="icon-plus icon-white"></i>&nbsp;Add Tag</button>
			</form>
			
			<div id="tags">
				<ul class="tagit ui-widget ui-widget-content ui-corner-all">
					<c:forEach var="tag" items="${user.tags}">
					<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all">
						<span class="tagit-label" id="${tag.id}">${tag.value}</span>
						<a class="close">
							<span class="icon-remove-sign"></span>
						</a>
					</li>
					</c:forEach>
					
					<li class="tagit-choice ui-widget-content ui-state-default ui-corner-all" id="tagTemplate" style="display:none;">
						<span class="tagit-label" id=""></span>
						<a class="close">
							<span class="icon-remove-sign"></span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="row" id="tozInfos">
			<h1>Toz</h1>
			<hr/>

<%-- 			<form action="<c:url value="/mypage/${user.id}/tag/add"/>" method="post" style="margin-left:10px"> --%>
<!-- 		    	<input name="tag" type="text" class="input-small search-query" placeholder="Tag"> -->
<!-- 				<button class="btn btn-small btn-primary" type="submit"><i class="icon-plus icon-white"></i>&nbsp;Add Tag</button> -->
<!-- 			</form> -->
		</div>
	</div>
</body>
</html>