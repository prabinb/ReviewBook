$(function(){
	loadTrendingReviews();
	bindEvents();
	
});


function loadTrendingReviews() {
	var reviewModelList = new ReviewModelList();
	reviewModelList.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model.models);
		}
	});
}

function bindEvents(){
	bindNavigationEvents();
}

function bindNavigationEvents(){
	$('.lef_nav a').on("click", function(e) {
		var $this = $(this);
		$this.addClass('active');
		switch($this.attr('action')){
			case 'postReview' :
				$this.parents('.lef_nav').find('a[action!=postReview]').removeClass('active');
				showPostReviewSection();
				break;
			case 'myReviews' : 
				$this.parents('.lef_nav').find('a[action!=myReviews]').removeClass('active');
				break;
			case 'myProfile' :
				$this.parents('.lef_nav').find('a[action!=myProfile]').removeClass('active');
				break;
		}
	});
}