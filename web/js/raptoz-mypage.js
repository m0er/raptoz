require.config({
	baseUrl: PREFIX + 'js',
    paths: {
    	form: 'jquery-plugin/jquery.form',
    	jquerypp: 'jquery-plugin/jquerypp',
    	jqueryui: 'jquery-ui/jquery-ui',
    	positioning: 'jquery-plugin/jquery-raptoz-positioning',
    	underscore: 'external/underscore',
        bootstrap: 'bootstrap/bootstrap',
        holder: 'template/holder',
        handlebars: 'template/handlebars'
    }
});

require(['bootstrap',
         'form',
         'positioning', 
         'jquery-plugin/select2',
         'holder',
         'common/raptoz-nav',
         'common/raptoz-post',
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
