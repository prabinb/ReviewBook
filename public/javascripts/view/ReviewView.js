var ReviewView = Backbone.View.extend({
		el: $('.trending_reviews'),
		trending_reviews_template : _.template( $('#trending-reviews-template').html() ),
		initialize: function(){
		},
		events: {
		},
		renderReviews : function(reviews){
			if(reviews && reviews.length){
				for(var i=0;i<reviews.length;i++){
					
				}
			}
		}
});