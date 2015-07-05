$(function(){
	loadTrendingReviews();
	bindEvents();
	
});


function loadTrendingReviews() {
	resetNavigations();
	var reviewModelList = new ReviewModelList();
	reviewModelList.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model.models);
		}
	});
}

function resetNavigations(){
	$('.lef_nav').find('a').removeClass('active');
}

function bindEvents(){
	bindNavigationEvents();
}

function bindNavigationEvents(){
	$('.lef_nav a').on("click", function(e) {
		if(!$('meta[name=userName]').attr("content")){
			alert('Please Log-In to continue');
			return;
		}
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