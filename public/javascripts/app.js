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
	
	
	var trendingProductsList = new TrendingProductsModelList();
	trendingProductsList.fetch({
		success : function(model, response, options){
			new TrendingProductsView().renderTrendingProducts(model.models);
		}
	});
	
	
	var trendingUsersList = new TrendingUserModelList();
	trendingUsersList.fetch({
		success : function(model, response, options){
			new TrendingUsersView().renderTrendingUsers(model.models);
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
	bindProductAutoSuggest();
	$('.review-by-div a').on("cick",function(e){
		alert();
	})
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

function bindProductAutoSuggest(){
	$( ".suggest_product" ).autocomplete({
	      source: function( request, response ) {
	    	  var searchSuggestionModel = new SearchSuggestionModelList();
	    	  searchSuggestionModel.url += '/'+request.term;
	    	  $(this).addClass('ui-autocomplete-loading');
	    	  var me = $(this);
	    	  searchSuggestionModel.fetch({
					success : function(model, data, options){
						response( data );
						me.removeClass('ui-autocomplete-loading');
					}
	    	  });
	      },
	      minLength: 3,
	      select: function( event, ui ) {
	    	searchReviews(ui.item.label);
	      },
	      open: function() {
	        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
	      },
	      close: function() {
	    	  $( this ).removeClass('ui-autocomplete-loading');
	        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
	      }
	    });
}