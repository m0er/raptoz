define(['template/handlebars'], function () {
	Handlebars.registerHelper('ifArray', function(conditional, options) {
		if ($.isArray(conditional)) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}
	});	
});