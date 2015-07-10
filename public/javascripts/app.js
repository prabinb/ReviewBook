$(function(){
	bindEvents();
	loadTrendingReviews();

	var categories = new ProductCategoriesModelList();
	categories.fetch({
		success : function(model, response, options){
			if(model.models && model.models.length>0){
				var el = $('.product_categories');
				for(var i=0;i<model.models.length;i++){
					var $a = $('<a></a>');
					$a.addClass('list-group-item');
					$a.attr('categoryid',model.models[i].attributes.id);
					$a.html(model.models[i].attributes.name);
					el.append($a);
				}
				
				el.find('a').click(function(){
					var $this = $(this);
					$this.addClass('selected');
					$this.siblings().removeClass('selected');
					$('body').mask('Loading..');
					var userReviewModel = new UserReviewModel();
					userReviewModel.urlRoot = '/getReviewForCategory/'+$this.attr('categoryid')+'/0';
					userReviewModel.fetch({
						success : function(model, response, options){
							new ReviewView().renderReviews(model);
							$('body').unmask();
						}
					});
				});
			}
		}
	})
	
});


function loadTrendingReviews() {
	$('.product_categories a').removeClass('selected')
	var reviewModel = new ReviewModel();
	$('body').mask('Loading..');
	reviewModel.urlRoot +='/0';
	reviewModel.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model);
			$('body').unmask();
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

function nextPage(){
	var url = $('.trending_reviews').attr('url');
	var split = url.split('/');
	split[split.length -1 ] = (parseInt(split[split.length -1 ]) + 1)*20;
	var reviewModel = new ReviewModel();
	$('body').mask('Loading..');
	reviewModel.urlRoot = split.join('/');
	reviewModel.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model);
			$('body').unmask();
		}
	});
}

function searchReviews(searchString) {
	$('body').mask('Loading..');
	var searchreviewModel = new SearchReviewModel();
	searchreviewModel.urlRoot += '/' + searchString +'/0';
	searchreviewModel.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model);
			$('body').unmask();
		}
	});
}

function loadUserReviews() {
	$('body').mask('Loading..');
	var userReviewModel = new UserReviewModel();
	userReviewModel.urlRoot += '/' + $('meta[name=userName]').attr("content")+'/0';
	userReviewModel.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model);
			$('body').unmask();
		}
	});
}

function bindEvents(){
	bindSearchEvents();
	bindProductAutoSuggest();
	$('.dropdown-toggle').dropdown()
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