require.config({
    paths: {
        jqueryui: 'jquery-ui',
        plugin: 'jquery-plugin',
        ember: 'ember',
        bootstrap: 'bootstrap',
        external: 'external',
        models: 'ember/models',
        controllers: 'ember/controllers',
        views: 'ember/views',
        selector : 'selector'
    }
});

require(['bootstrap/load', 'plugin/tag-it', 'plugin/jquery.form', 'plugin/jquery.ez-bg-resize', 'selector/select2'], function() {
	$(document).ready(function() {
		$("body").ezBgResize({
			img: "/img/living.social.street1.jpg"
		});
		
		$("#bgChange").click(function(e) {
			e.preventDefault();
			$("#jq_ez_bg img").attr("src", "/img/paper.jpg");
		});
		
		$("#profileImage").change(function() {
			$("#imageForm").ajaxSubmit({
				success : function(data) {
					$("td.profile img").attr("src", "data:image/gif;base64," + data);	
				}
			});
		});
		
		var clazz = "class";
		var success = "control-group success";
		var error = "control-group error";
		
		$("#curPwd").keyup(function() {
			var id = $("#infos h1").attr("id");
			var pwd = $(this);
			
			$.ajax({
				url : id + "/verify", type : "post", data : { pwd : pwd.val() }	
			}).done(function(data) {
				if (data) $(pwd).parent().parent().attr(clazz, success);
				else $(pwd).parent().parent().attr(clazz, error);
			});
		});
		
		$("#confirmPwd").keyup(function() {
			var id = $("#infos h1").attr("id");
			var newPwd = $("#newPwd");
			var confirmPwd = $(this);
			
			$.ajax({
				url : id + "/verifyNP", type : "post", data : { newPwd : newPwd.val(), confirmPwd : confirmPwd.val() }
			}).done(function(data) {
				var n = $(newPwd).parent().parent();
				var c = $(confirmPwd).parent().parent();
				if (data) {
					n.attr(clazz, success);
					c.attr(clazz, success);
				} else {
					n.attr(clazz, error);
					c.attr(clazz, error);
				}
			});
		});
		
//		$(document).on("click", "a.close", function() {
//			var userId = $("#myInfos").find("h1").attr("id");
//			var tagId = $(this).parent().find(".tagit-label").attr("id");
//			$.ajax({
//				url : userId + "/tag/" + tagId + "/delete"
//			});
//			$(this).parent().remove();
//		});
		
		$("#tags").select2({tags:null});
	});
});

require(['ember/load'], function(Ember) {
	console.log($);
	console.log(Ember);
});

