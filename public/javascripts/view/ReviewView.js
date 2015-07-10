var ReviewView = Backbone.View.extend({
		el: $('.trending_reviews'),
		trending_reviews_template : _.template( $('#trending-reviews-template').html() ),
		initialize: function(){
		},
		renderReviews : function(model){
			var self = this;
			self.$el.attr('url',model.urlRoot);
			var split = model.urlRoot.split('/');
			var startIndex = parseInt(split[split.length -1 ]);
			if( startIndex == 0){
				self.$el.empty();
			}
			if(model.attributes && model.attributes.userReviewVOs && model.attributes.userReviewVOs.length>0){
				for(var i=0;i<model.attributes.userReviewVOs.length;i++){
					var vo = model.attributes.userReviewVOs[i];
					var values = {
							reviewBy : vo.fullName,
							reviewItem : vo.productName,
							reviewHeading : vo.reviewTitle,
							reviewBody : vo.reviewContent,
							recommendVisible : vo.recommend ? 'block' : 'none',
							notRecommendVisible : vo.recommend ? 'none' : 'block',
							reviewId: vo.reviewId,
							helpful : vo.helpfulCount,
							notHelpful : vo.notHelpfulCount,
							postedDate : $.datepicker.formatDate( "dd-MM-yy", new Date(vo.postedDate))
					}
					self.$el.append(self.trending_reviews_template(values));
				}
				
					if(Math.ceil(model.attributes.rowCount/20)-1 > startIndex){
						self.$el.scroll(function() {
							if ($(this).scrollTop() >= ($(this)[0].scrollHeight - $(this).outerHeight())) {
										nextPage();
							}
						});
					}
					else{
						self.$el.unbind('scroll');
					}
					
			}
			else{
				self.$el.append(' <br> <br> No Reviews Avaiable');
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