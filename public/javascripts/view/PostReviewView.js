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
	 bindProductAutoSuggest();
 }

 
 function bindPostReviewEvents(){
	 
	 $('#uploadBill').fileupload({
	        dataType: 'json',
	        add: function (e, data) {
	        	$('.post-review-div .btn-default').on({
	       		 click : function(){
	       			data.submit();
	       		 }});
	        },
	        done: function (e, data) {
	        	$('.modal-title').html('Information');
				$('.modal-body p').html('Yipeeee!!!! You have submitted your review successfully');
				$('.modal').modal({
					keyboard: true,
					show : true
				});
                loadTrendingReviews();
	        },
	        fail : function(){
	        	$('.modal-title').html('Warning');
				$('.modal-body p').html('Oops !! Error occured during submitting your review :( ');
				$('.modal').modal({
					keyboard: true,
					show : true
				});
	        }
	    });
	 
	/* $('.post-review-div .btn-default').on({
		 click : function(){
			 var form=$('form[name=post-review-form]')
			 $.ajax({
		            type: form.attr('method'),
		            url: form.attr('action'),
		            data: form.serialize(),
		            success: function (data) {
		            	$('.modal-title').html('Information');
						$('.modal-body p').html('Yipeeee!!!! You have submitted your review successfully');
						$('.modal').modal({
							keyboard: true,
							show : true
						});
		                loadTrendingReviews();
		            },
		            error : function(){
		            	$('.modal-title').html('Warning');
						$('.modal-body p').html('Oops !! Error occured during submitting your review :( ');
						$('.modal').modal({
							keyboard: true,
							show : true
						});
		            }
			 			
		        });
		 }
	 })*/
 }
 
