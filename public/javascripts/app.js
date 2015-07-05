$(function(){
	loadTrendingReviews();
});


function loadTrendingReviews() {
	var reviewModelList = new ReviewModelList();
	reviewModelList.fetch({
		success : function(model, response, options){
			new ReviewView().renderReviews(model.models);
		}
	});
}