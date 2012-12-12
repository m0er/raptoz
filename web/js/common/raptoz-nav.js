define(['jquery',
        'jqueryui/load',
        'plugin/jquery.timeago',
        'plugin/jquery.ez-bg-resize'], function ($) {
	
$(document).ready(function() {
	
	$("body").ezBgResize({
		img: getBackgroundImageUrl()
	});
	
	$("#bgChange").click(function(e) {
		e.preventDefault();
		$("#jq_ez_bg img").attr("src", "/img/paper.jpg");
	});
	
	// Bootstrap에서 Modal 폼이 backdrop 뒤에 있는 현상 수정
	// 참고: http://jsfiddle.net/ATeaH/8/
	$('.modal').appendTo($('body'));
	
	$("#search").submit(function() {
		var term = $(this).find("input").val();
		$(this).attr("action", $(this).attr("action") + "/" + term);
		
		return true;
	});
	
	if ($("#inbox").size() > 0) {
		$("#notification").position({
			of: "#inbox",
			at: "bottom",
			my: "top",
			collision: "none"
		}).removeClass("not visible");
		
		$("#inbox").click(function(e) {
			$("#notification").toggleClass("in");
			
			var $inbox = $(this);
			
			$.get($inbox.attr("data-read-url"), function(data) {
				$inbox.find(".badge").css("visibility", "hidden");
			});
		});
		
		$(".notification-timeago").timeago();
	}
});

function getBackgroundImageUrl() {
	var url = "";
	if (parseInt(Math.random() * 2) == 1) {
		url = "/img/living.social.street" + parseInt(Math.random() * 9 + 1);
	} else {
		url = "/img/twitter-cover" + parseInt(Math.random() * 9 + 1);
	}
	
	return PREFIX + url + ".jpg";
}

});