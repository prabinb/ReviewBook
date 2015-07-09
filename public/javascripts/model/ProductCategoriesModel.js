var ProductCategoriesModel = Backbone.Model.extend({
});

var ProductCategoriesModelList =Backbone.Collection.extend({
	url: '/listProductCategories',
    model:  ProductCategoriesModel
});

