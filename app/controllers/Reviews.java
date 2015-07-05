package controllers;

import model.User;
import model.form.UserReviewForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UserService;
import services.impl.UserServiceImpl;
import api.results.APIResult;

import com.avaje.ebean.Ebean;

public class Reviews extends Controller {

  public UserService userService = new UserServiceImpl();

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

  public Result getAllUserReviews(String emailId) {
    if (emailId == null) {
      return badRequest("Invalid User");
    }
    User user = Ebean.find(User.class).where().eq("email_id", emailId).findUnique();
    if (user == null) {
      return badRequest("Invalid User");
    }
    return ok(Json.toJson(userService.getAllUserReviews(emailId)));
  }

  public Result getAllProductReviews(String productName) {
    if (productName == null) {
      return badRequest("Invalid Product");
    }
    return ok(Json.toJson(userService.getAllProductReviews(productName)));
  }
}
