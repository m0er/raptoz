<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="reply hide" id="postModalReplyTemplate">
	<c:if test="${sessionScope.loginUser ne null}">
		<button class="close reply-delete">x</button>
	</c:if>
	<c:choose>
		<c:when test="${not empty sessionScope.loginUser.encodeProfileImage}">
			<img class="profile-image" alt="${sessionScope.loginUser.nickname}`s profile image" src="data:image/gif;base64,${user.encodeProfileImage}"/>
		</c:when>
		<c:otherwise>
			<img class="profile-image" alt="anonymous profile image" src="" data-src="holder.js/50x50/social"/>
		</c:otherwise>
	</c:choose>
	<p class="replyer-nickname"><b>nickname</b></p>
 	<p class="reply-content">reply test</p>
 </article>