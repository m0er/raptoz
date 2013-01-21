define(['jquery',
        'plugin/jquerypp',
        'bootstrap/bootstrap',
        'external/underscore',
        'common/raptoz-nav',
        'common/raptoz-post',
        'template/handlebars',
        'common/raptoz-handlebars',
        'external/ember'], function ($) {
	
	$.fn.reverse = [].reverse;
	
	$.fn.isExists = function() {
		if (this.length > 0) return this;
		else return false;
	};
	
});

function getHandlebarsParsingResult(selector, context) {
	return $(Handlebars.compile($(selector).html()).call(null, context));
}