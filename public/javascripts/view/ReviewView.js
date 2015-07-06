var ReviewView = Backbone.View.extend({
		el: $('.trending_reviews'),
		trending_reviews_template : _.template( $('#trending-reviews-template').html() ),
		initialize: function(){
		},
		renderReviews : function(models){
			var self = this;
			self.$el.empty();
			if(models && models.length>0){
				for(var i=0;i<models.length;i++){
					var values = {
							reviewBy : models[i].attributes.fullName,
							reviewItem : models[i].attributes.productName,
							reviewHeading : models[i].attributes.reviewTitle,
							reviewBody : models[i].attributes.reviewContent,
							recommendVisible : models[i].attributes.recommend ? 'block' : 'none',
							notRecommendVisible : models[i].attributes.recommend ? 'none' : 'block',
							reviewId: models[i].attributes.reviewId,
							helpful : models[i].attributes.helpful,
							notHelpful : models[i].attributes.notHelpful
					}
					self.$el.append(self.trending_reviews_template(values));
				}
			}
		},
		events: {
		}
});