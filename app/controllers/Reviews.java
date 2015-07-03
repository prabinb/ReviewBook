package controllers;

import model.form.PostReviewForm;
import model.form.UserForm;
import api.results.APIResult;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;

public class Reviews extends Controller {

	public UserService userService = new UserServiceImpl();

	public Result getAllReviews() {
		UserService userService = new UserServiceImpl();
		return ok(Json.toJson(userService.getAllReviews()));
	}

	public Result saveReview() {
		Form<PostReviewForm> form = Form.form(PostReviewForm.class)
				.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(form.errorsAsJson());
		} else {
			PostReviewForm reviewForm = form.get();
			if (userService.saveUserReview(reviewForm.getReview())) {
				return ok(Json.toJson(new APIResult("Saved  successfully.")));
			}
			else
			{
				return ok(Json.toJson(new APIResult("Could be some Problem while saving")));
			}
			
		}
	}
}