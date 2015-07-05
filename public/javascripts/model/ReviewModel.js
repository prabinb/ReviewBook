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



var SearchReviewModel = Backbone.Model.extend({
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : ''
		}
});

var SearchReviewModelList = Backbone.Collection.extend({
	url: '/searchReviews',
  model:  SearchReviewModel
});



var UserReviewModel = Backbone.Model.extend({
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : ''
		}
});

var UserReviewModelList = Backbone.Collection.extend({
	url: '/getAllUserReviews',
  model:  UserReviewModel
});

