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

require(['bootstrap/load', 'plugin/tag-it', 'plugin/jquery.form', 'plugin/jquery.ez-bg-resize', 'plugin/jquery-pinterest-positioning'], function() {
	$(document).ready(function() {
		var enter = 13;
		
		// Bootstrap에서 Modal 폼이 backdrop 뒤에 있는 현상 수정
		// 참고: http://jsfiddle.net/ATeaH/8/
		$('.modal').appendTo($('body'));
		
		$("body").ezBgResize({
			img: "/img/living.social.street1.jpg"
		});
		
		$(document).on("keypress", ".reply-input", function(e) {
			if (e.keyCode == enter) {
				e.preventDefault();
				
				var $target = $(e.target);
				var $form = $target.parents("form");
				var params = $form.serialize();
				params += "&postId=" + $target.parents("section").attr("id").replace("postModal", "");
				
				console.log(params);
				
				$.ajax({
					url: $form.attr("action"),
					data: params,
					type: $form.attr("method").toUpperCase()
				}).done(function(reply) {
					console.log(reply);
					
					$postModal = $("#postModal" + reply.postId);
					
					var $replyTemplate = $("#postModalReplyTemplate").clone().removeClass("hide");
					$replyTemplate.attr("id", reply.idString).find("b").text(reply.writer.nickname).end()
						.children("img").attr("src", getProfileImageIfExists(reply.writer)).end()
						.children(":last").text(reply.content).attr("contenteditable", "true");
					
					$form.before($replyTemplate).find("textarea").val("").blur();
				});
			}
		}).on("keypress", ".reply-content", function(e) {
			if (e.keyCode == enter) {
				e.preventDefault();
				
				var $target = $(e.target);
				var params = "postId=" + $target.parents("section").attr("id").replace("postModal", "");
				params += "&content=" + $target.text();
				params += "&id=" + $target.parent().attr("id");
				
				console.log(params);
				
				$.ajax({
					url: "/reply/update",
					data: params,
					type: "POST"
				}).done(function(reply) {
					console.log(reply);
					
					$("#" + reply.idString).find(".reply-content").text(reply.content);
				});
			}
		}).on("click", ".reply-delete", function(e) {
			var replyId = $(e.target).parent().attr("id");
			
			console.log(replyId);
			
			var url = "/reply/delete/" + replyId;
			$.get(url, function(data) {
				$("#" + replyId).remove();
			});
		}).on("click", ".post-modify", function(e) {
			var $writePostForm = $("#writePostForm");
			var $section = $(e.target).parents(".modal-post");
			
			$writePostForm.find("#title").val($section.find(".post-title").text());
			$writePostForm.find("#content").val($section.find(".post-content").text());
			$writePostForm.append(getHiddenInput("id", $section.attr("id").replace("postModal", "")));
			$writePostForm.submit();
		}).on("click", ".post-delete", function(e) {
			e.preventDefault();
			
			var $section = $(e.target).parents(".modal-post");
			window.location = "post/" + $section.attr("id").replace("postModal", "") + "/delete";
		});
		
		function getHiddenInput(name, value) {
			return $("<input type='hidden'/>").attr({
				name: name,
				value: value
			});
		}
		
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
				$post.focus();
				return;
			}
			
			var url = "/post/" + postId;
			$.get(url, function(post) {
				console.log(post);
				
				var template = getPostModalTemplates(postId);
				writePostModal(post, template);
				appendWriter(postId, template);
				
				$("article[data-post-id=" + postId + "]").find(".post-view-count .number").text(post.viewCount);
				
				$("postModal" + postId).focus();
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
			template.title = template.header.children("h3");
			template.content = template.header.find("article");
			template.body = template.outer.children(".modal-body");
			template.reply = template.body.children(".modal-reply");
			template.footer = template.outer.children(".modal-footer");
			
			return template;
		}
		
		function isExists($target) {
			return $target.length != 0;
		}
		
		function writePostModal(post, template) {
			template.title.text(post.title).end()
				.children("img").attr("src", getProfileImageIfExists(post.writer));
			template.content.text(post.content);
			
			isNotWriter(post.writer.nickname, function() {
				template.footer.find("[type=submit]").remove();
			});
			
			isWriter(post.writer.nickname, function() {
				template.title.attr("contenteditable", "true");
				template.content.attr("contenteditable", "true");
			});
		}
		
		function isNotWriter(nickname, callback) {
			if (nickname != $("#mypage").attr("data-sessionuser-nickname"))
				callback.call();
		}
		
		function isWriter(nickname, callback) {
			if (nickname == $("#mypage").attr("data-sessionuser-nickname"))
				callback.call();
		}
		
		function appendWriter(postId, template) {
			var url = "/reply/" + postId;
			$.get(url, function(data) {
				
				console.log(data);
				
				$.each(data, function(index, reply) {
					var $replyTemplate = $("#postModalReplyTemplate").clone().removeClass("hide");
					$replyTemplate.attr("id", reply.idString).find("b").text(reply.writer.nickname).end()
						.children("img").attr("src", getProfileImageIfExists(reply.writer)).end()
						.children(":last").text(reply.content);
					
					isNotWriter(reply.writer.nickname, function() {
						$replyTemplate.children(".close").remove();
					});
					
					isWriter(reply.writer.nickname, function() {
						$replyTemplate.find(".reply-content").attr("contenteditable", "true");
					});
					
					var $form = template.reply.children("form");
					if ($form.size() > 0)
						$form.before($replyTemplate);
					else
						template.reply.append($replyTemplate);
				});
				template.outer.appendTo("#posts").modal("show");
			});
		}
		
		function getProfileImageIfExists(target) {
			return target.encodeProfileImage == "" ? "/img/50x50.gif" : "data:image/gif;base64," + target.encodeProfileImage;
		}
		
		$("#posts .row, .post").positioning();
		
	});
});

//require(['ember/load'], function(Ember) {
//	console.log($);
//	console.log(Ember);
//});

