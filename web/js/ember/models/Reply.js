define(['ember/load'], function() {
	return Em.Object.extend({
		id: null,
		title: null,
		created: null,
		viewCount: null,
		content: null,
		writer: null,
		tags: null,
		replyIds: null
	});
});