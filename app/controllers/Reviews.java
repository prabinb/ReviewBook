package controllers;

import model.form.UserReviewForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;
import api.results.APIResult;

public class Reviews extends Controller {

  public UserService userService = new UserServiceImpl();

  public Result getAllReviews() {
    UserService userService = new UserServiceImpl();
    return ok(Json.toJson(userService.getAllReviews()));
  }

  public Result postReview() {
    Form<UserReviewForm> form = Form.form(UserReviewForm.class).bindFromRequest();
    if (form.hasErrors()) {
      return badRequest(form.errorsAsJson());
    } else {
      UserReviewForm reviewForm = form.get();
      if (userService.saveUserReview(reviewForm.getReview())) {
        return ok(Json.toJson(new APIResult("Saved  successfully.")));
      } else {
        return ok(Json.toJson(new APIResult("Could be some Problem while saving")));
      }

    }
  }
}
