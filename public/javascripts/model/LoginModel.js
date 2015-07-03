var LoginModel = Backbone.Model.extend({
	urlRoot: '/saveUserInfo',
	  defaults: {
		  fullName: '',
		  emailId : ''
		 }
});