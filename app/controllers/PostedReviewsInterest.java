/**
 * 
 */
package controllers;

import api.results.APIResult;
import model.form.PostedReviewsInterestForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;

/**
 * @author Pambure
 *
 */
public class PostedReviewsInterest extends Controller{
	public UserService userService = new UserServiceImpl();
	
	public Result saveReviewInterest(){
		Form<PostedReviewsInterestForm> form =  Form.form(PostedReviewsInterestForm.class).bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(form.errorsAsJson());
		} else {
			PostedReviewsInterestForm formToSave = form.get();
			if(userService.savePostedReviewsInterest(formToSave)){
				return ok(Json.toJson(new APIResult("true")));
			}else{
				return ok(Json.toJson(new APIResult("false")));
			}
		}
	}
}
