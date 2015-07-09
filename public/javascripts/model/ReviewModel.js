var ReviewModel = Backbone.Model.extend({
	urlRoot : '/allReviews',
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : '',
		  pageIndex : 0
		}
});




var SearchReviewModel = Backbone.Model.extend({
	urlRoot : '/searchReviews',
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : '',
		  pageIndex : 0
		}
});




var UserReviewModel = Backbone.Model.extend({
	urlRoot : '/getAllUserReviews',
	  defaults: {
		  emailId: '',
		  fullName : '',
		  productName : '',
		  reviewContent : '',
		  title : '',
		  pageIndex : 0
		}
});



var UsefulReviewModel = Backbone.Model.extend({
	urlRoot: '/postedReviewsInterest',
	  defaults: {
	  }
});

