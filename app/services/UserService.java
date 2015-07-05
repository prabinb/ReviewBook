package services;

import java.util.List;

import model.User;
import model.UserReviews;
import model.form.PostedReviewsInterestForm;

public interface UserService {

  boolean create(User user);

  List<UserReviews> getAllUserReviews(String emailId);

  List<UserReviews> getAllProductReviews(String productName);

  boolean saveUserReview(UserReviews reviews);
  
  Boolean savePostedReviewsInterest(PostedReviewsInterestForm postedReviewsInterestForm);

}
