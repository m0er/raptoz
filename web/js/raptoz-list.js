require.config({
    paths: {
    	jqueryui: 'jquery-ui',
        plugin: 'jquery-plugin',
        bootstrap: 'bootstrap',
        template: 'template',
        external: 'external',
        common: 'common'
    }
});

require(['bootstrap/bootstrap',
         'plugin/jquerypp',
         'plugin/jquery.form',
         'plugin/jquery-raptoz-positioning',
         'template/holder',
         'common/raptoz-common',
         'external/underscore'], function() {
	
	$(document).ready(function() {
		
		$(document).on("click", ".send-message-form [type=submit]", function(e) {
			e.preventDefault();
			
			var $form = $(this).parent();
			var url = $form.attr("action") + $form.attr("data-user-id");
			
			$.post(url, $form.serialize(), function(data) {
				$(".userinfo[data-user-id=" + $form.attr("data-user-id") + "]").find(".send-message").popover("hide");
			});
		});
		
		$("body").ezBgResize({
			img: getBackgroundImageUrl()
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
		
		$("#users .userinfo .send-message").each(function() {
			$(this).popover({
				title: $(this).parents("article").find(".username").text(),
				content: function() {
					var userId = $(this).parents(".userinfo").attr("data-user-id");
					$template = $("#sendMessageTemplate").clone();
					$template.children("form").attr("data-user-id", userId).addClass("send-message-form");
					return $template.html();
				},
				html: true
			});
		});
		
		$("#posts .row, .post").positioning({
			wrapper: "#resultContainer"
		});
		
	});
});
