var SearchSuggestionModel = Backbone.Model.extend({
	  defaults: {
		}
});

var SearchSuggestionModelList = Backbone.Collection.extend({
	url: '/getProductSuggestions',
    model:  SearchSuggestionModel
});
