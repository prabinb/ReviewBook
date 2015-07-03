package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.avaje.ebean.Model;

@Entity
@Table(name = "user_reviews", uniqueConstraints = {@UniqueConstraint(columnNames = {"email_id",
    "product_name"})})
public class UserReviews extends Model {

  User user;
  String productName;
  String reviewContent;
  String reviewTitle;
  
  @ManyToOne(cascade = CascadeType.REMOVE)
  @JoinColumn(name = "email_id", referencedColumnName = "email_id", table = "user")
  public User getUser() {
    return user;
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

  @Column(name = "review_content")
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

}
