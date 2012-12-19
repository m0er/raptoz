require.config({
    paths: {
        jqueryui: 'jquery-ui',
        plugin: 'jquery-plugin',
        bootstrap: 'bootstrap',
        selector : 'selector',
        common: 'common'
    }
});

require(['bootstrap/load',
         'plugin/jquery.form',
         'plugin/jquery-raptoz-positioning', 
         'plugin/select2',
         'template/handlebars',
         'common/raptoz-nav',
         'common/raptoz-post'], function() {
	
	$(document).ready(function() {
		
		$("body").ezBgResize({
			img: getBackgroundImageUrl()
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
			var id = $("#mypageInfo").attr("data-user-id");
			var pwd = $(this);
			
			$.ajax({
				url : id + "/verify", type : "post", data : { pwd : pwd.val() }	
			}).done(function(data) {
				if (data) $(pwd).parent().parent().attr(clazz, success);
				else $(pwd).parent().parent().attr(clazz, error);
			});
		});
		
		$("#confirmPwd").keyup(function() {
			var id = $("#mypageInfo").attr("data-user-id");
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
		
		$("#posts .row, .post").positioning({
			column: 4,
			wrapper: "#resultContainer"
		});
		
	});
	
	function getBackgroundImageUrl() {
		var url = "";
		if (parseInt(Math.random() * 2) == 1) {
			url = "img/living.social.street" + parseInt(Math.random() * 9 + 1);
		} else {
			url = "img/twitter-cover" + parseInt(Math.random() * 9 + 1);
		}
		
		return PREFIX + url + ".jpg";
	}
});
