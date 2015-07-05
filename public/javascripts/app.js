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

function searchReviews(searchString) {
	resetNavigations();
	var searchreviewModelList = new SearchReviewModelList();
	searchreviewModelList.url += '/' + searchString;
	searchreviewModelList.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model.models);
		}
	});
}

function loadUserReviews() {
	resetNavigations();
	var userReviewModelList = new UserReviewModelList();
	userReviewModelList.url += '/' + $('meta[name=userName]').attr("content");
	userReviewModelList.fetch({
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
	bindSearchEvents();
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
				loadUserReviews();
				break;
			case 'myProfile' :
				$this.parents('.lef_nav').find('a[action!=myProfile]').removeClass('active');
				break;
		}
	});
}

function bindSearchEvents(){
	$('.search_bar input').on('change',function(e){
		var $this = $(this);
		if($this.val() && $this.val().length > 3){
			searchReviews($this.val());
		}
	});
	
	$('.search_bar input').keyup(function(e){
		var $this = $(this);
		if(e.keyCode == 13){
			if($this.val()){
				searchReviews($this.val());
			}
			else{
				loadTrendingReviews();
			}
			
		}
	});
}