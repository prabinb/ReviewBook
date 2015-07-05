package controllers;

import model.User;
import model.UserReviews;
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
    // FilePart filePart = request().body().asMultipartFormData().getFile("receipt");
    if (form.hasErrors()) {
      return badRequest(form.errorsAsJson());
    } else {
      UserReviewForm reviewForm = form.get();
      UserReviews review = reviewForm.getReview();
      /*
       * if (filePart != null) { File file = filePart.getFile();; byte[] imageData = new byte[(int)
       * file.length()]; try { FileInputStream fileInputStream = new FileInputStream(file);
       * fileInputStream.read(imageData); fileInputStream.close(); } catch (Exception e) {
       * e.printStackTrace(); } review.setImageName(filePart.getFilename());
       * review.setImageData(imageData); }
       */
      if (userService.saveUserReview(review)) {

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
  
  public Result getAllReviews(){
	  return ok(Json.toJson(userService.getAllReviews("")));
  }
  
  public Result searchReviews(String searchString){
	  return ok(Json.toJson(userService.getAllReviews(searchString)));
  }
}
