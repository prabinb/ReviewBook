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
			self.$el.append(self.post_reviews_template({emailId:$('meta[name=userName]').attr("content")}));
		}
});



 function showPostReviewSection(){
	 new PostReviewView().render();
	 bindPostReviewEvents();
 }

 
 function bindPostReviewEvents(){
	 $('.post-review-div .btn-default').on({
		 click : function(){
			 var form=$('form[name=post-review-form]')
			 $.ajax({
		            type: form.attr('method'),
		            url: form.attr('action'),
		            data: form.serialize(),
		            success: function (data) {
		                alert('Submitted Your Review Successfully. Thank You');
		                loadTrendingReviews();
		            },
		            error : function(){
		            	alert('Failed to submit form')
		            }
			 			
		        });
		 }
	 })
 }
 
