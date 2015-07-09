package controllers;

import java.io.FileInputStream;

import model.User;
import model.UserReviews;
import model.form.UserReviewForm;

import org.apache.commons.io.FilenameUtils;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
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
      UserReviews review = reviewForm.getReview();
      
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart bill = body.getFile("bill");
	  if (bill != null) {
		byte[] bFile = new byte[(int) bill.getFile().length()];
		FileInputStream fileInputStream=null;
		try {
			fileInputStream = new FileInputStream(bill.getFile());
		    fileInputStream.read(bFile);
		    fileInputStream.close();
		}
		catch(Exception ex){
			
		}
		review.setImageData(bFile);
		review.setImageType(FilenameUtils.getExtension(bill.getFilename()));
	  } else {
		return ok(Json.toJson(new APIResult("Could be some Problem while saving")));
	  }
      if (userService.saveUserReview(review)) {

        return ok(Json.toJson(new APIResult("Saved  successfully.")));
      } else {
        return ok(Json.toJson(new APIResult("Could be some Problem while saving")));
      }
    }
  }

  public Result getAllUserReviews(String emailId,Integer startIndex) {
    if (emailId == null) {
      return badRequest("Invalid User");
    }
    User user = Ebean.find(User.class).where().eq("email_id", emailId).findUnique();
    if (user == null) {
      return badRequest("Invalid User");
    }
    return ok(Json.toJson(userService.getAllUserReviews(emailId,startIndex)));
  }

  public Result getAllProductReviews(String productName) {
    if (productName == null) {
      return badRequest("Invalid Product");
    }
    return ok(Json.toJson(userService.getAllProductReviews(productName)));
  }

  public Result getAllReviews(Integer startIndex) {
    return ok(Json.toJson(userService.getAllReviews("",startIndex)));
  }

  public Result searchReviews(String searchString,Integer startIndex) {
    return ok(Json.toJson(userService.getAllReviews(searchString,startIndex)));
  }


  public Result trendingUsers() {
    return ok(Json.toJson(userService.getTrendingUsers()));
  }



  public Result trendingProducts() {
    return ok(Json.toJson(userService.getTrendingProducts()));
  }
  
  public Result getProductSuggestions(String searchString){
	  return ok(Json.toJson(userService.getProductSuggestions(searchString)));
  }
  
  public Result listProductCategories(){
	  return ok(Json.toJson(userService.listProductCategories()));
  }
  
  public Result getReviewForCategory(Integer categoryId,Integer startIndex){
	  return ok(Json.toJson(userService.getReviewForCategory(categoryId,startIndex)));
  }
  
  public Result fetchReceipt(Integer reviewId){
	  return ok(Json.toJson(userService.fetchReceipt(reviewId)));
  }

}
