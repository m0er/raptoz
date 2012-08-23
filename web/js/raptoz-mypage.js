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
		
		$("#curPwd").keyup(function() {
			var id = $("#infos h1").attr("id");
			var pwd = $(this);
			
			$.ajax({
				url : id + "/verify", type : "post", data : { pwd : pwd.val() }	
			}).done(function(data) {
				if (data) {
					$(pwd).parent().parent().attr("class", "control-group success");
				} else {
					$(pwd).parent().parent().attr("class", "control-group error");
				}
			});
		});
		
		$("#confirmPwd").keyup(function() {
			var id = $("#infos h1").attr("id");
			var newPwd = $("#newPwd");
			var confirmPwd = $(this);
			
			$.ajax({
				url : id + "/verifyNewPassword", type : "post", data : { newPwd : newPwd.val(), confirmPwd : confirmPwd.val() }
			}).done(function(data) {
				if (data) {
					$(newPwd).parent().parent().attr("class", "control-group success");
					$(confirmPwd).parent().parent().attr("class", "control-group success");
				} else {
					$(newPwd).parent().parent().attr("class", "control-group error");
					$(confirmPwd).parent().parent().attr("class", "control-group error");
				}
			});
		});
		
		
//		$("#tags").change(function(e) {
//			e.preventDefault();
//			var value = $(this).val();
//			var url = $(this).parent().attr("action") + "?" + $(this).serialize();
//			console.log(value);
//			$.ajax({
//				url : url
//			});
//		});
		
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

