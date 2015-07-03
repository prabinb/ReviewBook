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
							reviewBy : models[i].attributes.user.fullname,
							reviewTime : '07-03-2015',
							reviewItem : models[i].attributes.productName,
							reviewHeading : models[i].attributes.reviewTitle,
							reviewBody : models[i].attributes.reviewContent
					}
					self.$el.append(self.trending_reviews_template(values));
				}
			}
		},
		events: {
		}
});