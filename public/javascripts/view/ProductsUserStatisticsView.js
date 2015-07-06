var TrendingProductsView = Backbone.View.extend({
		el: $('.trending_products'),
		trending_products_template : _.template( $('#trending_products_template').html() ),
		initialize: function(){
		},
		renderTrendingProducts : function(models){
			var self = this;
			self.$el.find('.table-responsive').remove();
			self.$el.append(self.trending_products_template);
			var tableBody = self.$el.find('.table-responsive').find('tbody');
			if(models && models.length>0){
				for(var i=0;i<models.length;i++){
					var tr = $('<tr></tr>');
					tr.append('<td>'+(i+1)+'</td');
					tr.append('<td>'+models[i].attributes.productName+'</td');
					tr.append('<td>'+models[i].attributes.is_recommended+'</td');
					tr.append('<td>'+(models[i].attributes.total_count - models[i].attributes.is_recommended)+'</td');
					tableBody.append(tr);
				}
			}
		},
		events: {
		}
});


var TrendingUsersView = Backbone.View.extend({
	el: $('.top_users'),
	trending_users_template : _.template( $('#trending_users_template').html() ),
	initialize: function(){
	},
	renderTrendingUsers : function(models){
		var self = this;
		self.$el.find('.table-responsive').remove();
		self.$el.append(self.trending_users_template);
		var tableBody = self.$el.find('.table-responsive').find('tbody');
		if(models && models.length>0){
			for(var i=0;i<models.length;i++){
				var tr = $('<tr></tr>');
				tr.append('<td>'+(i+1)+'</td');
				tr.append('<td>'+models[i].attributes.full_name+'</td');
				tr.append('<td>'+models[i].attributes.reviews_count+'</td');
				tableBody.append(tr);
			}
		}
	},
	events: {
	}
});