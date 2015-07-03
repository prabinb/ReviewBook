var PostReviewView = Backbone.View.extend({
		el: $('.trending_reviews'),
		post_reviews_template : _.template( $('#post-review-template').html() ),
		initialize: function(){
		},
		events: {
		},
		render : function(){
			var self = this;
			self.$el.empty();
			self.$el.append(self.post_reviews_template);
		}
});



 function showPostReviewSection(){
	 new PostReviewView().render();
 }