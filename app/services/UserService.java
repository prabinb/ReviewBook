package services;

import java.util.List;

import model.ProductCategories;
import model.User;
import model.UserReviews;
import model.VO.ImageVO;
import model.VO.ProductTrendsVO;
import model.VO.UserReviewsWrapperVO;
import model.VO.UserTrendsVO;
import model.form.PostedReviewsInterestForm;

public interface UserService {

  boolean create(User user);

  UserReviewsWrapperVO getAllUserReviews(String emailId, int startIndex);

  UserReviewsWrapperVO getAllProductReviews(String productName);

  boolean saveUserReview(UserReviews reviews);

  Boolean savePostedReviewsInterest(PostedReviewsInterestForm postedReviewsInterestForm);

  List<ProductTrendsVO> getTrendingProducts();

  List<UserTrendsVO> getTrendingUsers();

  UserReviewsWrapperVO getAllReviews(String search, int startIndex);

  List<String> getProductSuggestions(String searchString);
  
  public List<ProductCategories> listProductCategories();
  
  public UserReviewsWrapperVO getReviewForCategory(int categoryId, int startIndex);

  ImageVO fetchReceipt(int reviewId);

}
