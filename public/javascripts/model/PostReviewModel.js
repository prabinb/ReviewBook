var PostReviewModel = Backbone.Model.extend({
	urlRoot: '/postReview',
	  defaults: {
		  emailId: '',
		  title : '',
		  productName : '',
		  comments : '',
		  bill : ''
		}
});