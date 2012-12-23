<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section class="modal hide modal-post" id="postModalTemplate">
	<header class="modal-header">
 		<img class="profile-image" src="" data-src="holder.js/50x50/social" alt="questioner image" />
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
							<img class="profile-image" alt="your profile image" src="" data-src="holder.js/50x50/social"/>
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