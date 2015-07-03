/**
 * 
 */

var LoginView = Backbone.View.extend({
		el: $('header div'),
		login_template : _.template( $('#loginHeader-template').html() ),
		initialize: function(){
			this.$el.empty().append(this.login_template)
		},
		events: {
		 }
});

var LogoutView = Backbone.View.extend({
	el: $('header div'),
	logout_template : _.template( $('#logoutHeader-template').html() ),
	initialize: function(){
		this.$el.append(this.logout_template(this.model.attributes));
	},
	updateView : function(){
		this.logout_template(this.model.attributes)
	},
	events: {
		'click button' : 'logout'
	 },
	 logout : function(){
		var me = this;
		var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function() {
			me.hideView();
		});
		 
	 }
})

function onSignIn(googleUser) {
  var profile = googleUser.getBasicProfile();
  var logout = new Logout({name:profile.getName(),emailId:profile.getEmail()});
  new LogoutView({model:logout}).updateView();
}
