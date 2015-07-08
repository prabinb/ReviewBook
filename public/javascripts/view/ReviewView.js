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
							helpful : models[i].attributes.helpfulCount,
							notHelpful : models[i].attributes.notHelpfulCount
					}
					self.$el.append(self.trending_reviews_template(values));
				}
			}
			self.bindEvents();
		},
		bindEvents : function() {
				$('.helpful_content_div a').click(function(){
					var $this = $(this);
					var reviewByDiv = $this.parents('.review-by-div');
					var useFulModel = new UsefulReviewModel();
					useFulModel.save({
						reviewId : reviewByDiv.attr('reviewid'),
						emailId : $('meta[name=userName]').attr("content"),
						helpful : $this.attr('helpful') == 'true' ? true : false
					},{
						success : function(model, response, options){
							if(response && response.message == 'true'){
								if($this.attr('helpful') == 'true'){
									$this.html('Helpful('+(parseInt($this.attr('count'))+1)+')');
								}
								else{
									$this.html('Not Helpful('+(parseInt($this.attr('count'))+1)+')');
								}
							}
							else{
								alert('You Have already marked for this post');
							}
						}
					})
				})
		}
});