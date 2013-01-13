require.config({
    paths: {
        jqueryui: 'jquery-ui',
        plugin: 'jquery-plugin',
        bootstrap: 'bootstrap',
        selector : 'selector',
        common: 'common'
    }
});

require(['bootstrap/bootstrap',
         'plugin/jquery.form',
         'plugin/jquery-raptoz-positioning', 
         'plugin/select2',
         'template/holder',
         'common/raptoz-common'], function() {
	
	$(document).ready(function() {
		
		$("body").ezBgResize({
			img: getBackgroundImageUrl()
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
