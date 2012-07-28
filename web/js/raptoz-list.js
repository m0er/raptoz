require.config({
    paths: {
        jqueryui: 'jquery-ui',
        plugin: 'jquery-plugin',
        ember: 'ember',
        bootstrap: 'bootstrap',
        external: 'external',
        models: 'ember/models',
        controllers: 'ember/controllers',
        views: 'ember/views'
    }
});

require(['bootstrap/load', 'plugin/tag-it', 'plugin/jquery.form', 'plugin/jquery.ez-bg-resize'], function() {
	$(document).ready(function() {
		var enter = 13;
		
		$("body").ezBgResize({
			img: "/img/living.social.street1.jpg"
		});
		
		$(document).on("keypress", ".reply-input", function(e) {
			if (e.keyCode == enter) {
				e.preventDefault();
				
				var $target = $(e.target);
				var $form = $target.parents("form");
				var params = $form.serialize();
				params += "&postId=" + $target.parents("section").attr("id");
				
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
			
			var url = "/reply/delete/" + replyId;
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
			$("#jq_ez_bg img").attr("src", "/img/paper.jpg");
		});
		
		$("#writePost").click(function (e) {
			e.preventDefault();
			
			var target = $(e.target).attr("href");
			
			var url = '/user/islogin';
			$.get(url, function(data) {
				if (data) $(target).modal("show");
			});
		});
		
		$(".post header, .post .content").click(function(e) {
			var $target = $(e.target);
			var postId = $target.hasClass(".post") ? $target.attr("data-post-id") : $target.parents(".post").attr("data-post-id");
			var $post = $("#postModal" + postId);
			
			if (isExists($post)) {
				$post.modal("show");
				return;
			}
			
			var url = "/post/" + postId;
			$.get(url, function(post) {
				console.log(post);
				
				var template = getPostModalTemplates(postId);
				writePostModal(post, template);
				appendWriter(postId, template);
			});
		});
		
		$(".post footer a").click(function(e) {
			e.stopPropagation();
			return true;
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
			template.body.children("p").text(post.content);
		}
		
		function appendWriter(postId, template) {
			var url = "/reply/" + postId;
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
			return target.encodeProfileImage == "" ? "/img/50x50.gif" : "data:image/gif;base64," + target.encodeProfileImage;
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
});

//require(['ember/load'], function(Ember) {
//	console.log($);
//	console.log(Ember);
//});

