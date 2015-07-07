package services;

import java.util.List;

import model.User;
import model.UserReviews;
import model.VO.ProductTrendsVO;
import model.VO.UserReviewsVO;
import model.VO.UserTrendsVO;
import model.form.PostedReviewsInterestForm;

public interface UserService {

  boolean create(User user);

  List<UserReviewsVO> getAllUserReviews(String emailId);

  List<UserReviewsVO> getAllProductReviews(String productName);

  boolean saveUserReview(UserReviews reviews);

  Boolean savePostedReviewsInterest(PostedReviewsInterestForm postedReviewsInterestForm);

  List<ProductTrendsVO> getTrendingProducts();

  List<UserTrendsVO> getTrendingUsers();

  List<UserReviewsVO> getAllReviews(String search);

  List<String> getProductSuggestions(String searchString);

}
