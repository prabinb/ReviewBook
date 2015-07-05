var ReviewModel = Backbone.Model.extend({
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : ''
		}
});

var ReviewModelList = Backbone.Collection.extend({
	url: '/allReviews',
    model:  ReviewModel
});

