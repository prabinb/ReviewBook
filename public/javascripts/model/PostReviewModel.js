var PostReviewModel = Backbone.Model.extend({
	urlRoot: '/postReview',
	  defaults: {
		  emailId: '',
		  reviewTitle : '',
		  productName : '',
		  reviewContent : '',
		  recommend : true
		}
});