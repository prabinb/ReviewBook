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
							notHelpful : models[i].attributes.notHelpfulCount,
							postedDate : $.datepicker.formatDate( "dd-MM-yy", new Date(models[i].attributes.postedDate))
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
								$('.modal-title').html('Warning');
								$('.modal-body').html('Oops!!!! Seems like you have already marked for this post');
								$('.modal').modal({
									keyboard: true,
									show : true
								});
							}
						}
					})
				});
				
				$('.viewReceipt_div a').click(function(){
					var model = new ReviewModel();
					model.url = '/fetchReceipt/'+$(this).parents('.review-by-div').attr('reviewid');
					model.fetch({
						success : function(model, response, options){
							$('.modal-title').html('Receipt');
							$('.modal-body').html('<img src="data:image/'+model.attributes.imageType+';base64,'+model.attributes.imageData+'"></img>');
							$('.modal').modal({
								keyboard: true,
								show : true
							});
							
						}
					})
					
				})
		}
});