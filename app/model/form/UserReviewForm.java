package model.form;

import model.ProductCategories;
import model.User;
import model.UserReviews;
import play.data.validation.Constraints.Required;

import com.avaje.ebean.Ebean;

public class UserReviewForm {
  @Required
  private String productName;
  @Required
  private String reviewContent;
  @Required
  private String reviewTitle;
  @Required
  private String emailId;
  @Required
  private String recommend;
  @Required
  private int productCategoryId;

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getReviewContent() {
    return reviewContent;
  }

  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }

  public String getReviewTitle() {
    return reviewTitle;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  public String getRecommend() {
    return recommend;
  }

  public void setRecommend(String recommend) {
    this.recommend = recommend;
  }

  public UserReviews getReview() {
    User user = Ebean.find(User.class).where().eq("email_id", emailId).findUnique();
    UserReviews review = new UserReviews();
    review.setUser(user);
    review.setProductName(productName);
    review.setReviewContent(reviewContent);
    review.setReviewTitle(reviewTitle);
    review.setRecommend((recommend.toUpperCase().equals("TRUE")) ? true : false);
    ProductCategories productCategory = new ProductCategories();
    productCategory.setId(this.productCategoryId);
    review.setProductCategory(productCategory);
    return review;
  }

public int getProductCategoryId() {
	return productCategoryId;
}

public void setProductCategoryId(int productCategoryId) {
	this.productCategoryId = productCategoryId;
}

}
