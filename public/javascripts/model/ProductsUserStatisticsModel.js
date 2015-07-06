var TrendingProductsModel = Backbone.Model.extend({
	  defaults: {
		}
});

var TrendingProductsModelList = Backbone.Collection.extend({
	url: '/trendingProducts',
    model:  TrendingProductsModel
});


var TrendingUserModel = Backbone.Model.extend({
	  defaults: {
	  }
});

var TrendingUserModelList = Backbone.Collection.extend({
	url: '/trendingUsers',
  model:  TrendingUserModel
});