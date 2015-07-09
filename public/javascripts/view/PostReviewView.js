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
	 var actualData;
	 $('#uploadBill').fileupload({
	        dataType: 'json',
	        add: function (e, data) {
	        	if(!data.files[0].type || data.files[0].type.indexOf('image') == -1){
	        		$('.modal-title').html('Warning');
					$('.modal-body p').html('Sorry !!! You can upload only image files of type png,jpeg,jpg etc... ');
					$('.modal').modal({
						keyboard: true,
						show : true
					});
					return;
	        	}
	        	$('.uploadFileName').html(data.files[0].name);
	        	actualData = data;
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
	 
	 
	 	$('.post-review-div .btn-default').on({
	   		 click : function(){
	   			 var proceed = true;
	   			 $(this).parents('form').find('[required=true]').each(function(){
	   				 if(!$(this).val()){
	   					$('.modal-title').html('Warning!!');
						$('.modal-body p').html('All the fields are mandatory.');
						$('.modal').modal({
							keyboard: true,
							show : true
						});
						proceed = false;
						return;
	   				 }
	   			 })
	   			 
	   			 if(!proceed){
	   				 return;
	   			 }
	   			 
	   			 if(actualData){
	   				actualData.submit();
	   			 }
	   			 else{
	   				$('.modal-title').html('Warning!!');
					$('.modal-body p').html('You need to upload your receipt or bill to post a review.');
					$('.modal').modal({
						keyboard: true,
						show : true
					});
	   			 }
	   			
   		 }});
	 
 }
 
