package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;

@Entity
@Table(name = "user_reviews", uniqueConstraints = {@UniqueConstraint(columnNames = {"email_id",
    "product_name"})})
public class UserReviews extends Model {

  @Id
  @GeneratedValue
  @Column(name = "review_id")
  int reviewId;
  User user;
  String productName;
  String reviewContent;
  String reviewTitle;
  boolean recommend;
  List<PostedReviewsInterest> postedReviewsInterest;

  // String imageName;
  // byte[] imageData;

  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "email_id", referencedColumnName = "email_id", table = "user")
  public User getUser() {
    return user;
  }

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "review_id", referencedColumnName = "review_id",
      table = "posted_reviews_interest")
  public List<PostedReviewsInterest> getPostedReviewsInterest() {
    return postedReviewsInterest;
  }

  public void setPostedReviewsInterest(List<PostedReviewsInterest> postedReviewsInterest) {
    this.postedReviewsInterest = postedReviewsInterest;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "product_name")
  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  @Column(name = "review_content", columnDefinition = "TEXT")
  public String getReviewContent() {
    return reviewContent;
  }

  public void setReviewContent(String reviewContent) {
    this.reviewContent = reviewContent;
  }

  @Column(name = "review_title")
  public String getReviewTitle() {
    return reviewTitle;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  @Column(name = "is_recommended")
  public boolean isRecommend() {
    return recommend;
  }

  public void setRecommend(boolean recommend) {
    this.recommend = recommend;
  }

  public int getReviewId() {
    return reviewId;
  }

  public void setReviewId(int reviewId) {
    this.reviewId = reviewId;
  }

  /*
   * @Column(name = "image_name", unique = false, nullable = true, length = 100) public String
   * getImageName() { return imageName; }
   * 
   * public void setImageName(String imageName) { this.imageName = imageName; }
   * 
   * @Column(name = "image_data", unique = false, nullable = false, length = 55535) public byte[]
   * getImageData() { return imageData; }
   * 
   * public void setImageData(byte[] imageData) { this.imageData = imageData; }
   */
}
