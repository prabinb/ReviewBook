package model.VO;

import java.util.List;

import model.PostedReviewsInterest;
import model.UserReviews;


public class UserReviewsVO {
  int reviewId;
  String fullName;
  String emailId;
  String productName;
  String reviewContent;
  String reviewTitle;
  boolean recommend;
  int helpful;
  int notHelpful;

  public UserReviewsVO(UserReviews userReviews) {
    this.reviewId = userReviews.getReviewId();
    this.fullName = userReviews.getUser().getFullname();
    this.emailId = userReviews.getUser().getEmailId();
    this.productName = userReviews.getProductName();
    this.reviewContent = userReviews.getReviewContent();
    this.reviewTitle = userReviews.getReviewTitle();
    this.recommend = userReviews.isRecommend();
    List<PostedReviewsInterest> interests = userReviews.getPostedReviewsInterest();
    for (PostedReviewsInterest interest : interests) {
      if (interest.getHelpful() == Boolean.TRUE) {
        this.helpful++;
      } else {
        this.notHelpful++;
      }
    }

  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

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

  public boolean isRecommend() {
    return recommend;
  }

  public void setRecommend(boolean recommend) {
    this.recommend = recommend;
  }

  public int getHelpful() {
    return helpful;
  }

  public void setHelpful(int helpful) {
    this.helpful = helpful;
  }

  public int getNotHelpful() {
    return notHelpful;
  }

  public void setNotHelpful(int notHelpful) {
    this.notHelpful = notHelpful;
  }


}
