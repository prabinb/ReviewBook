package services;

import java.util.List;

import model.User;
import model.UserReviews;
import model.form.PostReviewForm;

public interface UserService {
	
  boolean create(User user);
  
List<UserReviews> getUserReviews(String emailId);

List<UserReviews> getAllReviews();

boolean saveUserReview(PostReviewForm reviewForm);

}
